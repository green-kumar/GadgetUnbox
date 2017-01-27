package com.planb.controller.category;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.planb.dao.mobile.Mobile;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.metadata.CbdToRdbToInMem;
import com.planb.metadata.ProductMetaData;
import com.planb.metadata.ProductMetaDataRDBService;
import com.planb.metadata.ReviewMetaDataRDBService;
import com.planb.product.util.ElectConstants;
import com.planb.service.ProductService;
import com.planb.service.RankingService;

@Controller
@RequestMapping({"/mobile","/mobiles","/smartphone"})
public class SmartPhoneController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ReviewMetaDataRDBService reviewMetaDataRDBService;
	
	@Autowired
	ProductMetaDataRDBService productMetaDataRDBService;
	
	@Autowired
	CbdToRdbToInMem cbdToRdbToInMem;
	
	@Autowired
	RankingService  rankingService;
	
	@Value("${mobile.brand}")
	String mobileBrand;
	
	@Value("${mobile.OS}")
	String mobileOS;
	
	@Value("${mobile.feature}")
	String mobileFeature;
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	@RequestMapping(value = "/details/{productId}", method = RequestMethod.GET)
	public String productDetailsView(@PathVariable("productId") String productId,@RequestParam(value="avgRating",required = false) String avgRating, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		
		Mobile mobileDetail=(Mobile)productService.getProduct(productId, "mobile");
		//fetch only top rated review
		
		List<String> reviewIds=   reviewMetaDataRDBService.getTopRatedReviewId(productId);
		/*List<String> nonUniqReviewIds=reviewIds.stream().map(str->str.substring(0, str.length()-10)).collect(Collectors.toList());*/
		
		ProductMetaData pmd = productMetaDataRDBService.get(productId);
		if(pmd!=null){
			model.put("avgRating", pmd.getAvgRating()+"");
		}
		
		if(reviewIds!=null && !reviewIds.isEmpty()){
			model.put("allReview",productService.multiGet(reviewIds,ProductEnum.REVIEW.getValue()));
		}
		model.put("productDetail", mobileDetail);
		
		if(request.getHeader("X-custom-Header") != null && request.getHeader("X-custom-Header").equals("popup")){
			return "mobileDetailsView";
		}
		
		
			return "mobileDetailsBrowserView";
		
			
	}


	
	
   @RequestMapping(value="/list/top-rated-smartphone/filter" ,method=RequestMethod.GET)
   public String topRatedSmartPhone(@RequestParam(value="brand" ,required = false) String brand,@RequestParam(value="OS",required = false) String OS,@RequestParam(value="feature",required = false) String feature,HttpServletRequest request, HttpServletResponse response, ModelMap model){

	   String userEmail = (String) request.getSession().getAttribute("userEmail");
	   ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(ProductEnum.MOBILE.getValue());
	   if(userEmail != null){
			electInMemoryService.populatedLoggedUserMetaData(userEmail, model);
	   }
	   
	   Map<String,List<String>> filterCriteria = new HashMap<String,List<String>>();
	   
	   if(!StringUtils.isEmpty(brand))
		   filterCriteria.put(ElectConstants.BRAND, Arrays.asList(brand.split(",")));
	   
	   if(!StringUtils.isEmpty(OS))
		   filterCriteria.put(ElectConstants.OS, Arrays.asList(OS.split(",")));

	   if(!StringUtils.isEmpty(feature))
		   filterCriteria.put(ElectConstants.FEATURE, Arrays.asList(feature.split(",")));

	   
	   List<ProductMetaData> sortedSmartPhoneList=electInMemoryService.getTopRatedFilterProduct(filterCriteria);
	   model.put("sortedSmartPhoneList", sortedSmartPhoneList);
	   populateMobileFilterData(model);
		return "mobielFilterListingView";
		
		
	
   }
	
   @RequestMapping(value = "/list/top-rated-smartphone", method = RequestMethod.GET)
	public String productListingView(@RequestParam(value="brand" ,required = false) String brand,@RequestParam(value="OS",required = false) String OS,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
	   ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(ProductEnum.MOBILE.getValue());
	   String userEmail = (String) request.getSession().getAttribute("userEmail");
	   if(userEmail != null){
		   
			electInMemoryService.populatedLoggedUserMetaData(userEmail, model);
	   }
		   List<ProductMetaData> sortedSmartPhoneList=electInMemoryService.getTopRatedProduct();
		   
		   Map<String,List<String>> filterCriteria = new HashMap<String,List<String>>();
		   
		   if(!StringUtils.isEmpty(brand))
			   filterCriteria.put(ElectConstants.BRAND, Arrays.asList(brand.split(",")));
		   
		   if(!StringUtils.isEmpty(OS))
			   filterCriteria.put(ElectConstants.OS, Arrays.asList(OS.split(",")));
		   
		   if(!CollectionUtils.isEmpty(filterCriteria))
		   sortedSmartPhoneList=electInMemoryService.getTopRatedFilterProduct(filterCriteria); 
			   
		   
	   model.put("sortedSmartPhoneList", sortedSmartPhoneList);
	   populateMobileFilterData(model);
		return "mobielListingView";
		
		
	}




private void populateMobileFilterData(ModelMap model) {
	
    List<String> mobileBrandList = Arrays.asList(mobileBrand.split(","));
	List<String> mobileOSList = Arrays.asList(mobileOS.split(","));
	List<String> mobileFeatureList = Arrays.asList(mobileFeature.split(","));
	
	Collections.sort(mobileBrandList);
	Collections.sort(mobileOSList);
	Collections.sort(mobileFeatureList);
	
	model.put("mobileBrandList" ,mobileBrandList );
	model.put("mobileOSList" ,mobileOSList);
	model.put("mobileFeatureList" ,mobileFeatureList);
	
}
	
}
