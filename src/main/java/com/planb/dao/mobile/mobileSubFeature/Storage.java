package com.planb.dao.mobile.mobileSubFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {
	private String internalStorage;
	private List<String> expendableStorageFeatures=new ArrayList<String>();//is exandableStorage available,expendableStorageType,expendableStorageUpto etc
	public String getInternalStorage() {
		return internalStorage;
	}
	public void setInternalStorage(String internalStorage) {
		this.internalStorage = internalStorage;
	}
	public List<String> getExpendableStorageFeatures() {
		return expendableStorageFeatures;
	}
	public void setExpendableStorageFeatures(List<String> expendableStorageFeatures) {
		this.expendableStorageFeatures = expendableStorageFeatures;
	}
	@Override
	public String toString() {
		return "Storage [internalStorage=" + internalStorage + ", expendableStorageFeatures="
				+ expendableStorageFeatures + "]";
	}


}
