package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Focus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3767873914733509120L;
	private String focusAdjustment;
	private String autoFocusTechnology;
	private String focusRange;
	public String getFocusAdjustment() {
		return focusAdjustment;
	}
	public void setFocusAdjustment(String focusAdjustment) {
		this.focusAdjustment = focusAdjustment;
	}
	public String getAutoFocusTechnology() {
		return autoFocusTechnology;
	}
	public void setAutoFocusTechnology(String autoFocusTechnology) {
		this.autoFocusTechnology = autoFocusTechnology;
	}
	public String getFocusRange() {
		return focusRange;
	}
	public void setFocusRange(String focusRange) {
		this.focusRange = focusRange;
	}
	@Override
	public String toString() {
		return "Focus [focusAdjustment=" + focusAdjustment + ", autoFocusTechnology=" + autoFocusTechnology
				+ ", focusRange=" + focusRange + "]";
	}
	

}
