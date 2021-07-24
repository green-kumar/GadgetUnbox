package com.planb.test;

import java.util.ArrayList;

public class Test {
public static void main(String[] args) {
	
	String str ="samsung galxy j7 prime";
	
	String productId = str.replace(" ", "-")+ "-"+123;
	
	
/*	ArrayList<String> al =new ArrayList<String>();
	al.add("dkljf;dalkAndroid v5.0 (Lollipop)");
	al.add("dkljf;dalkAndroid v5.0 (Lollipop)aa");*/
	
	
	
	System.out.println(productId);
	
}
}
