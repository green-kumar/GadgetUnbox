package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;

public class Accessories implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1916652581575065392L;
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
	@Override
	public String toString() {
		return "Accessories [name=" + name + ", value=" + value + ", image_url=" + image_url + "]";
	}

}
