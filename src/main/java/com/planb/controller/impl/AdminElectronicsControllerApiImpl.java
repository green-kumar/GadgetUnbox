package com.planb.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.tablet.Tablet;
import com.planb.product.util.FetchRequiredProductDescriptionKeysUtil;
import com.planb.product.util.EUtil;
import com.planb.service.MobileService;
import com.planb.service.ProductService;
import com.planb.validator.MobileFormValidator;

@Controller
@RequestMapping("/admin")
public class AdminElectronicsControllerApiImpl{
  
	@Autowired
	FetchRequiredProductDescriptionKeysUtil fetchRequiredProductDescriptionKeysUtil;
	
	@Autowired
	EUtil productUtil;
	
	@Autowired
	MobileFormValidator mobileFormValidator;
	
	@Autowired
	MobileService mobileService;
	
	@Autowired
	ProductService productService;
	
	
	
	
    /*@ModelAttribute("ProductKeyDetailsMap")
    public Map<String,String> getInitializedMyObject() {
        return fetchRequiredProductDescriptionKeysUtil.getProductCategoryKeyMap();
    }*/
	
	 @RequestMapping(value = "/fetchAllProductDetails/{category}",method = RequestMethod.GET)
	public String fetchAllProductDetails(@PathVariable("category") String category,ModelMap model) {
			model.put("category", category);
		if("mobile".equalsIgnoreCase(category) || "smartphone".equalsIgnoreCase(category)){
			List<Mobile> mobileList=mobileService.getAllMobileList();
			if(mobileList == null || mobileList.size()==0){
				model.put("Empty_msg","No any product found");
				model.addAttribute("css", "danger");
			}else{
				model.put("Empty_msg","");
				model.addAttribute("css", "success");
			}
			model.put("mobileList", mobileList);
			return "mobileList";
		}else if(category.equalsIgnoreCase("camera")){
			List<Object> cameraObjectList=productService.getAllProduct("camera");
       List<camera> cameraList=new ArrayList<camera>();
			for(Object obj:cameraObjectList){
				cameraList.add((camera)obj);
			}
			//List<camera> cameraList=(List<camera>)(Object)cameraObjectList;
			if(cameraList == null || cameraList.size()==0){
				model.put("Empty_msg","No any product found");
				model.addAttribute("css", "danger");
			}else{
				model.put("Empty_msg","");
				model.addAttribute("css", "success");
			}
			model.put("cameraList", cameraList);
			return "cameraList";
		}else if("tablet".equalsIgnoreCase(category)){
			List<Object> tabletObjectList=productService.getAllProduct("tablet");
			@SuppressWarnings("unchecked")
			List<Tablet> tabletList=(List<Tablet>)(Object)tabletObjectList;
			if(tabletList == null || tabletList.size()==0){
				model.put("Empty_msg","No any product found");
				model.addAttribute("css", "danger");
			}else{
				model.put("Empty_msg","");
				model.addAttribute("css", "success");
			}
			model.put("tabletList", tabletList);
			return "tabletList";
		}else if("laptop".equalsIgnoreCase(category)){
			List<Object> laptopObjectList=productService.getAllProduct("laptop");
			@SuppressWarnings("unchecked")
			List<Laptop> laptopList=(List<Laptop>)(Object)laptopObjectList;
			if(laptopList == null || laptopList.size()==0){
				model.put("Empty_msg","No any product found");
				model.addAttribute("css", "danger");
			}else{
				model.put("Empty_msg","");
				model.addAttribute("css", "success");
			}
			model.put("laptopList", laptopList);
			return "laptopList";
		}else if("headphone".equalsIgnoreCase(category)){
			List<Object> headphoneObjectList=productService.getAllProduct("headphone");
			@SuppressWarnings("unchecked")
			List<Headphone> headphoneList=(List<Headphone>)(Object)headphoneObjectList;
			if(headphoneList == null || headphoneList.size()==0){
				model.put("Empty_msg","No any product found");
				model.addAttribute("css", "danger");
			}else{
				model.put("Empty_msg","");
				model.addAttribute("css", "success");
			}
			model.put("headphoneList", headphoneList);
			return "headphoneList";
			
		}
		return "404NotFound";
	}

