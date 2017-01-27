package com.planb.dao.review.camera;

import com.planb.dao.review.ReviewDetails;

public class CameraReview extends ReviewDetails {
	private double overallRating;
	private double featureWiseRating;
	private double performanceWiseRating;
	private double designWiseRating;
	private double valueWiseRating;
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public double getFeatureWiseRating() {
		return featureWiseRating;
	}
	public void setFeatureWiseRating(double featureWiseRating) {
		this.featureWiseRating = featureWiseRating;
	}
	public double getPerformanceWiseRating() {
		return performanceWiseRating;
	}
	public void setPerformanceWiseRating(double performanceWiseRating) {
		this.performanceWiseRating = performanceWiseRating;
	}
	public double getDesignWiseRating() {
		return designWiseRating;
	}
	public void setDesignWiseRating(double designWiseRating) {
		this.designWiseRating = designWiseRating;
	}
	public double getValueWiseRating() {
		return valueWiseRating;
	}
	public void setValueWiseRating(double valueWiseRating) {
		this.valueWiseRating = valueWiseRating;
	}

}
