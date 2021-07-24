package com.planb.couchbase.CouchbaseUtilImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import rx.Observable;
import rx.functions.Func1;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.SerializableDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planb.care.dao.CareSearchResultDao;
import com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods;
import com.planb.dao.mobile.Mobile;
import com.planb.metadata.ProductMetaData;
import com.planb.product.util.EUtil;

@Service
public class CouchbaseUtilityMethodsImpl implements CouchbaseUtilityMethods {

	
	@Value("${couchbase.hosts}")
	private String CouchbaseHosts;
	
	@Value("${bucket.details}")
	private String bucketDetails;
	
	
	
	private Cluster cluster;
	
	
	public Map<String,Bucket> BucketClientsMap;
	
	public Map<String,String> CategaryMasterKeyMap;
	
	@Autowired
	EUtil productUtil;
	
	ObjectMapper mapper;
	
	
	@PostConstruct
	public void init(){
		mapper = new ObjectMapper();
		
		BucketClientsMap=new HashMap<String, Bucket>();
		CategaryMasterKeyMap=new HashMap<String, String>();
		List<String> nodes = new ArrayList<String>();
		
		
		if(CouchbaseHosts!=null && !"".equals(CouchbaseHosts)){
			 String[] statsHostsArray = CouchbaseHosts.split(",");
			 for(int i=0; i<statsHostsArray.length; i++) {
				 statsHostsArray[i] = statsHostsArray[i].trim();
				 nodes.add(statsHostsArray[i]);
			 }
			 cluster=CouchbaseCluster.create(nodes);
		 }
		
		if(bucketDetails!=null && !"".equals(bucketDetails)){
			String bucketDetailsArray[]=bucketDetails.split(",");
			for(String bucketDetail:bucketDetailsArray){
				BucketClientsMap.put(bucketDetail.split("@")[0], cluster.openBucket(bucketDetail.split("@")[0], bucketDetail.split("@")[1]));
			}
			
		}
		try {
			main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods#insert(com.couchbase.client.java.document.JsonDocument, com.couchbase.client.java.Bucket)
	 */
	public void insert(JsonDocument doc, Bucket bucket) {
		
		try{
			bucket.insert(doc);
			}catch(Exception e){
				e.printStackTrace();
			}
				/*Observable<JsonDocument> inserted = bucket.async().insert(doc);*/
		
	}
     /*
      * (non-Javadoc)
      * @see com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods#upsert(com.couchbase.client.java.document.JsonDocument, com.couchbase.client.java.Bucket)
      */
	public void upsert(JsonDocument doc, Bucket bucket) {
		try{
		bucket.upsert(doc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		/*Observable<JsonDocument> inserted = bucket.async().upsert(doc);
		System.out.println(inserted);*/
	}

	
	
	/*CAS actually stands for check-and-set, and is a method of optimistic locking. The CAS value or ID is associated with each document which is updated whenever the document changes - a bit like a revision ID. The intent is that instead of pessimistically locking a document (and the associated lock overhead) you just read it's CAS value, and then only perform the write if the CAS matches.

	The general use-case is:

	Read an existing document, and obtain it's current CAS (get_with_cas)
	Prepare a new value for that document, assuming no-one else has modified the document (and hence caused the CAS to change).
	Write the document using the check_and_set operation, providing the CAS value from (1).
	Step 3 will only succeed (perform the write) if the document is unchanged between (1) and (3) -
	 i.e. no other user has modified it in the meantime. 
	 Typically if (3) does fail you would retry the whole sequence (get_with_cas, modify, check_and_set).

	There's a much more detailed description of check-and-set in the Couchbase Developer Guide under Check and Set (CAS).
*/	
	/*
	 * (non-Javadoc)
	 * @see com.planb.couchbase.CouchbaseUtil.CouchbaseUtilityMethods#replace(com.couchbase.client.java.document.JsonDocument, com.couchbase.client.java.Bucket)
	 */
	public void replace(JsonDocument doc, Bucket bucket) {
		Observable<JsonDocument> inserted = bucket.async().replace(doc);
	}

	
	public JsonDocument get(String id, Bucket bucket) {
	/*	JsonDocument found = bucket.get("id").toBlocking().singleOrDefault(null);
		if (found == null) {
		   return null;
		} else {
			return found;
		}*/
		JsonDocument found = bucket.get(id);
		return found;
	}

	public JsonDocument delete(String id, Bucket bucket) {
		// Create document with some content
		JsonDocument stored = bucket.upsert(JsonDocument.create(id, JsonObject.create()));

		// Delete it with the CAS check included
		JsonDocument removed = bucket.remove(stored);
		return removed;
		
		
	}

	public List<JsonDocument> multiGet(List<String> ids, final Bucket bucket) {
		
		/*List<JsonDocument> result = new ArrayList<JsonDocument>();
		for (String id : ids) {
			try {
				
				JsonDocument doc = bucket.get(id);
				if(doc != null){
				result.add(doc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;*/
		
		/**
		 * Below async get is not working
		 */
	    return Observable
	            .from(ids)
	            .flatMap(new Func1<String, Observable<JsonDocument>>() {
	                public Observable<JsonDocument> call(String id) {
	                    return bucket.async().get(id);
	                }
	            })
	            .toList()
	            .toBlocking()
	            .single();

	}

	public void multiPut(List<JsonDocument> docs, final Bucket bucket) {
		
		/*for (JsonDocument doc : docs) {
			try {
				bucket.upsert(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		*//**
		 * below async upsert is not working
		 */
		
		Observable
		.from(docs)
		.flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
			public Observable<JsonDocument> call(final JsonDocument docs) {
				return bucket.async().upsert(docs);
			}
		})
		.last()
		.toBlocking()
		.single();
		
	}
	
	public List<String> getCareAutoCompleteResult(String queryStr,Bucket bkt,String req){
		List<String> reqList = new ArrayList<String>();
		N1qlQuery query = N1qlQuery.simple(queryStr);
		for (N1qlQueryRow row : bkt.query(query)) {
			reqList.add(row.value().getString(req));
		}
		return reqList;
	}
	
	
	public List<CareSearchResultDao> getCareSearchResult(String queryStr,Bucket bkt,String category) throws JsonParseException, JsonMappingException, IOException{
		List<CareSearchResultDao> reqList = new ArrayList<CareSearchResultDao>();
		N1qlQuery query = N1qlQuery.simple(queryStr);
		for (N1qlQueryRow row : bkt.query(query)) {
			/**
			 * row.value() return bucket name as the key and object json as value
			 */
			
			reqList.add(mapper.readValue(row.value().getObject(bkt.name()).toString(),CareSearchResultDao.class));
		}
		return reqList;
	}
	
	//*********************************************Serializable methods*****************************************//
	public void upsertSerializable(SerializableDocument sdoc,Bucket bucket){
		bucket.async().upsert(sdoc);
		
	}
	public void insertSerialzable(SerializableDocument sdoc,Bucket bucket) {
		Observable<SerializableDocument> inserted = bucket.async().insert(sdoc);
	}
	public SerializableDocument deleteSerialzable(String id, Bucket bucket) {
		SerializableDocument stored = bucket.upsert(SerializableDocument.create(id));
		SerializableDocument removed = bucket.remove(stored);
		return removed;
	}
	public void replaceSerialzable(SerializableDocument sdoc, Bucket bucket) {
		Observable<SerializableDocument> inserted = bucket.async().replace(sdoc);
	}
	public SerializableDocument getSerialzable(String id, Bucket bucket) {
	
		SerializableDocument found = bucket.get(id,SerializableDocument.class);
			return found;
		}
	public List<SerializableDocument> multiGetSerialzable(List<String> ids, final Bucket bucket) {
	    return Observable
	            .from(ids)
	            .flatMap(new Func1<String, Observable<SerializableDocument>>() {
	                public Observable<SerializableDocument> call(String id) {
	                    return bucket.async().get(id,SerializableDocument.class);
	                }
	            })
	            .toList()
	            .toBlocking()
	            .single();

	}

	public void multiPutSerialzable(List<SerializableDocument> sdocs, final Bucket bucket) {
		Observable
		.from(sdocs)
		.flatMap(new Func1<SerializableDocument, Observable<SerializableDocument>>() {
			public Observable<SerializableDocument> call(final SerializableDocument sdocs) {
				return bucket.async().upsert(sdocs);
			}
		})
		.last()
		.toBlocking()
		.single();
		
	}
	
	
	@PreDestroy
	public void cleanUpTask(){
		cluster.disconnect();
	}
	
	@SuppressWarnings("resource")
	public  void main(String[] args) throws Exception {/*
		JsonObject content = JsonObject.empty().put("name", "Michael");
		JsonDocument doc = JsonDocument.create("docId", content);
		System.out.println("inside main");
		//ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		//ProductUtil productUtil=context.getBean("ProductUtil",ProductUtil.class);
		//ProductUtil productUtil=new ProductUtil();
		//productUtil.afterPropertiesSet();
		List<Mobile> ls=productUtil.getMobileList();
		Mobile m1=ls.get(0);
		
		SerializableDocument sdoc=SerializableDocument.create("0001",m1);
		//CouchbaseUtilityMethodsImpl CouchbaseUtilityMethods = context.getBean("couchbaseUtilityMethodsImpl",CouchbaseUtilityMethodsImpl.class);
		CouchbaseUtilityMethodsImpl CouchbaseUtilityMethods=new CouchbaseUtilityMethodsImpl();
		CouchbaseUtilityMethods.init();
		insertSerialzable(sdoc, BucketClientsMap.get("Mobile"));
		SerializableDocument found = getSerialzable("0001",BucketClientsMap.get("Mobile"));
		System.out.println("done:::"+((Mobile)found.content()).getBrand());
		
		//CouchbaseUtilityMethods.insert(doc, CouchbaseUtilityMethods.BucketClientsMap.get("Mobile"));
		//System.out.println(CouchbaseUtilityMethods.get("docId", CouchbaseUtilityMethods.BucketClientsMap.get("Mobile")));
	*/}
	public Map<String, Bucket> getBucketClientsMap() {
		return BucketClientsMap;
	}
	
	public Map<String, String> getCategaryMasterKeyMap() {
		return CategaryMasterKeyMap;
	}
	
}
