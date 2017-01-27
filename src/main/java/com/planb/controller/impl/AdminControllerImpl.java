package com.planb.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.planb.dao.admin.AdminLoginForm;
import com.planb.dao.admin.AdminSessionDetail;
import com.planb.validator.AdminLoginValidator;


@Controller
public class AdminControllerImpl {

	@Autowired
	public ApplicationContext appContext;
	
	@Autowired
	AdminLoginValidator adminLoginValidator;
	
	@RequestMapping(value = "/adminloginajax",method = RequestMethod.POST ,headers="Accept=application/json")
	public @ResponseBody AdminLoginForm adminLoginajaxGet(@RequestBody AdminLoginForm adminLoginForm,HttpServletRequest request,HttpServletResponse response) {
	 if(request!=null && !"".equals(request)){
	      try {
			 String credential=new StringBuilder().append(adminLoginForm.getName()).append(",").append(adminLoginForm.getEmail()).append(",").append(adminLoginForm.getPassword()).toString();
			if(!adminLoginValidator.getAdminCredentialsMap().containsKey(credential)){
				adminLoginForm.setErrorMsg("Wrong credentials!!!Try Again");
				return adminLoginForm;
			}else{
				AdminSessionDetail adminSessionDetail=(AdminSessionDetail)appContext.getBean("adminSessionDetail"); 
				adminSessionDetail.setAdminSessionDetail(adminLoginForm.getName(), adminLoginForm.getEmail(), true);
				request.getSession().setAttribute("adminData", adminSessionDetail);
				adminLoginForm.setErrorMsg("success");
				return adminLoginForm;
			}
			
		      } catch (Exception e) {
		    	  return null;
		   } 
	  
	}
 else{
	 return null;
	 
 }
}
	
	 @RequestMapping(value = "/adminlogin",method = RequestMethod.GET)
		public  String adminLoginGet(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) {
		 AdminSessionDetail adminSessionDetail= (AdminSessionDetail)request.getSession().getAttribute("adminData");  
		 if(adminSessionDetail==null){
		 AdminLoginForm adminLoginForm=(AdminLoginForm)appContext.getBean("adminLoginForm");
		    modelMap.put("adminLoginForm", adminLoginForm);
		    return "adminlogin";
		}
	 else{
		 return "index";
		 
	 }
}
	 
	 @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
		public  String adminLoginPost(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@ModelAttribute("adminLoginForm")AdminLoginForm adminLoginForm,BindingResult result) {
		    
		 adminLoginValidator.validate(adminLoginForm, result);
		 if(result.hasErrors()){
			 return "adminlogin";
		 }else{
			 AdminSessionDetail adminSessionDetail=(AdminSessionDetail)appContext.getBean("adminSessionDetail");
			 adminSessionDetail.setAdminSessionDetail(adminLoginForm.getName(), adminLoginForm.getEmail(), true);
			 request.getSession().setAttribute("adminData",adminSessionDetail);
			return "index";
		 }
		 
		}
	 @RequestMapping(value = "/adminlogout",method = RequestMethod.GET)
		public  String adminLogOut(HttpServletRequest request, HttpServletResponse response) {
		 request.getSession().removeAttribute("adminData");
		    return "redirect:/index";
		}
}
