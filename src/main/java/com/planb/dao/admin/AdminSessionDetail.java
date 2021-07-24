package com.planb.dao.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AdminSessionDetail {
private String name;
private String email;
private boolean isLoggedIn;
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
public boolean isLoggedIn() {
	return isLoggedIn;
}
public void setLoggedIn(boolean isLoggedIn) {
	this.isLoggedIn = isLoggedIn;
}

/**
 * @param name
 * @param email
 * @param isLoggedIn
 */
public void setAdminSessionDetail(String name, String email, boolean isLoggedIn) {
	this.name = name;
	this.email = email;
	this.isLoggedIn = isLoggedIn;
}
}
