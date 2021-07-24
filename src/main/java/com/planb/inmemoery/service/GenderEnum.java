package com.planb.inmemoery.service;

public enum GenderEnum {
	


	MALE("male"), FEMALE("female"),OTHER("other");
	

	private final String value;

	GenderEnum(String input) {
		this.value = input;
	}
	

	public String getValue() {
		return this.value;
	}
	

	//overirde toString for print enum value with enum
	@Override
	public String toString() {
		return this.value;
	}


}