	@RequestMapping(value = "/{category}/updateProduct/{productId}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable("category") String category, @PathVariable("productId") String productId,ModelMap model) {
		
		model.put("category",category);
		model.put("requestedJob", "Update Product");
		if(category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("smartphone")){
			Mobile mobile =	mobileService.getMobile(productId);
			model.put("productForm",mobile);
			defaultModelMethod(model);
			return "addOrUpdateMobile";
		}else if(category.equalsIgnoreCase("camera")){
			camera camera=(camera)productService.getProduct(productId, "camera");
			model.put("productForm",camera);
			defaultModelMethod(model);
			return "addOrUpdateCamera";
		}else if("tablet".equalsIgnoreCase(category)){
			Tablet tablet=(Tablet)productService.getProduct(productId, "tablet");
			model.put("productForm",tablet);
			defaultModelMethod(model);
			return "addOrUpdateTablet";
		}else if("laptop".equalsIgnoreCase(category)){
			Laptop laptop=(Laptop)productService.getProduct(productId, "laptop");
			model.put("productForm",laptop);
			defaultModelMethod(model);
			return "addOrUpdateLaptop";
		}else if("headphone".equalsIgnoreCase(category)){
			Headphone headphone=(Headphone)productService.getProduct(productId, "headphone");
			model.put("productForm",headphone);
			defaultModelMethod(model);
			return "addOrUpdateHeadphone";
		}
		
		return "404notFound";
	}
	@RequestMapping(value="/{category}/deleteProduct/{productId}",method = RequestMethod.GET)
	public @ResponseBody String deleteProduct(@PathVariable("category") String category, @PathVariable("productId") String productId) {
		if("mobile".equalsIgnoreCase(category) || "smartphone".equalsIgnoreCase(category)){
			mobileService.deleteMobile(productId);
			return "success";
		}else if("camera".equalsIgnoreCase(category)){
			productService.deleteProduct(productId,"camera");
			return "success";
		}else if("tablet".equalsIgnoreCase(category)){
			productService.deleteProduct(productId,"tablet");
			return "success";
		}else if("laptop".equalsIgnoreCase(category)){
			productService.deleteProduct(productId,"laptop");
			return "success";
		}else if("headphone".equalsIgnoreCase(category)){
			productService.deleteProduct(productId,"headphone");
			return "success";
		}else{
			return "something went wrong!!!";
		}
	}
	
	 @RequestMapping(value = "/AddProduct", method = RequestMethod.GET)
		public String addProductForm(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
			
			model.put("productDetailsMap", fetchRequiredProductDescriptionKeysUtil.getProductCategoryKeyMapInKeyValueForm());
			return "addProduct";
		}

	@RequestMapping(value = "/AddProduct/{category}", method = RequestMethod.GET)
	public String addProductForCategoryFormGet(@PathVariable("category") String category,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		model.put("productDetailsKey", fetchRequiredProductDescriptionKeysUtil.getProductCategoryKeyMapInKeyValueForm().get(category.toLowerCase().trim()));
		model.put("category",category);
		model.put("requestedJob", "Add Product");
		if(category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("smartphone")){
		Mobile mobile=new Mobile();
		model.put("productForm",mobile);
		}else if(category.equalsIgnoreCase("camera")){
			camera camera=new camera();
			model.put("productForm",camera);
		}else if("tablet".equalsIgnoreCase(category)){
			Tablet tablet=new Tablet();
			model.put("productForm",tablet);
		}else if("laptop".equalsIgnoreCase(category)){
			Laptop laptop=new Laptop();
			model.put("productForm",laptop);
		}else if("headphone".equalsIgnoreCase(category)){
			Headphone headphone=new Headphone();
			model.put("productForm",headphone);
		}
		return "addProductForCategories";
	}

	
	@RequestMapping(value = "{category}/AddProduct", method = RequestMethod.GET)
	public String addProductForCategoryNewFormGet(@PathVariable("category") String category,HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		model.put("category",category);
		model.put("requestedJob", "Add Product");
		if(category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("smartphone")){
		    Mobile mobile=new Mobile();
			model.put("productForm",mobile);
			defaultModelMethod(model);
			return "addOrUpdateMobile";
		}else if(category.equalsIgnoreCase("camera")){
			camera camera=new camera();
			model.put("productForm",camera);
			defaultModelMethod(model);
			return "addOrUpdateCamera";
		}else if("tablet".equalsIgnoreCase(category)){
			Tablet tablet=new Tablet();
			model.put("productForm",tablet);
			defaultModelMethod(model);
			return "addOrUpdateTablet";
		}else if("laptop".equalsIgnoreCase(category)){
			Laptop laptop=new Laptop();
			model.put("productForm",laptop);
			defaultModelMethod(model);
			return "addOrUpdateLaptop";
		}else if("headphone".equalsIgnoreCase(category)){
			Headphone headphone=new Headphone();
			model.put("productForm",headphone);
			defaultModelMethod(model);
			return "addOrUpdateHeadphone";
		}
		return "404notFound";
	}
	
	/*
	 *submit(post) api : works for both update product and add product
	 * 
	 */
	
