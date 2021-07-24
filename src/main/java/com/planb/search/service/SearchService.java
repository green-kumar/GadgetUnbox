package com.planb.search.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.product.util.ElectConstants;
import com.planb.search.dao.SearchProductDao;
import com.planb.search.dao.SearchSequenceCount;
import com.planb.search.dao.SearchTokenToSearchProductMap;
import com.planb.service.ProductService;

@Service
public class SearchService {

	@Autowired
	Trie trie;

	@Autowired
	ProductService productService;

	@Autowired
	TokenGenerator tokenGenerator;

	@Autowired
	CouchbaseUtilityMethods couchbaseUtilityMethods;
    
	@Cacheable(value = "autosuggest", key = "#token")
	public List<SearchProductDao> getAutoSuggestedProducts(String token,String category, int maxResult) {
		List<String> autoCompletedTokens = findAutoCompletionsTokenFromTrie(token);
		if(!CollectionUtils.isEmpty(autoCompletedTokens)){
		List<SearchProductDao> autoSuggestedProducts = getAutoSuggestedProductsfromCB(autoCompletedTokens,category, maxResult);
		return autoSuggestedProducts;
		}else{
			return null;
		}
	}

	public void loadAllTokenIntoTrie(List<SearchProductDao> SPDL) {
        for(SearchProductDao searchProductDao : SPDL){
		
		List<String> productTokenList = Arrays.asList(searchProductDao.getProductName().split(" "));
		if (productTokenList.size() > 1) {
			List<String> totalTokenPermulation = tokenGenerator.generateTokensPermutations(productTokenList);
			for (String token : totalTokenPermulation) {
				trie.load(token);
				/**
				 * generate searchTokenToSearchId map for only those SearchProductDao
				 * whoose token is not yet generated after generating
				 * searchTokenToSearchId,update SearchProductDao with true
				 * isSearchTokenPersistedToCB
				 */
				//if (!searchProductDao.isSearchTokenPersistedToCB()) 
					//storeTokenSearcIdMapToCB(token, searchProductDao);

			}
		} else {
			trie.load(productTokenList.get(0));
			/**
			 * generate searchTokenToSearchId map for only those SearchProductDao
			 * whoose token is not yet generated after generating
			 * searchTokenToSearchId,update SearchProductDao with true
			 * isSearchTokenPersistedToCB
			 */
			//if (!searchProductDao.isSearchTokenPersistedToCB()) 
				//storeTokenSearcIdMapToCB(productTokenList.get(0), searchProductDao);
						
			

		}
		/*if (!searchProductDao.isSearchTokenPersistedToCB()){
		searchProductDao.setSearchTokenPersistedToCB(true);
		productService.updateProduct(searchProductDao.getSearchId(), searchProductDao, ProductEnum.SEARCH.toString());
		}
*/
        }
	}

	public List<String> findAutoCompletionsTokenFromTrie(String token) {
		return trie.findCompletions(token);

	}

	public synchronized int getSearchProductSeqCount() throws Exception {
		int count;
		ObjectMapper mapper = new ObjectMapper();

		JsonDocument jsonDocument = couchbaseUtilityMethods.get(ElectConstants.SEARCH_PRODUCT_COUNT_KEY,
				couchbaseUtilityMethods.getBucketClientsMap().get("search"));
		SearchSequenceCount SSC = null;
		if (jsonDocument != null) {
			String json = jsonDocument.content().toString();
			SSC = mapper.readValue(json, SearchSequenceCount.class);
		}

		if (SSC == null) {
			SSC = new SearchSequenceCount();
			count = 1;
			SSC.setValue(count);
		} else {

			count = SSC.getValue() + 1;
			SSC.setValue(count);
		}

		JsonObject jobj;
		JsonDocument jdoc;

		jobj = JsonObject.fromJson(mapper.writeValueAsString(SSC));
		jdoc = JsonDocument.create(ElectConstants.SEARCH_PRODUCT_COUNT_KEY, jobj);
		couchbaseUtilityMethods.upsert(jdoc, couchbaseUtilityMethods.getBucketClientsMap().get("search"));

		return count;

	}

