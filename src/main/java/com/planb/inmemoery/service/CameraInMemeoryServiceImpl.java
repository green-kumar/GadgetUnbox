package com.planb.inmemoery.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import com.cloudinary.Cloudinary;
import com.planb.care.dao.CareSearchResultDao;
import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.tablet.Tablet;
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
@Service
public class CameraInMemeoryServiceImpl implements ElectInMemoryService {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	EUtil eUtil;
	
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
	CbdToRdbToInMem cbdToRdbToInMem;


	@Autowired
	ProductMetaDataRDBService productMetaDataRDBService;

	@Autowired
	ReviewMetaDataRDBService reviewMetaDataRDBService;
	
	@Autowired
	RankingService rankingService;

	

	@Override
	public List<ProductMetaData> fetchLatestProductList() {
		List<ProductMetaData> latestCamera = cbdToRdbToInMem.getLatestCameraList();
	if (!org.springframework.util.CollectionUtils.isEmpty(latestCamera)) {
		
		if (latestCamera.size() > 16) {
			return new ArrayList<ProductMetaData>(latestCamera.subList(0, 16));
		} else {
			return latestCamera;
		}
	}
	return new ArrayList<ProductMetaData>();
	}

	@Override
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom) {	
		List<ProductMetaData> latestCameraList = cbdToRdbToInMem.getLatestCameraList();
	if (!org.springframework.util.CollectionUtils.isEmpty(latestCameraList)) {
		int maxListSize = cbdToRdbToInMem.getLatestCameraList().size();
		int fromIndex = startFrom * 16;
		int toIndex = fromIndex + 16;
		if (fromIndex < maxListSize && toIndex <= maxListSize) {
			return new ArrayList<ProductMetaData>(latestCameraList.subList(fromIndex, toIndex));
		} else if (fromIndex < maxListSize && toIndex > maxListSize) {
			return (new ArrayList<ProductMetaData>(latestCameraList.subList(fromIndex, maxListSize)));
		}
	}

	return new ArrayList<ProductMetaData>();}

	@Override
	public void addReviewForProduct(String reviewId, String productId) {

		Object prodObj = productService.getProduct(productId, ProductEnum.CAMERA.getValue());
		camera camera = (camera) prodObj;
		List<String> userRevieweIds = camera.getUserReviewIds();
		if (userRevieweIds == null) {
			userRevieweIds = new ArrayList<String>();
			userRevieweIds.add(reviewId);
			camera.setUserReviewIds(userRevieweIds);
		} else {
			userRevieweIds.add(reviewId);
		}
		camera.setUserReviewCount(camera.getUserReviewCount() + 1);
		productService.updateProduct(productId, camera, ProductEnum.CAMERA.getValue());

	

	}

	@Override
	public void populateMetaDataService(ReviewDetails reviewDetails) {

		List<ProductMetaData> latestCamera = cbdToRdbToInMem.getLatestCameraList();
		Map<String, ProductMetaData> latestCameraMap = latestCamera.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		String productId = reviewDetails.getForProductId();

		if (latestCameraMap.containsKey(productId)) {
			ProductMetaData productMetaData = latestCameraMap.get(productId);
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

		List<ProductMetaData> latestCamera = cbdToRdbToInMem.getLatestCameraList();
		Map<String, ProductMetaData> latestCameraMap = latestCamera.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		if (latestCameraMap.containsKey(productId)) {
			ProductMetaData pmd = latestCameraMap.get(productId);
			String mainImageUrl = pmd.getMainImageUrl();
			String thumbnailImageUrl = ElectConstants.CLOUDINARY_THUMB_NAIL_BASE_URL
					+ mainImageUrl.substring(mainImageUrl.lastIndexOf("/") + 1);
			UserFavProduct userFavProduct = new UserFavProduct(pmd.getProductName(), ProductEnum.MOBILE.getValue(),
					productId, thumbnailImageUrl, pmd.getAvgRating(),currentDate);
			return userFavProduct;
		}

		return null;
	
	}

	@Override
	public void addUserInProductFavList(String productId, String userId) {
		camera camera = (camera) productService.getProduct(productId, ProductEnum.CAMERA.getValue());
		List<String> favedByUsers = camera.getFavedByUsers();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.add(userId);

		productService.updateProduct(productId, camera, ProductEnum.CAMERA.getValue());
	}

	@Override
	public void removeUserFromProductFavList(String productId, String userId) {

		camera camera = (camera) productService.getProduct(productId, ProductEnum.CAMERA.getValue());
		List<String> favedByUsers = camera.getFavedByUsers();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.remove(productId);
		productService.updateProduct(productId, camera, ProductEnum.CAMERA.getValue());

	
	}

	@Override
	public Class getClassName() {
		return camera.class;
	}

	@Override
	public void populatedLoggedUserMetaData(String userEmail, ModelMap model) {

		try {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			List<UserFavProduct> favList = userDetail.getFavProducts();
			if (!CollectionUtils.isEmpty(favList)) {
				List<String> favProductList = favList.stream()
						.filter(userFavProduct -> userFavProduct.getCategory().equals(ProductEnum.CAMERA.getValue()))
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

		List<ProductMetaData> sortedCameraList = cbdToRdbToInMem.getLatestCameraList();
		//sort the productmetadata on the basis of (score)rank in category in descending order
		   Collections.sort(sortedCameraList,new Comparator<ProductMetaData>(){
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
		if (sortedCameraList.size() > 200) {
			sortedCameraList = new ArrayList<ProductMetaData>(sortedCameraList.subList(0, 199));
		}
		return sortedCameraList;
	
	}

	@Override
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String, List<String>> filterCriteria) {

		
		List<ProductMetaData> latestCameraList = cbdToRdbToInMem.getLatestCameraList();
		
		if(filterCriteria.containsKey(ElectConstants.BRAND)){
			List<String> brands = filterCriteria.get(ElectConstants.BRAND);
			latestCameraList = latestCameraList.stream().filter(pmd -> brands.contains(pmd.getBrand())).collect(Collectors.toList());
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
		   Collections.sort(latestCameraList,new Comparator<ProductMetaData>(){
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
		if (latestCameraList.size() > 200) {
			latestCameraList = new ArrayList<ProductMetaData>(latestCameraList.subList(0, 199));
		}
		return latestCameraList;
	
	}

	@Override
	public CareSearchResultDao getCareSearchDataFromProductId(String id) {
		camera m = (camera) productService.getProduct(id, ProductEnum.CAMERA.getValue());
		CareSearchResultDao csrd = null;
		if (m != null) {

			csrd = new CareSearchResultDao(m.getName(), ProductEnum.CAMERA.toString(), id, m.getBrand(),
					m.getBestBuyPrice());
			csrd.setCreatedOn(m.getCreatedOn());
		}
		return csrd;
	}

	

	@Override
	public String careUtil(ModelMap model,String productId) {
		if(StringUtils.isEmpty(productId)){
			model.put("formAttribute",new camera());
			}else{
				model.put("formAttribute",productService.getProduct(productId, ProductEnum.CAMERA.toString()));
			}
		return "createCamera";
	}

	@Override
	public String saveCareProduct(Object obj, ModelMap model) {
		camera camera = (camera)obj;
		String productId="";
		if(camera.getProductId() == null || "".equalsIgnoreCase(camera.getProductId()) ){
		String seqCount=sequenceService.getNextSequenceCount(ProductEnum.CAMERA.toString());
		/*productId="CA_"+camera.getName().substring(0, 3)+"_"+camera.getBrand().substring(0,3)+"_"+seqCount;*/
		
		productId = camera.getName().replace(" ", "-")+ "-"+seqCount;
		camera.setProductId(productId);
		camera.setCreatedOn(eUtil.getCurrentDateAndTime());
		camera.setCategory(ProductEnum.CAMERA.toString());
		model.put("msg", productId+" added successfully!");
		productService.addProduct(camera, ProductEnum.CAMERA.toString(), productId);
		}else{
			/**
			 * old product for update 
			 */
			productId=camera.getProductId();
			camera.setUpdatedOn(eUtil.getCurrentDateAndTime());
			productService.updateProduct(productId, camera, ProductEnum.CAMERA.toString());
			model.put("msg", productId+" updated successfully!");
		}
model.put("msgDetails", ((camera)productService.getProduct(productId, ProductEnum.CAMERA.toString())).toString());
		
		return "careSubmitInfo";	
	}

	@Override
	public void storeMetaDataToRDB(List<Object> allProduct) {

		DecimalFormat df = new DecimalFormat("#.#");
		List<ReviewMetaData> reviewMetaDataListForRDB = new ArrayList<ReviewMetaData>();
		List<ProductMetaData> productMetaDataList = new ArrayList<ProductMetaData>();

		for (Object obj : allProduct) {
			try {
				camera camera = (camera) obj;
				List<String> listOfReviewsId = camera.getUserReviewIds();
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

				List<String> topHeadlFeature = camera.getTopHeadlineFeatures();
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
				double score = rankingService.evaluteScoreForCamera(camera);
				double avgRating = (score/20);
				double formattedAvgRating = Double.parseDouble(df.format(avgRating));
				
				int totalFavCount = getTotalFavCountForProduct(camera);
				ProductMetaData pmd = new ProductMetaData(camera.getProductId(),
						topHeadFeature.toString().substring(0, topHeadFeature.toString().length() - 1).toString(),
						camera.getName(), camera.getBrand(), camera.getMainImageUrl(), camera.getBestBuyPrice(),
						null, totalFavCount, ProductEnum.CAMERA.getValue(), totalCountOfRatings,
						camera.getOverView().getMarketStaus(), formattedAvgRating,score, -1, true);
				pmd.setReleaseDate(camera.getOverView().getReleaseDate());
				pmd.setCreatedOn(eUtil.getDateFormatFromStringFormat(camera.getCreatedOn()));
				productMetaDataList.add(pmd);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		
		System.out.println("Total count review for camera" + reviewMetaDataListForRDB.size());
		
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

	private int getTotalFavCountForProduct(camera camera) {

		List<String> userList = camera.getFavedByUsers();
		if(!CollectionUtils.isEmpty(userList)){
			return userList.size();
		}
		return 0;
	
	}
	public static void main(String[] args) {

		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		ProductService productService=context.getBean("productService",ProductService.class);
		ModelMap map = new ModelMap(); 
		List<Object> objList = productService.getAllProduct(ProductEnum.CAMERA.toString());
		for(Object obj : objList){
			camera t = (camera)obj;
			if(!t.getProductId().equalsIgnoreCase("CA_Son_Son_004")){
				productService.deleteProduct(t.getProductId(), ProductEnum.CAMERA.toString());
			}
		}
		
		
			
		for(int i = 0 ;i <50;i++){
			camera obj = (camera)productService.getProduct("CA_Son_Son_004",ProductEnum.CAMERA.toString());
			obj.setName(obj.getName()+"_"+i);
			obj.setProductId(null);
			CameraInMemeoryServiceImpl cInMSImpl = context.getBean("cameraInMemeoryServiceImpl",CameraInMemeoryServiceImpl.class);
			
			cInMSImpl.saveCareProduct(obj,map);
		}
		
	  System.out.println("done");
	
	
	
	}

}
