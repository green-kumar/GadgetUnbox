package com.planb.dao.tablet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.planb.dao.mobile.mobileSubFeature.Accessories;
import com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures;
import com.planb.dao.mobile.mobileSubFeature.Battery;
import com.planb.dao.mobile.mobileSubFeature.Camera;
import com.planb.dao.mobile.mobileSubFeature.ConnectivityAndFeatures;
import com.planb.dao.mobile.mobileSubFeature.Display;
import com.planb.dao.mobile.mobileSubFeature.OverView;
import com.planb.dao.mobile.mobileSubFeature.Storage;

public class Tablet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7752808382320663282L;
	/*
	 * planb own product id
	 */
	private String productId;
	/* same as storage capacity ex:16GB,32GB,64GB---->an important 
	 * features(eye catching feature) for any tablet 
	 */
	private String size;
	/*
	 * Samsung Galaxy Note 10.1 (2014) or Apple iPad (4th Gen)
	 */
	private String name;
	/*same as mobile(but doesnt contain ram spec.):will be like below
	  Size(Storage capacity) : 32GB
	  Screen Size: 9.7 inch 
      Batter life: 10 hours
	  Camera:10 megapixal
		*/
	private List<String> topHeadlineFeatures=new ArrayList<String>();
	private String brand;
	private String mainImageUrl;
	private String tabletWeight;
	
	
	
	private List<String> restImageUrl=new ArrayList<String>();
	private double bestBuyPrice;
	private List<String> colors=new ArrayList<String>();
	private String processor;
	private String RAM;
	private String releasedOS;
	private String createdOn;
	private String updatedOn;
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
	private String category;
	private OverView overView;
	private Camera camera;
	private Display display;
	private Battery battery;
	private Storage storage;
	private int userReviewCount;
	private int criticsReviewCount;
	private List<String> userReviewIds=new ArrayList<String>();
	private List<String> criticsReviewIds=new ArrayList<String>();
	
	private List<String> favedByUser;
	
	private ConnectivityAndFeatures connectivityAndFeatures;
	public Tablet() {
		super();
	}
	public Tablet(String productId, String size, String name, List<String> topHeadlineFeatures, String brand, String mainImageUrl, List<String> restImageUrl, double bestBuyPrice,
			List<String> colors, String processor, String rAM, String releasedOS, String createdOn, String updatedOn, List<Accessories> accessories,
			List<AdditionalFeatures> additionalFeatures, OverView overView, Camera camera, Display display, Battery battery, Storage storage, int userReviewCount,
			int criticsReviewCount, List<String> userReviewIds, List<String> criticsReviewIds, ConnectivityAndFeatures connectivityAndFeatures) {
		super();
		this.productId = productId;
		this.size = size;
		this.name = name;
		this.topHeadlineFeatures = topHeadlineFeatures;
		this.brand = brand;
		this.mainImageUrl = mainImageUrl;
		this.restImageUrl = restImageUrl;
		this.bestBuyPrice = bestBuyPrice;
		this.colors = colors;
		this.processor = processor;
		RAM = rAM;
		this.releasedOS = releasedOS;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTopHeadlineFeatures() {
		return topHeadlineFeatures;
	}
	public void setTopHeadlineFeatures(List<String> topHeadlineFeatures) {
		this.topHeadlineFeatures = topHeadlineFeatures;
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
	public ConnectivityAndFeatures getConnectivityAndFeatures() {
		return connectivityAndFeatures;
	}
	public void setConnectivityAndFeatures(ConnectivityAndFeatures connectivityAndFeatures) {
		this.connectivityAndFeatures = connectivityAndFeatures;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tablet [productId=");
		builder.append(productId);
		builder.append(", size=");
		builder.append(size);
		builder.append(", name=");
		builder.append(name);
		builder.append(", topHeadlineFeatures=");
		builder.append(topHeadlineFeatures);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", mainImageUrl=");
		builder.append(mainImageUrl);
		builder.append(", restImageUrl=");
		builder.append(restImageUrl);
		builder.append(", bestBuyPrice=");
		builder.append(bestBuyPrice);
		builder.append(", colors=");
		builder.append(colors);
		builder.append(", processor=");
		builder.append(processor);
		builder.append(", RAM=");
		builder.append(RAM);
		builder.append(", releasedOS=");
		builder.append(releasedOS);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", accessories=");
		builder.append(accessories);
		builder.append(", additionalFeatures=");
		builder.append(additionalFeatures);
		builder.append(", overView=");
		builder.append(overView);
		builder.append(", camera=");
		builder.append(camera);
		builder.append(", display=");
		builder.append(display);
		builder.append(", battery=");
		builder.append(battery);
		builder.append(", storage=");
		builder.append(storage);
		builder.append(", userReviewCount=");
		builder.append(userReviewCount);
		builder.append(", criticsReviewCount=");
		builder.append(criticsReviewCount);
		builder.append(", userReviewIds=");
		builder.append(userReviewIds);
		builder.append(", criticsReviewIds=");
		builder.append(criticsReviewIds);
		builder.append(", connectivityAndFeatures=");
		builder.append(connectivityAndFeatures);
		builder.append("]");
		return builder.toString();
	}
	public String getTabletWeight() {
		return tabletWeight;
	}
	public void setTabletWeight(String tabletWeight) {
		this.tabletWeight = tabletWeight;
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
	
	
}
