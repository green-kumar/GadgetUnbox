package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class LaptopDisplay implements Serializable {

	private String displaySize;
	private String displayType;
	private String pixalDensity;
	private String resolution;
	public String getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getPixalDensity() {
		return pixalDensity;
	}
	public void setPixalDensity(String pixalDensity) {
		this.pixalDensity = pixalDensity;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaptopDisplay [displaySize=");
		builder.append(displaySize);
		builder.append(", displayType=");
		builder.append(displayType);
		builder.append(", pixalDensity=");
		builder.append(pixalDensity);
		builder.append(", resolution=");
		builder.append(resolution);
		builder.append("]");
		return builder.toString();
	}
	
}
