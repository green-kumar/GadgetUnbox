package com.planb.dao.user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.planb.inmemoery.service.GenderEnum;
@Component
@Scope("prototype")
public class UserDetail implements Serializable{
     
	private static final long serialVersionUID = -6310482961182828642L;
	private String userId;
     private String name;
     private  String email;
     private String password;
     private String accessKey;
     private String DOB;//format will be like 31 may,2016
     private GenderEnum gender;
     private List<String> reviewIds;
     private List<UserFavProduct> favProducts;
     private boolean isVerified;
     private Map<String,Double> productIdsAndRating;
     
     public UserDetail(){
    	 this.DOB="DD-MM-YYYY";
    	 this.gender=GenderEnum.MALE;
     }
     
 	public UserDetail(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
     
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getReviewIds() {
		return reviewIds;
	}
	public void setReviewIds(List<String> reviewIds) {
		this.reviewIds = reviewIds;
	}

	public Map<String, Double> getProductIdsAndRating() {
		return productIdsAndRating;
	}
	public void setProductIdsAndRating(Map<String, Double> productIdsAndRating) {
		this.productIdsAndRating = productIdsAndRating;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public List<UserFavProduct> getFavProducts() {
		return favProducts;
	}

	public void setFavProducts(List<UserFavProduct> favProducts) {
		this.favProducts = favProducts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", accessKey=" + accessKey + ", DOB=" + DOB + ", gender=" + gender + ", reviewIds=" + reviewIds
				+ ", favProducts=" + favProducts + ", isVerified=" + isVerified + ", productIdsAndRating="
				+ productIdsAndRating + "]";
	}




     


}
