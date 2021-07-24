package com.planb.dao.camera;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.planb.dao.camera.cameraSubFeature.Accessories;
import com.planb.dao.camera.cameraSubFeature.AdditionalFeatures;
import com.planb.dao.camera.cameraSubFeature.CameraBattery;
import com.planb.dao.camera.cameraSubFeature.CameraStorage;
import com.planb.dao.camera.cameraSubFeature.Exposure;
import com.planb.dao.camera.cameraSubFeature.Focus;
import com.planb.dao.camera.cameraSubFeature.Lens;
import com.planb.dao.camera.cameraSubFeature.Overview;
import com.planb.dao.camera.cameraSubFeature.Sensor;
import com.planb.dao.camera.cameraSubFeature.VideoRecording;



public class camera implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3719756662157498488L;


	/*
	 * planb own productId
	 */
	private String productId;
	
	
	//headline feature as mentioned on samsung site for galaxy mobile.
	private List<String> topHeadlineFeatures;
	
	private String name;
	/*
	 * camera type would be digital compact(point and shoot),digital slr(dslr),
	 * mirrorless,camlorders
	 */
	private String cameraType;
	private String mainImageUrl;
	private List<String> restImageUrl;
	private double bestBuyPrice;
	private List<String> colors;
	/*
	 * view finder types are optical, digital
	 * 
	 */
	private String viewFinderType;
	private String brand;
	private String displaySize;
	/*
	 * name of accessories and image url of corresponding accessories 
	 * 
	 */
	/*
	 * a singnificant percentage of viewer focus on avalable accesories with product 
	 */
	private List<Accessories> accessories=new ArrayList<Accessories>();//name,value,image_url
	
	/*
	 * all the feature which is not listed in pojo,
	 * will fall into additionalFeature map with name,value pair
	 */
	
	/*
	 * all the feature which is not listed in pojo,
	 * will fall into additionalFeature map with name,value pair
	 * 
	 */
	private List<AdditionalFeatures> additionalFeatures=new ArrayList<AdditionalFeatures>();//name,value
	
	
	
	private String totalMegaPixal;
	private String effectiveMegaPixal;
	
	private int userReviewCount;
	private int criticsReviewCount;
	private List<String> userReviewIds=new ArrayList<String>();
	private List<String> criticsReviewIds=new ArrayList<String>();
	private List<String> favedByUsers=new ArrayList<String>();
	private String updatedOn;
	private String createdOn;
	private boolean isTrendingProduct;
	private Overview overView;
	private Sensor sensor;
	private VideoRecording videoRecording;
    private Exposure exposure;
    private Lens lens;
    private Focus focus;
    private CameraBattery cameraBattery;
    private CameraStorage cameraStorage;
    private String category;
    
    public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public List<String> getTopHeadlineFeatures() {
		return topHeadlineFeatures;
	}
	public void setTopHeadlineFeatures(List<String> topHeadlineFeatures) {
		this.topHeadlineFeatures = topHeadlineFeatures;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCameraType() {
		return cameraType;
	}
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	public List<String> getRestImageUrl() {
		return restImageUrl;
	}
	public void setRestImageUrl(List<String> restImageUrl) {
		this.restImageUrl = restImageUrl;
	}
	public double getBestBuyPrice() {
		return bestBuyPrice;
	}
	public void setBestBuyPrice(double bestBuyPrice) {
		this.bestBuyPrice = bestBuyPrice;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public String getViewFinderType() {
		return viewFinderType;
	}
	public void setViewFinderType(String viewFinderType) {
		this.viewFinderType = viewFinderType;
	}
	public String getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
	
	public String getTotalMegaPixal() {
		return totalMegaPixal;
	}
	public void setTotalMegaPixal(String totalMegaPixal) {
		this.totalMegaPixal = totalMegaPixal;
	}
	public String getEffectiveMegaPixal() {
		return effectiveMegaPixal;
	}
	public void setEffectiveMegaPixal(String effectiveMegaPixal) {
		this.effectiveMegaPixal = effectiveMegaPixal;
	}
	public Overview getOverView() {
		return overView;
	}
	public void setOverView(Overview overView) {
		this.overView = overView;
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public VideoRecording getVideoRecording() {
		return videoRecording;
	}
	public void setVideoRecording(VideoRecording videoRecording) {
		this.videoRecording = videoRecording;
	}
	public Exposure getExposure() {
		return exposure;
	}
	public void setExposure(Exposure exposure) {
		this.exposure = exposure;
	}
	public Lens getLens() {
		return lens;
	}
	public void setLens(Lens lens) {
		this.lens = lens;
	}
	public Focus getFocus() {
		return focus;
	}
	public void setFocus(Focus focus) {
		this.focus = focus;
	}
	public CameraBattery getCameraBattery() {
		return cameraBattery;
	}
	public void setCameraBattery(CameraBattery cameraBattery) {
		this.cameraBattery = cameraBattery;
	}
	
	public List<Accessories> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<Accessories> accessories) {
		this.accessories = accessories;
	}
	public List<AdditionalFeatures> getAdditionalFeatures() {
		return additionalFeatures;
	}
	public void setAdditionalFeatures(List<AdditionalFeatures> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}
	public CameraStorage getCameraStorage() {
		return cameraStorage;
	}
	public void setCameraStorage(CameraStorage cameraStorage) {
		this.cameraStorage = cameraStorage;
	}
	
	
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getUserReviewCount() {
		return userReviewCount;
	}
	public void setUserReviewCount(int userReviewCount) {
		this.userReviewCount = userReviewCount;
	}
	public int getCriticsReviewCount() {
		return criticsReviewCount;
	}
	public void setCriticsReviewCount(int criticsReviewCount) {
		this.criticsReviewCount = criticsReviewCount;
	}
	public List<String> getUserReviewIds() {
		return userReviewIds;
	}
	public void setUserReviewIds(List<String> userReviewIds) {
		this.userReviewIds = userReviewIds;
	}
	public List<String> getCriticsReviewIds() {
		return criticsReviewIds;
	}
	public void setCriticsReviewIds(List<String> criticsReviewIds) {
		this.criticsReviewIds = criticsReviewIds;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	@Override
	public String toString() {
		return "camera [productId=" + productId + ", topHeadlineFeatures=" + topHeadlineFeatures + ", name=" + name
				+ ", cameraType=" + cameraType + ", mainImageUrl=" + mainImageUrl + ", restImageUrl=" + restImageUrl
				+ ", bestBuyPrice=" + bestBuyPrice + ", colors=" + colors + ", viewFinderType=" + viewFinderType
				+ ", brand=" + brand + ", displaySize=" + displaySize + ", accessories=" + accessories
				+ ", additionalFeatures=" + additionalFeatures + ", totalMegaPixal=" + totalMegaPixal
				+ ", effectiveMegaPixal=" + effectiveMegaPixal + ", userReviewCount=" + userReviewCount
				+ ", criticsReviewCount=" + criticsReviewCount + ", userReviewIds=" + userReviewIds
				+ ", criticsReviewIds=" + criticsReviewIds + ", favedByUsers=" + favedByUsers + ", updatedOn="
				+ updatedOn + ", createdOn=" + createdOn + ", isTrendingProduct=" + isTrendingProduct + ", overView="
				+ overView + ", sensor=" + sensor + ", videoRecording=" + videoRecording + ", exposure=" + exposure
				+ ", lens=" + lens + ", focus=" + focus + ", cameraBattery=" + cameraBattery + ", cameraStorage="
				+ cameraStorage + ", category=" + category + "]";
	}
	public boolean isTrendingProduct() {
		return isTrendingProduct;
	}
	public void setTrendingProduct(boolean isTrendingProduct) {
		this.isTrendingProduct = isTrendingProduct;
	}
	public List<String> getFavedByUsers() {
		return favedByUsers;
	}
	public void setFavedByUsers(List<String> favedByUsers) {
		this.favedByUsers = favedByUsers;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
    
	

}
