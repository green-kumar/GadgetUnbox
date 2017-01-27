package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Sensor implements Serializable {
	private String imageSensorFormat;
	private String sensorTyp;
	private String sensorSize;
	public String getImageSensorFormat() {
		return imageSensorFormat;
	}
	public void setImageSensorFormat(String imageSensorFormat) {
		this.imageSensorFormat = imageSensorFormat;
	}
	public String getSensorTyp() {
		return sensorTyp;
	}
	public void setSensorTyp(String sensorTyp) {
		this.sensorTyp = sensorTyp;
	}
	public String getSensorSize() {
		return sensorSize;
	}
	public void setSensorSize(String sensorSize) {
		this.sensorSize = sensorSize;
	}
	@Override
	public String toString() {
		return "Sensor [imageSensorFormat=" + imageSensorFormat + ", sensorTyp=" + sensorTyp + ", sensorSize="
				+ sensorSize + "]";
	} 

}
