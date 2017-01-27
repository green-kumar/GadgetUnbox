package com.planb.dao.review;

import java.io.Serializable;

public class ReviewDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -369286280675574288L;
	private String reviewId;
	private String forProductId;
	private String reviewHeadline;
    private String reviewPros;
    private String reviewCons;
	private String byUserId;
	private String category;
	private int like;
	private double rating;
	private String createdOn;
	private String modifiedOn;
	private String productThumbNailImg;
	private double avgRatingForProduct;
	private String productName;
	
	public ReviewDetails() {
		super();
	}
	public ReviewDetails(String reviewId, String forProductId, String reviewHeadline, String reviewPros,
			String reviewCons, String byUserId, String category, int like, double rating, String createdOn,
			String modifiedOn) {
		super();
		this.reviewId = reviewId;
		this.forProductId = forProductId;
		this.reviewHeadline = reviewHeadline;
		this.reviewPros = reviewPros;
		this.reviewCons = reviewCons;
		this.byUserId = byUserId;
		this.category = category;
		this.like = like;
		this.rating = rating;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
	
	public String getByUserId() {
		return byUserId;
	}
	public void setByUserId(String byUserId) {
		this.byUserId = byUserId;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getReviewPros() {
		return reviewPros;
	}
	public void setReviewPros(String reviewPros) {
		this.reviewPros = reviewPros;
	}
	public String getReviewCons() {
		return reviewCons;
	}
	public void setReviewCons(String reviewCons) {
		this.reviewCons = reviewCons;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getReviewHeadline() {
		return reviewHeadline;
	}
	public void setReviewHeadline(String reviewHeadline) {
		this.reviewHeadline = reviewHeadline;
	}
	public String getForProductId() {
		return forProductId;
	}
	public void setForProductId(String forProductId) {
		this.forProductId = forProductId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ReviewDetails [reviewId=" + reviewId + ", forProductId=" + forProductId + ", reviewHeadline="
				+ reviewHeadline + ", reviewPros=" + reviewPros + ", reviewCons=" + reviewCons + ", byUserId="
				+ byUserId + ", category=" + category + ", like=" + like + ", rating=" + rating + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + "]";
	}
	public static void main(String[] args) {
		
	}
	public String getProductThumbNailImg() {
		return productThumbNailImg;
	}
	public void setProductThumbNailImg(String productThumbNailImg) {
		this.productThumbNailImg = productThumbNailImg;
	}
	public double getAvgRatingForProduct() {
		return avgRatingForProduct;
	}
	public void setAvgRatingForProduct(double avgRatingForProduct) {
		this.avgRatingForProduct = avgRatingForProduct;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

}
