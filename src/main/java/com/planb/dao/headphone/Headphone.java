package com.planb.dao.headphone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.planb.dao.mobile.mobileSubFeature.Accessories;
import com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures;

public class Headphone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8871815615029154549L;
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
	
	private String name;
	private String description;
	
	private String brand;
	private String mainImageUrl;
	private List<String> restImageUrl=new ArrayList<String>();
	private double bestBuyPrice;
	private List<String> colors=new ArrayList<String>();
	
	//January 12, 2016
	String releasedOn;
	
	//weight will be in gram
	private String weight;
	/*
	 * Headphone type in-ear or on-ear
	 */
	private String headphoneType;
	/*
	 * wirless or wired
	 */
	private String wireType;
	/*
	 * Design type like over the head
	 */
	private String design;
	private boolean mic;
	private String cordLength;
	private String jackDiameter;
	private String category;
	/*
	 * sound output like be stereo
	 */
	private String soundOutput;
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
	private String createdOn;
	private String updatedOn;
	private int userReviewCount;
	private int criticsReviewCount;
	private boolean isTrendingProduct;
	private List<String> favedByUser=new ArrayList<String>();
	private List<String> userReviewIds=new ArrayList<String>();
	private List<String> criticsReviewIds=new ArrayList<String>();
	public Headphone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Headphone(String productId, List<String> topHeadlineFeatures, String name, String description, String brand, String mainImageUrl, List<String> restImageUrl,
			double bestBuyPrice, List<String> colors, String weight, String headphoneType, String wireType, String design, boolean mic, String cordLength, String jackDiameter,
			String soundOutput) {
		super();
		this.productId = productId;
		this.topHeadlineFeatures = topHeadlineFeatures;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.mainImageUrl = mainImageUrl;
		this.restImageUrl = restImageUrl;
		this.bestBuyPrice = bestBuyPrice;
		this.colors = colors;
		this.weight = weight;
		this.headphoneType = headphoneType;
		this.wireType = wireType;
		this.design = design;
		this.mic = mic;
		this.cordLength = cordLength;
		this.jackDiameter = jackDiameter;
		this.soundOutput = soundOutput;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeadphoneType() {
		return headphoneType;
	}
	public void setHeadphoneType(String headphoneType) {
		this.headphoneType = headphoneType;
	}
	public String getWireType() {
		return wireType;
	}
	public void setWireType(String wireType) {
		this.wireType = wireType;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public boolean isMic() {
		return mic;
	}
	public void setMic(boolean mic) {
		this.mic = mic;
	}
	public String getCordLength() {
		return cordLength;
	}
	public void setCordLength(String cordLength) {
		this.cordLength = cordLength;
	}
	public String getJackDiameter() {
		return jackDiameter;
	}
	public void setJackDiameter(String jackDiameter) {
		this.jackDiameter = jackDiameter;
	}
	public String getSoundOutput() {
		return soundOutput;
	}
	public void setSoundOutput(String soundOutput) {
		this.soundOutput = soundOutput;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Headphone [productId=");
		builder.append(productId);
		builder.append(", topHeadlineFeatures=");
		builder.append(topHeadlineFeatures);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
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
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", headphoneType=");
		builder.append(headphoneType);
		builder.append(", wireType=");
		builder.append(wireType);
		builder.append(", design=");
		builder.append(design);
		builder.append(", mic=");
		builder.append(mic);
		builder.append(", cordLength=");
		builder.append(cordLength);
		builder.append(", jackDiameter=");
		builder.append(jackDiameter);
		builder.append(", soundOutput=");
		builder.append(soundOutput);
		builder.append("]");
		return builder.toString();
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
	public String getReleasedOn() {
		return releasedOn;
	}
	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}
	
}
