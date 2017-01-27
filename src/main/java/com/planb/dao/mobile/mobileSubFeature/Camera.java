package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;
import java.util.List;

public class Camera implements Serializable {
	private  List<String> frontcameraInMegaPixalWithFeatures;//for ex 13 MP, f/2.2, phase detection autofocus, dual-LED (dual tone) flashif not->NA
	private List<String> rearCameraInMegaPixalWithFeatures;
	private String videoResolution;
	private List<String> otherCameraFeatures;//Geo-tagging, touch focus, face detection, HDR, panorama
	public List<String> getFrontcameraInMegaPixalWithFeatures() {
		return frontcameraInMegaPixalWithFeatures;
	}
	public void setFrontcameraInMegaPixalWithFeatures(List<String> frontcameraInMegaPixalWithFeatures) {
		this.frontcameraInMegaPixalWithFeatures = frontcameraInMegaPixalWithFeatures;
	}
	public List<String> getRearCameraInMegaPixalWithFeatures() {
		return rearCameraInMegaPixalWithFeatures;
	}
	public void setRearCameraInMegaPixalWithFeatures(List<String> rearCameraInMegaPixalWithFeatures) {
		this.rearCameraInMegaPixalWithFeatures = rearCameraInMegaPixalWithFeatures;
	}
	public String getVideoResolution() {
		return videoResolution;
	}
	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}
	public List<String> getOtherCameraFeatures() {
		return otherCameraFeatures;
	}
	public void setOtherCameraFeatures(List<String> otherCameraFeatures) {
		this.otherCameraFeatures = otherCameraFeatures;
	}
	@Override
	public String toString() {
		return "Camera [frontcameraInMegaPixalWithFeatures=" + frontcameraInMegaPixalWithFeatures
				+ ", rearCameraInMegaPixalWithFeatures=" + rearCameraInMegaPixalWithFeatures + ", videoResolution="
				+ videoResolution + ", otherCameraFeatures=" + otherCameraFeatures + "]";
	}

}
