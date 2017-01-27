package com.planb.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.product.util.ElectConstants;
import com.planb.search.dao.SearchProductDao;
import com.planb.search.service.SearchService;
import com.planb.service.ProductService;

@Service
public class CbdToRdbToInMem implements InitializingBean {

	@Value("${product.categories}")
	String productCategory;

	@Autowired
	ProductMetaDataRDBService productMetaDataService;

	@Autowired
	ProductService productService;

	@Autowired
	MetaDataUtil metaDataUtil;
	
	@Value("${updateRDBFromCB}")
	boolean updateRDBFromCB;
	
	@Value("${updateRDBFromCB.dealy.minute}")
    int updateRDBFromCBDealyMinutes;
	
	@Autowired
	ElectInMemoryFactory electInMemoryFactory;
	
	@Autowired
	SearchService searchService;
	
	 private final Logger logger = LoggerFactory.getLogger(CbdToRdbToInMem.class);
	
	private ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
	private ConcurrentHashMap<String, ArrayList<ProductMetaData>> ProductMetaDataMap;
	private LinkedHashMap<String, ArrayList<ProductMetaData>> frontMetaDataMap;
	private LinkedHashMap<String, ArrayList<ProductMetaData>> trendingMap;
	private ArrayList<ProductMetaData> latestSmartphoneList;
	private ArrayList<ProductMetaData> latestCameraList;
	private ArrayList<ProductMetaData> latestTabletList;
	private ArrayList<ProductMetaData> latestLaptopList;
	private ArrayList<ProductMetaData> latestHeadphoneList;
	private ArrayList<ProductMetaData> trendingProductList;

