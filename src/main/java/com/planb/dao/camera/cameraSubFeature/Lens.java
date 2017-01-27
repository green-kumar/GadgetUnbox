package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class Lens implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2043270372343583453L;
	private String digitalZoom;
	private String lensType;
	private String lensElement;
	private String lensGroup;
	private String filterSize;
	public String getDigitalZoom() {
		return digitalZoom;
	}
	public void setDigitalZoom(String digitalZoom) {
		this.digitalZoom = digitalZoom;
	}
	public String getLensType() {
		return lensType;
	}
	public void setLensType(String lensType) {
		this.lensType = lensType;
	}
	public String getLensElement() {
		return lensElement;
	}
	public void setLensElement(String lensElement) {
		this.lensElement = lensElement;
	}
	public String getLensGroup() {
		return lensGroup;
	}
	public void setLensGroup(String lensGroup) {
		this.lensGroup = lensGroup;
	}
	public String getFilterSize() {
		return filterSize;
	}
	public void setFilterSize(String filterSize) {
		this.filterSize = filterSize;
	}
	@Override
	public String toString() {
		return "Lens [digitalZoom=" + digitalZoom + ", lensType=" + lensType + ", lensElement=" + lensElement
				+ ", lensGroup=" + lensGroup + ", filterSize=" + filterSize + "]";
	}
	
	
}