	@RequestMapping(value = {"/mobile/AddProduct","/smartphone/AddProduct"}, method = RequestMethod.POST)
	public String addProductForMobileFormPost(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("productForm")Mobile mobile,BindingResult result) throws Exception {
	
		model.put("category","mobile");
		model.put("requestedJob", "Add Product");
		
		System.out.println(mobile.toString());
		mobileFormValidator.validate(mobile, result);
		if(result.hasErrors()){
			defaultModelMethod(model);
		return	"addOrUpdateMobile";
		}else{
			String productId;
			//***new product for add****//
			if(mobile.getProductId() == null || "".equalsIgnoreCase(mobile.getProductId()) ){
			productId="Mobile_"+mobile.getName()+"_"+mobile.getBrand()+"_"+String.valueOf(productUtil.getRandomInt());
			model.put("msg", "Product added successfully!");
			mobileService.addMobile(mobile,productId);
			}else{
				//**old product for update ****//
				productId=mobile.getProductId();
				mobileService.updateMobile(mobile);
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("mobile",mobileService.getMobile(productId));
			return "showMobile";
		}
		
	}

	
	@RequestMapping(value = "/camera/AddProduct", method = RequestMethod.POST)
	public String addProductForCameraFormPost( HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("productForm")camera camera) {
		
		System.out.println(camera.toString());
		try {
			return productUtil.postProcessForm(camera, "camera", model);
		} catch (Exception e) {
			e.printStackTrace();
			return "404NotFound";
		}
	
	}
	
	@RequestMapping(value = "/tablet/AddProduct", method = RequestMethod.POST)
	public String addProductForTabletFormPost( HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("productForm")Tablet tablet) {
		
		System.out.println(tablet.toString());
		try {
			
			return productUtil.postProcessForm(tablet, "tablet", model);
		} catch (Exception e) {
			e.printStackTrace();
			return "404NotFound";
		}
	
	}
	
	@RequestMapping(value = "/laptop/AddProduct", method = RequestMethod.POST)
	public String addProductForLaptopFormPost( HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("productForm")Laptop laptop) {
		
		System.out.println(laptop.toString());
		try {
			return productUtil.postProcessForm(laptop, "laptop", model);
		} catch (Exception e) {
			e.printStackTrace();
			return "404NotFound";
		}
	
	}
	@RequestMapping(value = "/headphone/AddProduct", method = RequestMethod.POST)
	public String addProductForHeadphoneFormPost( HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("productForm")Headphone headphone) {
		
		System.out.println(headphone.toString());
		try {
			return productUtil.postProcessForm(headphone, "headphone", model);
		} catch (Exception e) {
			e.printStackTrace();
			return "404NotFound";
		}
	
	}
	
	
	
	
	
	
	//*****************************************************************************************************//
	//***************************TEST METHODS***************************************************************//
	
	
	
	 @RequestMapping(value = "/fetchRequiredProductDescriptionKeys/{category}",method = RequestMethod.GET)
		public @ResponseBody String fetchRequiredProductDescriptionKeys(@PathVariable("category") String category) {
		     return fetchRequiredProductDescriptionKeysUtil.getProductCategoryKeyMap().get(category.toLowerCase().trim());
		}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET ,produces="text/plain")
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}
    
	@RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
	public ModelAndView welcome() {
		  return new ModelAndView("index");  
	}
	
	@RequestMapping(value = "/AsyncTest", method = RequestMethod.GET ,produces="text/plain")
	public Callable<String> callableWithView(final ModelMap model) {
		return new Callable<String>() {			
			public String call() throws Exception {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(5000);
				model.addAttribute("test", "sjkdkl");
				return "addProduct";
			}
		};
	}
	@RequestMapping(value = "/AsyncMethodTest", method = RequestMethod.GET ,produces="text/plain")
	public String asyncMethodTest(ModelMap model) {
		model.addAttribute("test", "sjkdkl");
		System.out.println(Thread.currentThread().getName());
		fetchRequiredProductDescriptionKeysUtil.asyncTest();
		return "addProduct";
	}
    public void defaultModelMethod(ModelMap model){
    	ArrayList<String> colorList=new ArrayList<String>();
		colorList.add("Green");
		colorList.add("Red");
		colorList.add("Blue");
		model.put("colorList",colorList);
		
    }
    @RequestMapping(value = "/concurrencyTest/{num}", method = RequestMethod.GET ,produces="text/plain")
    public void concurrencyTest(@PathVariable("num") String number,HttpServletRequest req, HttpServletResponse resp){
		try {
			System.out.println("Servlet no. "+number+" called.");
			Thread.sleep(100000000);
			//get = callServer(number);
			System.out.println("Servlet no."+number+" returning.");
			resp.setContentType("text/plain");
			resp.getWriter().write("OK "+number+"\n");
    }catch(Exception e){
    	e.printStackTrace();
    }

}

	
}
