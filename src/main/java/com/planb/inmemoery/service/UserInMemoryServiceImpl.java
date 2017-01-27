package com.planb.inmemoery.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.planb.care.dao.CareSearchResultDao;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.user.UserDetail;
import com.planb.dao.user.UserFavProduct;
import com.planb.metadata.ProductMetaData;

public class UserInMemoryServiceImpl implements ElectInMemoryService {

	@Override
	public List<ProductMetaData> fetchLatestProductList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductMetaData> fetchLoadMoreProductList(int startFrom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReviewForProduct(String reviewId, String productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void populateMetaDataService(ReviewDetails reviewDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserFavProduct populateUserFavProductMetaDta(String productId, String currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserInProductFavList(String productId, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUserFromProductFavList(String productId, String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class getClassName() {
		return UserDetail.class;
	}

	@Override
	public void populatedLoggedUserMetaData(String userEmail, ModelMap model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductMetaData> getTopRatedProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductMetaData> getTopRatedFilterProduct(Map<String, List<String>> filterCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CareSearchResultDao getCareSearchDataFromProductId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String careUtil(ModelMap model,String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveCareProduct(Object obj, ModelMap model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeMetaDataToRDB(List<Object> allProduct) {
		// TODO Auto-generated method stub
		
	}

}
