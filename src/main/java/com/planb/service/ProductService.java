package com.planb.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods;
import com.planb.dao.mobile.Mobile;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.product.util.EUtil;

@Service
public class ProductService {
	@Autowired
	CouchbaseUtilityMethods couchbaseUtilityMethods;
   
	@Autowired
	EUtil productUtil;
	
	@Value("${product.bucket.client.name}")
	String productBucketClientName;
	
	@Value("${search.bucket.client.name}")
	String searchBucketClientName;
	
	@Value("${master.mobile.key}")
	String mastermobileKey;
	
	@Value("${master.camera.key}")
	String mastercameraKey;
	
	@Value("${master.tablet.key}")
	String mastertabletKey;
	
	@Value("${master.laptop.key}")
	String masterlaptopKey;
	
	@Value("${master.headphone.key}")
	String masterheadphoneKey;
	
	@Value("${master.user.key}")
	String masteruserKey;
	
	@Value("${master.review.key}")
	String masterreviewKey;
	
	ObjectMapper mapper;
	
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	Map<String,String> categoryToBucketMap;
	
	@PostConstruct
	void init(){
		mapper = new ObjectMapper();
		categoryToBucketMap = new HashMap<String,String>();
		categoryToBucketMap.put(ProductEnum.CAMERA.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.MOBILE.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.LAPTOP.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.TABLET.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.HEADPHONE.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.REVIEW.toString(), productBucketClientName);
		categoryToBucketMap.put(ProductEnum.USER.toString(), productBucketClientName);
		
		categoryToBucketMap.put(ProductEnum.SEARCH.toString(), searchBucketClientName);
		categoryToBucketMap.put(ProductEnum.SEARCH_TOKEN.toString(), searchBucketClientName);
		
		
	}
	
	
	public void addProduct(Object obj,String category,String productId){
		
			String productBucketClientName=categoryToBucketMap.get(category);
		
		JsonObject jobj;
		JsonDocument jdoc;
		
		try {
			 jobj = JsonObject.fromJson(mapper.writeValueAsString(obj));
			 jdoc = JsonDocument.create(productId,jobj);
			 couchbaseUtilityMethods.upsert(jdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JsonDocument newDoc;
		//inserting product in Couchbase
		//SerializableDocument sdoc=SerializableDocument.create(productId,(Serializable) obj);
		//couchbaseUtilityMethods.upsertSerializable(sdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		
		//Adding or Updating master key
		String masterProductKey="master"+category+"Key";
		
		JsonDocument doc=couchbaseUtilityMethods.get(masterProductKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));   
	      if(doc == null){
	    	  JsonObject content = JsonObject.empty().put("AllIds", productId);
	    	 newDoc = JsonDocument.create(masterProductKey,content);
	    	 couchbaseUtilityMethods.upsert(newDoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	    	 
	      }else{
	    	  String value=doc.content().getString("AllIds");
	    	  List<String> MasterKeyList = Arrays.asList(value.split(","));
	    	  if(!MasterKeyList.contains(productId)){
	    	  JsonObject content = JsonObject.empty().put("AllIds", value+","+productId);
	    	  newDoc = JsonDocument.create(masterProductKey,content);
	    	  couchbaseUtilityMethods.upsert(newDoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	    	  }
	    	  
	      }
	      
	
		
		
		
	}
	@SuppressWarnings("unchecked")
	public Object getProduct(String productId,String category){
		String productBucketClientName=categoryToBucketMap.get(category);	
		JsonDocument jsonDocument = couchbaseUtilityMethods.get(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		Object obj = null;
		if(jsonDocument != null){
		ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(category);
		String json = jsonDocument.content().toString();
		try {
		 obj = mapper.readValue(json, electInMemoryService.getClassName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		
		return obj;
		
		/*SerializableDocument found=couchbaseUtilityMethods.getSerialzable(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		if(found !=null){
		return	(Object)found.content();
		}
		return null;*/
	}
	//@Async
	public void deleteProduct(String productId,String category){
		String productBucketClientName=categoryToBucketMap.get(category);	
		couchbaseUtilityMethods.delete(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		
		//couchbaseUtilityMethods.deleteSerialzable(productId, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
		String masterProductKey="master"+category+"Key";
		
		JsonDocument doc=couchbaseUtilityMethods.get(masterProductKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));   
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
	    	  couchbaseUtilityMethods.upsert(JsonDocument.create(masterProductKey,content), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	    	  
	      }
	    
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllProduct(String category){
		String productBucketClientName=categoryToBucketMap.get(category);		
		String masterProductKey="master"+category+"Key";
		JsonDocument doc=couchbaseUtilityMethods.get(masterProductKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	     if(doc == null){
	    	 return null;
	     }else{
	    	 ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(category);
	    	 List<Object> list=new ArrayList<Object>();
	    	 String productId=doc.content().getString("AllIds");
	    	 String productIds[]=productId.split(",");
	    	 List<JsonDocument> JsonProductList=couchbaseUtilityMethods.multiGet(Arrays.asList(productIds), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	    	 for(JsonDocument jdoc:JsonProductList){
	    		 try {
					list.add(mapper.readValue(jdoc.content().toString(), electInMemoryService.getClassName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	 }
	    	 
	    	 /*List<SerializableDocument> SerProductList=couchbaseUtilityMethods.multiGetSerialzable(Arrays.asList(productIds), couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	         for(SerializableDocument sdoc:SerProductList){
	        	 list.add((Object)sdoc.content());
	        	 
	         }*/
	         return list;
	     }
	 
	}

	public void updateProduct(String productId,Object obj,String category){
		String productBucketClientName=categoryToBucketMap.get(category);		
		JsonObject jobj;
		JsonDocument jdoc;
		
		try {
			 jobj = JsonObject.fromJson(mapper.writeValueAsString(obj));
			 jdoc = JsonDocument.create(productId,jobj);
			 couchbaseUtilityMethods.upsert(jdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*SerializableDocument sdoc=SerializableDocument.create(productId,(Serializable) obj);
		couchbaseUtilityMethods.upsertSerializable(sdoc, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
*/		
	}
	public List<String> getAllMasterKeyId(String category){
		String productBucketClientName=categoryToBucketMap.get(category);		
		String masterProductKey="master"+category+"Key";
		JsonDocument doc=couchbaseUtilityMethods.get(masterProductKey, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
	     if(doc == null){
	    	 return null;
	     }else{
	    	 String productId=doc.content().getString("AllIds");
	    	 String productIds[]=productId.split(",");
	    	 List<String> list=Arrays.asList(productIds);
	         return list;
	     }
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> multiGet(List<String> ids,String category){
		String productBucketClientName=categoryToBucketMap.get(category);	
    if(ids==null || ids.size()==0){
    	return null;
    }else{
   	 List<Object> list=new ArrayList<Object>();
   	 ElectInMemoryService eSer = electInMemoryFactory.getElectInMemoryService(category);
   	 /*List<SerializableDocument> SerProductList=couchbaseUtilityMethods.multiGetSerialzable(ids, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
        for(SerializableDocument sdoc:SerProductList){
       	 list.add((Object)sdoc.content());
       	 
        }*/
   	 List<JsonDocument> Jlist = couchbaseUtilityMethods.multiGet(ids, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName));
   	 
   	 for(JsonDocument jsonDocument : Jlist){
   		 try {
			list.add(mapper.readValue(jsonDocument.content().toString(), eSer.getClassName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
   		 
   	 }
   	 
        return list;
    }
	}
	
	
	public static void main(String[] args) throws ParseException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		ProductService productService=context.getBean("productService",ProductService.class);
		
			List<Object> objList = productService.getAllProduct(ProductEnum.MOBILE.toString());
		
		
		
		List<Mobile> mobileList = objList.stream().map(obj -> (Mobile)obj).collect(Collectors.toList());
	for(Mobile m : mobileList){
			try{
			SimpleDateFormat 	dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");;
            Date convertedDate = dateFormat.parse(m.getCreatedOn());
			String  d = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(convertedDate); 
			m.setCreatedOn(d);
			productService.updateProduct(m.getProductId(), m, ProductEnum.MOBILE.toString());
		System.out.println(((Mobile)(productService.getProduct(m.getProductId(), ProductEnum.MOBILE.toString()))).getCreatedOn());
			}catch(Exception e){
				e.getMessage();
			}
	}
	  
	  System.out.println("done");
	
	
	}
	
}
