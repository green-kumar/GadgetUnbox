<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.planb.dao.mobile.Mobile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD|UPDATE</title>
</head>
<body>
<%!
String var="AddProduct";
Map<String,ArrayList<String>> productKeyList=null;
String categary="";
String list="accessories";
String listName="name";
String listValue="value";
String listImage_url="image_url";
String list1="additionalFeatures";
String list1Name="name";
String list1Value="value";
String list2="topHeadlineFeatures";
String list3="restImageUrl";
String list4="colors";
String list5="camera.frontcameraInMegaPixalWithFeatures";
String list6="camera.rearCameraInMegaPixalWithFeatures";
String list7="camera.otherCameraFeatures";
Map<String,ArrayList<String>> ListTypeParemeter=new HashMap<String,ArrayList<String>>();
String list8="display.otherDisplayFeatures";
String list9="storage.expendableStorageFeatures";
public Map<String,ArrayList<String>> retrunListTypeParemeter(){
ListTypeParemeter.put("mainFeatures",new ArrayList<String>(Arrays.asList(list+"@"+listName+","+listValue+","+listImage_url,list1+"@"+list1Name+","+list1Value,list2,list3,list4)));
ListTypeParemeter.put("camera",new ArrayList<String>(Arrays.asList(list5,list6,list7)));
ListTypeParemeter.put("display", new ArrayList<String>(Arrays.asList(list8)));
ListTypeParemeter.put("storage",new ArrayList<String>(Arrays.asList(list9)));
return ListTypeParemeter;
}

public String getAcceName(String list,int count,String appender){
	return list+"["+count+"]."+appender;
}

public String getAcces(String list,int count){
	return list+"["+count+"]";
}

public String getAdded(String exp1,String exp2){
	return exp1+"."+exp2;
}

String requestedJob="";

public String getUrl(String pre,String post){
	return "/"+pre+"/"+post;
	
}

%>

<%
try{
productKeyList=(Map)request.getAttribute("productDetailsKey");
categary=request.getAttribute("category").toString();
requestedJob=request.getAttribute("requestedJob").toString();
for(Map.Entry<String,ArrayList<String>> mp:productKeyList.entrySet())
{ 
	out.println(mp.getKey());
	 ArrayList<String> al=mp.getValue();
	
	 for(String key:al){
		 out.println(key);
}
}
}catch(Exception e){
	out.println(e);
}

%>

 <div class="container">
		<%
			if ("Add Product".equalsIgnoreCase(requestedJob)) {
		%>
		<h1>ADD product</h1>

		<%
			} else {
		%>
		<h1>Update Product</h1>
		<%
			}
		%>


	<br />

