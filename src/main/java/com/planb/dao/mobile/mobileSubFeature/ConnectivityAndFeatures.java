package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;
import java.util.List;

public class ConnectivityAndFeatures implements Serializable{

	private List<String> NetworkFeatures;	//GSM , HSPA , LTE
	private List<String> wireLessConnectivityFeatures;
	/*Infrared
	Mobile Hotspot Tethering
	4G
	Bluetooth
	NFC
	WiFi
	3G*/
	private String dualSimSupportabilty;//supported or not supported
	private String bluetoothVersion;
	private String simCardSize;//nanoSim or microSim
	private List<String> chargingOptions;//like microUSB or wireless
	private List<String> sensors;
	/*
	 * Accelerometer
		Barometer
		Digital Compass
		Fingerprint ID
		GPS
		Gyroscope
		Heart Rate Monitor
	 */
	private List<String> othersConnectivityFeatures;
	public List<String> getNetworkFeatures() {
		return NetworkFeatures;
	}
	public void setNetworkFeatures(List<String> networkFeatures) {
		NetworkFeatures = networkFeatures;
	}
	public List<String> getWireLessConnectivityFeatures() {
		return wireLessConnectivityFeatures;
	}
	public void setWireLessConnectivityFeatures(List<String> wireLessConnectivityFeatures) {
		this.wireLessConnectivityFeatures = wireLessConnectivityFeatures;
	}
	public String getDualSimSupportabilty() {
		return dualSimSupportabilty;
	}
	public void setDualSimSupportabilty(String dualSimSupportabilty) {
		this.dualSimSupportabilty = dualSimSupportabilty;
	}
	public String getBluetoothVersion() {
		return bluetoothVersion;
	}
	public void setBluetoothVersion(String bluetoothVersion) {
		this.bluetoothVersion = bluetoothVersion;
	}
	public String getSimCardSize() {
		return simCardSize;
	}
	public void setSimCardSize(String simCardSize) {
		this.simCardSize = simCardSize;
	}
	public List<String> getChargingOptions() {
		return chargingOptions;
	}
	public void setChargingOptions(List<String> chargingOptions) {
		this.chargingOptions = chargingOptions;
	}
	public List<String> getSensors() {
		return sensors;
	}
	public void setSensors(List<String> sensors) {
		this.sensors = sensors;
	}
	public List<String> getOthersConnectivityFeatures() {
		return othersConnectivityFeatures;
	}
	public void setOthersConnectivityFeatures(List<String> othersConnectivityFeatures) {
		this.othersConnectivityFeatures = othersConnectivityFeatures;
	}
	@Override
	public String toString() {
		return "ConnectivityAndFeatures [NetworkFeatures=" + NetworkFeatures + ", wireLessConnectivityFeatures="
				+ wireLessConnectivityFeatures + ", dualSimSupportabilty=" + dualSimSupportabilty
				+ ", bluetoothVersion=" + bluetoothVersion + ", simCardSize=" + simCardSize + ", chargingOptions="
				+ chargingOptions + ", sensors=" + sensors + ", othersConnectivityFeatures="
				+ othersConnectivityFeatures + "]";
	}
	
}
