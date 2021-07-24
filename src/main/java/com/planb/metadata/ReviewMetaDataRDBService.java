package com.planb.metadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.planb.product.util.EUtil;

@Service
public class ReviewMetaDataRDBService {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private EUtil eUtil;
	
	@Value("${review.pagination.count}")
	private Integer paginationCount;
	
	public List<String> getTopRatedReviewId(String productId){
		String hql = "SELECT r.reviewId FROM ReviewMetaData r where r.productId =:productId"+
	                  " order by r.likes desc";
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hql);
		query.setParameter("productId", productId);
		query.setFirstResult(0);
		query.setMaxResults(paginationCount);
		@SuppressWarnings("unchecked")
		List<String> reviewIds=query.list();
		session.close();
		
		
		return reviewIds;
	}
	
	public List<String> getReviewIdForPagination(String productId,int startFrom){
		
		int firstResult = (startFrom-1)*paginationCount;
		int maxResult = firstResult + paginationCount;
		
		String hql="SELECT r.reviewId from ReviewMetaData r where r.productId= :productId"
				+ " order by createdOn desc";
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(hql);
		query.setParameter("productId", productId);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		@SuppressWarnings("unchecked")
		List<String> reviewIds=query.list();
		session.close();
		return reviewIds;
	}
	

	
	public void saveReviewMetaData(ReviewMetaData reviewMetaData){
		Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.save(reviewMetaData);
		 tx.commit();
		 session.close();
		
	}
	
	public void updateReviewMetaData(ReviewMetaData reviewMetaData){
		Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.update(reviewMetaData);
		 tx.commit();
		 session.close();
		
	}
	
	public void BatchInsert(List<ReviewMetaData> list){
		StopWatch sw =new StopWatch("batch test");
		//sw.start("without batch");

		/*for(int i =0;i<list.size();i++){
			try{
			saveReviewMetaData(list.get(i));
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
		
		//sw.stop();
		
		sw.start("batch insert");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	try{	   
		for ( int i=0; i<list.size(); i++ ) {
			try{
		    session.saveOrUpdate(list.get(i));
		    if ( i % 50 == 0 && i >0) { //50, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
		    }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		   
		tx.commit();
	}catch(HibernateException e){
		if(tx !=null)
			tx.rollback();
		e.printStackTrace();
		
	}finally{
		
	
		session.close();
	}
	sw.stop();
	
	System.out.println(sw.prettyPrint());
	
	
	}
	public static void main(String[] args) {
		ApplicationContext appContext=new ClassPathXmlApplicationContext("context.xml");
		ReviewMetaDataRDBService rmd=appContext.getBean("reviewMetaDataRDBService", ReviewMetaDataRDBService.class);
		ArrayList<ReviewMetaData> rmdl=new ArrayList<ReviewMetaData>();
		
		System.out.println("*****"+rmd.getReviewIdForPagination("review_@.com_mobile_Asus Zenfone 2 Laser ZE500KL_Asus_6d9b9d53-a94e-4a04-84c3-82927a3ba91a41751d6c-d",0));
		
		/*for(int i=0;i<40;i++){
			
          rmdl.add(new ReviewMetaData("temp"+i+2, "productId",i, new Date(), new Date()))		;
		
		}
		rmd.BatchInsert(rmdl);*/
		//rmd.BatchInsert(Arrays.asList(new ReviewMetaData("temp1", "productId",6, new Date(), new Date())));
	}

}
