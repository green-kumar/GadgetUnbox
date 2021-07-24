<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
ArrayList<ProductMetaData> latestProductList=null;
String category="";
String categoryHeader="";

%>
<%
latestProductList=(ArrayList)request.getAttribute("latestProductList");
category=(String)request.getAttribute("category");
categoryHeader=(String)request.getAttribute("categoryHeader");
%>
<% 	
if(latestProductList!=null &&latestProductList.size()>0){

%>
<div class=trendingpannel>
	
	<%
		int count=4;
	    int incrementFactor=0;
		while(count-->0){
		for (int i = 0; i < 4; i++) {
			if((i+incrementFactor) < latestProductList.size()){
			ProductMetaData obj=latestProductList.get(i+incrementFactor);
			String mainImageUrl=obj.getMainImageUrl();
	%>
	<div class=imageholder>
	<span class="userfavicon profileheartunselected" title="Add in my favourite list" data-productId='<%=obj.getProductId()%>' data-avgRating='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right;  top:10px; right:5px;position:absolute;z-index:2"></span>
	 <img style="z-index:1;position:absolute; max-height: 100%;max-width: 100%; width: auto;height: auto;  top: 0;  bottom: 0;  left: 0;  right: 0;  margin: auto;" title="<%=obj.getProductName()%>" src="<%=mainImageUrl%>"
			alt='latest <%=obj.getProductName()%> image' id="product-main-image" data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>' data-avgRating='<%=obj.getAvgRating()%>'
			onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';"
			width="175" height="225" />	
	</div>
	<%
		}}
	%>
	<%
		for (int i = 0; i < 4; i++) {
			if((i+incrementFactor) < latestProductList.size()){
			ProductMetaData obj=latestProductList.get(i+incrementFactor);
	%>

	<div class='trendingdetails' style="margin-bottom:50px">
		<ul>
			<li><%=obj.getProductName()%></li>
			<li>Best buy&nbsp;:&nbsp;<%=obj.getBestBuyPrice()%></li>
			<li>Rating&nbsp;:&nbsp;<%=obj.getAvgRating()%>/5</li>
			<li>Fav it&nbsp;:&nbsp;<span class="userfavicon profileheartunselected" data-productId='<%=obj.getProductId()%>' data-productName='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>'></span></li>
			<li><a href="javascript:void(0)" class='btn-info detail' id='1'
				data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>'
				data-avgRating='<%=obj.getAvgRating()%>'>Details</a><a
				href="javascript:void(0)" class='btn-info detail' id='2'
				data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>' data-avgRating='<%=obj.getAvgRating()%>'>Reviews</a></li>
			
					</ul>
	</div>
	<%
		}}
	incrementFactor+=4;
		}

	%>
	

</div>
<%}%>



