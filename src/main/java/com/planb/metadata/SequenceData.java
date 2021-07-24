package com.planb.metadata;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sequencedata")
public class SequenceData implements Serializable{
     	
	
	@Id
	@Column(name="category")
	String category;
	
	@Column(name="count")
	int count;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

	@Override
	public String toString() {
		return "SequenceData [category=" + category + ", count=" + count + "]";
	}

}
