<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD|UPDATE Mobile</title>
</head>
<body>
<%!
String requestedJob="";
String mainFeatures[]={"name",
"brand",
"mainImageUrl",
"phoneType",
"bestBuyPrice",
"processor",
"RAM",
"releasedOS",};
String mainFeaturesNonEdit[]={"createdOn",
"updatedOn",
"userReviewCount",
"criticsReviewCount"};
String overview[]={"overView.description","overView.releaseDate","overView.marketStaus","overView.phoneWeight","overView.dimension" ,"overView.bodyMaterial"};
String cameraList[]={"camera.frontcameraInMegaPixalWithFeatures","camera.rearCameraInMegaPixalWithFeatures","camera.otherCameraFeatures"};
String display[]={"display.screenSize","display.screenResolution","display.screenMaterial","display.displayTechnology"};
String battery[]={"battery.talkTime","battery.batteryCapacity","battery.batteryTechnology"};
String connectivity[]={"connectivityAndFeatures.dualSimSupportabilty","connectivityAndFeatures.bluetoothVersion","connectivityAndFeatures.simCardSize"};
String connectivityList[]={"connectivityAndFeatures.NetworkFeatures","connectivityAndFeatures.wireLessConnectivityFeatures", "connectivityAndFeatures.chargingOptions" ,"connectivityAndFeatures.sensors", "connectivityAndFeatures.othersConnectivityFeatures"};
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
		<h1>ADD product</h1>

<%}else{%>
		<h1>Update Product</h1>
<%}%>
	    <br/>
	 
	 <spring:url value="/mobile/AddProduct" var="AddProductActionUrl" />   
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
		
		
		<!---------------------------------------------------start of OverView---------------------------------------- -->
		<H1>OverView</H1>
		<%for(String subKey:overview){ %>
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
	<!-----------------------------------------------------end of OverView-------------------------------------------------- -->
		
		
		<!-------------------------------------------------------start of Camera --------------------------------------->	
		<H1>Camera</H1>
		<spring:bind path="camera.videoResolution">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">camera.videoResolution</label>
			<div class="col-sm-10">
				<form:input path="camera.videoResolution" type="text" class="form-control " id="camera.videoResolution" placeholder="camera.videoResolution"/>
				<form:errors path="camera.videoResolution" class="control-label" />
			</div>
		</div>
	  </spring:bind> 
	  <%for(String subKey:cameraList){ %>
	   <label><%=subKey%></label>
		<spring:bind path="<%=subKey%>">
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%	for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%=subKey%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path="<%=getAcces(subKey,i)%>" type="text" class="form-control " id="<%=getAcces(subKey,i)%>" placeholder="<%=getAcces(subKey,i)%>"/>
					<form:errors path="<%=getAcces(subKey,i)%>" class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
	  <%} %>
	<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of Camera----------------------------------------------------- -->
		
		
		<!-------------------------------------------------------start of display----------------------------------------------------- -->
		<H1>Display</H1>	
		<%for(String subKey:display) {%>
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
		<label>otherDisplayFeatures</label>
		<spring:bind path='display.otherDisplayFeatures'>
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%="display.otherDisplayFeatures"%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path='<%=getAcces("display.otherDisplayFeatures",i)%>' type="text" class="form-control " id='<%=getAcces("display.otherDisplayFeatures",i)%>' placeholder='<%=getAcces("display.otherDisplayFeatures",i)%>'/>
					<form:errors path='<%=getAcces("display.otherDisplayFeatures",i)%>' class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind>
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of Dispay----------------------------------------------------- -->
		
		
		<!-------------------------------------------------------start of Battery----------------------------------------------------- -->
		<H1>Battery</H1>	
		<%for(String subKey:battery) {%>
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
	<!-------------------------------------------------------end of Battery ------------------------------------------------------->
	
	
	
	<!--------------------------------------start of Storeage------------------------------------------>
		<H1>Storeage</H1>
		<spring:bind path='storage.internalStorage'>
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">internalStorage</label>
				<div class="col-sm-10">
					<form:input path='storage.internalStorage' type="text" class="form-control " id='storage.internalStorage%>' placeholder='storage.internalStorage'/>
					<form:errors path='storage.internalStorage' class="control-label" />
				</div>
			</div>
		</spring:bind> 
		
		<label>expendableStorageFeatures</label>
		<spring:bind path='storage.expendableStorageFeatures'>
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%="storage.expendableStorageFeatures"%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path='<%=getAcces("storage.expendableStorageFeatures",i)%>' type="text" class="form-control " id='<%=getAcces("storage.expendableStorageFeatures",i)%>' placeholder='<%=getAcces("storage.expendableStorageFeatures",i)%>'/>
					<form:errors path='<%=getAcces("storage.expendableStorageFeatures",i)%>' class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
		<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of Storage ------------------------------------------------------->
	 <!--------------------------------------start of ConnectivityAndFeatures------------------------------------------>
		<H1>ConnectivityAndFeatures</H1>
		<%for(String subKey:connectivity){ %>
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
		 <%for(String subKey:connectivityList){ %>
	   <label><%=subKey%></label>
		<spring:bind path="<%=subKey%>">
			<div class="form-group ${status.error ? 'has-error' : ''}">
		<%	for(int i=0;i<5;i++){%>
				<label class="col-sm-2 control-label"><%=subKey%><%="-"%><%=i%></label>
				<div class="col-sm-10">
					<form:input path="<%=getAcces(subKey,i)%>" type="text" class="form-control " id="<%=getAcces(subKey,i)%>" placeholder="<%=getAcces(subKey,i)%>"/>
					<form:errors path="<%=getAcces(subKey,i)%>" class="control-label" />
				</div>
			<% 	} %>
			</div>
		</spring:bind> 
	  <%} %>
	<hr style="height:5px;border:none;color:#333;background-color:#333;" />
	<!-------------------------------------------------------end of ConnectivityAndFeatures----------------------------------------------------- -->
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