package com.planb.dao.laptop.laptopSubFeatures;

import java.io.Serializable;

public class Connectivity implements Serializable {
	private String ethernet;
	private String wifi;
	private String bluetooth;
	private String lanport;
	//usb ports like 1*USB2.0,2*USB3.0
	private String cardReader;
	private String headPhoneJack;
	private String securityLockPort;
	public String getEthernet() {
		return ethernet;
	}
	public void setEthernet(String ethernet) {
		this.ethernet = ethernet;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String wifi) {
		this.wifi = wifi;
	}
	public String getBluetooth() {
		return bluetooth;
	}
	public void setBluetooth(String bluetooth) {
		this.bluetooth = bluetooth;
	}
	public String getLanport() {
		return lanport;
	}
	public void setLanport(String lanport) {
		this.lanport = lanport;
	}
	public String getCardReader() {
		return cardReader;
	}
	public void setCardReader(String cardReader) {
		this.cardReader = cardReader;
	}
	public String getHeadPhoneJack() {
		return headPhoneJack;
	}
	public void setHeadPhoneJack(String headPhoneJack) {
		this.headPhoneJack = headPhoneJack;
	}
	public String getSecurityLockPort() {
		return securityLockPort;
	}
	public void setSecurityLockPort(String securityLockPort) {
		this.securityLockPort = securityLockPort;
	}
	@Override
	public String toString() {
		return "Connectivity [ethernet=" + ethernet + ", wifi=" + wifi + ", bluetooth=" + bluetooth + ", lanport="
				+ lanport + ", cardReader=" + cardReader + ", headPhoneJack=" + headPhoneJack + ", securityLockPort="
				+ securityLockPort + "]";
	}
	
    
}
