package com.planb.product.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.planb.dao.camera.camera;
import com.planb.dao.camera.cameraSubFeature.CameraBattery;
import com.planb.dao.camera.cameraSubFeature.CameraStorage;
import com.planb.dao.camera.cameraSubFeature.Exposure;
import com.planb.dao.camera.cameraSubFeature.Focus;
import com.planb.dao.camera.cameraSubFeature.Lens;
import com.planb.dao.camera.cameraSubFeature.Overview;
import com.planb.dao.camera.cameraSubFeature.Sensor;
import com.planb.dao.camera.cameraSubFeature.VideoRecording;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.laptop.laptopSubFeatures.Connectivity;
import com.planb.dao.laptop.laptopSubFeatures.Graphics;
import com.planb.dao.laptop.laptopSubFeatures.Input;
import com.planb.dao.laptop.laptopSubFeatures.LaptopDisplay;
import com.planb.dao.laptop.laptopSubFeatures.Memory;
import com.planb.dao.laptop.laptopSubFeatures.Processor;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.mobile.mobileSubFeature.Battery;
import com.planb.dao.mobile.mobileSubFeature.Camera;
import com.planb.dao.mobile.mobileSubFeature.ConnectivityAndFeatures;
import com.planb.dao.mobile.mobileSubFeature.Display;
import com.planb.dao.mobile.mobileSubFeature.OverView;
import com.planb.dao.mobile.mobileSubFeature.Storage;
import com.planb.dao.tablet.Tablet;

@Service
public class FetchRequiredProductDescriptionKeysUtil implements InitializingBean {

	Map<String,String> productCategoryKeyMap=new HashMap<String,String>();
	Map<String,HashMap<String,ArrayList<String>>> productCategoryKeyMapInKeyValueForm=new HashMap<String,HashMap<String,ArrayList<String>>>();

	
	public void afterPropertiesSet() throws Exception {/*
		Mobile mobile=new Mobile();
		mobile.setBattery(new Battery());
        mobile.setCamera(new Camera());		
        mobile.setConnectivityAndFeatures(new ConnectivityAndFeatures());
        mobile.setDisplay(new Display());
        mobile.setOverView(new OverView());
        mobile.setStorage(new Storage());
		ObjectMapper mapper = new ObjectMapper();
		String mobileCategoryKeyJson=mapper.writeValueAsString(mobile);
		productCategoryKeyMap.put("mobile", mobileCategoryKeyJson);
		productCategoryKeyMap.put("smartphone", mobileCategoryKeyJson);
		
		
		camera cameraa=new camera();
		cameraa.setExposure(new Exposure());
		cameraa.setFocus(new Focus());
		cameraa.setLens(new Lens());
		cameraa.setOverView(new Overview());
		cameraa.setSensor(new Sensor());
		cameraa.setCameraStorage(new CameraStorage());
		cameraa.setVideoRecording(new VideoRecording());
		cameraa.setCameraBattery(new CameraBattery());
		String cameraCategoryKeyJson=mapper.writeValueAsString(cameraa);
		productCategoryKeyMap.put("camera", cameraCategoryKeyJson);
		
		
		
		
		Tablet tablet=new Tablet();
		tablet.setOverView(new OverView());
		tablet.setCamera(new Camera());
		tablet.setDisplay(new Display());
		tablet.setBattery(new Battery());
		tablet.setStorage(new Storage());
		String tabletCategoryKeyJson=mapper.writeValueAsString(tablet);
		productCategoryKeyMap.put("tablet", tabletCategoryKeyJson);
		
		
		Laptop laptop =new Laptop();
		String laptopCategoryKeyJson=mapper.writeValueAsString(laptop);
		productCategoryKeyMap.put("laptop", laptopCategoryKeyJson);
		
		Headphone headPhone=new Headphone();
		String headPhoneCategoryKeyJson=mapper.writeValueAsString(headPhone);
		productCategoryKeyMap.put("headphone", headPhoneCategoryKeyJson);
		
		
		List<Object> list=new ArrayList<Object>();
		list.add(mobile);
		list.add(cameraa);
		list.add(tablet);
		list.add(laptop);
		list.add(headPhone);
		
		for(Object obj:list){
		
		HashMap<String, ArrayList<String>> fieldMap=new HashMap<String,ArrayList<String>>();
		ArrayList<String> fieldList=new ArrayList<String>();
		//System.out.println(Arrays.asList(obj.getClass().getDeclaredFields()));
		for (Field field : obj.getClass().getDeclaredFields()) {
			
			
		    field.setAccessible(true); // You might want to set modifier to public first.
		      if (field.getType().equals(String.class) ||field.getType().equals(double.class)  || field.getType().equals(int.class) ||field.getType().equals(boolean.class) ) {
		    	  fieldList.add(field.getName());
		    	  
		      }else if(field.getType().equals(List.class)){
		    	  continue;
		    	  
			}else{
				 ArrayList<String> SubfieldList=new ArrayList<String>();
				 
		    	  for(Field f1 : Class.forName(field.getType().getName()).newInstance().getClass().getDeclaredFields()){
		    		 if (f1.getType().equals(List.class)) {
		    		  continue;
		    		 }else{  SubfieldList.add(f1.getName());
		    		 }
		    	  }
		    	  fieldMap.put(field.getName(), SubfieldList);
			}
		}
		fieldMap.put("mainFeatures", fieldList);
		
		
		
		if(obj.equals(mobile)){
		productCategoryKeyMapInKeyValueForm.put("mobile",fieldMap);
		productCategoryKeyMapInKeyValueForm.put("smartphone",fieldMap);
		}else{
			productCategoryKeyMapInKeyValueForm.put(obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf('.')+1, obj.getClass().getName().length()).toLowerCase(),fieldMap);
		}
		}
		
	*/}
	
	
	


	public Map<String, HashMap<String, ArrayList<String>>> getProductCategoryKeyMapInKeyValueForm() {
		return productCategoryKeyMapInKeyValueForm;
	}





	public void setProductCategoryKeyMapInKeyValueForm(Map<String, HashMap<String, ArrayList<String>>> productCategoryKeyMapInKeyValueForm) {
		this.productCategoryKeyMapInKeyValueForm = productCategoryKeyMapInKeyValueForm;
	}





	public Map<String, String> getProductCategoryKeyMap() {
		return productCategoryKeyMap;
	}





	public void setProductCategoryKeyMap(Map<String, String> productCategoryKeyMap) {
		this.productCategoryKeyMap = productCategoryKeyMap;
	}
	@Async("taskExecutor")
	public void asyncTest(){
		try{
			System.out.println("inside asynMethod");
			Thread.sleep(50000);
			System.out.println("async method done");
		}catch(Exception e){
			
		}
	}
	
	
public static void main(String[] args) throws Exception {
	Mobile m=new Mobile();
	int p;
	//System.out.println(m.toString());
	//System.out.println(new ObjectMapper().writeValueAsString(m));
	FetchRequiredProductDescriptionKeysUtil obj=new FetchRequiredProductDescriptionKeysUtil();
	obj.afterPropertiesSet();
	//System.out.println(obj.productCategoryKeyMapInKeyValueForm);
	System.out.println(obj.productCategoryKeyMap.get("headPhone"));
}

}
