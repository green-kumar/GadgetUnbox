package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;

public class OverView implements Serializable{
	private String description;
	private String releaseDate;
	private String marketStaus;//released or available in market or not
	private String phoneWeight;
	private String dimension;
	private String bodyMaterial;
	
	public String getPhoneWeight() {
		return phoneWeight;
	}
	public void setPhoneWeight(String phoneWeight) {
		this.phoneWeight = phoneWeight;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getBodyMaterial() {
		return bodyMaterial;
	}
	public void setBodyMaterial(String bodyMaterial) {
		this.bodyMaterial = bodyMaterial;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getMarketStaus() {
		return marketStaus;
	}
	public void setMarketStaus(String marketStaus) {
		this.marketStaus = marketStaus;
	}
	public String getReleaseOS() {
		return releaseOS;
	}
	public void setReleaseOS(String releaseOS) {
		this.releaseOS = releaseOS;
	}
	private String releaseOS;

	@Override
	public String toString() {
		return "OverView [description=" + description + ", releaseDate=" + releaseDate + ", marketStaus=" + marketStaus
				+ ", phoneWeight=" + phoneWeight + ", dimension=" + dimension + ", bodyMaterial=" + bodyMaterial
				+ ", releaseOS=" + releaseOS + "]";
	}

}
