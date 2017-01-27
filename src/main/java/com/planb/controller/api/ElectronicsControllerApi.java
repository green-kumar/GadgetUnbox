package com.planb.controller.api;

import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.planb.dao.camera.camera;
import com.planb.dao.mobile.Mobile;


//@Controller
/*@RequestMapping("/electronics")*/
public interface ElectronicsControllerApi {
	
	//add encryption in  all api so that everyone can't access or make api private
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)  
	public ModelAndView welcome();
	
   @RequestMapping(value = "/fetchAllProductDetails/{category}",method = RequestMethod.GET)
   public  String fetchAllProductDetails(@PathVariable("category") String category,ModelMap model);

	
    @RequestMapping(value = "/fetchProductDetails/{category}/{productId}",method = RequestMethod.GET)
    public  String fetchProductDetails(@PathVariable("category") String category,@PathVariable("productId") String productId);
	
    //This method accept ids of product in request body in json format
    @RequestMapping(value = "/multiFetchProductDetails/{category}",method = RequestMethod.GET,produces="application/json")
    public  String multiFetchProductDetails(@PathVariable("category") String category,@PathVariable("productId") String productId);

  //accepst product details for single product or multi product in json arraylist return product id
  	@RequestMapping(value = "/AddProduct/{category}", method = RequestMethod.GET)
  	public String addProductForCategoryFormGet(@PathVariable("category") String category,HttpServletRequest request, HttpServletResponse response,ModelMap model);
   
  	@RequestMapping(value = "/AddProduct", method = RequestMethod.GET)
  	public String addProductForm(HttpServletRequest request, HttpServletResponse response,ModelMap model);

    
    
	//accepst product details for single product or multi product in json arraylist return product id
	@RequestMapping(value = "/AddProduct/{category}", method = RequestMethod.POST)
	public String addProductForCategoryFormPost(@PathVariable("category") String category,HttpServletRequest request, HttpServletResponse response,ModelMap model,Model m/*@ModelAttribute("productForm")Object obj*/);
	
	
	@RequestMapping(value = "/updateProduct/{category}/{productId}", method = RequestMethod.GET)
	public  String updateProduct(@PathVariable("category") String category,@PathVariable("productId") String productId, ModelMap model);
	
	
	@RequestMapping(value="/deleteProduct/{category}/{productId}",method = RequestMethod.GET,produces="text/plain")
	public  String deleteProduct(@PathVariable("category") String category,@PathVariable("productId") String productId);
	
	@RequestMapping(value="/multiDeleteProduct/{category}/{productId}",method = RequestMethod.GET,produces="text/plain")
	public  String multiDeleteProduct(@PathVariable("category") String category,@PathVariable("productId") String productId);

	@RequestMapping(value = "/fetchRequiredProductDescriptionKeys/{category}",method = RequestMethod.GET,produces="application/json")
	 public  @ResponseBody String fetchRequiredProductDescriptionKeys(@PathVariable("category") String category);

	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET ,produces="text/plain")
	public  String test();
	
	@RequestMapping(value = "/AsyncTest", method = RequestMethod.GET ,produces="text/plain")
	public Callable<String> callableWithView(final ModelMap model);
	
	@RequestMapping(value = "/AsyncMethodTest", method = RequestMethod.GET ,produces="text/plain")
	public  String asyncMethodTest(final ModelMap model);
	
	
	
	

}


