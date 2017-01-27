package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Overview implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5768667441181788868L;
	private String description;
	private String releaseDate;
	private String marketStaus;//released or available in market or not
	private String CameraWeight;
	private String dimension;
	private String bodyMaterial;
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
	public String getCameraWeight() {
		return CameraWeight;
	}
	public void setCameraWeight(String cameraWeight) {
		CameraWeight = cameraWeight;
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
	@Override
	public String toString() {
		return "Overview [description=" + description + ", releaseDate=" + releaseDate + ", marketStaus=" + marketStaus
				+ ", CameraWeight=" + CameraWeight + ", dimension=" + dimension + ", bodyMaterial=" + bodyMaterial
				+ "]";
	}
	

}
