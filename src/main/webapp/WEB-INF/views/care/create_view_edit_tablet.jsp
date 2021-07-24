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
String overview[]={"overView.description","overView.releaseDate","overView.marketStaus","overView.phoneWeight","overView.dimension" ,"overView.bodyMaterial"};
String cameraList[]={"camera.frontcameraInMegaPixalWithFeatures","camera.rearCameraInMegaPixalWithFeatures","camera.otherCameraFeatures"};
String display[]={"display.screenSize","display.screenResolution","display.screenMaterial","display.displayTechnology"};
String battery[]={"battery.talkTime","battery.batteryCapacity","battery.batteryTechnology"};
String storage[]={"storage.expendableStorageFeatures"};
String connectivity[]={"connectivityAndFeatures.dualSimSupportabilty","connectivityAndFeatures.bluetoothVersion","connectivityAndFeatures.simCardSize"};
String connectivityList[]={"connectivityAndFeatures.NetworkFeatures","connectivityAndFeatures.wireLessConnectivityFeatures", "connectivityAndFeatures.chargingOptions" ,"connectivityAndFeatures.sensors", "connectivityAndFeatures.othersConnectivityFeatures"};
String operation;
String productId;


public String getAcces(String list,int count){
	return list+"["+count+"]";
}
public String getAcceName(String list,int count,String appender){
	return list+"["+count+"]."+appender;
}
%>
<%
operation = (String)request.getAttribute("operation");
productId= (String)request.getAttribute("productId");
%>

<div class="enginHeader"  style="margin-top:30px">
<%if(operation.equalsIgnoreCase("add")){ %>
<span class="profileHeader" >Create Tablet</span>
<%}else{ %>
<span class="profileHeader" >Edit <%=productId%></span>
<%} %>

</div>
<div class="enginContent" style="height:auto;">
<form:form modelAttribute="formAttribute" method="POST" action="/care/save/tablet">
<div class="enginContent" style="height:auto;">
        <div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >General</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
					<table class="planbFont">
				<tr>
					<td>Id</td>
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
					<td>Size</td>
					<td><form:input path="size" type="text" placeholder="ex:16GB,32GB,64GB"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price</td>
					<td><form:input path="bestBuyPrice" type="text" placeholder="Price"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Processor</td>
					<td><form:input path="processor" type="text" placeholder="Processor"/></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				
				<tr>
					<td>RAM</td>
					<td><form:input path="RAM" placeholder = "RAM in GB/MB"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;releasedOS</td>
					<td><form:input path="releasedOS" type="text" placeholder="releasedOS"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TabletWeight</td>
					<td><form:input path="tabletWeight" type="text" placeholder="tabletWeight"/></td>
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
		
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >OverView</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		     <table class="planbFont">
				     <%for(String subKey:overview){ %>
				    <tr><td width=120px><%=subKey.split("\\.")[1].toLowerCase()%><%-- <%=subKey.split(".")[0].toLowerCase()%> --%></td><td><form:input path="<%=subKey%>" type="text" /></td></tr>
				   <%} %>
		   </table>
		</div>
		
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >Camera</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
            <table class="PlanbFont">
				<tr>
				<td width=120px>Video Resolution</td>
				<td><form:input path="camera.videoResolution" type="text" /></td>
		 </tr>
		 <tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
	  <%for(String subKey:cameraList){ %>
	   <tr><td><%=subKey.split("\\.")[1]%></td></tr>
	   
		<%	for(int i=0;i<5;i++){%>
					<tr><td><%=subKey.split("\\.")[1]+"_"+i%></td><td><form:input path="<%=getAcces(subKey,i)%>" type="text" /></td></tr>
			<% 	} %>
			<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
	  <%} %>
	  </table>
		</div>
		
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >Display</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		 <table class="planbFont">
				     <%for(String subKey:display){ %>
				    <tr><td width=120px><%=subKey.split("\\.")[1].toLowerCase()%></td><td><form:input path="<%=subKey%>" type="text" /></td></tr>
				   <%} %>
		   <tr><td style="line-height: 10px" colspan=2>&nbsp;</td></tr>
		   
		   <tr><td>otherDisplayFeatures</td></tr>
		<%for(int i=0;i<5;i++){%>
				<tr><td>otherDisplayFeatures<%="-"%><%=i%></td>
				<td>	<form:input path='<%=getAcces("display.otherDisplayFeatures",i)%>' type="text" /></td>
				</tr>
			<% 	} %>
			
		   </table>
		</div>
		
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >Battery</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		<table class="planbFont">
		<%for(String subKey:battery){ %>
				    <tr><td width=120px><%=subKey.split("\\.")[1].toLowerCase()%></td><td><form:input path="<%=subKey%>" type="text" /></td></tr>
				   <%} %>
		
		</table>
		</div>
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >Storeage</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		 <table class="PlanbFont">
				<tr>
				<td width=120px>Internal Storage</td>
				<td><form:input path="storage.internalStorage" type="text" /></td>
		 </tr>
		 <tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
	  <%for(String subKey:storage){ %>
	   <tr><td><%=subKey.split("\\.")[1]%></td></tr>
	   
		<%	for(int i=0;i<5;i++){%>
					<tr><td><%=subKey.split("\\.")[1]+"_"+i%></td><td><form:input path="<%=getAcces(subKey,i)%>" type="text" /></td></tr>
			<% 	} %>
			<tr><td style="line-height: 10px" colspan=2>&nbsp;</td></tr>
	  <%} %>
	  </table>
		</div>
		
		<div class="enginHeader"  style="margin-top:30px ;height:30px; width:1000px;margin-left:50px"><span class="profileHeader" >ConnectivityAndFeatures</span></div>
		<div class="enginContent" style="height:auto; width:1000px;margin-left:50px">
		<table class="planbFont">
		<%for(String subKey:connectivity){ %>
				    <tr><td width=120px><%=subKey.split("\\.")[1].toLowerCase()%></td><td><form:input path="<%=subKey%>" type="text" /></td></tr>
				   <%} %>
		<tr><td style="line-height: 10px" colspan=2>&nbsp;</td></tr>
		<%for(String subKey:connectivityList){ %>
	   <tr><td><%=subKey.split("\\.")[1]%></td></tr>
	   
		<%	for(int i=0;i<5;i++){%>
					<tr><td><%=subKey.split("\\.")[1]+"_"+i%></td><td><form:input path="<%=getAcces(subKey,i)%>" type="text" /></td></tr>
			<% 	} %>
			<tr><td style="line-height: 10px" colspan=2>&nbsp;</td></tr>
	  <%} %>
		
		</table>
		</div>

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