package com.planb.dao.mobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.planb.dao.mobile.mobileSubFeature.Accessories;
import com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures;
import com.planb.dao.mobile.mobileSubFeature.Battery;
import com.planb.dao.mobile.mobileSubFeature.Camera;
import com.planb.dao.mobile.mobileSubFeature.ConnectivityAndFeatures;
import com.planb.dao.mobile.mobileSubFeature.Display;
import com.planb.dao.mobile.mobileSubFeature.OverView;
import com.planb.dao.mobile.mobileSubFeature.Storage;

public class Mobile implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -654957161149508168L;
	/*
	 * planb own product id
	 */
	private String productId;
	/*
	 * same as mobile(but doesnt contain ram spec.):will be like below
	 * Size(Storage capacity) : 32GB
	 *  Screen Size: 9.7 inch 
	* Batter life: 10 hours
	*	Camera:10 megapixal
	*	RAM:4GB
	*	OS type:android jely bean
	 * CPU Type: Octa-Core
	 */
	private List<String> topHeadlineFeatures=new ArrayList<String>();
	
	//os name will also be append with phone
	private String name;
	
	private String brand;
	private String mainImageUrl;
	
	//smart phone or featur phone 
	private String phoneType;
	private List<String> restImageUrl=new ArrayList<String>();
	private double bestBuyPrice;
	private List<String> colors=new ArrayList<String>();
	private String processor;
	private String RAM;
	private String releasedOS;
	private String createdOn;
	private String updatedOn;
	private String category;
	/*
	 * a singnificant percentage of viewer focus on avalable accesories with product 
	 */
	private List<Accessories> accessories=new ArrayList<Accessories>();//name,value,image_url
	/*
	 * all the feature which is not listed in pojo,
	 * will fall into additionalFeature map with name,value pair
	 * 
	 */
	private List<AdditionalFeatures> additionalFeatures=new ArrayList<AdditionalFeatures>();//name,value
	private boolean isTrendingProduct;
	private OverView overView;
	private Camera camera;
	private Display display;
	private Battery battery;
	private Storage storage;
	private int userReviewCount;
	private int criticsReviewCount;
	private List<String> userReviewIds=new ArrayList<String>();
	private List<String> criticsReviewIds=new ArrayList<String>();
	private List<String> favedByUser = new ArrayList<String>();
	private ConnectivityAndFeatures connectivityAndFeatures;
	

	public Mobile() {
		overView=new OverView();
	    camera =new Camera();
	    display=new Display();
	    battery=new Battery();
	    storage=new Storage();
	    connectivityAndFeatures=new ConnectivityAndFeatures();
		
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
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	public String getReleasedOS() {
		return releasedOS;
	}
	public void setReleasedOS(String releasedOS) {
		this.releasedOS = releasedOS;
	}
	public OverView getOverView() {
		return overView;
	}
	public void setOverView(OverView overView) {
		this.overView = overView;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public Display getDisplay() {
		return display;
	}
	public void setDisplay(Display display) {
		this.display = display;
	}
	public Battery getBattery() {
		return battery;
	}
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	public ConnectivityAndFeatures getConnectivityAndFeatures() {
		return connectivityAndFeatures;
	}
	public void setConnectivityAndFeatures(ConnectivityAndFeatures connectivityAndFeatures) {
		this.connectivityAndFeatures = connectivityAndFeatures;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
	public Mobile(String productId, List<String> topHeadlineFeatures, String name, String brand, String mainImageUrl, String phoneType, List<String> restImageUrl,
			double bestBuyPrice, List<String> colors, String processor, String rAM, String releasedOS, List<Accessories> accessories, List<AdditionalFeatures> additionalFeatures,
			OverView overView, Camera camera, Display display, Battery battery, Storage storage, int userReviewCount, int criticsReviewCount, List<String> userReviewIds,
			List<String> criticsReviewIds, ConnectivityAndFeatures connectivityAndFeatures) {
		super();
		this.productId = productId;
		this.topHeadlineFeatures = topHeadlineFeatures;
		this.name = name;
		this.brand = brand;
		this.mainImageUrl = mainImageUrl;
		this.phoneType = phoneType;
		this.restImageUrl = restImageUrl;
		this.bestBuyPrice = bestBuyPrice;
		this.colors = colors;
		this.processor = processor;
		RAM = rAM;
		this.releasedOS = releasedOS;
		this.accessories = accessories;
		this.additionalFeatures = additionalFeatures;
		this.overView = overView;
		this.camera = camera;
		this.display = display;
		this.battery = battery;
		this.storage = storage;
		this.userReviewCount = userReviewCount;
		this.criticsReviewCount = criticsReviewCount;
		this.userReviewIds = userReviewIds;
		this.criticsReviewIds = criticsReviewIds;
		this.connectivityAndFeatures = connectivityAndFeatures;
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
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public boolean isTrendingProduct() {
		return isTrendingProduct;
	}
	public void setTrendingProduct(boolean isTrendingProduct) {
		this.isTrendingProduct = isTrendingProduct;
	}


	public List<String> getFavedByUser() {
		return favedByUser;
	}


	public void setFavedByUser(List<String> favedByUser) {
		this.favedByUser = favedByUser;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Mobile [productId=" + productId + ", topHeadlineFeatures=" + topHeadlineFeatures + ", name=" + name
				+ ", brand=" + brand + ", mainImageUrl=" + mainImageUrl + ", phoneType=" + phoneType + ", restImageUrl="
				+ restImageUrl + ", bestBuyPrice=" + bestBuyPrice + ", colors=" + colors + ", processor=" + processor
				+ ", RAM=" + RAM + ", releasedOS=" + releasedOS + ", createdOn=" + createdOn + ", updatedOn="
				+ updatedOn + ", category=" + category + ", accessories=" + accessories + ", additionalFeatures="
				+ additionalFeatures + ", isTrendingProduct=" + isTrendingProduct + ", overView=" + overView
				+ ", camera=" + camera + ", display=" + display + ", battery=" + battery + ", storage=" + storage
				+ ", userReviewCount=" + userReviewCount + ", criticsReviewCount=" + criticsReviewCount
				+ ", userReviewIds=" + userReviewIds + ", criticsReviewIds=" + criticsReviewIds + ", favedByUser="
				+ favedByUser + ", connectivityAndFeatures=" + connectivityAndFeatures + "]";
	}


	
}