<spring:url value="<%=getUrl(var,categary)%>" var="AddProductActionUrl" />
<form:form class="form-horizontal" method="post" commandName="productForm" action="${AddProductActionUrl}">

        
        
   
			 <%
				for (Map.Entry<String, ArrayList<String>> mp : productKeyList.entrySet()) {
					  String key=mp.getKey();
					  ArrayList<String> value = mp.getValue();
					  %>
					  <hr style="height:5px;border:none;color:#333;background-color:#333;" />
					  <H1><%=key%></H1>
					  <%
					 if(key.equalsIgnoreCase("mainFeatures")){
						 for(String subKey:value){
							 if(subKey.equalsIgnoreCase("productId")){%>
								 <form:hidden path="productId" />
					<%	 }else{ %>
								 <spring:bind path="<%=subKey%>">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="col-sm-2 control-label"><%=subKey%></label>
										<div class="col-sm-10">
											<form:input path="<%=subKey%>" type="text" class="form-control " id="<%=subKey%>" placeholder="<%=subKey%>"/>
											<form:errors path="<%=subKey%>" class="control-label" />
										</div>
									</div>
								</spring:bind>  
							
		
					<% 
							 }
						 }
						 Map<String,ArrayList<String>> ListTypeParemeter=retrunListTypeParemeter();	 
						 if(ListTypeParemeter.containsKey(key)){
							 ArrayList<String> parametersList=ListTypeParemeter.get(key);
							 for(String parameters:parametersList){
								 String array[]=parameters.split("@");
								 if(array.length>1){
									 %>
									 <label><%=array[0]%></label>
									 <spring:bind path="<%=array[0]%>">
			                       <div class="form-group ${status.error ? 'has-error' : ''}">
			                       		<%	for(int i=0;i<5;i++){%>
			 	                 <label class="col-sm-2 control-label"><%=array[0]%><%="-"%><%=i%></label>
				            <div class="col-sm-10">
				            <% for(String str:array[1].split(",")){
				            	
				            	%>
					       <form:input path="<%=getAcceName(array[0],i,str)%>" type="text" class="form-control " id="<%=getAcceName(array[0],i,str)%>" placeholder="<%=getAcceName(array[0],i,str)%>"/>
					       <form:errors path="<%=getAcceName(array[0],i,str)%>" class="control-label" />
				           <%
				            }
				            %>
			               
				          </div>
				          <%} %>
			             </div>
		                 </spring:bind> 
									 <%
								 }else{
									 %>
									  <label><%=array[0]%></label>
		<spring:bind path="<%=array[0]%>">
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%	for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%=array[0]%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path="<%=getAcces(array[0],i)%>" type="text" class="form-control " id="<%=getAcces(array[0],i)%>" placeholder="<%=getAcces(array[0],0)%>"/>
					<form:errors path="<%=getAcces(array[0],i)%>" class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
									 
									 
									 <%
									 
								 }
							 }
						 }
						 
						 
					%>	 
						 
						 
				<%		 
					 }else{
            //***********************Product Subfeatures*****************************
						 for(String subKey:value){
							%>
		<spring:bind path="<%=getAdded(key,subKey)%>">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><%=getAdded(key,subKey)%></label>
				<div class="col-sm-10">
					<form:input path="<%=getAdded(key,subKey)%>" type="text" class="form-control " id="<%=getAdded(key,subKey)%>" placeholder="<%=getAdded(key,subKey)%>"/>
					<form:errors path="<%=getAdded(key,subKey)%>" class="control-label" />
				</div>
			</div>
		</spring:bind> 
					<% 
						 }
						 Map<String,ArrayList<String>> ListTypeParemeter=retrunListTypeParemeter();	 
						 if(ListTypeParemeter.containsKey(key)){
							 ArrayList<String> parametersList=ListTypeParemeter.get(key);
							 for(String parameters:parametersList){
								 String array[]=parameters.split("@");
								 if(array.length>1){
									 %>
									 <label><%=array[0]%></label>
									 <spring:bind path="<%=array[0]%>">
			                       <div class="form-group ${status.error ? 'has-error' : ''}">
			                       <%for(int i=0;i<5;i++){ %>
			 	                 <label class="col-sm-2 control-label"><%=array[0]%><%="-"%><%=i%></label>
				            <div class="col-sm-10">
				            <% for(String str:array[1].split(",")){
				            	
				            	%>
					       <form:input path="<%=getAcceName(array[0],i,str)%>" type="text" class="form-control " id="<%=getAcceName(array[0],i,str)%>" placeholder="<%=getAcceName(array[0],i,str)%>"/>
					       <form:errors path="<%=getAcceName(array[0],i,str)%>" class="control-label" />
				           <%
				            }
				            %>
			               
				          </div>
				          <%} %>
			             </div>
		                 </spring:bind> 
									 <%
								 }else{
									 %>
									 <label><%=array[0]%></label>
									  <spring:bind path="<%=array[0]%>">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<%for(int i=0;i<5;i++) {%>
				<label class="col-sm-2 control-label"><%=array[0]%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path="<%=getAcces(array[0],i)%>" type="text" class="form-control " id="<%=getAcces(array[0],i)%>" placeholder="<%=getAcces(array[0],i)%>"/>
					<form:errors path="<%=getAcces(array[0],i)%>" class="control-label" />
				</div>
				<% }%>
			</div>
		</spring:bind> 
									 
									 
									 <%
									 
								 }
							 }
						 }
						 
						 
					 
					 }
					
				}
					
					
					
					
			      
			%>
			
			
			
			<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<%
			if ("Add Product".equalsIgnoreCase(requestedJob)) {
		%>
		<button type="submit" class="btn-lg btn-primary pull-right">Add</button>

		<%
			} else {
		%>
	<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
		<%
			}
		%>
			
						</div>
		</div>
			
</form:form>

</div>

</body>
</html>