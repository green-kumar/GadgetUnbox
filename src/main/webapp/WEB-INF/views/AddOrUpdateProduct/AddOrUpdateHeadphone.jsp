<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD|UPDATE HeadPhone</title>
</head>
<body>
<%!
String requestedJob="";
String mainFeatures[]={"name","description","brand","mainImageUrl","headphoneType","weight","bestBuyPrice","wireType","design","mic","cordLength","jackDiameter","soundOutput"};
String mainFeaturesNonEdit[]={"createdOn","updatedOn","userReviewCount","criticsReviewCount"};
public String getAcceName(String list,int count,String appender){
	return list+"["+count+"]."+appender;
}

public String getAcces(String list,int count){
	return list+"["+count+"]";
}

public String getAdded(String exp1,String exp2){
	return exp1+"."+exp2;
}
%>


<%
requestedJob=request.getAttribute("requestedJob").toString();
%>

<div class="container">
<%if("Add Product".equalsIgnoreCase(requestedJob)) {%>
		<h1>Add Headphone</h1>

<%}else{%>
		<h1>Update Headphone</h1>
<%}%>
	    <br/>
	 
	 <spring:url value="/headphone/AddProduct" var="AddProductActionUrl" />   
	 <form:form class="form-horizontal" method="post" commandName="productForm" action="${AddProductActionUrl}">
	 <hr style="height:5px;border:none;color:#333;background-color:#333;" />
	 <!---------------------------------------start of main features------------------------------------------->
	 <form:hidden path="productId" />
	 <H1>MainFeatures</H1>
	 <%for(String subKey:mainFeatures){ %>
	  <spring:bind path="<%=subKey%>">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><%=subKey%></label>
			<div class="col-sm-10">
				<form:input path="<%=subKey%>" type="text" class="form-control " id="<%=subKey%>" placeholder="<%=subKey%>"/>
				<form:errors path="<%=subKey%>" class="control-label" />
			</div>
		</div>
	</spring:bind> 
	<%} %> 
	<%for(String subkeyNonEdit:mainFeaturesNonEdit){ %>
	<spring:bind path="<%=subkeyNonEdit%>">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label"><%=subkeyNonEdit%></label>
			<div class="col-sm-10">
				<form:input path="<%=subkeyNonEdit%>" type="text" class="form-control " id="<%=subkeyNonEdit%>" placeholder="<%=subkeyNonEdit%>" />
				<form:errors path="<%=subkeyNonEdit%>" class="control-label" />
			</div>
		</div>
	</spring:bind> 
	<%}%>
	
	<label>accessories</label>
 <spring:bind path='accessories'>
                 <div class="form-group ${status.error ? 'has-error' : ''}">
                 		<%for(int i=0;i<5;i++){%>
             <label class="col-sm-2 control-label">accessories<%="-"%><%=i%></label>
       <div class="col-sm-10">
      
   <form:input path='<%=getAcceName("accessories",i,"name")%>' type="text" class="form-control " id='<%=getAcceName("accessories",i,"name")%>' placeholder='<%=getAcceName("accessories",i,"name")%>'/>
   <form:errors path='<%=getAcceName("accessories",i,"name")%>' class="control-label" />
     
    <form:input path='<%=getAcceName("accessories",i,"value")%>' type="text" class="form-control " id='<%=getAcceName("accessories",i,"value")%>' placeholder='<%=getAcceName("accessories",i,"value")%>'/>
   <form:errors path='<%=getAcceName("accessories",i,"value")%>' class="control-label" />
   
    <form:input path='<%=getAcceName("accessories",i,"image_url")%>' type="text" class="form-control " id='<%=getAcceName("accessories",i,"image_url")%>' placeholder='<%=getAcceName("accessories",i,"image_url")%>'/>
   <form:errors path='<%=getAcceName("accessories",i,"image_url")%>' class="control-label" />
     
     </div>
     <%} %>
       </div>
    </spring:bind> 
    <label>additionalFeatures</label>
    <spring:bind path='additionalFeatures'>
                 <div class="form-group ${status.error ? 'has-error' : ''}">
                 		<%for(int i=0;i<5;i++){%>
             <label class="col-sm-2 control-label">additionalFeatures<%="-"%><%=i%></label>
       <div class="col-sm-10">
   <form:input path='<%=getAcceName("additionalFeatures",i,"name")%>' type="text" class="form-control " id='<%=getAcceName("additionalFeatures",i,"name")%>' placeholder='<%=getAcceName("additionalFeatures",i,"name")%>'/>
   <form:errors path='<%=getAcceName("additionalFeatures",i,"name")%>' class="control-label" />
    <form:input path='<%=getAcceName("additionalFeatures",i,"value")%>' type="text" class="form-control " id='<%=getAcceName("additionalFeatures",i,"value")%>' placeholder='<%=getAcceName("additionalFeatures",i,"value")%>'/>
   <form:errors path='<%=getAcceName("additionalFeatures",i,"value")%>' class="control-label" />
     </div>
     <%} %>
    </div>
    </spring:bind> 
	<label>topHeadlineFeatures</label>
		<spring:bind path='topHeadlineFeatures'>
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%	for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label">topHeadlineFeatures-<%=i%></label>
				<div class="col-sm-10">
					<form:input path='<%=getAcces("topHeadlineFeatures",i)%>' type="text" class="form-control " id='<%=getAcces("topHeadlineFeatures",i)%>' placeholder='<%=getAcces("topHeadlineFeatures",i)%>'/>
					<form:errors path='<%=getAcces("topHeadlineFeatures",i)%>' class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
		<label>restImageUrl</label>
		<spring:bind path='restImageUrl'>
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%="restImageUrl"%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path='<%=getAcces("restImageUrl",i)%>' type="text" class="form-control " id='<%=getAcces("restImageUrl",i)%>' placeholder='<%=getAcces("restImageUrl",i)%>'/>
					<form:errors path='<%=getAcces("restImageUrl",i)%>' class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
		<spring:bind path="colors">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">colors</label>
				<div class="col-sm-10">
					<form:checkboxes path="colors" items="${colorList}" element="label class='checkbox-inline'" />
					<br />
					<form:errors path="colors" class="control-label" />
				</div>
			</div>
		</spring:bind>
		 <hr style="height:5px;border:none;color:#333;background-color:#333;" />
		<!---------------------------------------------------end of main features---------------------------------- -->
		
		
	
			<div class="form-group">
		   <div class="col-sm-offset-2 col-sm-10">
			<%if ("Add Product".equalsIgnoreCase(requestedJob)){%>
		<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
		<%} else {%>
	<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
		<%}%>
		  </div>
		</div>
	 </form:form>
 
 </div>
</body>
</html>