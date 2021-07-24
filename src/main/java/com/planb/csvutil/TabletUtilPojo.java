package com.planb.csvutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TabletUtilPojo {

   String size;
   String tabletWeight;
   
   
   
    String topHeadlineFeatures;
	String name;
	String brand;
	String mainImageUrl;
    //String phoneType;
	String restImageUrl;
	String bestBuyPrice;
	String colors;
	String processor;
	String releasedOS;
	String accessories;
	String additionalFeatures;
	String ram;
	
	
	String	overViewDescription;
	String	overViewReleaseDate;
	String	overViewMarketStaus;
	String	overViewPhoneWeight;
	String	overViewDimension;
	String	overViewBodyMaterial;
	String	overViewReleaseOS;
	String cameraFrontcameraInMegaPixalWithFeatures;
	String cameraRearCameraInMegaPixalWithFeatures;
	String cameraVideoResolution;
	String cameraOtherCameraFeatures;
	String displayScreenSize;
	String displayScreenResolution;
	String displayScreenMaterial;
	String displayDisplayTechnology;
	String displayDtherDisplayFeatures;
	String batteryTalkTime;
	String batteryBatteryCapacity;
	String batteryBatteryTechnology;
	String InternalStorage;
	String ExpendableStorageFeatures;
	String  connectivityAndFeaturesWireLessConnectivityFeatures;
	String  connectivityAndFeaturesDualSimSupportabilty;
	String  connectivityAndFeaturesBluetoothVersion;
	String  connectivityAndFeaturesSimCardSize;
	String  connectivityAndFeaturesChargingOptions;
	String  connectivityAndFeaturesSensors;
	String  connectivityAndFeaturesothersConnectivityFeatures;
    String  connectivityAndFeaturesNetworkFeatures;
    
	public String getTopHeadlineFeatures() {
		return topHeadlineFeatures;
	}
	public void setTopHeadlineFeatures(String topHeadlineFeatures) {
		this.topHeadlineFeatures = topHeadlineFeatures;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	
	public String getRestImageUrl() {
		return restImageUrl;
	}
	public void setRestImageUrl(String restImageUrl) {
		this.restImageUrl = restImageUrl;
	}
	public String getBestBuyPrice() {
		return bestBuyPrice;
	}
	public void setBestBuyPrice(String bestBuyPrice) {
		this.bestBuyPrice = bestBuyPrice;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getReleasedOS() {
		return releasedOS;
	}
	public void setReleasedOS(String releasedOS) {
		this.releasedOS = releasedOS;
	}
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public String getAdditionalFeatures() {
		return additionalFeatures;
	}
	public void setAdditionalFeatures(String additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getOverViewDescription() {
		return overViewDescription;
	}
	public void setOverViewDescription(String overViewDescription) {
		this.overViewDescription = overViewDescription;
	}
	public String getOverViewReleaseDate() {
		return overViewReleaseDate;
	}
	public void setOverViewReleaseDate(String overViewReleaseDate) {
		this.overViewReleaseDate = overViewReleaseDate;
	}
	public String getOverViewMarketStaus() {
		return overViewMarketStaus;
	}
	public void setOverViewMarketStaus(String overViewMarketStaus) {
		this.overViewMarketStaus = overViewMarketStaus;
	}
	public String getOverViewPhoneWeight() {
		return overViewPhoneWeight;
	}
	public void setOverViewPhoneWeight(String overViewPhoneWeight) {
		this.overViewPhoneWeight = overViewPhoneWeight;
	}
	public String getOverViewDimension() {
		return overViewDimension;
	}
	public void setOverViewDimension(String overViewDimension) {
		this.overViewDimension = overViewDimension;
	}
	public String getOverViewBodyMaterial() {
		return overViewBodyMaterial;
	}
	public void setOverViewBodyMaterial(String overViewBodyMaterial) {
		this.overViewBodyMaterial = overViewBodyMaterial;
	}
	public String getOverViewReleaseOS() {
		return overViewReleaseOS;
	}
	public void setOverViewReleaseOS(String overViewReleaseOS) {
		this.overViewReleaseOS = overViewReleaseOS;
	}
	public String getCameraFrontcameraInMegaPixalWithFeatures() {
		return cameraFrontcameraInMegaPixalWithFeatures;
	}
	public void setCameraFrontcameraInMegaPixalWithFeatures(String cameraFrontcameraInMegaPixalWithFeatures) {
		this.cameraFrontcameraInMegaPixalWithFeatures = cameraFrontcameraInMegaPixalWithFeatures;
	}
	public String getCameraRearCameraInMegaPixalWithFeatures() {
		return cameraRearCameraInMegaPixalWithFeatures;
	}
	public void setCameraRearCameraInMegaPixalWithFeatures(String cameraRearCameraInMegaPixalWithFeatures) {
		this.cameraRearCameraInMegaPixalWithFeatures = cameraRearCameraInMegaPixalWithFeatures;
	}
	public String getCameraVideoResolution() {
		return cameraVideoResolution;
	}
	public void setCameraVideoResolution(String cameraVideoResolution) {
		this.cameraVideoResolution = cameraVideoResolution;
	}
	public String getCameraOtherCameraFeatures() {
		return cameraOtherCameraFeatures;
	}
	public void setCameraOtherCameraFeatures(String cameraOtherCameraFeatures) {
		this.cameraOtherCameraFeatures = cameraOtherCameraFeatures;
	}
	public String getDisplayScreenSize() {
		return displayScreenSize;
	}
	public void setDisplayScreenSize(String displayScreenSize) {
		this.displayScreenSize = displayScreenSize;
	}
	public String getDisplayScreenResolution() {
		return displayScreenResolution;
	}
	public void setDisplayScreenResolution(String displayScreenResolution) {
		this.displayScreenResolution = displayScreenResolution;
	}
	public String getDisplayScreenMaterial() {
		return displayScreenMaterial;
	}
	public void setDisplayScreenMaterial(String displayScreenMaterial) {
		this.displayScreenMaterial = displayScreenMaterial;
	}
	public String getDisplayDisplayTechnology() {
		return displayDisplayTechnology;
	}
	public void setDisplayDisplayTechnology(String displayDisplayTechnology) {
		this.displayDisplayTechnology = displayDisplayTechnology;
	}
	public String getDisplayDtherDisplayFeatures() {
		return displayDtherDisplayFeatures;
	}
	public void setDisplayDtherDisplayFeatures(String displayDtherDisplayFeatures) {
		this.displayDtherDisplayFeatures = displayDtherDisplayFeatures;
	}
	public String getBatteryTalkTime() {
		return batteryTalkTime;
	}
	public void setBatteryTalkTime(String batteryTalkTime) {
		this.batteryTalkTime = batteryTalkTime;
	}
	public String getBatteryBatteryCapacity() {
		return batteryBatteryCapacity;
	}
	public void setBatteryBatteryCapacity(String batteryBatteryCapacity) {
		this.batteryBatteryCapacity = batteryBatteryCapacity;
	}
	public String getBatteryBatteryTechnology() {
		return batteryBatteryTechnology;
	}
	public void setBatteryBatteryTechnology(String batteryBatteryTechnology) {
		this.batteryBatteryTechnology = batteryBatteryTechnology;
	}
	public String getInternalStorage() {
		return InternalStorage;
	}
	public void setInternalStorage(String internalStorage) {
		InternalStorage = internalStorage;
	}
	public String getExpendableStorageFeatures() {
		return ExpendableStorageFeatures;
	}
	public void setExpendableStorageFeatures(String expendableStorageFeatures) {
		ExpendableStorageFeatures = expendableStorageFeatures;
	}
	public String getConnectivityAndFeaturesWireLessConnectivityFeatures() {
		return connectivityAndFeaturesWireLessConnectivityFeatures;
	}
	public void setConnectivityAndFeaturesWireLessConnectivityFeatures(String connectivityAndFeaturesWireLessConnectivityFeatures) {
		this.connectivityAndFeaturesWireLessConnectivityFeatures = connectivityAndFeaturesWireLessConnectivityFeatures;
	}
	public String getConnectivityAndFeaturesDualSimSupportabilty() {
		return connectivityAndFeaturesDualSimSupportabilty;
	}
	public void setConnectivityAndFeaturesDualSimSupportabilty(String connectivityAndFeaturesDualSimSupportabilty) {
		this.connectivityAndFeaturesDualSimSupportabilty = connectivityAndFeaturesDualSimSupportabilty;
	}
	public String getConnectivityAndFeaturesBluetoothVersion() {
		return connectivityAndFeaturesBluetoothVersion;
	}
	public void setConnectivityAndFeaturesBluetoothVersion(String connectivityAndFeaturesBluetoothVersion) {
		this.connectivityAndFeaturesBluetoothVersion = connectivityAndFeaturesBluetoothVersion;
	}
	public String getConnectivityAndFeaturesSimCardSize() {
		return connectivityAndFeaturesSimCardSize;
	}
	public void setConnectivityAndFeaturesSimCardSize(String connectivityAndFeaturesSimCardSize) {
		this.connectivityAndFeaturesSimCardSize = connectivityAndFeaturesSimCardSize;
	}
	public String getConnectivityAndFeaturesChargingOptions() {
		return connectivityAndFeaturesChargingOptions;
	}
	public void setConnectivityAndFeaturesChargingOptions(String connectivityAndFeaturesChargingOptions) {
		this.connectivityAndFeaturesChargingOptions = connectivityAndFeaturesChargingOptions;
	}
	public String getConnectivityAndFeaturesSensors() {
		return connectivityAndFeaturesSensors;
	}
	public void setConnectivityAndFeaturesSensors(String connectivityAndFeaturesSensors) {
		this.connectivityAndFeaturesSensors = connectivityAndFeaturesSensors;
	}
	public String getConnectivityAndFeaturesothersConnectivityFeatures() {
		return connectivityAndFeaturesothersConnectivityFeatures;
	}
	public void setConnectivityAndFeaturesothersConnectivityFeatures(String connectivityAndFeaturesothersConnectivityFeatures) {
		this.connectivityAndFeaturesothersConnectivityFeatures = connectivityAndFeaturesothersConnectivityFeatures;
	}
	public String getConnectivityAndFeaturesNetworkFeatures() {
		return connectivityAndFeaturesNetworkFeatures;
	}
	public void setConnectivityAndFeaturesNetworkFeatures(String connectivityAndFeaturesNetworkFeatures) {
		this.connectivityAndFeaturesNetworkFeatures = connectivityAndFeaturesNetworkFeatures;
	}
	
    
public static void main(String[] args) throws IOException {
	BufferedReader br=new BufferedReader(new FileReader("C://Users//mmt5185//Desktop//gk.txt"));
	String line;
	while((line=br.readLine())!=null){
		System.out.print(line);
		System.out.print(",");
		
	}
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public String getTabletWeight() {
	return tabletWeight;
}
public void setTabletWeight(String tabletWeight) {
	this.tabletWeight = tabletWeight;
}
@Override
public String toString() {
	return "TabletUtilPojo [size=" + size + ", tabletWeight=" + tabletWeight + ", topHeadlineFeatures=" + topHeadlineFeatures + ", name=" + name + ", brand=" + brand
			+ ", mainImageUrl=" + mainImageUrl + ", restImageUrl=" + restImageUrl + ", bestBuyPrice=" + bestBuyPrice + ", colors=" + colors + ", processor=" + processor
			+ ", releasedOS=" + releasedOS + ", accessories=" + accessories + ", additionalFeatures=" + additionalFeatures + ", ram=" + ram + ", overViewDescription="
			+ overViewDescription + ", overViewReleaseDate=" + overViewReleaseDate + ", overViewMarketStaus=" + overViewMarketStaus + ", overViewPhoneWeight="
			+ overViewPhoneWeight + ", overViewDimension=" + overViewDimension + ", overViewBodyMaterial=" + overViewBodyMaterial + ", overViewReleaseOS=" + overViewReleaseOS
			+ ", cameraFrontcameraInMegaPixalWithFeatures=" + cameraFrontcameraInMegaPixalWithFeatures + ", cameraRearCameraInMegaPixalWithFeatures="
			+ cameraRearCameraInMegaPixalWithFeatures + ", cameraVideoResolution=" + cameraVideoResolution + ", cameraOtherCameraFeatures=" + cameraOtherCameraFeatures
			+ ", displayScreenSize=" + displayScreenSize + ", displayScreenResolution=" + displayScreenResolution + ", displayScreenMaterial=" + displayScreenMaterial
			+ ", displayDisplayTechnology=" + displayDisplayTechnology + ", displayDtherDisplayFeatures=" + displayDtherDisplayFeatures + ", batteryTalkTime=" + batteryTalkTime
			+ ", batteryBatteryCapacity=" + batteryBatteryCapacity + ", batteryBatteryTechnology=" + batteryBatteryTechnology + ", InternalStorage=" + InternalStorage
			+ ", ExpendableStorageFeatures=" + ExpendableStorageFeatures + ", connectivityAndFeaturesWireLessConnectivityFeatures="
			+ connectivityAndFeaturesWireLessConnectivityFeatures + ", connectivityAndFeaturesDualSimSupportabilty=" + connectivityAndFeaturesDualSimSupportabilty
			+ ", connectivityAndFeaturesBluetoothVersion=" + connectivityAndFeaturesBluetoothVersion + ", connectivityAndFeaturesSimCardSize=" + connectivityAndFeaturesSimCardSize
			+ ", connectivityAndFeaturesChargingOptions=" + connectivityAndFeaturesChargingOptions + ", connectivityAndFeaturesSensors=" + connectivityAndFeaturesSensors
			+ ", connectivityAndFeaturesothersConnectivityFeatures=" + connectivityAndFeaturesothersConnectivityFeatures + ", connectivityAndFeaturesNetworkFeatures="
			+ connectivityAndFeaturesNetworkFeatures + "]";
}



}
