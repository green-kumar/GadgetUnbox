package com.planb.inmemoery.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.planb.care.dao.CareSearchResultDao;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.user.UserFavProduct;
import com.planb.metadata.ProductMetaData;

public interface ElectInMemoryService {
	
	public List<ProductMetaData> fetchLatestProductList();
	
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom);
	
	public void addReviewForProduct(String reviewId,String productId);
	
	public void populateMetaDataService(ReviewDetails reviewDetails);
	
	public UserFavProduct populateUserFavProductMetaDta(String productId,String currentDate);
	
	public void addUserInProductFavList(String productId,String userId);
	
	public void removeUserFromProductFavList(String productId,String userId);
	
	public Class getClassName();
	
	void populatedLoggedUserMetaData(String userEmail, ModelMap model);

	public List<ProductMetaData> getTopRatedProduct();
	
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String,List<String>> filterCriteria);

	public CareSearchResultDao getCareSearchDataFromProductId(String id);
	
	public String careUtil(ModelMap model,String productId);
	
	public String saveCareProduct(Object obj,ModelMap model);
	
	public void storeMetaDataToRDB(List<Object> allProduct);
	
	
	
}
