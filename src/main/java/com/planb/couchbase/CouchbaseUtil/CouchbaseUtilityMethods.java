
package com.planb.couchbase.CouchbaseUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.SerializableDocument;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.planb.care.dao.CareSearchResultDao;



@Service
public interface CouchbaseUtilityMethods {
	//The insert method allows you to store a Document, if it does not already exist in the bucket.
	//If it does exist, the Observable fails with a DocumentAlreadyExistsException (or it is thrown in the synchronous counterpart)
	public void insert(JsonDocument doc,Bucket bucket);
	
	//upsert method used in both insert and update operation
	
	//The upsert method works similar to insert, 
	//but it also overrides a already stored Document (so there is no DocumentAlreadyExistsException thrown.
	public void upsert(JsonDocument doc,Bucket bucket);
	
	//The replace method replaces the Document if it exists, but fails with a DocumentDoesNotExistException otherwise.
	public void replace(JsonDocument doc,Bucket bucket);
	
	public JsonDocument get(String id,Bucket bucket);
	
	//If you pass in a document which also has the CAS value populated, the SDK will make sure to only delete the document if they match:


	public JsonDocument delete(String id,Bucket bucket);
	
	
	public List<JsonDocument> multiGet(List<String> ids,Bucket bucket);
	
	public void multiPut(List<JsonDocument> ids,Bucket bucket);
	
	public List<CareSearchResultDao> getCareSearchResult(String queryStr,Bucket bkt,String category) throws JsonParseException, JsonMappingException, IOException;
	
	//*********************Serializable methods************************//
	public void upsertSerializable(SerializableDocument sdoc,Bucket bucket);
	public void insertSerialzable(SerializableDocument sdoc,Bucket bucket);
	public SerializableDocument deleteSerialzable(String id, Bucket bucket);
	public void replaceSerialzable(SerializableDocument sdoc, Bucket bucket);
	public SerializableDocument getSerialzable(String id, Bucket bucket);
	public List<SerializableDocument> multiGetSerialzable(List<String> ids, final Bucket bucket);
	public void multiPutSerialzable(List<SerializableDocument> sdocs, final Bucket bucket);
	
	//*******************************************************UTILITY METHODS*************
	public Map<String, Bucket> getBucketClientsMap();
	
	public Map<String, String> getCategaryMasterKeyMap();
	
	//****************N1Ql query methods**********************
	public List<String> getCareAutoCompleteResult(String queryStr,Bucket bkt,String req);

}
