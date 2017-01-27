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
<title>ADD|UPDATE Camera</title>
</head>
<body>
<%!
String requestedJob="";


String mainFeatures[]={"name","brand","mainImageUrl","cameraType","bestBuyPrice","viewFinderType","displaySize","totalMegaPixal","effectiveMegaPixal"};
String mainFeaturesNonEdit[]={"createdOn","updatedOn","userReviewCount","criticsReviewCount"};
String overview[]={"overView.description","overView.releaseDate","overView.marketStaus","overView.CameraWeight","overView.dimension" ,"overView.bodyMaterial"};
String sensor[]={"sensor.imageSensorFormat", "sensor.sensorTyp", "sensor.sensorSize"};
String videoRecording[]={"videoRecording.videoResolution","videoRecording.videoFileFormat","videoRecording.micType"};
String exposure[]={ "exposure.exposureCompensation","exposure.autoExposureBracketing","exposure.exposureMating","exposure.exposureModes"};
String lens[]={"lens.digitalZoom","lens.lensType","lens.lensElement","lens.lensGroup","lens.filterSize"};
String focus[]={ "focus.focusAdjustment", "focus.autoFocusTechnology","focus.focusRange"};
String cameraBattery[]={"cameraBattery.batteryLife","cameraBattery.batteryDetails"};
String cameraStorage[]={"cameraStorage.fileFormat","cameraStorage.storageMedia"};
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
	 
	 <spring:url value="/camera/AddProduct" var="AddProductActionUrl" />   
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
		<label>Colors</label>
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
	
	
	
	<!---------------------------------------------------start of Sensor---------------------------------------- -->
		<H1>Sensor</H1>
		<%for(String subKey:sensor){ %>
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
	<!-----------------------------------------------------end of Sensor-------------------------------------------------- --> 
	
	<!---------------------------------------------------start of VideoRecording---------------------------------------- -->
		<H1>VideoRecording</H1>
		<%for(String subKey:videoRecording){ %>
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
	<!-----------------------------------------------------end of VideoRecording-------------------------------------------------- -->
	
	
	<!---------------------------------------------------start of Exposure---------------------------------------- -->
		<H1>Exposure</H1>
		<%for(String subKey:exposure){ %>
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
	<!-----------------------------------------------------end of Exposure-------------------------------------------------- -->
	
	
	<!---------------------------------------------------start of Lens---------------------------------------- -->
		<H1>Lens</H1>
		<%for(String subKey:lens){ %>
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
	<!-----------------------------------------------------end of Lens-------------------------------------------------- -->
	
	<!---------------------------------------------------start of Focus---------------------------------------- -->
		<H1>Focus</H1>
		<%for(String subKey:focus){ %>
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
	<!-----------------------------------------------------end of Focus-------------------------------------------------- -->
	
	
	<!---------------------------------------------------start of CameraBattery---------------------------------------- -->
		<H1>CameraBattery</H1>
		<%for(String subKey:cameraBattery){ %>
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
	<!-----------------------------------------------------end of CameraBattery-------------------------------------------------- -->
	
	
	<!---------------------------------------------------start of CameraStorage---------------------------------------- -->
		<H1>CameraStorage</H1>
		<%for(String subKey:cameraStorage){ %>
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
	<!-----------------------------------------------------end of CameraStorage-------------------------------------------------- -->
	
	
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