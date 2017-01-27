package com.planb.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.service.ProductService;

@Controller
public class CBServiceController {

	 @Autowired
	 ProductService productService;
	
	 /*
	  * TODO : Add various filter support in review api
	 * 
	 */
	@RequestMapping(value = "/review/json", method = RequestMethod.GET)
	public @ResponseBody List<ReviewDetails> getAllRevies(){
		List<Object> objReviewDetailsList=productService.getAllProduct(ProductEnum.REVIEW.getValue()); 
		List<ReviewDetails> reviewDetailsList=objReviewDetailsList.stream().map(obj->(ReviewDetails)(obj)).collect(Collectors.toList());
		return reviewDetailsList;
		
	}
	 /*
	  * TODO : Add various filter support in review api
	 * 
	 */
	@RequestMapping(value = "/mobile/json", method = RequestMethod.GET)
	public @ResponseBody List<Mobile> getAllMobile(){
		List<Object> objMobileList=productService.getAllProduct(ProductEnum.MOBILE.getValue()); 
		List<Mobile> mobileList=objMobileList.stream().map(obj->(Mobile)(obj)).collect(Collectors.toList());
		return mobileList;
		
	}
	
}
