package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class CameraBattery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2799503976774956755L;
	private String batteryLife;
	private String batteryDetails;
	public String getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}
	public String getBatteryDetails() {
		return batteryDetails;
	}
	public void setBatteryDetails(String batteryDetails) {
		this.batteryDetails = batteryDetails;
	}
	@Override
	public String toString() {
		return "CameraBattery [batteryLife=" + batteryLife + ", batteryDetails=" + batteryDetails + "]";
	}

}
