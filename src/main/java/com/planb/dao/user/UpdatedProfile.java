package com.planb.dao.user;

public class UpdatedProfile {
	
	String updatedName;
	String updatedEmail;
	String updatedDOB;
	String updatedGender;
	public String getUpdatedName() {
		return updatedName;
	}
	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}
	public String getUpdatedEmail() {
		return updatedEmail;
	}
	public void setUpdatedEmail(String updatedEmail) {
		this.updatedEmail = updatedEmail;
	}
	public String getUpdatedDOB() {
		return updatedDOB;
	}
	public void setUpdatedDOB(String updatedDOB) {
		this.updatedDOB = updatedDOB;
	}
	public String getUpdatedGender() {
		return updatedGender;
	}
	public void setUpdatedGender(String updatedGender) {
		this.updatedGender = updatedGender;
	}
	@Override
	public String toString() {
		return "UpdatedProfile [updatedName=" + updatedName + ", updatedEmail=" + updatedEmail + ", updatedDOB="
				+ updatedDOB + ", updatedGender=" + updatedGender + "]";
	}
	

}
