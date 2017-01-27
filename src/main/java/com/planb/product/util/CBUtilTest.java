package com.planb.product.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.SerializableDocument;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.dao.user.UserDetail;

public class CBUtilTest {
	
	public static void main(String[] args) {
		
		
		
		
	 Cluster cluster = null;
      
		String CouchbaseHosts="127.0.0.1";
		
		List<String> nodes = new ArrayList<String>();
		
		
		if(CouchbaseHosts!=null && !"".equals(CouchbaseHosts)){
			 String[] statsHostsArray = CouchbaseHosts.split(",");
			 for(int i=0; i<statsHostsArray.length; i++) {
				 statsHostsArray[i] = statsHostsArray[i].trim();
				 nodes.add(statsHostsArray[i]);
			 }
			 cluster=CouchbaseCluster.create(nodes);
		 }
		
		Bucket bucket=cluster.openBucket("Mobile", "mobile");
		
		//get
		SerializableDocument found = bucket.get("mobile_Asus Zenfone 2 Laser ZE500KL_Asus",SerializableDocument.class);
		Object obj=(Object)found.content();
		Mobile rd=(Mobile)obj;
		System.out.println(rd);
		
		
		//upsert
		/*ReviewDetails rd = new ReviewDetails();
		rd.setReviewId("tempReview_20160827");
		
		SerializableDocument sdoc=SerializableDocument.create("tempReview_20160827",(Serializable) rd);
		bucket.async().upsert(sdoc);
		*/

		
		System.out.println("done");
	
	}

}
