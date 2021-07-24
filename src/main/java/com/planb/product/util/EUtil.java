package com.planb.product.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.tablet.Tablet;
import com.planb.dao.user.UserFavProduct;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.service.ProductService;
@Service
public class EUtil implements InitializingBean{

	@Autowired
	ProductService productService;
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	
	HttpClient client;
	
	Random random;
    DateFormat dateFormat ;
    DateFormat dateFormatUpdated;
    
	Date date;
	Map<String,String> frontUIHeaderUrlLookup;
	Cloudinary cloudinary;
	
	Map<Integer,String> ratingTitleMap;
	
	public void afterPropertiesSet() throws Exception {
		
		//populating frontUIHeaderUrlLookup map at the server startup time
		
		populateFrontUIHeaderUrlLookupMap();
		
		//universal date format for whole application
		dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateFormatUpdated=new SimpleDateFormat("dd-MM-yyyy");
		
		MultiThreadedHttpConnectionManager connectionManager =	new MultiThreadedHttpConnectionManager();
	  	/*connectionManager.setMaxTotalConnections(32000);
	  	connectionManager.setMaxConnectionsPerHost(32000);*/
	  	client = new HttpClient(connectionManager);
		random=new Random(); 
		 cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "planb",
				  "api_key", "359772246374455",
				  "api_secret", "FZ2xj9siN-q9nt4AcK_5YJw-Js8"));
		 populateRatingTitleMap();
		
		
	}
	
	
	private void populateRatingTitleMap() {
		ratingTitleMap=new HashMap<Integer,String>();	
		ratingTitleMap.put(0, ElectConstants.ZERO_RATINIG);
		ratingTitleMap.put(1, ElectConstants.ONE_RATINIG);ratingTitleMap.put(2, ElectConstants.TWO_RATINIG);
		ratingTitleMap.put(3, ElectConstants.THREE_RATINIG);ratingTitleMap.put(4, ElectConstants.FOUR_RATINIG);
	}


	/*
	 * 
	 */
	public void populateProductMetaData(ReviewDetails reviewDetails) throws Exception{
		ElectInMemoryService electInMemoryService=electInMemoryFactory.getElectInMemoryService(reviewDetails.getCategory());
		 electInMemoryService.populateMetaDataService(reviewDetails);
		
		
	}
	
	public UserFavProduct populateUserFavMetaData(String productId,String category){
		ElectInMemoryService electInMemoryService=electInMemoryFactory.getElectInMemoryService(category);
		String currentDate=getStringFormatFromDateFormat(getCurrentDate());
		return electInMemoryService.populateUserFavProductMetaDta(productId,currentDate);
	}
 
	private void populateFrontUIHeaderUrlLookupMap() {
		frontUIHeaderUrlLookup =new HashMap<String,String>();
		frontUIHeaderUrlLookup.put(ElectConstants.TRENDING_GADGETS, ElectConstants.TRENING_GADGETS_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.LATEST_SMARTPHONES, ElectConstants.LATEST_SMARTPHONES_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.LATEST_CAMERAS, ElectConstants.LATEST_CAMERAS_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.LATEST_TABLETS, ElectConstants.LATEST_TABLETS_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.LATEST_LAPTOPS, ElectConstants.LATEST_LAPTOPS_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.LATEST_HEADPHONES, ElectConstants.LATEST_HEADPHONES_URL);
		frontUIHeaderUrlLookup.put(ElectConstants.TECH_BLOGS, ElectConstants.TECH_BLOGS_URL);
		
	}

	
	/*
	 * Utility method for converting date format to String format
	 * 
	 */
	public String getStringFormatFromDateFormat(Date date){
		String stringDate=null;
		
		if(date != null)
		stringDate= dateFormat.format(date);
		
		return stringDate;
	}
	
	/*
	 * utility method for converting String to Date format
	 * 
	 */
	
	public Date getDateFormatFromStringFormat(String stringDate){
		Date date=null;
		
		if(stringDate !=null && !stringDate.equals("")){
			try {
			date=dateFormat.parse(stringDate);
			} catch (ParseException e) {
				DateFormat atedateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				try {
					date=atedateFormat.parse(stringDate);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
	}
		return date;
			
		
	}
	
	
	 //increase the date
	public  Date increaseDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); 
        return cal.getTime();
    }

	
	//updated date in 20-04-2016 like format
	public String getUpdatedCurrentDate(){
		Date currentDate=new Date();
		return dateFormatUpdated.format(currentDate);
		
	}
	
	public int getRandomInt(){
		return random.nextInt(Integer.SIZE-1);
	}

	
	public String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	public String getCurrentDateAndTime(){
		
		date = new Date();
		return  dateFormat.format(date);
	}
	
	public Date getCurrentDate(){
		return new Date();
		
		
	}
	
	public String postProcessForm(Object obj,String category,ModelMap model) throws Exception{
		if(category.equalsIgnoreCase("mobile") || category.equalsIgnoreCase("smartphone")){
			String productId;
			Mobile mobile=(Mobile)obj;
			//*************new product for add****//
			if(mobile.getProductId() == null || "".equalsIgnoreCase(mobile.getProductId()) ){
			
				//if use randomInt,u can't able to fetch product details with only name and brand.
			//productId="mobile_"+mobile.getName()+"_"+mobile.getBrand()+"_"+String.valueOf(getRandomInt());
			
			productId="mobile_"+mobile.getName()+"_"+mobile.getBrand();	
			mobile.setProductId(productId);
			mobile.setCreatedOn(getCurrentDateAndTime());
			mobile.setUpdatedOn(getCurrentDateAndTime());
			productService.addProduct(mobile, "mobile", productId);
			model.put("msg", "Product added successfully!");
			}else{
				//**old product for update ****//
				productId=mobile.getProductId();
				productService.updateProduct(productId, mobile, "mobile");
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("mobile",(Mobile)productService.getProduct(productId, "mobile"));
			return "showMobile";
		}else if(category.equalsIgnoreCase("camera")){
			String productId;
			camera camera=(camera)obj;
			//*************new product for add****//
			if(camera.getProductId() == null || "".equalsIgnoreCase(camera.getProductId()) ){
			productId="camera_"+camera.getName()+"_"+camera.getBrand()+"_"+String.valueOf(getRandomInt());
			camera.setProductId(productId);
			camera.setCreatedOn(getCurrentDateAndTime());
			camera.setUpdatedOn(getCurrentDateAndTime());
			productService.addProduct(camera, "camera", productId);
			model.put("msg", "Product added successfully!");
			}else{
				//**old product for update ****//
				productId=camera.getProductId();
				productService.updateProduct(productId, camera, "camera");
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("camera",(camera)productService.getProduct(productId, "camera"));
			return "showCamera";
		}else if(category.equalsIgnoreCase("tablet")){
			String productId;
			Tablet tablet=(Tablet)obj;
			//*************new product for add****//
			if(tablet.getProductId() == null || "".equalsIgnoreCase(tablet.getProductId()) ){
			productId="tablet_"+tablet.getName()+"_"+tablet.getBrand()+"_"+String.valueOf(getRandomInt());
			tablet.setProductId(productId);
			tablet.setCreatedOn(getCurrentDateAndTime());
			tablet.setUpdatedOn(getCurrentDateAndTime());
			productService.addProduct(tablet, "tablet", productId);
			model.put("msg", "Product added successfully!");
			}else{
				//**old product for update ****//
				productId=tablet.getProductId();
				productService.updateProduct(productId, tablet, "tablet");
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("tablet",(Tablet)productService.getProduct(productId, "tablet"));
			return "showTablet";	
		}else if(category.equalsIgnoreCase("laptop")){
			String productId;
			Laptop laptop=(Laptop)obj;
			//*************new product for add****//
			if(laptop.getProductId() == null || "".equalsIgnoreCase(laptop.getProductId()) ){
			productId="laptop_"+laptop.getName()+"_"+laptop.getBrand()+"_"+String.valueOf(getRandomInt());
			laptop.setProductId(productId);
			laptop.setCreatedOn(getCurrentDateAndTime());
			laptop.setUpdatedOn(getCurrentDateAndTime());
			productService.addProduct(laptop, "laptop", productId);
			model.put("msg", "Product added successfully!");
			}else{
				//**old product for update ****//
				productId=laptop.getProductId();
				productService.updateProduct(productId, laptop, "laptop");
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("laptop",(Laptop)productService.getProduct(productId, "laptop"));
			return "showLaptop";	
		}else if(category.equalsIgnoreCase("headphone")){
			String productId;
			Headphone headphone=(Headphone)obj;
			//*************new product for add****//
			if(headphone.getProductId() == null || "".equalsIgnoreCase(headphone.getProductId()) ){
			productId="headphone_"+headphone.getName()+"_"+headphone.getBrand()+"_"+String.valueOf(getRandomInt());
			headphone.setProductId(productId);
			headphone.setCreatedOn(getCurrentDateAndTime());
			headphone.setUpdatedOn(getCurrentDateAndTime());
			productService.addProduct(headphone, "headphone", productId);
			model.put("msg", "Product added successfully!");
			}else{
				//**old product for update ****//
				productId=headphone.getProductId();
				productService.updateProduct(productId, headphone, "headphone");
				model.put("msg", "Product updated successfully!");
				
			}
			
			model.put("css","success");
			model.put("headphone",(Headphone)productService.getProduct(productId, "headphone"));
			return "showHeadphone";	
		}
		return "404notFound";	
	}

	public HttpClient getClient() {
		return client;
	}

	public Map<String, String> getFrontUIHeaderUrlLookup() {
		return frontUIHeaderUrlLookup;
	}


	public Cloudinary getCloudinary() {
		return cloudinary;
	}


	public void setCloudinary(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}


	public Map<Integer, String> getRatingTitleMap() {
		return ratingTitleMap;
	}


	public void setRatingTitleMap(Map<Integer, String> ratingTitleMap) {
		this.ratingTitleMap = ratingTitleMap;
	}


	public void addUserinProductFavList(String productId, String category, String userEmail) {
      ElectInMemoryService electInMemoryService=electInMemoryFactory.getElectInMemoryService(category);
      electInMemoryService.addUserInProductFavList(productId, userEmail);
		
		
	}
	
	public void removeUserFromProductFavList(String productId, String category, String userEmail) {
		ElectInMemoryService electInMemoryService=electInMemoryFactory.getElectInMemoryService(category);
		electInMemoryService.removeUserFromProductFavList(productId, userEmail);
		
	}



	
	
}
