package com.planb.metadata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.product.util.EUtil;
import com.planb.service.ProductService;

@Service
public class MetaDataUtil {
	
	@Autowired
	ProductMetaDataRDBService productMetaDataRDBService;
	
	@Autowired
	ReviewMetaDataRDBService reviewMetaDataRDBService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	EUtil eUtil;
	

	public void storeAllProductToRDBMS(List<Object> allProduct, String category) {
		if(allProduct==null ||allProduct.size()==0)
		  return;	
		
		List<ReviewMetaData> reviewMetaDataListForRDB=new ArrayList<ReviewMetaData>();
		
		
		if(category.equalsIgnoreCase("mobile")){
			for(Object obj:allProduct){
				try{
				Mobile mobile=(Mobile)obj;
				List<String> listOfReviewsId=mobile.getUserReviewIds();
				double avgRating=0;
				int totalCountOfRatings=0;
				if(listOfReviewsId!=null && !listOfReviewsId.isEmpty()){
					
					int denom=0;
					List<Object> reviews=productService.multiGet(listOfReviewsId,ProductEnum.REVIEW.getValue());
					
					/*temporary i, for making uniq product review and created date 
					 * 
					 */
					int i=100;
					for(Object review:reviews){
						ReviewDetails rd=(ReviewDetails)review;
						avgRating+=rd.getRating();
						totalCountOfRatings++;
						
						Date createdDate=new Date();
						Date modifiedDate=new Date();
						
						if(!StringUtils.isEmpty(rd.getCreatedOn()))
							createdDate = eUtil.getDateFormatFromStringFormat(rd.getCreatedOn());
						
						createdDate=eUtil.increaseDays(createdDate, i++);
						
						if(!StringUtils.isEmpty(rd.getModifiedOn()))
							modifiedDate = eUtil.getDateFormatFromStringFormat(rd.getModifiedOn());
						   String reviewId=rd.getReviewId()+UUID.randomUUID().toString().substring(0, 10);
						   System.out.println(reviewId);
						reviewMetaDataListForRDB.add(new ReviewMetaData(reviewId,rd.getForProductId(), rd.getLike(), createdDate, modifiedDate));
						
					}
					avgRating/=reviews.size();
				}
				
			List<String> topHeadlFeature=mobile.getTopHeadlineFeatures();
			StringBuffer topHeadFeature=new StringBuffer("");
			if(topHeadlFeature!=null && !topHeadlFeature.isEmpty()){
				for(String features:topHeadlFeature){
					topHeadFeature.append(features).append(",");
				}
				
			}
			/*
			 * TODO: add createdOn and modifiedOn date in ProductMetaData
			 * constructor so that it should be stored in mysql and furthur
			 * would be helpful in comparable sort for deciding latest product.
			 */
			
				//ProductMetaData pmd1=new ProductMetaData(productId, topHeadlineFeatures, productName, brand, mainImageUrl, bestBuyPrice, oS, totalRecommendation, category, totalCountOfRatings, marketStatus, avgRating, rankInCategory, isActive)
				ProductMetaData pmd=new ProductMetaData(mobile.getProductId(), topHeadFeature.toString().substring(0, topHeadFeature.toString().length()-1).toString(), mobile.getName(), mobile.getBrand(), mobile.getMainImageUrl(), mobile.getBestBuyPrice(), mobile.getReleasedOS(), -1, "mobile", totalCountOfRatings, mobile.getOverView().getMarketStaus(), avgRating, 0,-1,true);
			     String releaseDate = mobile.getOverView().getReleaseDate();
			     if(releaseDate.contains("(")){
			    	 releaseDate = releaseDate.substring(0,releaseDate.indexOf("("));
			     }
			     pmd.setReleaseDate(releaseDate);
				
				productMetaDataRDBService.save(pmd);
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
	}

}
		System.out.println("Total count review for "+ category + reviewMetaDataListForRDB.size());
		reviewMetaDataRDBService.BatchInsert(reviewMetaDataListForRDB);
		
	}
}
