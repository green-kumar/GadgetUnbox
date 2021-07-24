package com.planb.dao.user;

import java.io.Serializable;

public class UserFavProduct implements Serializable{

	
	private static final long serialVersionUID = -3385220679471703211L;
	String productName;
	String category;
	String productId;
	String thumbnailImageUrl;
	double avgRatingforProduct;
	String createdOn;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}
	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}
	public double getAvgRatingforProduct() {
		return avgRatingforProduct;
	}
	public void setAvgRatingforProduct(double avgRatingforProduct) {
		this.avgRatingforProduct = avgRatingforProduct;
	}
	
	public UserFavProduct(String productName, String category, String productId, String thumbnailImageUrl,
			double avgRatingforProduct, String createdOn) {
		super();
		this.productName = productName;
		this.category = category;
		this.productId = productId;
		this.thumbnailImageUrl = thumbnailImageUrl;
		this.avgRatingforProduct = avgRatingforProduct;
		this.createdOn=createdOn;
	}
	public UserFavProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "UserFavProduct [productName=" + productName + ", category=" + category + ", productId=" + productId
				+ ", thumbnailImageUrl=" + thumbnailImageUrl + ", avgRatingforProduct=" + avgRatingforProduct
				+ ", createdOn=" + createdOn + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserFavProduct other = (UserFavProduct) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
}
