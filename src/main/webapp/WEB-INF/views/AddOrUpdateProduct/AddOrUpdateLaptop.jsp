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
<title>ADD|UPDATE Laptop</title>
</head>
<body>
<%!
String requestedJob="";
String mainFeatures[]={"name","description","brand","mainImageUrl","laptopType","weight","bestBuyPrice","dimension","displaySize","batteryLife","batteryQuality","releasedOS",};
String mainFeaturesNonEdit[]={"createdOn","updatedOn","userReviewCount","criticsReviewCount"};
String processor[]={ "processor.processor","processor.processorImgUrl","processor.processorSpeed","processor.processorCores","processor.cache"};
String graphics[]={ "graphics.graphicsProcessorSupport","graphics.totalGraphicsMemory"};
String memory[]={ "memory.ramIncluded","memory.ramPossible","memory.storage","memory.storageTechnology"};
String laptopDisplay[]={ "laptopDisplay.displaySize","laptopDisplay.displayType","laptopDisplay.pixalDensity","laptopDisplay.resolution"};
String connectivity[]={	 "connectivity.ethernet","connectivity.wifi","connectivity.bluetooth","connectivity.lanport","connectivity.cardReader","connectivity.headPhoneJack","connectivity.securityLockPort"};
String input[]={ "input.camera","input.speaker","input.Sound","input.opticalDrive"};
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
		<h1>Add Laptop</h1>

<%}else{%>
		<h1>Update Laptop</h1>
<%}%>
	    <br/>
	 
	 <spring:url value="/laptop/AddProduct" var="AddProductActionUrl" />   
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
    <label>WarrantyAndAdditionalFeatures</label>
    <spring:bind path='WarrantyAndAdditionalFeatures'>
                 <div class="form-group ${status.error ? 'has-error' : ''}">
                 		<%for(int i=0;i<5;i++){%>
             <label class="col-sm-2 control-label">WarrantyAndAdditionalFeatures<%="-"%><%=i%></label>
       <div class="col-sm-10">
   <form:input path='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"name")%>' type="text" class="form-control " id='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"name")%>' placeholder='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"name")%>'/>
   <form:errors path='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"name")%>' class="control-label" />
    <form:input path='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"value")%>' type="text" class="form-control " id='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"value")%>' placeholder='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"value")%>'/>
   <form:errors path='<%=getAcceName("WarrantyAndAdditionalFeatures",i,"value")%>' class="control-label" />
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
		
		
		<!---------------------------------------------------start of processor---------------------------------------- -->
		<H1>processor</H1>
		<%for(String subKey:processor){ %>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-----------------------------------------------------end of processor-------------------------------------------------- -->
		
		
		
		
		
		<!-------------------------------------------------------start of graphics----------------------------------------------------- -->
		<H1>graphics</H1>	
		<%for(String subKey:graphics) {%>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of graphics ------------------------------------------------------->
	
	<!-------------------------------------------------------start of memory----------------------------------------------------- -->
		<H1>memory</H1>	
		<%for(String subKey:memory) {%>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of memory ------------------------------------------------------->
	
	<!-------------------------------------------------------start of laptopDisplay----------------------------------------------------- -->
		<H1>laptopDisplay</H1>	
		<%for(String subKey:laptopDisplay) {%>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of laptopDisplay ------------------------------------------------------->
	
	<!-------------------------------------------------------start of connectivity----------------------------------------------------- -->
		<H1>connectivity</H1>	
		<%for(String subKey:connectivity) {%>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of connectivity ------------------------------------------------------->
	
	<!-------------------------------------------------------start of input----------------------------------------------------- -->
		<H1>input</H1>	
		<%for(String subKey:input) {%>
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
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of input ------------------------------------------------------->
	
	
	
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