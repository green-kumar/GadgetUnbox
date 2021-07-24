package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class Graphics implements Serializable {

	private String graphicsProcessorSupport;
	private String totalGraphicsMemory;
	public String getGraphicsProcessorSupport() {
		return graphicsProcessorSupport;
	}
	public void setGraphicsProcessorSupport(String graphicsProcessorSupport) {
		this.graphicsProcessorSupport = graphicsProcessorSupport;
	}
	public String getTotalGraphicsMemory() {
		return totalGraphicsMemory;
	}
	public void setTotalGraphicsMemory(String totalGraphicsMemory) {
		this.totalGraphicsMemory = totalGraphicsMemory;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Graphics [graphicsProcessorSupport=");
		builder.append(graphicsProcessorSupport);
		builder.append(", totalGraphicsMemory=");
		builder.append(totalGraphicsMemory);
		builder.append("]");
		return builder.toString();
	} 
	
}
