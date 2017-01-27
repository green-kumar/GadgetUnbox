package com.planb.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.planb.dao.admin.AdminLoginForm;

@Service
public class AdminLoginValidator implements Validator,InitializingBean {

	
	@Value("${admin.credentials}")
	String adminCredentilas;
	
	Map<String,Integer> adminCredentialsMap=new HashMap<String,Integer>();
	
	public boolean supports(Class<?> arg0) {
		return AdminLoginForm.class.equals(arg0);
	}
	
	public void afterPropertiesSet() throws Exception {
		if(adminCredentilas!=null && !"".equals(adminCredentilas)){
			
			String arr[]=adminCredentilas.split("\\|");
			for(String str:arr){
				adminCredentialsMap.put(str, 1);
			}
		}
		
	}

	public void validate(Object obj, Errors errors) {
		AdminLoginForm adminLoginForm=  (AdminLoginForm)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.Admin.name","name is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.Admin.email","email is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.Admin.password","password is required!");
		if(!errors.hasErrors()){
			StringBuilder sb=new StringBuilder();
			sb.append(adminLoginForm.getName()).append(",").append(adminLoginForm.getEmail()).append(",").append(adminLoginForm.getPassword());
			String credential=sb.toString();
			if(!adminCredentialsMap.containsKey(credential)){
				errors.rejectValue("errorMsg","wrong.credentials","Wrong credentials!!!Try Again");
			}
		}
	}

	public Map<String, Integer> getAdminCredentialsMap() {
		return adminCredentialsMap;
	}

	public void setAdminCredentialsMap(Map<String, Integer> adminCredentialsMap) {
		this.adminCredentialsMap = adminCredentialsMap;
	}

	

}
