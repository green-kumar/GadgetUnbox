package com.planb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.tablet.Tablet;

@Service
public class RankingService {
	
	@Autowired
	ProductService productService; 
	
	
	
	
	/*
	 * OP like productId = {rank = n1,score = n2,rating = n3};
	 */
	public Map<String, HashMap<String, Double>> rankWiseRatedMobile(List<Mobile> mobileList) {
		return null;
	}

	public Map<String, HashMap<String, Double>> rankWiseRatedCamera(List<camera> cameraList) {
		return null;
	}
	public Map<String, HashMap<String, Double>> rankWiseRatedTablet(List<Tablet> TabletList) {
		return null;
	}
	public Map<String, HashMap<String, Double>> rankWiseRatedLaptop(List<Laptop> laptopList) {
		return null;
	}
	public Map<String, HashMap<String, Double>> rankWiseRatedHeadphone(List<Headphone> headphoneList) {
		return null;
	}
	
	
	/**
	 * Score would be out of 100,
	 * 
	 */
	
	public double evaluteScoreForMobile(Mobile mobile){
		return getRandomNo();
	}
	public double evaluteScoreForCamera(camera camera){
		return getRandomNo();
	}
	public double evaluteScoreForTablet(Tablet tablet){
		return getRandomNo();
	}
	public double evaluteScoreForLaptop(Laptop laptop){
		return getRandomNo();
	}
	public double evaluteScoreForHeadphone(Headphone headphone){
		return getRandomNo();
	}
		
    /**
     * For testin purpose ,generatin score randomly out of 100
     */
	
	int getRandomNo(){
		Random rand = new Random();

		int  n = rand.nextInt(100) + 1;
		//50 is the maximum and the 1 is our minimum 
		
		return n;
	}
	
	
	
	
	
	
}
