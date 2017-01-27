package com.planb.inmemoery.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import com.planb.care.dao.CareSearchResultDao;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
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
@Service
public class HeadphoneInMemoryServiceImpl implements ElectInMemoryService {
	
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

	@Override
	public List<ProductMetaData> fetchLatestProductList() {
		List<ProductMetaData> latestHeadphone = cbdToRdbToInMem.getLatestHeadphoneList();
	if (!org.springframework.util.CollectionUtils.isEmpty(latestHeadphone)) {
		
		if (latestHeadphone.size() > 16) {
			return new ArrayList<ProductMetaData>(latestHeadphone.subList(0, 16));
		} else {
			return latestHeadphone;
		}
	}
	return new ArrayList<ProductMetaData>();
	}

	@Override
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom) {

		List<ProductMetaData> latestHeadphoneList = cbdToRdbToInMem.getLatestHeadphoneList();
		if (!org.springframework.util.CollectionUtils.isEmpty(latestHeadphoneList)) {
			int maxListSize = cbdToRdbToInMem.getLatestHeadphoneList().size();
			int fromIndex = startFrom * 16;
			int toIndex = fromIndex + 16;
			if (fromIndex < maxListSize && toIndex <= maxListSize) {
				return new ArrayList<ProductMetaData>(latestHeadphoneList.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				return (new ArrayList<ProductMetaData>(latestHeadphoneList.subList(fromIndex, maxListSize)));
			}
		}

		return new ArrayList<ProductMetaData>();
	
	}

	@Override
	public void addReviewForProduct(String reviewId, String productId) {

		Object prodObj = productService.getProduct(productId, ProductEnum.HEADPHONE.getValue());
		Headphone headphone = (Headphone) prodObj;
		List<String> userRevieweIds = headphone.getUserReviewIds();
		if (userRevieweIds == null) {
			userRevieweIds = new ArrayList<String>();
			userRevieweIds.add(reviewId);
			headphone.setUserReviewIds(userRevieweIds);
		} else {
			userRevieweIds.add(reviewId);
		}
		headphone.setUserReviewCount(headphone.getUserReviewCount() + 1);
		productService.updateProduct(productId, headphone, ProductEnum.HEADPHONE.getValue());

	

	}

	@Override
	public void populateMetaDataService(ReviewDetails reviewDetails) {
		List<ProductMetaData> latestHeadphone = cbdToRdbToInMem.getLatestHeadphoneList();
		Map<String, ProductMetaData> latestHeadphoneMap = latestHeadphone.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		String productId = reviewDetails.getForProductId();

		if (latestHeadphoneMap.containsKey(productId)) {
			ProductMetaData productMetaData = latestHeadphoneMap.get(productId);
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


		List<ProductMetaData> latestHeadphone = cbdToRdbToInMem.getLatestHeadphoneList();
		Map<String, ProductMetaData> latestHeadphoneMap = latestHeadphone.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		if (latestHeadphoneMap.containsKey(productId)) {
			ProductMetaData pmd = latestHeadphoneMap.get(productId);
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

		Headphone headphone = (Headphone) productService.getProduct(productId, ProductEnum.HEADPHONE.getValue());
		List<String> favedByUsers = headphone.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.add(userId);

		productService.updateProduct(productId, headphone, ProductEnum.HEADPHONE.getValue());
	
	}

	@Override
	public void removeUserFromProductFavList(String productId, String userId) {



		Headphone headphone = (Headphone) productService.getProduct(productId, ProductEnum.HEADPHONE.getValue());
		List<String> favedByUsers = headphone.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.remove(productId);
		productService.updateProduct(productId, headphone, ProductEnum.HEADPHONE.getValue());

	
		
	}

	@Override
	public Class getClassName() {
		return Headphone.class;
	}

	@Override
	public void populatedLoggedUserMetaData(String userEmail, ModelMap model) {

		try {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			List<UserFavProduct> favList = userDetail.getFavProducts();
			if (!CollectionUtils.isEmpty(favList)) {
				List<String> favProductList = favList.stream()
						.filter(userFavProduct -> userFavProduct.getCategory().equals(ProductEnum.HEADPHONE.getValue()))
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
		List<ProductMetaData> sortedHeadphoneList = cbdToRdbToInMem.getLatestHeadphoneList();
		//sort the productmetadata on the basis of (score)rank in category in descending order
		   Collections.sort(sortedHeadphoneList,new Comparator<ProductMetaData>(){
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
		if (sortedHeadphoneList.size() > 200) {
			sortedHeadphoneList = new ArrayList<ProductMetaData>(sortedHeadphoneList.subList(0, 199));
		}
		return sortedHeadphoneList;
	}

	@Override
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String, List<String>> filterCriteria) {
		
		List<ProductMetaData> latestHeadphoneList = cbdToRdbToInMem.getLatestHeadphoneList();
		
		if(filterCriteria.containsKey(ElectConstants.BRAND)){
			List<String> brands = filterCriteria.get(ElectConstants.BRAND);
			latestHeadphoneList = latestHeadphoneList.stream().filter(pmd -> brands.contains(pmd.getBrand())).collect(Collectors.toList());
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
		   Collections.sort(latestHeadphoneList,new Comparator<ProductMetaData>(){
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
		if (latestHeadphoneList.size() > 200) {
			latestHeadphoneList = new ArrayList<ProductMetaData>(latestHeadphoneList.subList(0, 199));
		}
		return latestHeadphoneList;
	}

	@Override
	public CareSearchResultDao getCareSearchDataFromProductId(String productId) {
		Headphone m = (Headphone) productService.getProduct(productId, ProductEnum.HEADPHONE.getValue());
		CareSearchResultDao csrd = null;
		if (m != null) {

			csrd = new CareSearchResultDao(m.getName(), ProductEnum.HEADPHONE.toString(), productId, m.getBrand(),
					m.getBestBuyPrice());
			csrd.setCreatedOn(m.getCreatedOn());
		}
		return csrd;
	}

	@Override
	public String careUtil(ModelMap model,String productId) {
		if(StringUtils.isEmpty(productId)){
			model.put("formAttribute",new Headphone());
			}else{
				model.put("formAttribute",productService.getProduct(productId, ProductEnum.HEADPHONE.toString()));
			}		return "createHeadphone";
	}

	@Override
	public String saveCareProduct(Object obj, ModelMap model) {
		Headphone headphone  = (Headphone)obj;
		String productId="";
		if(headphone.getProductId() == null || "".equalsIgnoreCase(headphone.getProductId()) ){
		String seqCount=sequenceService.getNextSequenceCount(ProductEnum.HEADPHONE.toString());
		/*productId="HPH_"+headphone.getName().substring(0, 3)+"_"+headphone.getBrand().substring(0,3)+"_"+seqCount;*/
		
		productId = headphone.getName().replace(" ", "-")+ "-"+seqCount;
		headphone.setProductId(productId);
		headphone.setCreatedOn(eUtil.getCurrentDateAndTime());
		headphone.setCategory(ProductEnum.HEADPHONE.toString());
		model.put("msg", productId+" added successfully!");
		productService.addProduct(headphone, ProductEnum.HEADPHONE.toString(), productId);
		}else{
			/**
			 * old product for update 
			 */
			productId=headphone.getProductId();
			headphone.setUpdatedOn(eUtil.getCurrentDateAndTime());
			productService.updateProduct(productId, headphone, ProductEnum.HEADPHONE.toString());
			model.put("msg", productId+" updated successfully!");
		}
			
      model.put("msgDetails", ((Headphone)productService.getProduct(productId, ProductEnum.HEADPHONE.toString())).toString());
		
		return "careSubmitInfo";	}

	@Override
	public void storeMetaDataToRDB(List<Object> allProduct) {
		DecimalFormat df = new DecimalFormat("#.#");
		List<ReviewMetaData> reviewMetaDataListForRDB = new ArrayList<ReviewMetaData>();
		List<ProductMetaData> productMetaDataList = new ArrayList<ProductMetaData>();

		for (Object obj : allProduct) {
			try {
				Headphone headphone = (Headphone) obj;
				List<String> listOfReviewsId = headphone.getUserReviewIds();
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

				List<String> topHeadlFeature = headphone.getTopHeadlineFeatures();
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
				double score = rankingService.evaluteScoreForHeadphone(headphone);
				double avgRating = (score/20);
				double formattedAvgRating = Double.parseDouble(df.format(avgRating));
				
				int totalFavCount = getTotalFavCountForProduct(headphone);
				ProductMetaData pmd = new ProductMetaData(headphone.getProductId(),
						topHeadFeature.toString().substring(0, topHeadFeature.toString().length() - 1).toString(),
						headphone.getName(), headphone.getBrand(), headphone.getMainImageUrl(), headphone.getBestBuyPrice(),
						null, totalFavCount, ProductEnum.HEADPHONE.getValue(), totalCountOfRatings,
						"released", formattedAvgRating,score, -1, true);
				if(!StringUtils.isEmpty(headphone.getReleasedOn())){
					pmd.setReleaseDate(headphone.getReleasedOn());
				}else{
					pmd.setReleaseDate("January 1, 2016");
				}
				
				pmd.setCreatedOn(eUtil.getDateFormatFromStringFormat(headphone.getCreatedOn()));
				productMetaDataList.add(pmd);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		
		System.out.println("Total count review for headphone" + reviewMetaDataListForRDB.size());
		
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

	private int getTotalFavCountForProduct(Headphone headphone) {
		List<String> userList = headphone.getFavedByUser();
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
	List<Object> objList = productService.getAllProduct(ProductEnum.HEADPHONE.toString());
	for(Object obj : objList){
		Headphone t = (Headphone)obj;
		if(!t.getProductId().equalsIgnoreCase("HPH_Son_Son_000")){
			productService.deleteProduct(t.getProductId(), ProductEnum.HEADPHONE.toString());
		}
	}
	
	
	for(int i = 0 ;i <50;i++){
		Headphone obj = (Headphone)productService.getProduct("HPH_Son_Son_000",ProductEnum.HEADPHONE.toString());
		obj.setName(obj.getName()+"_"+i);
		obj.setProductId(null);
		HeadphoneInMemoryServiceImpl cInMSImpl = context.getBean("headphoneInMemoryServiceImpl",HeadphoneInMemoryServiceImpl.class);
		cInMSImpl.saveCareProduct(obj,map);
	}
	
  System.out.println("done");
System.exit(0);

}
}
