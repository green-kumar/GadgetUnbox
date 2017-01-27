package com.planb.csvutil;

public class ReviewUtilPojo {
     
	private String reviewId;
	private String rating;
	private String reviewHeadline;
	private String reviewContent;
	//will be helpful in deciding pros or cons or ranking algo.....
	private String reviewDate;
	public ReviewUtilPojo(String reviewId, String rating, String reviewHeadline, String reviewContent,
			String reviewDate) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.reviewHeadline = reviewHeadline;
		this.reviewContent = reviewContent;
		this.reviewDate = reviewDate;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getReviewHeadline() {
		return reviewHeadline;
	}
	public void setReviewHeadline(String reviewHeadline) {
		this.reviewHeadline = reviewHeadline;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	@Override
	public String toString() {
		return "ReviewUtilPojo [reviewId=" + reviewId + ", rating=" + rating + ", reviewHeadline=" + reviewHeadline
				+ ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((reviewContent == null) ? 0 : reviewContent.hashCode());
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + ((reviewHeadline == null) ? 0 : reviewHeadline.hashCode());
		result = prime * result + ((reviewId == null) ? 0 : reviewId.hashCode());
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
		ReviewUtilPojo other = (ReviewUtilPojo) obj;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (reviewContent == null) {
			if (other.reviewContent != null)
				return false;
		} else if (!reviewContent.equals(other.reviewContent))
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (reviewHeadline == null) {
			if (other.reviewHeadline != null)
				return false;
		} else if (!reviewHeadline.equals(other.reviewHeadline))
			return false;
		if (reviewId == null) {
			if (other.reviewId != null)
				return false;
		} else if (!reviewId.equals(other.reviewId))
			return false;
		return true;
	}
	public ReviewUtilPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
