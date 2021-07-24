package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class Input implements Serializable {
	private String camera;
	private String speaker;
	private String Sound;
	private String opticalDrive;
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public String getSound() {
		return Sound;
	}
	public void setSound(String sound) {
		Sound = sound;
	}
	public String getOpticalDrive() {
		return opticalDrive;
	}
	public void setOpticalDrive(String opticalDrive) {
		this.opticalDrive = opticalDrive;
	}
	@Override
	public String toString() {
		return "Input [camera=" + camera + ", speaker=" + speaker + ", Sound=" + Sound + ", opticalDrive="
				+ opticalDrive + "]";
	}

}
