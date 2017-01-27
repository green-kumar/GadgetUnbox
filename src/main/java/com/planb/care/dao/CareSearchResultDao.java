package com.planb.care.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CareSearchResultDao implements Comparable<CareSearchResultDao> {

	String name;
	String category;
	String productId;
	String brand;
	double bestBuyPrice;
	String createdOn;
	public CareSearchResultDao() {
		super();
	}
	public CareSearchResultDao(String name, String category, String productId, String brand, double bestBuyPrice
			) {
		super();
		this.name = name;
		this.category = category;
		this.productId = productId;
		this.brand = brand;
		this.bestBuyPrice = bestBuyPrice;
	}
	public String getName() {
		return name;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	
	@Override
	public int compareTo(CareSearchResultDao o) {
		return this.name.compareTo(o.name);
	}
	public double getBestBuyPrice() {
		return bestBuyPrice;
	}
	public void setBestBuyPrice(double bestBuyPrice) {
		this.bestBuyPrice = bestBuyPrice;
	}
	@Override
	public String toString() {
		return "CareSearchResultDao [name=" + name + ", category=" + category + ", productId=" + productId + ", brand="
				+ brand + ", bestBuyPrice=" + bestBuyPrice + ", createdOn=" + createdOn + "]";
	}
	
	
	
	
	
	
}
