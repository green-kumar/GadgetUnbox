package com.planb.inmemoery.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import com.planb.care.customImplementation.OS;
import com.planb.care.dao.CareSearchResultDao;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.user.UserDetail;
import com.planb.dao.user.UserFavProduct;
import com.planb.metadata.CbdToRdbToInMem;
import com.planb.metadata.ProductMetaData;
import com.planb.metadata.ProductMetaDataRDBService;
import com.planb.metadata.ReviewMetaData;
import com.planb.metadata.ReviewMetaDataRDBService;
import com.planb.metadata.SequenceService;
import com.planb.product.util.EUtil;
import com.planb.product.util.ElectConstants;
import com.planb.service.ProductService;
import com.planb.service.RankingService;

public class MobileInMemoryServiceImpl implements ElectInMemoryService {

	@Autowired
	CbdToRdbToInMem cbdToRdbToInMem;

	@Autowired
	ProductService productService;


	@Autowired
	SequenceService sequenceService;

	@Autowired
	EUtil eUtil;

	@Autowired
	ProductMetaDataRDBService productMetaDataRDBService;

	@Autowired
	ReviewMetaDataRDBService reviewMetaDataRDBService;
	
	@Autowired
	RankingService rankingService;
	/**
	 * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
	 *  (If fromIndex and toIndex are equal, the returned list is empty.) 
	 */
	@Override
	public List<ProductMetaData> fetchLatestProductList() {
		List<ProductMetaData> latestSmartPhone = cbdToRdbToInMem.getlatestSmartphoneList();
		if (!org.springframework.util.CollectionUtils.isEmpty(latestSmartPhone)) {
			
			if (latestSmartPhone.size() > 16) {
				return new ArrayList<ProductMetaData>(latestSmartPhone.subList(0, 16));
			} else {
				return latestSmartPhone;
			}
		}
		return new ArrayList<ProductMetaData>();
	}

