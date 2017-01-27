package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class Processor implements Serializable {
   
	//intel core i3(3rd generation 3337u)
	private String processor;
	private String processorImgUrl;
	private String processorSpeed;
	private String processorCores;
	private String cache;
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getProcessorImgUrl() {
		return processorImgUrl;
	}
	public void setProcessorImgUrl(String processorImgUrl) {
		this.processorImgUrl = processorImgUrl;
	}
	public String getProcessorSpeed() {
		return processorSpeed;
	}
	public void setProcessorSpeed(String processorSpeed) {
		this.processorSpeed = processorSpeed;
	}
	public String getProcessorCores() {
		return processorCores;
	}
	public void setProcessorCores(String processorCores) {
		this.processorCores = processorCores;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Processor [processor=");
		builder.append(processor);
		builder.append(", processorImgUrl=");
		builder.append(processorImgUrl);
		builder.append(", processorSpeed=");
		builder.append(processorSpeed);
		builder.append(", processorCores=");
		builder.append(processorCores);
		builder.append(", cache=");
		builder.append(cache);
		builder.append("]");
		return builder.toString();
	}
}
