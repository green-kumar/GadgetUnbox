package com.planb.inmemoery.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ElectConfig {

	@Bean
	 public FactoryBean serviceLocatorFactoryBean() {
	    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
	    factoryBean.setServiceLocatorInterface(ElectInMemoryFactory.class);
	    return factoryBean;
	 }

	//multiple alias for bean should work but not working
	//need to debug : @Bean(name = {"mobile,smartphone"})
	 @Bean(name = "mobiles")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getElectInMemoryService() {
	    return new MobileInMemoryServiceImpl();
	 }
	 
	 @Bean(name = "mobile")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getElectMobileInMemoryService() {
	    return new MobileInMemoryServiceImpl();
	 }
	 
	 @Bean(name = "smartphone")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getSmartphoneInMemoryService() {
	    return new MobileInMemoryServiceImpl();
	 }
	 
	 
	 @Bean(name = "camera")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getCameraInMemoryService() {
	    return new CameraInMemeoryServiceImpl();
	 }
	 
	 @Bean(name = "tablet")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getTabletInMemoryService() {
	    return new TabletInMemoryServiceImpl();
	 }
	 
	 @Bean(name = "laptop")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getLaptopInMemoryService() {
	    return new LaptopInMemoryServiceImpl();
	 }
	 @Bean(name = "headphone")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getHeadphoneInMemoryService() {
	    return new HeadphoneInMemoryServiceImpl();
	 }
	 
	 
	 
	 @Bean(name = "review")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getUserInMemoryService() {
	    return new ReviewInMemoryServiceImpl();
	 }
	 @Bean(name = "user")
	 @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public ElectInMemoryService getReviewInMemoryService() {
	    return new UserInMemoryServiceImpl();
	 }

	 @Bean(name = "search")
	 @Scope("prototype")
	 public ElectInMemoryService getSearchInMemoryService()
	 {
		 return new SearchInMemoryServiceImpl();
	 }
	 @Bean(name = "searchToken")
	 @Scope("prototype")
	 public ElectInMemoryService getSearchTokenInMemoryService()
	 {
		 return new SearchTokenInMemoryServiceImpl();
	 }
	
	
}
