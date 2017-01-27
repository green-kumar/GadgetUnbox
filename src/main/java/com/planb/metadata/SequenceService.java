package com.planb.metadata;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class SequenceService {
	
	
	@Autowired
    private SessionFactory sessionFactory;

	public String getNextSequenceCount(String category){
		
		String seqCountResult = "-1";
		int seqCount = -1;
		
		String hql = "SELECT s FROM SequenceData s where s.category =:category";
		Session session=sessionFactory.openSession();
	    Query query=session.createQuery(hql);
	    query.setParameter("category", category);
	    List<SequenceData> result = query.list();
	
	    if(!CollectionUtils.isEmpty(result)){
	    	SequenceData seqData= result.get(0);
		    seqCount = seqData.getCount();
		    seqData.setCount(seqCount+1);
		    updateSequenceData(seqData);
	    }
	    session.close();
	    
	    if(seqCount<1000){
	    	seqCountResult =String.format("%03d", seqCount);
	    }else{
	    	seqCountResult = seqCount+"";
	    }
	    return seqCountResult;
      	}
	
	public void saveSequenceData(SequenceData sequenceData){
		Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.update(sequenceData);
		 tx.commit();
		 session.close();
	}
	
	public void updateSequenceData(SequenceData sequenceData){
		Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.update(sequenceData);
		 tx.commit();
		 session.close();
	}
	
	
}
