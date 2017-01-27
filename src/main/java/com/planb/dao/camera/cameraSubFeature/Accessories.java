package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Accessories implements Serializable {
	private String name;
	private String value;
	private String image_url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
