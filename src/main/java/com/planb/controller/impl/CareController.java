package com.planb.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.planb.care.dao.CareSearchResultDao;
import com.planb.care.service.CareService;
import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.tablet.Tablet;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.metadata.SequenceService;
import com.planb.product.util.EUtil;
import com.planb.service.ProductService;

@Controller
public class CareController {
    
	
	@Autowired
    CareService careService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	
	@Value("${colorList}")
	String colorList;
	
	@Value("${phoneType}")
	String phoneType;
	
	@Value("${cameraType}")
	String cameraType;
	
	@Value("${laptopType}")
	String laptopType;
	
	@Value("${headphoneType}")
	String headphoneType;
	
	@Value("${wireType}")
	String wireType;
	
	@Value("${viewFinderType}")
	String viewFinderType;
	
	@Value("${careTakers}")
	String careTakers;
	
	@Value("${login.msg}")
	String loginMsg;
	
	@Value("${NotAdmin.msg}")
	String NotAdminMsg;
	
	@RequestMapping(value = "/care", method = RequestMethod.GET)
	public String careView(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		String userEmail = (String)request.getSession().getAttribute("userEmail");
		if(userEmail == null){
		model.put("msg",loginMsg);
		return "careLogin";
		}
	    if(Arrays.asList(careTakers.split(",")).contains(userEmail)){
	    	return "care";
	    }else{
	    	model.put("msg",NotAdminMsg);
	    	return "careLogin";
	    }
	
	}
	
	@RequestMapping(value = "/care/delete/{category}/{productId}", method = RequestMethod.GET)
	public String careDeleteProduct(HttpServletRequest request, HttpServletResponse response,ModelMap model,@PathVariable("category") String category,@PathVariable("productId")String productId)
	{
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		productService.deleteProduct(productId, category);
		model.put("msg", "product \""+productId+ "\" have been delete successfully");
		return "careDeleteInfo";
		
	}
	
	
	@RequestMapping(value = "/care/create/{category}", method = RequestMethod.GET)
	public String getCareView(HttpServletRequest request, HttpServletResponse response,ModelMap model,@PathVariable("category") String category,@RequestParam(value ="id" ,required=false)String productId)
	{
		ElectInMemoryService electInMemoryService = electInMemoryFactory
				.getElectInMemoryService(category);
		String userEmail = (String)request.getSession().getAttribute("userEmail");
		if(userEmail == null){
		model.put("msg",loginMsg);
		return "careLogin";
		}
		model.put("category",category);
		if(!StringUtils.isEmpty(productId)){
			model.put("operation", "edit");
			model.put("productId", productId);
		
		}else{
		   model.put("operation", "add");
		}
		defaultModelMethod(model);
		return electInMemoryService.careUtil(model,productId);
		
	}
	
	@RequestMapping(value="/care/save/mobile",method=RequestMethod.POST)
	public String saveMobile(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("formAttribute")Mobile mobile){
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService("mobile");
		return electInMemoryService.saveCareProduct(mobile,model);
	}
	@RequestMapping(value="/care/save/camera",method=RequestMethod.POST)
	public String saveCamera(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("formAttribute")camera camera){
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService("camera");
		return electInMemoryService.saveCareProduct(camera,model);
	}
	@RequestMapping(value="/care/save/tablet",method=RequestMethod.POST)
	public String saveTablet(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("formAttribute")Tablet tablet){
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService("tablet");
		return electInMemoryService.saveCareProduct(tablet,model);
	}
	@RequestMapping(value="/care/save/laptop",method=RequestMethod.POST)
	public String saveLaptop(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("formAttribute")Laptop laptop){
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService("laptop");
		return electInMemoryService.saveCareProduct(laptop,model);
	}
	@RequestMapping(value="/care/save/headphone",method=RequestMethod.POST)
	public String saveHeadphone(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("formAttribute")Headphone headphone){
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService("headphone");
		return electInMemoryService.saveCareProduct(headphone,model);
	}
	
	
	
	@RequestMapping(value = "/care/autoComplete", method = RequestMethod.GET)
	public @ResponseBody List<String> getAutoCompleteBrandOrName(@RequestParam(value="b" ,required = false) String brand,
	                            @RequestParam(value="n" ,required = false) String name,
	                            @RequestParam(value="c" ,required = false) String category,
	                            @RequestParam(value="req" ,required = false) String req){
		return careService.getAutoCompleteResult(brand, name, category, req);
	}
	
	

	@RequestMapping(value = "/care/search", method = RequestMethod.GET)
	public String  careSearchView(@RequestParam(value="b" ,required = false) String brand,
	                            @RequestParam(value="n" ,required = false) String name,
	                            @RequestParam(value="id" ,required = false) String id,
	                            @RequestParam(value="sB" ,required = false) String sortBy,
	                            @RequestParam(value="oB" ,required = false) String orderBy ,
	                            @RequestParam(value="c" ,required = false) String category,
	                            @RequestParam(value="p" ,required = false) int page, @RequestParam(value="l" ,required = false)int limit,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JsonParseException, JsonMappingException, IOException{
		
		String isLoggedIn = defaultLoginCheck(request,model);
		if(isLoggedIn != "")
			return isLoggedIn;
		List<CareSearchResultDao> csrdList=null;
		if(!StringUtils.isBlank(id) && !StringUtils.isEmpty(category)){
			ElectInMemoryService electInMemoryService=electInMemoryFactory.getElectInMemoryService(category);
			CareSearchResultDao spd =electInMemoryService.getCareSearchDataFromProductId(id);
            if(spd!=null){
            	csrdList = new ArrayList<CareSearchResultDao>();
            	csrdList.add(spd);
            	model.put("totalCount", 1);
            }else{
            	model.put("totalCount", 0);
            }
			
           
		}else{
			csrdList = careService.getCareSearchResult(brand, name, category, orderBy, sortBy , limit, page,model);
		}
		
		 model.put("csrdList", csrdList);
         model.put("category", category);
         model.put("sortBy" , sortBy);
         model.put("orderBy", orderBy);
         model.put("page",page);
         model.put("limit", limit);
         return "careSearchResult";
         
	}
	
	private void defaultModelMethod(ModelMap model){
		model.put("colorList",Arrays.asList(colorList.split(",")));
		model.put("phoneTypeList",Arrays.asList(phoneType.split(",")));
		model.put("cameraTypeList",Arrays.asList(cameraType.split(",")));
		model.put("viewFinderType",Arrays.asList(viewFinderType.split(",")));
		model.put("laptopTypeList",Arrays.asList(laptopType.split(",")));
		model.put("headphoneTypeList", Arrays.asList(headphoneType.split(",")));
		model.put("wireTypeList", Arrays.asList(wireType.split(",")));
    }
	
	
	private String defaultLoginCheck(HttpServletRequest request,ModelMap model){
		String isValid = "";
		
		String userEmail = (String)request.getSession().getAttribute("userEmail");
		if(userEmail == null){
		model.put("msg",loginMsg);
		isValid =  "careLogin";
		return isValid;
		}
		
	    if(!Arrays.asList(careTakers.split(",")).contains(userEmail)){
	    	model.put("msg",NotAdminMsg);
	    	isValid = "careLogin";
	    }
	    return isValid;
	}
	
	
	
	
}
