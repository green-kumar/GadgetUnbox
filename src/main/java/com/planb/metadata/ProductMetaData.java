package com.planb.metadata;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productmetadata")
public class ProductMetaData implements Serializable,Comparable<ProductMetaData>{
	
	@Id
	@Column(name="productId")
	private String productId;
	
	@Column(name="topHeadlineFeatures")
	private String topHeadlineFeatures;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="mainImageUrl")
	private String mainImageUrl;
	
	@Column(name="bestBuyPrice")
	private double bestBuyPrice;
	
	@Column(name="OS")
	private String OS;
	
	@Column(name="totalFav")
	private int totalFav;
	
	@Column(name="category")
	private String category;
	
	@Column(name="totalCountOfRatings")
	private int totalCountOfRatings;
	
	@Column(name="marketStatus")
	private String marketStatus;
	
	@Column(name="avgRating")
	private double avgRating;
	
	@Column(name="rankInCategory")
	private int rankInCategory;
	
	@Column(name="isTrending")
	private boolean isTrending;
	
	@Column(name="createdOn")
	private Date createdOn;
	
	@Column(name="isActive")
	private boolean isActive;
	
	@Column(name="Score")
	private double Score;
	
	@Column(name="releaseDate")
	private String releaseDate;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTopHeadlineFeatures() {
		return topHeadlineFeatures;
	}
	public void setTopHeadlineFeatures(String topHeadlineFeatures) {
		this.topHeadlineFeatures = topHeadlineFeatures;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public double getBestBuyPrice() {
		return bestBuyPrice;
	}
	public void setBestBuyPrice(double bestBuyPrice) {
		this.bestBuyPrice = bestBuyPrice;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotalCountOfRatings() {
		return totalCountOfRatings;
	}
	public void setTotalCountOfRatings(int totalCountOfRatings) {
		this.totalCountOfRatings = totalCountOfRatings;
	}
	public String getMarketStatus() {
		return marketStatus;
	}
	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public int getRankInCategory() {
		return rankInCategory;
	}
	public void setRankInCategory(int rankInCategory) {
		this.rankInCategory = rankInCategory;
	}
	public ProductMetaData(String productId, String topHeadlineFeatures, String productName, String brand, String mainImageUrl, double bestBuyPrice, String oS,
			int totalFav, String category, int totalCountOfRatings, String marketStatus, double avgRating,double score, int rankInCategory,boolean isActive) {
		super();
		this.productId = productId;
		this.topHeadlineFeatures = topHeadlineFeatures;
		this.productName = productName;
		this.brand = brand;
		this.mainImageUrl = mainImageUrl;
		this.bestBuyPrice = bestBuyPrice;
		this.OS = oS;
		this.totalFav = totalFav;
		this.category = category;
		this.totalCountOfRatings = totalCountOfRatings;
		this.marketStatus = marketStatus;
		this.avgRating = avgRating;
		this.Score = score;
		this.rankInCategory = rankInCategory;
		this.isActive=isActive;
	}
	public ProductMetaData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean isTrending() {
		return isTrending;
	}
	public void setTrending(boolean isTrending) {
		this.isTrending = isTrending;
	}
		public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	//compareTo logic for sorting descending order in date wise
	/*o1,8,12 june
	o2,7,10 june
     o1 > o2 true==>1  o2,o1
     o1.after(o2) ==>1 o2,o1
      o1.after(o2) ==>-1  :o1,o2*/
	@Override
	public int compareTo(ProductMetaData o) {
		 SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		  
		 try {
			 
			 int i = this.releaseDate.indexOf("(");
			 if(i > 0){
				 this.releaseDate =  this.releaseDate.substring(0,i).trim();
			 }
			 
			 int j = o.releaseDate.indexOf("(");
			 if(j > 0){
				 o.releaseDate =  o.releaseDate.substring(0,j).trim();
			 }
			 
			 //or use compareto in reverse order for sorting in descing order date wise
			 
			 return sdf.parse(o.releaseDate).compareTo(sdf.parse(this.releaseDate));
			 //sdf.parse(this.dateString).compareTo(sdf.parse(person.dateString))
			//return sdf.parse(this.releaseDate).after(sdf.parse(o.releaseDate)) ?-1:(sdf.parse(o.releaseDate).after(sdf.parse(this.releaseDate))?-1:0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductMetaData other = (ProductMetaData) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		return true;
	}
	public int getTotalFav() {
		return totalFav;
	}
	public void setTotalFav(int totalFav) {
		this.totalFav = totalFav;
	}
	
	public double getScore() {
		return Score;
	}
	public void setScore(double score) {
		Score = score;
	}
	@Override
	public String toString() {
		return "ProductMetaData [productId=" + productId + ", topHeadlineFeatures=" + topHeadlineFeatures
				+ ", productName=" + productName + ", brand=" + brand + ", mainImageUrl=" + mainImageUrl
				+ ", bestBuyPrice=" + bestBuyPrice + ", OS=" + OS + ", totalFav=" + totalFav + ", category=" + category
				+ ", totalCountOfRatings=" + totalCountOfRatings + ", marketStatus=" + marketStatus + ", avgRating="
				+ avgRating + ", rankInCategory=" + rankInCategory + ", isTrending=" + isTrending + ", createdOn="
				+ createdOn + ", isActive=" + isActive + ", Score=" + Score + "]";
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
