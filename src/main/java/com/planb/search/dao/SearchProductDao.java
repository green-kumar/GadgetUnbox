package com.planb.search.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.planb.inmemoery.service.ProductEnum;

@JsonIgnoreProperties
public class SearchProductDao implements Comparable<SearchProductDao>{
	String productName;
	ProductEnum category;
	String SearchId;
	String producId;
	long score;
	String brand;
	double bestBuyPrice;
	String mainImageUrl;
	double rating;
	private String releaseDate;
	
	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public double getBestBuyPrice() {
		return bestBuyPrice;
	}


	public void setBestBuyPrice(double bestBuyPrice) {
		this.bestBuyPrice = bestBuyPrice;
	}
	boolean isSearchTokenPersistedToCB;
	
	public SearchProductDao() {
		super();
	}
	
	
	public SearchProductDao(String productName, ProductEnum category, String searchId, String producId,
			boolean isSearchTokenPersistedToCB) {
		super();
		this.productName = productName;
		this.category = category;
		SearchId = searchId;
		this.producId = producId;
		this.isSearchTokenPersistedToCB = isSearchTokenPersistedToCB;
	}

	public boolean isSearchTokenPersistedToCB() {
		return isSearchTokenPersistedToCB;
	}
	public void setSearchTokenPersistedToCB(boolean isSearchTokenPersistedToCB) {
		this.isSearchTokenPersistedToCB = isSearchTokenPersistedToCB;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductEnum getCategory() {
		return category;
	}
	public void setCategory(ProductEnum category) {
		this.category = category;
	}
	public String getSearchId() {
		return SearchId;
	}
	public void setSearchId(String searchId) {
		SearchId = searchId;
	}
	public String getProducId() {
		return producId;
	}
	public void setProducId(String producId) {
		this.producId = producId;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	@Override
	public int compareTo(SearchProductDao SPD) {
		return this.getScore() >SPD.getScore() ? -1 :(this.getScore() < SPD.getScore() ?1 :0 );
	}
	
	public static void main(String[] args) {
		
		SearchProductDao s1 =new SearchProductDao();
		SearchProductDao s2 =new SearchProductDao();
		SearchProductDao s3 =new SearchProductDao();
		s1.setScore(100);
		s2.setScore(200);
		s3.setScore(300);
		
		List<SearchProductDao> l =new ArrayList<SearchProductDao>();
		l.add(s1);
		l.add(s2);
		l.add(s3);
		Collections.sort(l);
		System.out.println(l);
		
	}
	@Override
	public String toString() {
		return "SearchProductDao [productName=" + productName + ", category=" + category + ", SearchId=" + SearchId
				+ ", producId=" + producId + ", score=" + score + ", isSearchTokenPersistedToCB="
				+ isSearchTokenPersistedToCB + "]";
	}


	

	public String getMainImageUrl() {
		return mainImageUrl;
	}


	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}




	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	

}
