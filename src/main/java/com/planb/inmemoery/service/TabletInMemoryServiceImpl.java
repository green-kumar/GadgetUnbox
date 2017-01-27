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
import com.planb.dao.camera.camera;
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
public class TabletInMemoryServiceImpl implements ElectInMemoryService {
	
	
	
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
		List<ProductMetaData> latestTablet = cbdToRdbToInMem.getLatestTabletList();
	if (!org.springframework.util.CollectionUtils.isEmpty(latestTablet)) {
		
		if (latestTablet.size() > 16) {
			return new ArrayList<ProductMetaData>(latestTablet.subList(0, 16));
		} else {
			return latestTablet;
		}
	}
	return new ArrayList<ProductMetaData>();}

	@Override
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom) {
		List<ProductMetaData> latestTabletList = cbdToRdbToInMem.getLatestTabletList();
		if (!org.springframework.util.CollectionUtils.isEmpty(latestTabletList)) {
			int maxListSize = cbdToRdbToInMem.getLatestTabletList().size();
			int fromIndex = startFrom * 16;
			int toIndex = fromIndex + 16;
			if (fromIndex < maxListSize && toIndex <= maxListSize) {
				return new ArrayList<ProductMetaData>(latestTabletList.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				return (new ArrayList<ProductMetaData>(latestTabletList.subList(fromIndex, maxListSize)));
			}
		}

		return new ArrayList<ProductMetaData>();
	}

	@Override
	public void addReviewForProduct(String reviewId, String productId) {

		Object prodObj = productService.getProduct(productId, ProductEnum.TABLET.getValue());
		Tablet tablet = (Tablet) prodObj;
		List<String> userRevieweIds = tablet.getUserReviewIds();
		if (userRevieweIds == null) {
			userRevieweIds = new ArrayList<String>();
			userRevieweIds.add(reviewId);
			tablet.setUserReviewIds(userRevieweIds);
		} else {
			userRevieweIds.add(reviewId);
		}
		tablet.setUserReviewCount(tablet.getUserReviewCount() + 1);
		productService.updateProduct(productId, tablet, ProductEnum.TABLET.getValue());

	
	}

	@Override
	public void populateMetaDataService(ReviewDetails reviewDetails) {

		List<ProductMetaData> latestTablet = cbdToRdbToInMem.getLatestTabletList();
		Map<String, ProductMetaData> latestTabletMap = latestTablet.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		String productId = reviewDetails.getForProductId();

		if (latestTabletMap.containsKey(productId)) {
			ProductMetaData productMetaData = latestTabletMap.get(productId);
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
		List<ProductMetaData> latestTablet = cbdToRdbToInMem.getLatestTabletList();
		Map<String, ProductMetaData> latestTabletMap = latestTablet.stream()
				.collect(Collectors.toMap(ProductMetaData::getProductId, Function.identity()));
		if (latestTabletMap.containsKey(productId)) {
			ProductMetaData pmd = latestTabletMap.get(productId);
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

		Tablet tablet = (Tablet) productService.getProduct(productId, ProductEnum.TABLET.getValue());
		List<String> favedByUsers = tablet.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.add(userId);

		productService.updateProduct(productId, tablet, ProductEnum.TABLET.getValue());
	
	}

	@Override
	public void removeUserFromProductFavList(String productId, String userId) {


		Tablet tablet = (Tablet) productService.getProduct(productId, ProductEnum.TABLET.getValue());
		List<String> favedByUsers = tablet.getFavedByUser();

		if (favedByUsers == null) {
			favedByUsers = new ArrayList<String>();
		}
		favedByUsers.remove(productId);
		productService.updateProduct(productId, tablet, ProductEnum.TABLET.getValue());

	
	
	}

	@Override
	public Class getClassName() {
		return Tablet.class;
	}

	@Override
	public void populatedLoggedUserMetaData(String userEmail, ModelMap model) {

		try {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			List<UserFavProduct> favList = userDetail.getFavProducts();
			if (!CollectionUtils.isEmpty(favList)) {
				List<String> favProductList = favList.stream()
						.filter(userFavProduct -> userFavProduct.getCategory().equals(ProductEnum.TABLET.getValue()))
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
		List<ProductMetaData> sortedTabletList = cbdToRdbToInMem.getLatestTabletList();
		//sort the productmetadata on the basis of (score)rank in category in descending order
		   Collections.sort(sortedTabletList,new Comparator<ProductMetaData>(){
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
		if (sortedTabletList.size() > 200) {
			sortedTabletList = new ArrayList<ProductMetaData>(sortedTabletList.subList(0, 199));
		}
		return sortedTabletList;
	}

	@Override
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String, List<String>> filterCriteria) {
		
		List<ProductMetaData> latestTabletList = cbdToRdbToInMem.getLatestTabletList();
		
		if(filterCriteria.containsKey(ElectConstants.BRAND)){
			List<String> brands = filterCriteria.get(ElectConstants.BRAND);
			latestTabletList = latestTabletList.stream().filter(pmd -> brands.contains(pmd.getBrand())).collect(Collectors.toList());
		}
		
		if(filterCriteria.containsKey(ElectConstants.OS)){
			List<String> OS = filterCriteria.get(ElectConstants.OS);
			latestTabletList = latestTabletList.stream().filter(pmd -> OS.contains(pmd.getOS())).collect(Collectors.toList());
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
		   Collections.sort(latestTabletList,new Comparator<ProductMetaData>(){
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
		if (latestTabletList.size() > 200) {
			latestTabletList = new ArrayList<ProductMetaData>(latestTabletList.subList(0, 199));
		}
		return latestTabletList;
	}

	@Override
	public CareSearchResultDao getCareSearchDataFromProductId(String productId) {

		Tablet m = (Tablet) productService.getProduct(productId, ProductEnum.TABLET.getValue());
		CareSearchResultDao csrd = null;
		if (m != null) {

			csrd = new CareSearchResultDao(m.getName(), ProductEnum.TABLET.toString(), productId, m.getBrand(),
					m.getBestBuyPrice());
			csrd.setCreatedOn(m.getCreatedOn());
		}
		return csrd;
	
	}

	@Override
	public String careUtil(ModelMap model,String productId) {
		if(StringUtils.isEmpty(productId)){
			model.put("formAttribute",new Tablet());
			}else{
				model.put("formAttribute",productService.getProduct(productId, ProductEnum.TABLET.toString()));
			}		return "createTablet";
	}

	@Override
	public String saveCareProduct(Object obj, ModelMap model) {
		Tablet tablet  = (Tablet)obj;
	String productId="";
	if(tablet.getProductId() == null || "".equalsIgnoreCase(tablet.getProductId()) ){
	String seqCount=sequenceService.getNextSequenceCount(ProductEnum.TABLET.toString());
	/*productId="TAB_"+tablet.getName().substring(0, 3)+"_"+tablet.getBrand().substring(0,3)+"_"+seqCount;*/
	
	productId = tablet.getName().replace(" ", "-")+ "-"+seqCount;
	tablet.setProductId(productId);
	tablet.setCreatedOn(eUtil.getCurrentDateAndTime());
	model.put("msg", productId+" added successfully!");
	tablet.setCategory(ProductEnum.TABLET.toString());
	productService.addProduct(tablet, ProductEnum.TABLET.toString(), productId);
	}else{
		/**
		 * old product for update 
		 */
		productId=tablet.getProductId();
		tablet.setUpdatedOn(eUtil.getCurrentDateAndTime());
		productService.updateProduct(productId, tablet, ProductEnum.TABLET.toString());
		model.put("msg", productId+" updated successfully!");
	}
		
	model.put("msgDetails", ((Tablet)productService.getProduct(productId, ProductEnum.TABLET.toString())).toString());
	
	return "careSubmitInfo";
	}

	@Override
	public void storeMetaDataToRDB(List<Object> allProduct) {

		DecimalFormat df = new DecimalFormat("#.#");
		List<ReviewMetaData> reviewMetaDataListForRDB = new ArrayList<ReviewMetaData>();
		List<ProductMetaData> productMetaDataList = new ArrayList<ProductMetaData>();

		for (Object obj : allProduct) {
			try {
				Tablet tablet = (Tablet) obj;
				List<String> listOfReviewsId = tablet.getUserReviewIds();
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

				List<String> topHeadlFeature = tablet.getTopHeadlineFeatures();
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
				double score = rankingService.evaluteScoreForTablet(tablet);
				double avgRating = (score/20);
				double formattedAvgRating = Double.parseDouble(df.format(avgRating));
				
				int totalFavCount = getTotalFavCountForProduct(tablet);
				ProductMetaData pmd = new ProductMetaData(tablet.getProductId(),
						topHeadFeature.toString().substring(0, topHeadFeature.toString().length() - 1).toString(),
						tablet.getName(), tablet.getBrand(), tablet.getMainImageUrl(), tablet.getBestBuyPrice(),
						tablet.getReleasedOS(), totalFavCount, ProductEnum.TABLET.getValue(), totalCountOfRatings,
						tablet.getOverView().getMarketStaus(), formattedAvgRating,score, -1, true);
				pmd.setReleaseDate(tablet.getOverView().getReleaseDate());
				pmd.setCreatedOn(eUtil.getDateFormatFromStringFormat(tablet.getCreatedOn()));
				productMetaDataList.add(pmd);
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		
		System.out.println("Total count review for tablet" + reviewMetaDataListForRDB.size());
		
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

	private int getTotalFavCountForProduct(Tablet tablet) {
		List<String> userList = tablet.getFavedByUser();
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
		
		List<Object> objList = productService.getAllProduct(ProductEnum.TABLET.toString());
		for(Object obj : objList){
			Tablet t = (Tablet)obj;
			if(!t.getProductId().equalsIgnoreCase("TAB_Sam_Sam_000")){
				productService.deleteProduct(t.getProductId(), ProductEnum.TABLET.toString());
			}
		}
		
		
		for(int i = 0 ;i <50;i++){
			Tablet obj = (Tablet)productService.getProduct("TAB_Sam_Sam_000",ProductEnum.TABLET.toString());
			obj.setName(obj.getName()+"_"+i);
			obj.setProductId(null);
			TabletInMemoryServiceImpl cInMSImpl = context.getBean("tabletInMemoryServiceImpl",TabletInMemoryServiceImpl.class);
			cInMSImpl.saveCareProduct(obj,map);
		}
		
	  System.out.println("done");
	}
	
}
