package com.planb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.planb.controller.api.ElectronicsControllerApi;
import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.tablet.Tablet;
import com.planb.dao.user.UserDetail;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.metadata.SequenceService;
import com.planb.product.util.EUtil;

@Service
public class ReviewService {

	@Autowired
	ProductService productService;

	@Autowired
	EUtil eUtil;

	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	@Autowired
	SequenceService sequenceService;

	@Async
	public void updateReview(ReviewDetails reviewDetails) {

		String reviewId = reviewDetails.getReviewId();
		if (reviewId != null && !"".equals(reviewDetails))
			productService.updateProduct(reviewId, reviewDetails, "review");

	}

	@Async
	public void submitReview(ReviewDetails reviewDetails) {

		// one product have multiple review, need to create unique review id
		// 1.created reviewID and submitted to couchbase db
		String userEmail = reviewDetails.getByUserId();
		String productId = reviewDetails.getForProductId();
		if (!StringUtils.isEmpty(userEmail) && !StringUtils.isEmpty(productId)) {
			String seqCount=sequenceService.getNextSequenceCount(ProductEnum.REVIEW.toString());
			String reviewId = "RE_" + seqCount + "_" + productId;
			reviewDetails.setReviewId(reviewId);
			reviewDetails.setCreatedOn(eUtil.getCurrentDateAndTime());
			productService.addProduct(reviewDetails, "review", reviewId);

			// 2,3.review id,rating for product will be added in userdata
			Object obj = productService.getProduct(userEmail, "user");
			if (obj != null) {
				UserDetail userDetail = (UserDetail) obj;
				List<String> reviewIds = userDetail.getReviewIds();
				if (reviewIds == null) {
					reviewIds = new ArrayList<String>();
					reviewIds.add(reviewId);
				} else {
					reviewIds.add(reviewId);
				}
				userDetail.setReviewIds(reviewIds);
				
				// ****************************************************

				// productIdsAndRating map stores only latest rating for a
				// product
				Map<String, Double> productIdsAndRating = userDetail.getProductIdsAndRating();
				if (productIdsAndRating == null) {
					productIdsAndRating = new HashMap<String, Double>();
					productIdsAndRating.put(productId, reviewDetails.getRating());
					userDetail.setProductIdsAndRating(productIdsAndRating);
				} else {
					productIdsAndRating.put(productId, reviewDetails.getRating());
				}
				productService.updateProduct(userEmail, userDetail, "user");
			}

			// 4.review id will be added in productData
			ElectInMemoryService ElectInMemoryService = electInMemoryFactory
					.getElectInMemoryService(reviewDetails.getCategory());
			ElectInMemoryService.addReviewForProduct(reviewId, productId);

		}

	}

	public void reviewPopulator(ModelMap map, String userEmail) {
		String isReviewAvailable = "true";
		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, ProductEnum.USER.getValue());
			List<String> reviewIds = userDetail.getReviewIds();
			if (!CollectionUtils.isEmpty(reviewIds)) {
				List<Object> objReviewList = productService.multiGet(reviewIds,ProductEnum.REVIEW.getValue());
				List<ReviewDetails> reviewList = objReviewList.stream().map(obj -> (ReviewDetails) obj)
						.collect(Collectors.toList());
				for (ReviewDetails reviewDetails : reviewList) {
					if (reviewDetails.getProductThumbNailImg() == null || reviewDetails.getAvgRatingForProduct() == 0 
							|| reviewDetails.getProductName()==null) {
						try {
							eUtil.populateProductMetaData(reviewDetails);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					

				}

				isReviewAvailable = "true";
				map.put("reviewList", reviewList);
			} else {
				isReviewAvailable = "false";
			}

		}
		map.put("isReviewAvailable", isReviewAvailable);

	}

	public void updateRating(String reviewId, String producId, double updatedRating, String userEmail) {
		ReviewDetails reviewDetails=(ReviewDetails)productService.getProduct(reviewId, ProductEnum.REVIEW.getValue());
		reviewDetails.setRating(updatedRating);
		productService.updateProduct(reviewId, reviewDetails, ProductEnum.REVIEW.getValue());
		
	}

}