package com.planb.metadata;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="reviewmetadata")
public class ReviewMetaData implements Serializable{
	@Id
	@Column(name="reviewId")
	String reviewId;
	
	@Column(name="productId")
	String productId;
	
	@Column(name="likes")
	int likes;
	
	@Column(name="createdOn")
	Date createdOn;
	
	@Column(name="updatedOn")
	Date updatedOn;
	
	public ReviewMetaData(String reviewId, String productId, int likes,
			Date createdOn, Date updatedOn) {
		super();
		this.reviewId = reviewId;
		this.productId = productId;
		this.likes = likes;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	

}
