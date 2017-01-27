package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class Memory implements Serializable{
	private String ramIncluded;
	private String ramPossible;
	//Hard disc capacity
	private String storage;
	private String storageTechnology;
	public String getRamIncluded() {
		return ramIncluded;
	}
	public void setRamIncluded(String ramIncluded) {
		this.ramIncluded = ramIncluded;
	}
	public String getRamPossible() {
		return ramPossible;
	}
	public void setRamPossible(String ramPossible) {
		this.ramPossible = ramPossible;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getStorageTechnology() {
		return storageTechnology;
	}
	public void setStorageTechnology(String storageTechnology) {
		this.storageTechnology = storageTechnology;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memory [ramIncluded=");
		builder.append(ramIncluded);
		builder.append(", ramPossible=");
		builder.append(ramPossible);
		builder.append(", storage=");
		builder.append(storage);
		builder.append(", storageTechnology=");
		builder.append(storageTechnology);
		builder.append("]");
		return builder.toString();
	}
	

}
