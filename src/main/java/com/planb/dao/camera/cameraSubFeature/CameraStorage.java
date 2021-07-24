package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class CameraStorage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5621461680309004312L;
	private String fileFormat;
	private String storageMedia;
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getStorageMedia() {
		return storageMedia;
	}
	public void setStorageMedia(String storageMedia) {
		this.storageMedia = storageMedia;
	}
	@Override
	public String toString() {
		return "CameraStorage [fileFormat=" + fileFormat + ", storageMedia=" + storageMedia + "]";
	}
	

}
