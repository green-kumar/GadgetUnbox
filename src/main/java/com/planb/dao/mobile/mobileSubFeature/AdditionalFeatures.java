package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;

public class AdditionalFeatures implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3566188237571416900L;
	private String name;
	private String value;
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
	@Override
	public String toString() {
		return "AdditionalFeatures [name=" + name + ", value=" + value + "]";
	}

}
