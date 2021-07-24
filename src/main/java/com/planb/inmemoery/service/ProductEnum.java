package com.planb.inmemoery.service;

public enum ProductEnum {

	//creating enum is like 
	//creating objects in enum with paratmeric constructor
	
	MOBILE("mobile"), SMARTPHONE("smartphone"),CAMERA("camera"), TABLET("tablet"), LAPTOP(
			"laptop"), HEADPHONE("headphone"),USER("user"),REVIEW("review"),SEARCH("search"),SEARCH_TOKEN("searchToken");
	;
	/*MOBILE("mobile",1), CAMERA("camera",2), TABLET("tablet",3), LAPTOP(
			"laptop",4), HEADPHONE("headphone",5);
	;*/

	private final String value;
	//private final int id;

	ProductEnum(String input) {
		this.value = input;
	}
	
	/*ProductEnum(String input,int id) {
		this.value = input;
		this.id=id;
	}*/

	public String getValue() {
		return this.value;
	}
	
	/*public int getId(){
		return this.id;
	}*/

	//overirde toString for print enum value with enum
	@Override
	public String toString() {
		return this.value;
	}
  public static void main(String[] args) {
	  String s = MOBILE.value;
	  System.out.println(s);
		System.out.println(MOBILE.getValue().equals("mobile"));
}
	
}