	public List<SearchProductDao> getAutoSuggestedProductsfromCB(List<String> token,String category, int searchResultCount) {
		List<Object> objList = productService.multiGet(token, ProductEnum.SEARCH_TOKEN.toString());
		List<SearchTokenToSearchProductMap> STTSPMList = objList.stream()
				.map(obj -> (SearchTokenToSearchProductMap) obj).collect(Collectors.toList());

		Set<String> SearchIdSet = new HashSet<String>();

		for (SearchTokenToSearchProductMap STTSPM : STTSPMList) {
			SearchIdSet.addAll(STTSPM.getSearchProductDaoIdList());

		}
		List<Object> resultObjList = productService.multiGet(new ArrayList<String>(SearchIdSet),
				ProductEnum.SEARCH.toString());
		List<SearchProductDao> SPDList = resultObjList.stream().map(obj -> (SearchProductDao) obj)
				.collect(Collectors.toList());
		if(!category.equals("all")){
		SPDList = SPDList.stream().filter(SPD ->SPD.getCategory().toString().equals(category)).collect(Collectors.toList());
		   if(CollectionUtils.isEmpty(SPDList))
			   return null;
		
		}
		
		
		Collections.sort(SPDList);
		if (searchResultCount == -1 || SPDList.size() <= searchResultCount) {
			return SPDList;
		} else {
			return new ArrayList<SearchProductDao>(SPDList.subList(0, searchResultCount));
		}

	}

	public void storeTokenSearcIdMapToCB(String token, SearchProductDao SPD) {
		SearchTokenToSearchProductMap stspm = (SearchTokenToSearchProductMap) productService.getProduct(token,
				ProductEnum.SEARCH_TOKEN.toString());
		if (stspm != null) {
			Set<String> sPDIdList = stspm.getSearchProductDaoIdList();
			sPDIdList.add(SPD.getSearchId());

		} else {
			stspm = new SearchTokenToSearchProductMap();
			stspm.setSearchToken(token);
			stspm.getSearchProductDaoIdList().add(SPD.getSearchId());

		}
		productService.addProduct(stspm, ProductEnum.SEARCH_TOKEN.toString(), token);

	}

	/**
	 * 
	 * @param SPD
	 *            use to store sreachProduct to CB at the same time when new
	 *            product will be created.
	 * 
	 */

	public void StoreSearchProductDaoToCB(SearchProductDao SPD) {
		productService.addProduct(SPD, ProductEnum.SEARCH.toString(), SPD.getSearchId());

	}

	public void test(){
		List<Object> OL = productService.getAllProduct(ProductEnum.SEARCH.toString());
		if(!CollectionUtils.isEmpty(OL)){
		List<SearchProductDao> SPDL = OL.stream().map(o->(SearchProductDao)o).collect(Collectors.toList());
		
		loadAllTokenIntoTrie(new ArrayList(OL.subList(0, 10)));
		
		}
	
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		SearchService ss = (SearchService) context.getBean("searchService", SearchService.class);
		SearchProductDao spd = new SearchProductDao();
		spd.setProductName("samsung galaxy duo");
		spd.setSearchId("S1");
		SearchProductDao spd1 = new SearchProductDao();
		spd1.setProductName("samsung galaxy j7");
		spd1.setSearchId("S2");

		/*ss.StoreSearchProductDaoToCB(spd1);
		ss.StoreSearchProductDaoToCB(spd);
		List<SearchProductDao> l =new ArrayList<SearchProductDao>();
		l.add(spd1);
		l.add(spd);
		ss.loadAllTokenIntoTrie(l);*/
		
		ss.test();

		System.out.println(ss.getAutoSuggestedProducts("S","all", 10));

	}

}
