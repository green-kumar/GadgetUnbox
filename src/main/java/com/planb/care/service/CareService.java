package com.planb.care.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.planb.care.dao.CareSearchResultDao;
import com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods;
import com.planb.dao.camera.camera;
import com.planb.dao.headphone.Headphone;
import com.planb.dao.laptop.Laptop;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.mobile.mobileSubFeature.Camera;
import com.planb.dao.tablet.Tablet;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.product.util.EUtil;
import com.planb.service.ProductService;

@Service
public class CareService {

	@Autowired
	CouchbaseUtilityMethods couchbaseUtilityMethods;
	
	@Autowired
	EUtil eUtil;
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	@Autowired
	ProductService productService;
	
	@Value("${product.bucket.client.name}")
	String productBucketClientName;
	
    public List<String> getAutoCompleteResult(String brand,String name,String category,String req){
    	String query = "";
        if(req.equalsIgnoreCase("brand")){
        	
        	if(!StringUtils.isBlank(name))
        		query =	"SELECT DISTINCT brand from "+productBucketClientName+" where brand LIKE \""+brand+"%\" and name = \""+name+"\"";
        	else
        		query =	"SELECT DISTINCT brand from "+productBucketClientName+" where brand LIKE \""+brand+"%\"";
        	
        }else if(req.equalsIgnoreCase("name")){
        	
        	if(!StringUtils.isBlank(brand))
        		query =	"SELECT DISTINCT name from "+productBucketClientName+" where name LIKE \""+name+"%\" and brand = \""+brand+"\"";
        	else
        		query =	"SELECT DISTINCT name from "+productBucketClientName+" where name LIKE \""+name+"%\"";
        	
        }
        return couchbaseUtilityMethods.getCareAutoCompleteResult(query, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName), req);
    }

    public List<CareSearchResultDao> getCareSearchResult(String brand,String name,String category,String orderBy,String sort,int limit,int offset,ModelMap model) throws JsonParseException, JsonMappingException, IOException{
    
    	boolean isEligbleToGetResultFromMasterKey = false;
    	List<CareSearchResultDao> searchResult  = null;
    	String review = "review";
    	StringBuffer sb=new StringBuffer();
    	sb.append("SELECT * from ").append(productBucketClientName);
    	String query="";
    	sb.append(" where");
    	sb.append(" category = \""+category+"\"");
    	sb.append(" and productId");
    	sb.append(" not like \""+review+"%\"");
    	
    	if(StringUtils.isBlank(brand) && StringUtils.isBlank(name)){
    		isEligbleToGetResultFromMasterKey = true;
    	}else{
    		sb.append(" and");
    		
    		if(!StringUtils.isBlank(brand) && StringUtils.isBlank(name)){
    			sb.append(" brand = \""+brand+"\"");
    		}else if(!StringUtils.isBlank(name) && StringUtils.isBlank(brand)){
    			sb.append(" name = \""+name+"\"");
    		}else{
    			sb.append(" brand = \""+brand+"\"");
    			sb.append(" and");
    			sb.append(" name = \""+name+"\"");
    		}
    		
    		
    	}
    	
    	//sb.append(" LIMIT").append(" ").append(limit).append(" OFFSET").append(" ").append(offset*10);
    	query= sb.toString();
    	if(isEligbleToGetResultFromMasterKey){
    		List<Object> allProductList = productService.getAllProduct(category);
    		 searchResult =  getTypeCastedSearchResult(allProductList,category);
    		
    	}else{
    	 searchResult = couchbaseUtilityMethods.getCareSearchResult(query, couchbaseUtilityMethods.getBucketClientsMap().get(productBucketClientName),category);
          
    	}
    	if(!CollectionUtils.isEmpty(searchResult)){
    		model.put("totalCount", searchResult.size());
    		if(sort.equals("default")){
    		
    			Collections.sort(searchResult,new Comparator<CareSearchResultDao>() {
    					    public int compare(CareSearchResultDao o1, CareSearchResultDao o2) {
    					    	
    					    	try {
    	                            SimpleDateFormat dateFormatlhs = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	                            Date convertedDatelhs = dateFormatlhs.parse(eUtil.getCurrentDateAndTime());
    	                            try{
    	                             convertedDatelhs = dateFormatlhs.parse(o1.getCreatedOn());
    	                            }catch(Exception e){
    	                            	System.out.println(o1.getProductId());
    	                            	convertedDatelhs = dateFormatlhs.parse(eUtil.getCurrentDateAndTime());
    	                            }
    	                            Calendar calendarlhs = Calendar.getInstance();
    	                            calendarlhs.setTime(convertedDatelhs);

    	                            SimpleDateFormat dateFormatrhs = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	                            Date convertedDaterhs=dateFormatlhs.parse(eUtil.getCurrentDateAndTime());
    	                            try{
    	                             convertedDaterhs = dateFormatrhs.parse(o2.getCreatedOn());
    	                            }catch(Exception e){
    	                            	System.out.println(o2.getProductId());
    	                            	convertedDatelhs = dateFormatlhs.parse(eUtil.getCurrentDateAndTime());
    	                            }
    	                            Calendar calendarrhs = Calendar.getInstance();
    	                            calendarrhs.setTime(convertedDaterhs);

    	                            if(calendarlhs.getTimeInMillis() >= calendarrhs.getTimeInMillis())
    	                            {   
    	                            	if(orderBy.equals("1"))
    	                                return 1;
    	                            	else
    	                            	   return -1;
    	                            		
    	                            }
    	                            else
    	                            {
    	                            	if(orderBy.equals("1"))
        	                                return -1;
        	                            	else
        	                            	   return 1;
    	                            }
    	                        } catch (Exception e) {

    	                            e.printStackTrace();
    	                        }


    	                        return 0;

    					    }});
    				 
    				 
    			
    			
    		}else if(sort.equals("alphabetically")){
    			
    			if(orderBy.equals("1")){
    				Collections.sort(searchResult);
    			}else{
    				Collections.sort(searchResult,new Comparator<CareSearchResultDao>() {

						@Override
						public int compare(CareSearchResultDao o1, CareSearchResultDao o2) {
							return o2.getName().compareTo(o1.getName());
						}
					});
    			}
    			
    		}else if(sort.equals("price")){
    				Collections.sort(searchResult,new Comparator<CareSearchResultDao>() {
						@Override
						public int compare(CareSearchResultDao o1, CareSearchResultDao o2) {
							if(orderBy.equals("1")){
								return (int) (o1.getBestBuyPrice() - o2.getBestBuyPrice());
							}else{
								return (int) (o2.getBestBuyPrice() - o1.getBestBuyPrice());
							}
							
							
						}
					});
    			
    		}
    			
    			
    		
    		
    		int maxListSize = searchResult.size();
    		int fromIndex = limit*offset;
    		int toIndex  = fromIndex + limit;
    		if (fromIndex < maxListSize && toIndex <= maxListSize) {
				return new ArrayList<CareSearchResultDao>(searchResult.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				return (new ArrayList<CareSearchResultDao>(searchResult.subList(fromIndex, maxListSize)));
			}else{
				model.put("invalidMsg","invalid limit or page,listing all items");
				return searchResult;
				
			}
    		
    		    
    		
    	}else{
    		model.put("totalCount", 0);
    		return null;
    	}
    
    }

	private List<CareSearchResultDao> getTypeCastedSearchResult(List<Object> allProductList, String category) {
		   List<CareSearchResultDao> result = new ArrayList<CareSearchResultDao>();
		
		  if(category.equals(ProductEnum.MOBILE.toString())){
			  List<Mobile> mobileList = allProductList.stream().map(obj->(Mobile)obj).collect(Collectors.toList());
			  for(Mobile m:mobileList){
				  CareSearchResultDao c = new CareSearchResultDao(m.getName(),category,m.getProductId(),m.getBrand(),m.getBestBuyPrice());
				  c.setCreatedOn(m.getCreatedOn());
				  result.add(c);
				  
			  }
			     
		  }else if(category.equals(ProductEnum.CAMERA.toString())){
			  List<camera> cameraList = allProductList.stream().map(obj->(camera)obj).collect(Collectors.toList());
			  for(camera m:cameraList){
				  CareSearchResultDao c = new CareSearchResultDao(m.getName(),category,m.getProductId(),m.getBrand(),m.getBestBuyPrice());
				  c.setCreatedOn(m.getCreatedOn());
				  result.add(c);
				  
			  }
		  }else if(category.equals(ProductEnum.TABLET.toString())){
			  List<Tablet> tabletList = allProductList.stream().map(obj->(Tablet)obj).collect(Collectors.toList());
			  for(Tablet m:tabletList){
				  CareSearchResultDao c = new CareSearchResultDao(m.getName(),category,m.getProductId(),m.getBrand(),m.getBestBuyPrice());
				  c.setCreatedOn(m.getCreatedOn());
				  result.add(c);
				  
			  }
		  }else if(category.equals(ProductEnum.LAPTOP.toString())){
			  List<Laptop> laptopList = allProductList.stream().map(obj->(Laptop)obj).collect(Collectors.toList());
			  for(Laptop m:laptopList){
				  CareSearchResultDao c = new CareSearchResultDao(m.getName(),category,m.getProductId(),m.getBrand(),m.getBestBuyPrice());
				  c.setCreatedOn(m.getCreatedOn());
				  result.add(c);
				  
			  }
		  }else if(category.equals(ProductEnum.HEADPHONE.toString())){
			  List<Headphone> headphoneList = allProductList.stream().map(obj->(Headphone)obj).collect(Collectors.toList());
			  for(Headphone m:headphoneList){
				  CareSearchResultDao c = new CareSearchResultDao(m.getName(),category,m.getProductId(),m.getBrand(),m.getBestBuyPrice());
				  c.setCreatedOn(m.getCreatedOn());
				  result.add(c);
				  
			  }
		  }	  
		return result;
	
	}
    
    
    
    


}
