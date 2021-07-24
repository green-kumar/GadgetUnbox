package com.planb.dao.camera.cameraSubFeature;

import java.io.Serializable;

public class VideoRecording  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2935838183301453994L;
	private String videoResolution;
	private String videoFileFormat;
	private String micType;
	public String getVideoResolution() {
		return videoResolution;
	}
	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}
	public String getVideoFileFormat() {
		return videoFileFormat;
	}
	public void setVideoFileFormat(String videoFileFormat) {
		this.videoFileFormat = videoFileFormat;
	}
	public String getMicType() {
		return micType;
	}
	public void setMicType(String micType) {
		this.micType = micType;
	}
	@Override
	public String toString() {
		return "VideoRecording [videoResolution=" + videoResolution + ", videoFileFormat=" + videoFileFormat
				+ ", micType=" + micType + "]";
	}
	

}
