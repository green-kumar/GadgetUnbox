<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(".enginHeader").click(function(){
	 $(this).next("div.enginContent").slideToggle("fast");
});
$(".careDelete").click(function(e){
	 var productId = e.target.id;
	 var category = category = $('.catCheckbox:checked').val();
	 deleteProduct(category,productId);
});
</script>

<%!
String overview[]={"overView.description","overView.releaseDate","overView.marketStaus","overView.CameraWeight","overView.dimension" ,"overView.bodyMaterial"};
String Sensor[] = {"sensor.imageSensorFormat","sensor.sensorTyp","sensor.sensorSize"};
String videoRecording[] = {"videoRecording.videoResolution","VideoRecording.videoFileFormat","videoRecording.micType"};
String exposure[] = {"exposure.exposureCompensation","exposure.autoExposureBracketing","exposure.exposureMating","exposure.exposureModes"};
String lens[]= {"lens.digitalZoom","lens.lensType","lens.lensElement","lens.lensGroup","lens.filterSize"};
String focus[] = {"focus.focusAdjustment","focus.autoFocusTechnology","focus.focusRange"};
String cameraBattery[] = {"cameraBattery.batteryLife","cameraBattery.batteryDetails"};
String cameraStorage[] = { "cameraStorage.fileFormat" , "cameraStorage.storageMedia"}; 
String operation;
String productId;

List<String[]> subFeature =null;
public String getAcces(String list,int count){
	return list+"["+count+"]";
}
public String getAcceName(String list,int count,String appender){
	return list+"["+count+"]."+appender;
}
%>
<%
subFeature= new ArrayList<String[]>();
operation = (String)request.getAttribute("operation");
productId= (String)request.getAttribute("productId");
subFeature.add(overview);
subFeature.add(Sensor);
subFeature.add(videoRecording);
subFeature.add(exposure);
subFeature.add(lens);
subFeature.add(focus);
subFeature.add(cameraBattery);
subFeature.add(cameraStorage);

%>

<div class="enginHeader"  style="margin-top:30px">
<%if(operation.equalsIgnoreCase("add")){ %>
<span class="profileHeader" >Create Camera</span>
<%}else{ %>
<span class="profileHeader" >Edit <%=productId%></span>
<%} %>
</div>
<div class="enginContent" style="height:auto;">
<form:form modelAttribute="formAttribute" method="POST" action="/care/save/camera">
<div class="enginContent" style="height:auto;">
        <div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >General</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
					<table class="planbFont">
				<tr>
					<td width=150>Id</td>
					<td><form:input path="productId" type="text" placeholder="product id" readonly="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name</td>
					<td><form:input path="name" type="text" placeholder="Name"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Brand</td>
					<td><form:input path="brand" type="text" placeholder="brand"/></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				
				<tr>
					<td>cameraType</td>
					<td><form:select path="cameraType" items="${cameraTypeList}"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price</td>
					<td><form:input path="bestBuyPrice" type="text" placeholder="Price"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;viewFinderType</td>
					<td><form:select path="viewFinderType" items="${viewFinderType}" placeholder="viewFinderType"/></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				
				<tr>
					<td>Display Size</td>
					<td><form:input path="displaySize" placeholder = "display Size"/></td>
					
				</tr>
				
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Main image url</td>
					<td><form:input path="mainImageUrl" placeholder = "mainImageUrl(get from cloudinary)"/></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				
				<%for(int i=0;i<3;i++){%>
				<tr>
				<td>Rest image url<%="_"+i%></td>
				<td><form:input path='<%=getAcces("restImageUrl",i)%>' type="text"  placeholder='restImageUrl(get from cloudinary) '/>
			     </tr>
			<% 	} %>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				</table>
				<table class="planbFont">
				<tr>
				
					<td width=150>Total MegaPixal</td>
					<td><form:input path="totalMegaPixal" type="text" placeholder="Total MegaPixal"/></td>
					<td width=160>&nbsp;&nbsp;Effective MegaPixal</td>
					<td><form:input path="effectiveMegaPixal" type="text" placeholder="Effective MegaPixal"/></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				 <tr>
					<td>Create Date</td>
					<td><form:input path="createdOn" type="text" placeholder="createdOn"  readonly="true"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Updated Date</td>
					<td><form:input path="updatedOn" type="text" placeholder="updatedOn"  readonly="true"/></td>
				</tr> 
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
</table>		



<table class="planbFont">		
				<tr>
					<td width=150>Top headline feature:</td>
				</tr> 
				<%for(int i=0;i<5;i++){%>
				<tr>
				<td>Top headline features<%="_"+i%></td>
				<td><form:input path='<%=getAcces("topHeadlineFeatures",i)%>' type="text"  placeholder="Name"/></td>
			     </tr>
			<% 	} %>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Accessories:</td>
				</tr> 
				<%for(int i=0;i<3;i++){%>
				<tr>
				<td>Accessories<%="_"+i%></td>
				<td><form:input path='<%=getAcceName("accessories",i,"name")%>' type="text"  placeholder='Name'/></td>
				<td><form:input path='<%=getAcceName("accessories",i,"value")%>' type="text"  placeholder='Value'/></td>
				<td><form:input path='<%=getAcceName("accessories",i,"image_url")%>' type="text"  placeholder='Image Url'/></td>
			     </tr>
			<% 	} %>
			<tr><td style="line-height: 10px" colspan=2>&nbsp;</td></tr>
				<tr>
					<td>Additional features</td>
				</tr>
				<%for(int i=0;i<3;i++){%>
				<tr>
				<td>Additional features<%="_"+i%></td>
				<td><form:input path='<%=getAcceName("additionalFeatures",i,"name")%>' type="text"  placeholder='Name'/></td>
				<td><form:input path='<%=getAcceName("additionalFeatures",i,"value")%>' type="text"  placeholder='Value'/></td>
			     </tr>
			<% 	} %>
				
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Colors:</td><td><form:checkboxes path="colors" items="${colorList}" /></td>
				</tr> 
				</table>
		</div>
		  <%for(String subFeatureArray[] : subFeature){ %>
		 <div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" ><%=subFeatureArray[0].split("\\.")[0]%></span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		      <table class="planbFont">
				      <%for(String subKey:subFeatureArray){ %>
				    <tr><td width=260px><%=subKey.split("\\.")[1].toLowerCase()%></td><td><form:input path="<%=subKey%>" type="text" /></td></tr>
				   <%} %> 
		   </table>
		</div>
		<%} %>  
		
		<table>
			 <tr>
					
				<%if(operation.equalsIgnoreCase("add")){ %>
<td><input type=submit
						class="myButton"
						style="padding: 8px 20px"/></td><%}else{ %>
<td><input type=submit value="update" class="myButton"
						style="padding: 8px 20px"/></td>&nbsp;&nbsp;&nbsp;<td><input type=button id="${productId}" value="delete" class="myButton careDelete"
						style="padding: 8px 20px"/></td><td><span><img id="targetEditCare"
							src="<c:url value="/resources/usertheme/images/load.gif" />"
							alt="..." style="display: none" /></img><span></span></td>
<%} %>
						
						
					
				</tr>
				</table>


</div> 
</form:form>
</div>