	@Override
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom) {

		List<ProductMetaData> latestSmartPhoneList = cbdToRdbToInMem.getlatestSmartphoneList();
		if (!org.springframework.util.CollectionUtils.isEmpty(latestSmartPhoneList)) {
			int maxListSize = cbdToRdbToInMem.getlatestSmartphoneList().size();
			int fromIndex = startFrom * 16;
			int toIndex = fromIndex + 16;
			if (fromIndex < maxListSize && toIndex <= maxListSize) {
				return new ArrayList<ProductMetaData>(latestSmartPhoneList.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				return (new ArrayList<ProductMetaData>(latestSmartPhoneList.subList(fromIndex, maxListSize)));
			}
		}

		return new ArrayList<ProductMetaData>();
	}

	@Override
	public void addReviewForProduct(String reviewId, String productId) {
		Object prodObj = productService.getProduct(productId, ProductEnum.MOBILE.getValue());
		Mobile mobile = (Mobile) prodObj;
		List<String> userRevieweIds = mobile.getUserReviewIds();
		if (userRevieweIds == null) {
			userRevieweIds = new ArrayList<String>();
			userRevieweIds.add(reviewId);
			mobile.setUserReviewIds(userRevieweIds);
		} else {
			userRevieweIds.add(reviewId);
		}
		mobile.setUserReviewCount(mobile.getUserReviewCount() + 1);
		productService.updateProduct(productId, mobile, ProductEnum.MOBILE.getValue());

	}

	@Override
	public void populateMetaDataService(ReviewDetails reviewDetails) {
		List<ProductMetaData> latestSmartPhone = cbdToRdbToInMem.getlatestSmartphoneList();
		Map<String, ProductMetaData> latestSmartPhoneMap = latestSmartPhone.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		String productId = reviewDetails.getForProductId();

		if (latestSmartPhoneMap.containsKey(productId)) {
			ProductMetaData productMetaData = latestSmartPhoneMap.get(productId);
			reviewDetails.setAvgRatingForProduct(productMetaData.getAvgRating());
			reviewDetails.setProductName(productMetaData.getProductName());
			String mainImageUrl = productMetaData.getMainImageUrl();
			String thumbnailImageUrl = ElectConstants.CLOUDINARY_THUMB_NAIL_BASE_URL
					+ mainImageUrl.substring(mainImageUrl.lastIndexOf("/") + 1);
			reviewDetails.setProductThumbNailImg(thumbnailImageUrl);

		}
	}

	@Override
	public UserFavProduct populateUserFavProductMetaDta(String productId, String currentDate) {
		List<ProductMetaData> latestSmartPhone = cbdToRdbToInMem.getlatestSmartphoneList();
		Map<String, ProductMetaData> latestSmartPhoneMap = latestSmartPhone.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		if (latestSmartPhoneMap.containsKey(productId)) {
			ProductMetaData pmd = latestSmartPhoneMap.get(productId);
			String mainImageUrl = pmd.getMainImageUrl();
			String thumbnailImageUrl = ElectConstants.CLOUDINARY_THUMB_NAIL_BASE_URL
					+ mainImageUrl.substring(mainImageUrl.lastIndexOf("/") + 1);
			UserFavProduct userFavProduct = new UserFavProduct(pmd.getProductName(), ProductEnum.MOBILE.getValue(),
					productId, thumbnailImageUrl, pmd.getAvgRating(), currentDate);
			return userFavProduct;
		}

		return null;
	}

	@Override
	public void addUserInProductFavList(String productId, String userId) {
		Mobile mobile = (Mobile) productService.getProduct(productId, ProductEnum.MOBILE.getValue());
		List<String> favedByUsers = mobile.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.add(userId);

		productService.updateProduct(productId, mobile, ProductEnum.MOBILE.getValue());
	}

	@Override
	public void removeUserFromProductFavList(String productId, String userId) {
		Mobile mobile = (Mobile) productService.getProduct(productId, ProductEnum.MOBILE.getValue());
		List<String> favedByUsers = mobile.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.remove(productId);
		productService.updateProduct(productId, mobile, ProductEnum.MOBILE.getValue());

	}

	@Override
	public Class getClassName() {
		return Mobile.class;
	}

	@Override
	public void populatedLoggedUserMetaData(String userEmail, ModelMap model) {
		try {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			List<UserFavProduct> favList = userDetail.getFavProducts();
			if (!CollectionUtils.isEmpty(favList)) {
				List<String> favProductList = favList.stream()
						.filter(userFavProduct -> userFavProduct.getCategory().equals(ProductEnum.MOBILE.getValue()))
						.map(obj -> obj.getProductId()).collect(Collectors.toList());
				model.put("favProductList", favProductList);
			}

			List<String> reviewIds = userDetail.getReviewIds();
			if (!CollectionUtils.isEmpty(reviewIds)) {
				List<Object> objReviewList = productService.multiGet(reviewIds, ProductEnum.REVIEW.getValue());
				List<ReviewDetails> reviewList = objReviewList.stream().map(obj -> (ReviewDetails) obj)
						.collect(Collectors.toList());
				try {
					/*
					 * Collectors.toMap(keyMapper, valueMapper, mergeFunction):
					 * 
					 */

					Map<String, Double> userProductRatingMap = reviewList.stream().collect(
							Collectors.toMap(ReviewDetails::getForProductId, ReviewDetails::getRating, (pId1, pId2) -> {

								return pId2;

							}));
					model.put("userProductRatingMap", userProductRatingMap);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ProductMetaData> getTopRatedProduct() {
		List<ProductMetaData> sortedSmartPhoneList = cbdToRdbToInMem.getlatestSmartphoneList();
		//sort the productmetadata on the basis of (score)rank in category in descending order
		   Collections.sort(sortedSmartPhoneList,new Comparator<ProductMetaData>(){
			@Override
			public int compare(ProductMetaData o1, ProductMetaData o2) {
				/*
				 *  Since rank in category not populated in mssql,As of now sort on the basis of avgRating......
				 *  TODO: SORT on the basis of (Score)rank in category
				 */
				//return o1.getRankInCategory() > o2.getRankInCategory()?1:(o1.getRankInCategory() == o2.getRankInCategory()? 0:-1); 
				return o1.getAvgRating() > o2.getAvgRating()?-1:(o1.getAvgRating() == o2.getAvgRating()? 0:1); 
			}
		   });
        /**
         * IMDB list 250 product(in top rated product)
         */
		if (sortedSmartPhoneList.size() > 200) {
			sortedSmartPhoneList = new ArrayList<ProductMetaData>(sortedSmartPhoneList.subList(0, 199));
		}
		return sortedSmartPhoneList;
	}

	@Override
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String, List<String>> filterCriteria) {
		
		
		
		
		
		List<ProductMetaData> latestSmartPhoneList = cbdToRdbToInMem.getlatestSmartphoneList();
		
		if(filterCriteria.containsKey(ElectConstants.BRAND)){
			List<String> brands = filterCriteria.get(ElectConstants.BRAND);
			latestSmartPhoneList = latestSmartPhoneList.stream().filter(pmd -> brands.contains(pmd.getBrand())).collect(Collectors.toList());
		}
		
		if(filterCriteria.containsKey(ElectConstants.OS)){
			List<ProductMetaData> filteredOsSmartPhoneList = new ArrayList<ProductMetaData>();
			
			List<String> filteredOS = filterCriteria.get(ElectConstants.OS);
			
			for(ProductMetaData pmd : latestSmartPhoneList){
				String OS = pmd.getOS();
				if(!StringUtils.isEmpty(OS)){
				for(String filterOsString:filteredOS){
					if(OS.contains(filterOsString))
						filteredOsSmartPhoneList.add(pmd);
				}
				}
			}
			
			latestSmartPhoneList = filteredOsSmartPhoneList;
			
		}
		
		
		if(filterCriteria.containsKey(ElectConstants.FEATURE)){
			List<String> feature = filterCriteria.get(ElectConstants.FEATURE);
			
			/**
			 * TODO:
			 * ======Rank on the basis of feature====
			 * 
			 */
			
			
			
		}else{
		
		
		//sort the productmetadata on the basis of (score)rank in category in descending order
		   Collections.sort(latestSmartPhoneList,new Comparator<ProductMetaData>(){
			@Override
			public int compare(ProductMetaData o1, ProductMetaData o2) {
				/*
				 *  Since rank in category not populated in mssql,As of now sort on the basis of avgRating......
				 *  TODO: SORT on the basis of (Score)rank in category
				 */
				//return o1.getRankInCategory() > o2.getRankInCategory()?1:(o1.getRankInCategory() == o2.getRankInCategory()? 0:-1); 
				return o1.getAvgRating() > o2.getAvgRating()?-1:(o1.getAvgRating() == o2.getAvgRating()? 0:1); 
			}
		   });
		   
		}
		if (latestSmartPhoneList.size() > 200) {
			latestSmartPhoneList = new ArrayList<ProductMetaData>(latestSmartPhoneList.subList(0, 199));
		}
		return latestSmartPhoneList;
	}

	@Override
	public CareSearchResultDao getCareSearchDataFromProductId(String productId) {
		Mobile m = (Mobile) productService.getProduct(productId, ProductEnum.MOBILE.getValue());
		CareSearchResultDao csrd = null;
		if (m != null) {

			csrd = new CareSearchResultDao(m.getName(), ProductEnum.MOBILE.toString(), productId, m.getBrand(),
					m.getBestBuyPrice());
			csrd.setCreatedOn(m.getCreatedOn());
		}
		return csrd;
	}

	@Override
	public String careUtil(ModelMap model, String productId) {
		if (StringUtils.isEmpty(productId)) {
			model.put("formAttribute", new Mobile());
		} else {
			model.put("formAttribute", productService.getProduct(productId, ProductEnum.MOBILE.toString()));
		}
		return "createMobile";
	}

	@Override
	public String saveCareProduct(Object obj, ModelMap model) {
		Mobile mobile = (Mobile) obj;
		String productId = "";
		if (mobile.getProductId() == null || "".equalsIgnoreCase(mobile.getProductId())) {
			String seqCount = sequenceService.getNextSequenceCount(ProductEnum.MOBILE.toString());
			/*productId = "MO_" + mobile.getName().substring(0, 3) + "_" + mobile.getBrand().substring(0, 3) + "_"
					+ seqCount;*/
			productId = mobile.getName().replace(" ", "-")+ "-"+seqCount;
			mobile.setProductId(productId);
			mobile.setCreatedOn(eUtil.getCurrentDateAndTime());
			mobile.setCategory(ProductEnum.MOBILE.toString());
			model.put("msg", productId + " added successfully!");
			productService.addProduct(mobile, ProductEnum.MOBILE.toString(), productId);
		} else {
			/**
			 * old product for update
			 */
			productId = mobile.getProductId();
			mobile.setUpdatedOn(eUtil.getCurrentDateAndTime());
			productService.updateProduct(productId, mobile, ProductEnum.MOBILE.toString());
			model.put("msg", productId + " updated successfully!");
		}

		model.put("msgDetails",
				((Mobile) productService.getProduct(productId, ProductEnum.MOBILE.toString())).toString());

		return "careSubmitInfo";
	}

	@Override
	public void storeMetaDataToRDB(List<Object> allProduct) {
		DecimalFormat df = new DecimalFormat("#.#");
		List<ReviewMetaData> reviewMetaDataListForRDB = new ArrayList<ReviewMetaData>();
		List<ProductMetaData> productMetaDataList = new ArrayList<ProductMetaData>();

		for (Object obj : allProduct) {
			try {
				Mobile mobile = (Mobile) obj;
				List<String> listOfReviewsId = mobile.getUserReviewIds();
				int totalCountOfRatings = 0;
				if (!CollectionUtils.isEmpty(listOfReviewsId)) {
					List<Object> reviews = productService.multiGet(listOfReviewsId, ProductEnum.REVIEW.getValue());
					for (Object review : reviews) {
						ReviewDetails rd = (ReviewDetails) review;
						totalCountOfRatings++;
						reviewMetaDataListForRDB.add(new ReviewMetaData(rd.getReviewId(), rd.getForProductId(),
								rd.getLike(), eUtil.getDateFormatFromStringFormat(rd.getCreatedOn()),
								eUtil.getDateFormatFromStringFormat(rd.getModifiedOn())));
					}
				}

				List<String> topHeadlFeature = mobile.getTopHeadlineFeatures();
				StringBuffer topHeadFeature = new StringBuffer("");
				if (topHeadlFeature != null && !topHeadlFeature.isEmpty()) {
					for (String features : topHeadlFeature) {
						topHeadFeature.append(features).append(",");
					}

				}
				
				
				/**
				 * Score would be out of 100,
				 * avgRating would be scaled out of 5,
				 * formattedAvgRating would be stricted to one decimal point(4.25 --> 4.2,4.78666 -->4.7)
				 * 
				 * rankIncategory would be defined once all the product persisted in DB
				 * 
				 */
				double score = rankingService.evaluteScoreForMobile(mobile);
				double avgRating = (score/20);
				double formattedAvgRating = Double.parseDouble(df.format(avgRating));
				
				int totalFavCount = getTotalFavCountForProduct(mobile);
				ProductMetaData pmd = new ProductMetaData(mobile.getProductId(),
						topHeadFeature.toString().substring(0, topHeadFeature.toString().length() - 1).toString(),
						mobile.getName(), mobile.getBrand(), mobile.getMainImageUrl(), mobile.getBestBuyPrice(),
						mobile.getReleasedOS(), totalFavCount, ProductEnum.MOBILE.getValue(), totalCountOfRatings,
						mobile.getOverView().getMarketStaus(), formattedAvgRating,score, -1, true);
				pmd.setReleaseDate(mobile.getOverView().getReleaseDate());
				pmd.setCreatedOn(eUtil.getDateFormatFromStringFormat(mobile.getCreatedOn()));
				productMetaDataList.add(pmd);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		
		System.out.println("Total count review for mobile" + reviewMetaDataListForRDB.size());
		
		/**
		 * populating rankInCategory based on score
		 */
		
		Collections.sort(productMetaDataList, new Comparator<ProductMetaData>() {
			@Override
			public int compare(ProductMetaData o1, ProductMetaData o2) {
				double s1 = o1.getScore();
				double s2 = o2.getScore();
						
				return s1 > s2 ?-1:(s1 == s2?0:1); 
			}
		});
		for(int i =0;i < productMetaDataList.size() ; i++ ){
			try{
			ProductMetaData pmd = productMetaDataList.get(i);
					pmd.setRankInCategory(i+1);
					productMetaDataRDBService.save(pmd);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
				
		
		
		reviewMetaDataRDBService.BatchInsert(reviewMetaDataListForRDB);
		
	}

	private int getTotalFavCountForProduct(Mobile mobile) {
		List<String> userList = mobile.getFavedByUser();
		if(!CollectionUtils.isEmpty(userList)){
			return userList.size();
		}
		return 0;
	}

}
