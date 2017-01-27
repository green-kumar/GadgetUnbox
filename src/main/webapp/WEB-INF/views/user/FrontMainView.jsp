<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script>


$(document).ready(function () {
	
$(".searchHeader").css("display" , "none");
$(document).scroll(function() {
	  var y = $(this).scrollTop();
	  if (y > 130) {
	    $('.searchHeader').fadeIn();
	  } else {
	    $('.searchHeader').fadeOut();
	  }
	});
});
</script>

<%!LinkedHashMap<String, ArrayList<ProductMetaData>> frontMetaDataMap = null;
	String frontUiListingOrder = null;
	String frontUiListingOrderArray[] = null;
	Map<String,String> frontUIHeaderUrlLookup;
	String url;
	%>
<%
	frontMetaDataMap = (LinkedHashMap) request
			.getAttribute("frontMetaDataMap");
	frontUiListingOrder = (String) request
			.getAttribute("frontUiListingOrder");
	frontUIHeaderUrlLookup=(HashMap)request.getAttribute("frontUIHeaderUrlLookup");
	
	url = (String)request.getAttribute("url");

	if (frontUiListingOrder != null && !"".equals(frontUiListingOrder))
		frontUiListingOrderArray = frontUiListingOrder.split(",");
%>
<input type="hidden" id="url" value="<%=url%>">
<div class=toppannel>
	<div class=searchfilter>
		<select id="searchCat">
			<option selected>All</option>
			<option>Mobile</option>
			<option>Camera</option>
			<option>Tablet</option>
			<option>Laptop</option>
			<option>Headphone</option>
		</select>
	</div>
	<div class="searchfield">
	  <form name="frontMainSearchForm" id="searchBarForm" action="/search" method="GET">
		<input type="text" name="q" autocomplete="off" class="biginput" id="autocomplete"
			placeholder="search for any gadgets..."/><input type="submit" style="height: 0px; width: 0px; border: none; padding: 0px;margin: 0; margin-right:-2px;" hidefocus="true" />
			 <a href="javascript:void(0)"
						class="myButton" id="searchBarButton" style="padding: 9px 24px;"><img style="display: none" alt="" height ="20";width="20"; src="<c:url value="/resources/usertheme/images/load.gif" />"/><img dispaly="none" alt="" height ="20";width="20"; src="<c:url value="/resources/usertheme/images/search_icon_white.png" />"/></a>
						
	   <input style="height: 0px; width: 0px; border: none; padding: 0px; " hidefocus="true" type="hidden" id="category" name="category" value="all"/>
	</form>
	</div>
	 <div id="autosuggest" style=" border: 1px solid #b3b3b3;position: relative;display: none;width: 400px;left: 322px;opacity: 1;overflow: hidden;  z-index:1000;"></div>
	
</div> 
<%
	if (frontUiListingOrderArray != null && frontUiListingOrderArray.length > 0) {
		for (String listItem : frontUiListingOrderArray) {
			ArrayList<ProductMetaData> list = null;
			if (frontMetaDataMap != null) {
				list = frontMetaDataMap.get(listItem);
			}
			if (list != null && list.size() > 0) {
%>
<div class=trendingpannel>
	<div class=trendingpannelheader>
		<span class=frontviewheadings style="margin-left: 10px;"><%=listItem%></span>
		<hr
			style="height: 1px; border: none; color: #808080; background-color: #808080;" />
	</div>
	<%
		for (int i = 0; i < 4; i++) {
						ProductMetaData obj = list.get(i);
						String mainImageUrl = obj.getMainImageUrl();
	%>
	<div class=imageholder>
	<span class="userfavicon profileheartunselected" title="Add in my favourite list" data-productId='<%=obj.getProductId()%>' data-avgRating='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right;  top:10px; right:5px;position:absolute;z-index:2"></span>
		<img style="z-index:1;position:absolute; max-height: 100%;max-width: 100%; width: auto;height: auto;  top: 0;  bottom: 0;  left: 0;  right: 0;  margin: auto;" class="lazy" data-original="<%=mainImageUrl%>"
			alt='latest <%=obj.getProductName()%> image' id="product-main-image" data-productId='<%=obj.getProductId()%>'
				data-category='<%=obj.getCategory()%>' data-avgRating='<%=obj.getAvgRating()%>'
			onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';"
			width="175" height="225"  />
	</div>
	<%
		}
	%>
	<%
		for (int i = 0; i < 4; i++) {
		ProductMetaData obj = list.get(i);
	%>

	<div class=trendingdetails>
		<ul>
			<li><%=obj.getProductName()%></li>
			<li>Best buy&nbsp;:&nbsp;<b><%=obj.getBestBuyPrice()%></b></li>
			<li>Rating&nbsp;:&nbsp;<b><%=obj.getAvgRating()%>/5</b></li>
			<%-- <li><span class="userfavicon profileheartunselected" title="Add in my favourite list"data-productId='<%=obj.getProductId()%>' data-productName='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right; position:absolute; top:10px; right:5px;"></span></li> --%>
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
	<div class=trendingpannelloadmorebutton>
		<a href="<%=frontUIHeaderUrlLookup.get(listItem)%>" class="myButton">See more..</a>

	</div>

</div>
<%
	}
		}
	}
%>
<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>


