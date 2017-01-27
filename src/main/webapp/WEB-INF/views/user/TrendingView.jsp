<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
//String topHeaders[]={"Latest smartphones","Latest cameras","Latest tablets","Latest laptops","Latest headphones"};
//String itopHeaders[]={"Trending smartphones","Trending cameras","Trending tablets","Trending laptops","Trending headphones"};

String trendingUiListingOrder;
String trendingUiListingOrderArray[];

LinkedHashMap<String, ArrayList<ProductMetaData>> trendingMap=null;

%>
<%
trendingMap=(LinkedHashMap)request.getAttribute("trendingMap");
trendingUiListingOrder = (String)request.getAttribute("trendingUiListingOrder");
  if(trendingUiListingOrder != null && !trendingUiListingOrder.equals("")){
	  trendingUiListingOrderArray = trendingUiListingOrder.split(",");
  }
   

%>
<% 	for (int j = 0; j < trendingUiListingOrderArray.length; j++) {
	ArrayList<ProductMetaData> list=null;
	if(trendingMap!=null){
     list=trendingMap.get(trendingUiListingOrderArray[j]);
	}
if(list!=null &&list.size()>0){

%>
<div class=trendingpannel>
	<div class=trendingpannelheader>
		<span class=frontviewheadings style="margin-left: 10px;"><%=trendingUiListingOrderArray[j]%></span>
		<hr
			style="height: 1px; border: none; color: #808080; background-color: #808080;" />
	</div>
	<%if(trendingUiListingOrderArray[j].equalsIgnoreCase("Trending Smartphones")){
		int count=2;
		int incrementFactor=0;
		while(count-->0){
		
		%>
	<%
		for (int i = 0; i < 4; i++) {
			ProductMetaData obj=list.get(i+incrementFactor);
	%>
	<div class="imageholder">
	<span class="userfavicon profileheartunselected" title="Add in my favourite list"data-productId='<%=obj.getProductId()%>' data-productName='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right;  top:10px; right:5px; position:absolute;z-index:2"></span>
		<img style="z-index:1;position:absolute; max-height: 100%;max-width: 100%; width: auto;height: auto;  top: 0;  bottom: 0;  left: 0;  right: 0;  margin: auto;" class="lazy" data-original="<%=obj.getMainImageUrl()%>"
			alt='latest <%=obj.getProductName()%> image' id="product-main-image" data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>' data-avgRating='<%=obj.getAvgRating()%>'
			onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';"
			width="175" height="225" />
	</div>
	<%
		}
	%>
	<%
		for (int i = 0; i < 4; i++) {
			ProductMetaData obj=list.get(i+incrementFactor);
	%>

	<div class='trendingdetails' style="margin-bottom:20px">
		<ul>
			<li><%=obj.getProductName()%></li>
			<li>Best buy&nbsp;:&nbsp;<%=obj.getBestBuyPrice()%></li>
			<li>Rating&nbsp;:&nbsp;<%=obj.getAvgRating()%>/5</li>
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
		}
	%>
	<%
	incrementFactor+=4;
		}
}else{
		%>
	<%
		for (int i = 0; i < 4; i++) {
			ProductMetaData obj=list.get(i);
	%>
	<div class=imageholder>
	<span class="userfavicon profileheartunselected" title="Add in my favourite list"data-productId='<%=obj.getProductId()%>' data-productName='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right;  top:10px; right:5px; position:absolute;z-index:2"></span>
		<img style="z-index:1;position:absolute; max-height: 100%;max-width: 100%; width: auto;height: auto;  top: 0;  bottom: 0;  left: 0;  right: 0;  margin: auto;" class="lazy" data-original="<%=obj.getMainImageUrl()%>"
			alt='latest <%=obj.getProductName()%> image' id="product-main-image" data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>' data-avgRating='<%=obj.getAvgRating()%>'
			onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';"
			width="175" height="225" />
	</div>
	<%
		}
	%>
	<%
		for (int i = 0; i < 4; i++) {
			ProductMetaData obj=list.get(i);
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
		}
	%>
	
	<% }%>

</div>
<% 
	
}
}%>
<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
<div class="modal"></div>


