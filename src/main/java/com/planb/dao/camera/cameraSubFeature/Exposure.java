package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Exposure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695832169843787372L;
	private String exposureCompensation;
	private String autoExposureBracketing;
	private String exposureMating;
	private String exposureModes;
	public String getExposureCompensation() {
		return exposureCompensation;
	}
	public void setExposureCompensation(String exposureCompensation) {
		this.exposureCompensation = exposureCompensation;
	}
	public String getAutoExposureBracketing() {
		return autoExposureBracketing;
	}
	public void setAutoExposureBracketing(String autoExposureBracketing) {
		this.autoExposureBracketing = autoExposureBracketing;
	}
	public String getExposureMating() {
		return exposureMating;
	}
	public void setExposureMating(String exposureMating) {
		this.exposureMating = exposureMating;
	}
	public String getExposureModes() {
		return exposureModes;
	}
	public void setExposureModes(String exposureModes) {
		this.exposureModes = exposureModes;
	}
	@Override
	public String toString() {
		return "Exposure [exposureCompensation=" + exposureCompensation + ", autoExposureBracketing="
				+ autoExposureBracketing + ", exposureMating=" + exposureMating + ", exposureModes=" + exposureModes
				+ "]";
	} 

}
