package com.planb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.SerializableDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods;
import com.planb.dao.mobile.Mobile;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.product.util.EUtil;

@Service
public class MobileService {
  
	@Autowired
	CouchbaseUtilityMethods couchbaseUtilityMethods;
   
	@Autowired
	EUtil productUtil;
	
	@Value("${product.bucket.client.name}")
	String productBucketClientName;
	
	@Value("${master.mobile.key}")
	String masterMobileKey;
	
	ObjectMapper mapper;
	
	@PostConstruct
	void init(){
		mapper = new ObjectMapper();
	}
	
	
	public void addMobile(Mobile mobile,String ProductId) throws Exception{
		JsonDocument newDoc;
		mobile.setProductId(ProductId);
		mobile.setCreatedOn(productUtil.getCurrentDateAndTime());
		mobile.setUpdatedOn(productUtil.getCurrentDateAndTime());
		
		JsonObject jobj;
		JsonDocument jdoc;
		
		try {
			 jobj = JsonObject.fromJson(mapper.writeValueAsString(mobile));
			 jdoc = JsonDocument.create(ProductId,jobj);
			 couchbaseUtilityMethods.upsert(jdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//SerializableDocument sdoc=SerializableDocument.create(ProductId,mobile);
		//couchbaseUtilityMethods.upsertSerializable(sdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		JsonDocument doc=couchbaseUtilityMethods.get(masterMobileKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));   
	      if(doc == null){
	    	  JsonObject content = JsonObject.empty().put("AllIds", ProductId);
	    	 newDoc = JsonDocument.create(masterMobileKey,content);
	    	 
	      }else{
	    	  String value=doc.content().getString("AllIds");
	    	  JsonObject content = JsonObject.empty().put("AllIds", value+","+ProductId);
	    	  newDoc = JsonDocument.create(masterMobileKey,content);
	    	  
	      }
	      couchbaseUtilityMethods.upsert(newDoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	}
	public void updateMobile(Mobile mobile){
		mobile.setUpdatedOn(productUtil.getCurrentDateAndTime());
		
		
		JsonObject jobj;
		JsonDocument jdoc;
		
		try {
			 jobj = JsonObject.fromJson(mapper.writeValueAsString(mobile));
			 jdoc = JsonDocument.create(mobile.getProductId(),jobj);
			 couchbaseUtilityMethods.upsert(jdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//SerializableDocument sdoc=SerializableDocument.create(mobile.getProductId(),mobile);
		//couchbaseUtilityMethods.upsertSerializable(sdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	
		
	}
	public Mobile getMobile(String productId){
		
		JsonDocument jsonDocument = couchbaseUtilityMethods.get(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		Mobile obj = null;
		if(jsonDocument != null){
		String json = jsonDocument.content().toString();
		try {
		 obj = mapper.readValue(json, Mobile.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		
		return obj;
		
		/*SerializableDocument found = couchbaseUtilityMethods.getSerialzable(productId,couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		if(found!=null){
		return (Mobile)found.content();
		}else{
			return null;
		}
*/		
	}
	@Async
	public void deleteMobile(String productId){
		couchbaseUtilityMethods.delete(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		//couchbaseUtilityMethods.deleteSerialzable(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	  
		JsonDocument doc=couchbaseUtilityMethods.get(masterMobileKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));   
	      if(doc != null){
	    	  String value=doc.content().getString("AllIds");
	    	  String values[]=value.split(",");
	    	  value="";
	    	  for(String str:values){
	    		  if(!str.equalsIgnoreCase(productId))
	    			  value+=str+",";
	    	  }
	    	  value=value.substring(0, value.length()-1);
	    	  JsonObject content = JsonObject.empty().put("AllIds", value);
	    	  couchbaseUtilityMethods.upsert(JsonDocument.create(masterMobileKey,content), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	    	  
	      }
	    

	
	}
	public List<Mobile> getAllMobileList(){
		JsonDocument doc=couchbaseUtilityMethods.get(masterMobileKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));   
	      if(doc == null){
	    	return null;
	      }else{
	    	  
	    	  
	    	  String value=doc.content().getString("AllIds");
	    	  String values[]=value.split(",");
	 	//List<SerializableDocument> SerMobileList=couchbaseUtilityMethods.multiGetSerialzable(Arrays.asList(values), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	     List<Mobile> mobileList=new ArrayList<Mobile>();
	     /*for(SerializableDocument sdoc:SerMobileList){
	    	 mobileList.add((Mobile)sdoc.content());
	     }*/
	     
    	 List<JsonDocument> JsonProductList=couchbaseUtilityMethods.multiGet(Arrays.asList(values), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
    	 for(JsonDocument jdoc:JsonProductList){
    		 try {
    			 mobileList.add(mapper.readValue(jdoc.content().toString(), Mobile.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	 }

	     
	      return mobileList;
	      }
	}
}
