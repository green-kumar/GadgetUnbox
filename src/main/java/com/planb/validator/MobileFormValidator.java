package com.planb.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.planb.dao.mobile.Mobile;
@Component
public class MobileFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Mobile.class.equals(clazz);
	}

	public void validate(Object arg0, Errors errors) {
        Mobile mobile=(Mobile)arg0;
        		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.Mobile.name","Mobile name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneType", "NotEmpty.Mobile.phoneType","phone type is required");
		
	
		
		
		if(mobile.getAccessories().size()<3){
			errors.rejectValue("accessories", "accessories cant be less than 3");
		}
		
		

		
	}

}
