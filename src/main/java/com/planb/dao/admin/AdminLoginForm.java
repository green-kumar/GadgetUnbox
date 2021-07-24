package com.planb.dao.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AdminLoginForm {
 private String name;
 private  String email;
 private String password;
 private String errorMsg;
 private int retryCount;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
/**
 * @param name
 * @param email
 * @param password
 */
public AdminLoginForm(String name, String email, String password) {
	super();
	this.name = name;
	this.email = email;
	this.password = password;
}
/**
 * 
 */
public AdminLoginForm() {
	super();
	// TODO Auto-generated constructor stub
}
public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}
public int getRetryCount() {
	return retryCount;
}
public void setRetryCount(int retryCount) {
	this.retryCount = retryCount;
}

	
}
