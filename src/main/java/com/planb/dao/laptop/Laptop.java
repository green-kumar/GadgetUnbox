package com.planb.dao.laptop;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.planb.dao.laptop.laptopSubFeatures.Connectivity;
import com.planb.dao.laptop.laptopSubFeatures.Graphics;
import com.planb.dao.laptop.laptopSubFeatures.Input;
import com.planb.dao.laptop.laptopSubFeatures.LaptopDisplay;
import com.planb.dao.laptop.laptopSubFeatures.Memory;
import com.planb.dao.laptop.laptopSubFeatures.Processor;
import com.planb.dao.mobile.mobileSubFeature.Accessories;
import com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures;

public class Laptop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3678197014587894642L;
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
	
	//January 12, 2016
	String releasedOn;
	
	private String brand;
	private String mainImageUrl;
	
	//notebook or ultrabook or laptopa or chrombook or macbook 
	private String laptopType;
	
	private List<String> restImageUrl=new ArrayList<String>();
	private double bestBuyPrice;
	private List<String> colors=new ArrayList<String>();
	//weight will be in gram
	private String weight;
	//dimension will be in l*b*h
	private String dimension;
	//screen size like 14 inch,15.4 inch
	private String displaySize;
	/*
	 * a singnificant percentage of viewer focus on avalable accesories with product 
	 */
	private List<Accessories> accessories=new ArrayList<Accessories>();//name,value,image_url
	/*
	 * all the feature which is not listed in pojo,
	 * will fall into additionalFeature map with name,value pair
	 * 
	 */
	private String category;
	private String batteryLife;
	private String batteryQuality;
	private List<AdditionalFeatures> WarrantyAndAdditionalFeatures=new ArrayList<AdditionalFeatures>();//name,value
	private String releasedOS;
	private String createdOn;
	private String updatedOn;
	private boolean isTrendingProduct;
	private int userReviewCount;
	private int criticsReviewCount;
	private List<String> userReviewIds=new ArrayList<String>();
	private List<String> criticsReviewIds=new ArrayList<String>();
	private List<String> favedByUsers;
    private Processor processor=new Processor();
    private Graphics graphics=new Graphics();
    private Memory memory=new Memory();
    private LaptopDisplay laptopDisplay=new LaptopDisplay();
    private Connectivity connectivity=new Connectivity();
    private Input input=new Input();
	public Laptop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Laptop(String productId, List<String> topHeadlineFeatures, String name, String brand, String mainImageUrl, String laptopType, List<String> restImageUrl,
			double bestBuyPrice, List<String> colors, String weight, String dimension, String displaySize, List<Accessories> accessories, String batteryLife,
			String batteryQuality, List<AdditionalFeatures> warrantyAndAdditionalFeatures, String releasedOS, String createdOn, String updatedOn, int userReviewCount,
			int criticsReviewCount, List<String> userReviewIds, List<String> criticsReviewIds, Processor processor, Graphics graphics, Memory memory, LaptopDisplay laptopDisplay,
			Connectivity connectivity, Input input) {
		super();
		this.productId = productId;
		this.topHeadlineFeatures = topHeadlineFeatures;
		this.name = name;
		this.brand = brand;
		this.mainImageUrl = mainImageUrl;
		this.laptopType = laptopType;
		this.restImageUrl = restImageUrl;
		this.bestBuyPrice = bestBuyPrice;
		this.colors = colors;
		this.weight = weight;
		this.dimension = dimension;
		this.displaySize = displaySize;
		this.accessories = accessories;
		this.batteryLife = batteryLife;
		this.batteryQuality = batteryQuality;
		WarrantyAndAdditionalFeatures = warrantyAndAdditionalFeatures;
		this.releasedOS = releasedOS;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.userReviewCount = userReviewCount;
		this.criticsReviewCount = criticsReviewCount;
		this.userReviewIds = userReviewIds;
		this.criticsReviewIds = criticsReviewIds;
		this.processor = processor;
		this.graphics = graphics;
		this.memory = memory;
		this.laptopDisplay = laptopDisplay;
		this.connectivity = connectivity;
		this.input = input;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMainImageUrl() {
		return mainImageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	public String getLaptopType() {
		return laptopType;
	}
	public void setLaptopType(String laptopType) {
		this.laptopType = laptopType;
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
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}
	public List<Accessories> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<Accessories> accessories) {
		this.accessories = accessories;
	}
	public String getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}
	public String getBatteryQuality() {
		return batteryQuality;
	}
	public void setBatteryQuality(String batteryQuality) {
		this.batteryQuality = batteryQuality;
	}
	public List<AdditionalFeatures> getWarrantyAndAdditionalFeatures() {
		return WarrantyAndAdditionalFeatures;
	}
	public void setWarrantyAndAdditionalFeatures(List<AdditionalFeatures> warrantyAndAdditionalFeatures) {
		WarrantyAndAdditionalFeatures = warrantyAndAdditionalFeatures;
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
	public Processor getProcessor() {
		return processor;
	}
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	public Graphics getGraphics() {
		return graphics;
	}
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}
	public Memory getMemory() {
		return memory;
	}
	public void setMemory(Memory memory) {
		this.memory = memory;
	}
	public LaptopDisplay getLaptopDisplay() {
		return laptopDisplay;
	}
	public void setLaptopDisplay(LaptopDisplay laptopDisplay) {
		this.laptopDisplay = laptopDisplay;
	}
	public Connectivity getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}
	public Input getInput() {
		return input;
	}
	public void setInput(Input input) {
		this.input = input;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Laptop [productId=");
		builder.append(productId);
		builder.append(", topHeadlineFeatures=");
		builder.append(topHeadlineFeatures);
		builder.append(", name=");
		builder.append(name);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", mainImageUrl=");
		builder.append(mainImageUrl);
		builder.append(", laptopType=");
		builder.append(laptopType);
		builder.append(", restImageUrl=");
		builder.append(restImageUrl);
		builder.append(", bestBuyPrice=");
		builder.append(bestBuyPrice);
		builder.append(", colors=");
		builder.append(colors);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", dimension=");
		builder.append(dimension);
		builder.append(", displaySize=");
		builder.append(displaySize);
		builder.append(", accessories=");
		builder.append(accessories);
		builder.append(", batteryLife=");
		builder.append(batteryLife);
		builder.append(", batteryQuality=");
		builder.append(batteryQuality);
		builder.append(", WarrantyAndAdditionalFeatures=");
		builder.append(WarrantyAndAdditionalFeatures);
		builder.append(", releasedOS=");
		builder.append(releasedOS);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", userReviewCount=");
		builder.append(userReviewCount);
		builder.append(", criticsReviewCount=");
		builder.append(criticsReviewCount);
		builder.append(", userReviewIds=");
		builder.append(userReviewIds);
		builder.append(", criticsReviewIds=");
		builder.append(criticsReviewIds);
		builder.append(", processor=");
		builder.append(processor);
		builder.append(", graphics=");
		builder.append(graphics);
		builder.append(", memory=");
		builder.append(memory);
		builder.append(", laptopDisplay=");
		builder.append(laptopDisplay);
		builder.append(", connectivity=");
		builder.append(connectivity);
		builder.append(", input=");
		builder.append(input);
		builder.append("]");
		return builder.toString();
	}
   public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper=new ObjectMapper();
	Laptop laptop=new Laptop();
	System.out.println(mapper.writeValueAsString(laptop));
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
public String getReleasedOn() {
	return releasedOn;
}
public void setReleasedOn(String releasedOn) {
	this.releasedOn = releasedOn;
} 
	
}
