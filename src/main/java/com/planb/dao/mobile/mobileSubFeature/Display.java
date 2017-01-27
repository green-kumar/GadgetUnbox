package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;
import java.util.List;

public class Display implements Serializable {
	private String screenSize;
	private String screenResolution;
	private String screenMaterial;
	private String displayTechnology;
	private List<String> otherDisplayFeatures;
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	public String getScreenResolution() {
		return screenResolution;
	}
	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}
	public String getScreenMaterial() {
		return screenMaterial;
	}
	public void setScreenMaterial(String screenMaterial) {
		this.screenMaterial = screenMaterial;
	}
	public String getDisplayTechnology() {
		return displayTechnology;
	}
	public void setDisplayTechnology(String displayTechnology) {
		this.displayTechnology = displayTechnology;
	}
	public List<String> getOtherDisplayFeatures() {
		return otherDisplayFeatures;
	}
	public void setOtherDisplayFeatures(List<String> otherDisplayFeatures) {
		this.otherDisplayFeatures = otherDisplayFeatures;
	}
	@Override
	public String toString() {
		return "Display [screenSize=" + screenSize + ", screenResolution=" + screenResolution + ", screenMaterial="
				+ screenMaterial + ", displayTechnology=" + displayTechnology + ", otherDisplayFeatures="
				+ otherDisplayFeatures + "]";
	}

}
