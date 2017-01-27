package com.planb.metadata;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMetaDataRDBService implements InitializingBean {

	@Autowired
    private SessionFactory sessionFactory;
	
	/*@Autowired
	private ApplicationContext appContext;*/
	
	    public List<ProductMetaData> productlList() {
	        @SuppressWarnings("unchecked")
	       /* List<ProductMetaData> listMetaData = (List<ProductMetaData>) sessionFactory.getCurrentSession()
	                .createCriteria(ProductMetaData.class)
	                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/
	        Session session = sessionFactory.openSession();  
	        session.beginTransaction();
	        Criteria criteria = session.createCriteria(ProductMetaData.class); 
	       /* @SuppressWarnings("unchecked")*/
			List<ProductMetaData> listMetaData=criteria.list();
	        session.getTransaction().commit();  
	        session.close();  
	        return listMetaData;
	    }
	 
	    public void save(ProductMetaData ProductMetaData) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	    	 Session session = this.sessionFactory.openSession();
	         org.hibernate.Transaction tx = null;
	         try{
	            tx =  session.beginTransaction();
	             session.saveOrUpdate(ProductMetaData); 
	            tx.commit();
	         }catch (HibernateException e) {
	            if (tx!=null) tx.rollback();
	           e.printStackTrace(); 
	         }finally {
	            session.close(); 
	         }
	       
	    }
	 
	    public void delete(String productId) throws IllegalStateException, SystemException {
	    	
	    	
	    	Session session = this.sessionFactory.openSession();
	    	 org.hibernate.Transaction tx = null;
	         try{
	            tx = session.beginTransaction();
	            ProductMetaData productToDelete = new ProductMetaData();
		    	productToDelete.setProductId(productId);
	             session.delete(productToDelete); 
	            tx.commit();
	         }catch (Exception e) {
	            if (tx!=null) tx.rollback();
	            e.printStackTrace(); 
	         }finally {
	            session.close(); 
	         }
	       
	    	
	    }
	 
	    public ProductMetaData get(String productId) {
	    	 Session session = sessionFactory.openSession();  
		        session.beginTransaction();
	    	Criteria criteria = session.createCriteria(ProductMetaData.class);  
	    	criteria.add(Restrictions.eq("productId", productId));  
	    	List list = criteria.list();  
	    	 session.getTransaction().commit();  
		        session.close(); 
	    	return (ProductMetaData) list.get(0);
	    }
	    
	    public ProductMetaData getByProductName(String productName) {
	    	 Session session = sessionFactory.openSession();  
		        session.beginTransaction();
	    	Criteria criteria = session.createCriteria(ProductMetaData.class);  
	    	criteria.add(Restrictions.eq("productName", productName));  
	    	List list = criteria.list();  
	    	 session.getTransaction().commit();  
		        session.close(); 
	    	return (ProductMetaData) list.get(0);
	    }
	    
	    public void update(ProductMetaData ProductMetaData) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
	    	 Session session = this.sessionFactory.openSession();
	         org.hibernate.Transaction tx = null;
	         try{
	            tx =  session.beginTransaction();
	             session.update(ProductMetaData); 
	            tx.commit();
	         }catch (HibernateException e) {
	            if (tx!=null) tx.rollback();
	            e.printStackTrace(); 
	         }finally {
	            session.close(); 
	         }
	       
	    }

		@Override
		public void afterPropertiesSet() throws Exception {/*

			for(int i=101;i<201;i++){
				if(i<121){
					System.out.println(i);
					save(new ProductMetaData("100"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "mobile"+i, "brand"+i, 
						"url"+i, 6000+i, "Android", i, "mobile", (110+i), "released", (120-i)%5, i));
				}else if(i>120 && i<141){
					System.out.println(i);
					save(new ProductMetaData("100"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "camera"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "camera", i, "released", (140-i)%5, i-120));
				}else if(i>140 && i<161){
					System.out.println(i);
					save(new ProductMetaData("100"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "tablet"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "tablet", i, "released", (160-i)%5, i-140));
				}else if(i>160 && i<181){
					System.out.println(i);
					save(new ProductMetaData("100"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "laptop"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "laptop", i, "released", (180-i)%5, i-160));
				}else if(i>180 && i<201){
					save(new ProductMetaData("100"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "tablet"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "headphone", i, "released", (200-i)%5, i-80));
					System.out.println(i);
				}
			}
		
		*/}
		
		
		public static void main(String[] args) {/*
			ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
			ProductMetaDataService pmds=context.getBean("productMetaDataService",ProductMetaDataService.class);
			
			//ProductUtil productUtil=context.getBean("ProductUtil",ProductUtil.class);
			
			
			
			for(int i=1;i<101;i++){
				if(i<21){
					System.out.println(i);
				new ProductMetaData("10"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "mobile"+i, "brand"+i, 
						"url"+i, 6000+i, "Android", i, "mobile", 10+i, "released", 20-i, i);
				}else if(i>20 && i<41){
					System.out.println(i);
					new ProductMetaData("10"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "camera"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "camera", i, "released", 40-i, i);
				}else if(i>40 && i<61){
					System.out.println(i);
					new ProductMetaData("10"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "tablet"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "tablet", i, "released", 60-i, i);
				}else if(i>60 && i<81){
					System.out.println(i);
					new ProductMetaData("10"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "laptop"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "laptop", i, "released", 80-i, i);
				}else if(i>80 && i<101){
					new ProductMetaData("10"+i, "CPU Type: Octa-Core|Technology(Main Display): Dual Edge Super AMOLED|Resolution(Main Display): 2560 x 1440 (Quad HD)", "tablet"+i, "brand"+i, 
							"url"+i, 6000+i, "Android", i, "headphone", i, "released", 100-i, i);
					System.out.println(i);
				}
			}
		*/}
}