	@Override
	public void afterPropertiesSet() throws Exception {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				/**
				 * 
				 * Since this thread as an separate process of execution and any exception in this thread 
				 * doen't stop to war
				 * deployment so  for keeping track of proper execution of this thread,
				 * adding wrapping whole code in try-catch block
				 */
				try{
				System.out.println("****begning of startup Thread of CbdToRdbToInMem****");
				logger.info("****begning of startup Thread of CbdToRdbToInMem****");
				if(updateRDBFromCB){
				 updateRdbForProductMetaDataAndReviewMetaData();
				}
				/**
				 * create trie from SearchProductDao(persisted in CB) 
				 */
				createTrieFromSearchProductDao();
				ConcurrentHashMap<String, ArrayList<ProductMetaData>> categoryProductMetaDataMap = fetchMetaDataFromRdb();
				System.out.println("categoryProductMetaDataMap\n:" + categoryProductMetaDataMap);
				logger.info("categoryProductMetaDataMap\n:" + categoryProductMetaDataMap);
				populateProductDataForUI(categoryProductMetaDataMap);
				System.out.println("***End of startup Thread of CbdToRdbToInMem***");
				logger.info("***End of startup Thread of CbdToRdbToInMem***");
				}catch(Exception e){
					System.out.println("*****Exception occured in thread under CbToRdbtoInMem service*****");
					e.printStackTrace();
				}

			}

		};
		exec.scheduleAtFixedRate(thread, 0, updateRDBFromCBDealyMinutes, TimeUnit.MINUTES);
		// exec.scheduleWithFixedDelay(command, initialDelay, delay, unit)
	}

	protected void createTrieFromSearchProductDao() {

		List<Object> OL = productService.getAllProduct(ProductEnum.SEARCH.toString());
		if(!CollectionUtils.isEmpty(OL)){
		List<SearchProductDao> SPDL = OL.stream().map(o->(SearchProductDao)o).collect(Collectors.toList());
		searchService.loadAllTokenIntoTrie(SPDL);
	}
		
	}

	/*
	 * void updateRdbForReviewMetaData(ConcurrentHashMap<String,
	 * ArrayList<ProductMetaData>> categoryProductMetaDataMap) {
	 * 
	 * categoryProductMetaDataMap.forEach((k,v)->{ List<ProductMetaData>
	 * productListEachCategory =v.stream().filter(productMetaData ->
	 * productMetaData.isActive()).collect(Collectors.toList());
	 * for(ProductMetaData productMetaData:productListEachCategory){
	 * List<ReviewDetails> reviewDetailsList //multi save using batch update }
	 * 
	 * }); }
	 */

	protected ConcurrentHashMap<String, ArrayList<ProductMetaData>> fetchMetaDataFromRdb() {
		logger.info("Starting fetching product meta data from RDB: fetchMetaDataFromRdb()");
		
		ConcurrentHashMap<String, ArrayList<ProductMetaData>> categoryMetaDataProductListMap = new ConcurrentHashMap<String, ArrayList<ProductMetaData>>();
		List<ProductMetaData> list = productMetaDataService.productlList();
		List<ProductMetaData> ActiveMetaDataList = new ArrayList<ProductMetaData>();
		if (list == null || list.size() == 0) {
			logger.info("not able to fetch product from RDb to in memory");
			System.out.println("not able to fetch product from RDb to in memory");
		} else {
			logger.info("Total product found in RDB : {}",list.size() );
			for (ProductMetaData productMetaData : list) {
				if (productMetaData.isActive())
					ActiveMetaDataList.add(productMetaData);
			}
			logger.info("Total Active product found in RDB : {}",ActiveMetaDataList.size() );
		}

		for (ProductMetaData obj : ActiveMetaDataList) {
			try {

				String category = obj.getCategory();
				if (category != null && !"".equals(category)) {
					if (ProductEnum.MOBILE.getValue().equals(category)
							|| ProductEnum.SMARTPHONE.getValue().equals(category)) {
						if (categoryMetaDataProductListMap.containsKey(ElectConstants.LATEST_SMARTPHONES)) {
							ArrayList<ProductMetaData> templist = categoryMetaDataProductListMap
									.get(ElectConstants.LATEST_SMARTPHONES);
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_SMARTPHONES, templist);
						} else {
							ArrayList<ProductMetaData> templist = new ArrayList<ProductMetaData>();
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_SMARTPHONES, templist);
						}
					} else if (ProductEnum.CAMERA.getValue().equals(category)) {
						if (categoryMetaDataProductListMap.containsKey(ElectConstants.LATEST_CAMERAS)) {
							ArrayList<ProductMetaData> templist = categoryMetaDataProductListMap
									.get(ElectConstants.LATEST_CAMERAS);
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_CAMERAS, templist);
						} else {
							ArrayList<ProductMetaData> templist = new ArrayList<ProductMetaData>();
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_CAMERAS, templist);
						}
					} else if (ProductEnum.TABLET.getValue().equals(category)) {
						if (categoryMetaDataProductListMap.containsKey(ElectConstants.LATEST_TABLETS)) {
							ArrayList<ProductMetaData> templist = categoryMetaDataProductListMap
									.get(ElectConstants.LATEST_TABLETS);
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_TABLETS, templist);
						} else {
							ArrayList<ProductMetaData> templist = new ArrayList<ProductMetaData>();
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_TABLETS, templist);
						}

					} else if (ProductEnum.LAPTOP.getValue().equals(category)) {
						if (categoryMetaDataProductListMap.containsKey(ElectConstants.LATEST_LAPTOPS)) {
							ArrayList<ProductMetaData> templist = categoryMetaDataProductListMap
									.get(ElectConstants.LATEST_LAPTOPS);
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_LAPTOPS, templist);
						} else {
							ArrayList<ProductMetaData> templist = new ArrayList<ProductMetaData>();
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_LAPTOPS, templist);
						}

					} else if (ProductEnum.HEADPHONE.getValue().equals(category)) {
						if (categoryMetaDataProductListMap.containsKey(ElectConstants.LATEST_HEADPHONES)) {
							ArrayList<ProductMetaData> templist = categoryMetaDataProductListMap
									.get(ElectConstants.LATEST_HEADPHONES);
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_HEADPHONES, templist);
						} else {
							ArrayList<ProductMetaData> templist = new ArrayList<ProductMetaData>();
							templist.add(obj);
							categoryMetaDataProductListMap.put(ElectConstants.LATEST_HEADPHONES, templist);
						}

					}
				}

			} catch (Exception e) {
				logger.error("exception found while fetchMetaDataFromRdb()",e);
				e.printStackTrace();
			}

		}
		logger.info("Ending fetching product meta data from RDB: fetchMetaDataFromRdb()");
		return categoryMetaDataProductListMap;

	}

	private void updateRdbForProductMetaDataAndReviewMetaData() {
		if (!StringUtils.isEmpty(productCategory)) {
			String productCategoryArray[] = productCategory.split(",");
			for (String category : productCategoryArray) {
				
				List<Object> allProductForParticularCategory = productService.getAllProduct(category);
				if (!CollectionUtils.isEmpty(allProductForParticularCategory)) {
					System.out.println("Total product count for "+ category +":" +allProductForParticularCategory.size());
					ElectInMemoryService electInMemoryService =  electInMemoryFactory.getElectInMemoryService(category);
					electInMemoryService.storeMetaDataToRDB(allProductForParticularCategory);
					//metaDataUtil.storeAllProductToRDBMS(allProductForParticularCategory, category);
				}
				
			}

		}

	}

	@SuppressWarnings("unchecked")
	protected void populateProductDataForUI(ConcurrentHashMap<String, ArrayList<ProductMetaData>> categoryproductMetaDataMap) {
		// ****************TODO********************//
		// use intelligence in deciding trending product and latest product(sort
		// on the desc order of create date)
		// **********************************************************************//

		latestSmartphoneList = categoryproductMetaDataMap.get(ElectConstants.LATEST_SMARTPHONES);
		latestLaptopList = categoryproductMetaDataMap.get(ElectConstants.LATEST_LAPTOPS);
		latestCameraList = categoryproductMetaDataMap.get(ElectConstants.LATEST_CAMERAS);
		latestTabletList = categoryproductMetaDataMap.get(ElectConstants.LATEST_TABLETS);
		latestHeadphoneList = categoryproductMetaDataMap.get(ElectConstants.LATEST_HEADPHONES);

		System.out.println("******************count of product in RDB*************");
		logger.info("******************count of product in RDB*************");

		System.out.println("Smartphone count :" + (CollectionUtils.isEmpty(latestSmartphoneList)?0:latestSmartphoneList.size()) + "\ncamera count :"
				+ (CollectionUtils.isEmpty(latestCameraList)?0:latestCameraList.size()) + "\nTablet count :" + (CollectionUtils.isEmpty(latestTabletList)?0:latestTabletList.size()) + "\nLaptop count :"
				+ (CollectionUtils.isEmpty(latestLaptopList)?0:latestLaptopList.size()) + "\nHeadphone count  :" + (CollectionUtils.isEmpty(latestHeadphoneList)?0:latestHeadphoneList.size()));

		logger.info("Smartphone count :" + (CollectionUtils.isEmpty(latestSmartphoneList)?0:latestSmartphoneList.size()) + "\ncamera count :"
				+ (CollectionUtils.isEmpty(latestCameraList)?0:latestCameraList.size()) + "\nTablet count :" + (CollectionUtils.isEmpty(latestTabletList)?0:latestTabletList.size()) + "\nLaptop count :"
				+ (CollectionUtils.isEmpty(latestLaptopList)?0:latestLaptopList.size()) + "\nHeadphone count  :" + (CollectionUtils.isEmpty(latestHeadphoneList)?0:latestHeadphoneList.size()));

		// *****sorting latest product by the desc order of released date*****
		// ***in frontmetadta map each product count will be 4***
		frontMetaDataMap = new LinkedHashMap<String, ArrayList<ProductMetaData>>();
		
		/**
		 * TODO : create a algo based on which trending product could be decide
		 */
		// ***in trending map smartphone's count will be 8 and rest product's count are 4.***
				trendingMap = new LinkedHashMap<String, ArrayList<ProductMetaData>>();
		
		 if(!CollectionUtils.isEmpty(latestSmartphoneList)){
		  Collections.sort(latestSmartphoneList);
		  frontMetaDataMap.put(ElectConstants.LATEST_SMARTPHONES,new ArrayList<ProductMetaData>(latestSmartphoneList.subList(0, 4)));
		  trendingMap.put(ElectConstants.TRENDING_SMARTPHONES,new ArrayList<ProductMetaData>(latestSmartphoneList.subList(0, 8)));
		 }
		 if(!CollectionUtils.isEmpty(latestCameraList)){
		  Collections.sort(latestCameraList);
		  frontMetaDataMap.put(ElectConstants.LATEST_CAMERAS,new ArrayList<ProductMetaData>(latestCameraList.subList(0, 4)));
		  trendingMap.put(ElectConstants.TRENDING_CAMERAS,new ArrayList<ProductMetaData>(latestCameraList.subList(0, 4)));
		 }
		 if(!CollectionUtils.isEmpty(latestTabletList)){
		  Collections.sort(latestTabletList);
		  frontMetaDataMap.put(ElectConstants.LATEST_TABLETS,new ArrayList<ProductMetaData>(latestTabletList.subList(0, 4)));
		  trendingMap.put(ElectConstants.TRENDING_TABLETS,new ArrayList<ProductMetaData>(latestTabletList.subList(0, 4)));
		 }
		 if(!CollectionUtils.isEmpty(latestLaptopList)){
		  Collections.sort(latestLaptopList);
		  frontMetaDataMap.put(ElectConstants.LATEST_LAPTOPS,new ArrayList<ProductMetaData>(latestLaptopList.subList(0, 4)));
		  trendingMap.put(ElectConstants.TRENDING_LAPTOPS,new ArrayList<ProductMetaData>(latestLaptopList.subList(0, 4)));
		 }
		 if(!CollectionUtils.isEmpty(latestHeadphoneList)){
		  Collections.sort(latestHeadphoneList);
		  frontMetaDataMap.put(ElectConstants.LATEST_HEADPHONES,new ArrayList<ProductMetaData>(latestHeadphoneList.subList(0, 4)));
		  trendingMap.put(ElectConstants.TRENDING_HEADPHONES,new ArrayList<ProductMetaData>(latestHeadphoneList.subList(0, 4)));
		 }
		 

		frontMetaDataMap.put(ElectConstants.TECH_BLOGS,new ArrayList<ProductMetaData>(latestSmartphoneList.subList(0, 4)));
		

		frontMetaDataMap.put(ElectConstants.TRENDING_GADGETS,new ArrayList<ProductMetaData>(latestSmartphoneList.subList(0, 4)));		
		
		
		
		
		System.out.println("frontMetaDataMap:\n" + frontMetaDataMap);
		logger.info("frontMetaDataMap:\n" + frontMetaDataMap);
		
		System.out.println("trending product map:\n" + trendingMap);
		logger.info("trending product map:\n" + trendingMap);

	}

	public ConcurrentHashMap<String, ArrayList<ProductMetaData>> getProductMetaDataMap() {
		return ProductMetaDataMap;
	}

	public LinkedHashMap<String, ArrayList<ProductMetaData>> getFrontMetaDataMap() {
		return frontMetaDataMap;
	}

	public LinkedHashMap<String, ArrayList<ProductMetaData>> getTrendingMap() {
		return trendingMap;
	}

	public ArrayList<ProductMetaData> getlatestSmartphoneList() {
		return latestSmartphoneList;
	}

	public ArrayList<ProductMetaData> getLatestCameraList() {
		return latestCameraList;
	}

	public ArrayList<ProductMetaData> getLatestTabletList() {
		return latestTabletList;
	}

	public ArrayList<ProductMetaData> getLatestLaptopList() {
		return latestLaptopList;
	}

	public ArrayList<ProductMetaData> getLatestHeadphoneList() {
		return latestHeadphoneList;
	}

}
