package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;

public class Battery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3956438696788432295L;
	private String talkTime;
	private String batteryCapacity;
	private String batteryTechnology;
	public String getTalkTime() {
		return talkTime;
	}
	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}
	public String getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public String getBatteryTechnology() {
		return batteryTechnology;
	}
	public void setBatteryTechnology(String batteryTechnology) {
		this.batteryTechnology = batteryTechnology;
	}
	@Override
	public String toString() {
		return "Battery [talkTime=" + talkTime + ", batteryCapacity=" + batteryCapacity + ", batteryTechnology="
				+ batteryTechnology + "]";
	}
	

}
