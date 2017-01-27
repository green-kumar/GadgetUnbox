<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.concurrent.ConcurrentHashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var startFrom=1;
var isEmptyResponse=false;
var processing;
$(document).ready(function () {
	 
	
	 $(document).scroll(function(e){
		 if (processing)
	         return false;
	   if ($(window).scrollTop() >= $(document).height() - $(window).height() - 700){
		   processing=true
	          if(!isEmptyResponse){
        	  var category=$('#latestCategory').val();
	    	  loadMoreContent(startFrom,category);
	          }
	          }
	 }); 
	 
	 $("#loadMoreAfterResponseFailure").click(function() {
		 if(!isEmptyResponse){ 
			 var category=$('#latestCategory').val();
	    	  loadMoreContent(startFrom,category);
		 }
		})
	 
});

function successCallBack(){
	 startFrom=startFrom+1;
}

function loadMoreContent(startFrom,category){
	  jQuery.ajax({
          url: "/latest/"+category+"/loadMore/"+startFrom,
          success: function (result) {
        	  // /^/ -->regex for test string is html or not
              if(!(result.trim()=="")){
            	  successCallBack();
            	 
           	   $(result).insertBefore($('#placeHolder'));
           	   processing = false;
           	  
              }else{
            	  isEmptyResponse=true; 
            	  processing = false;
              }
          },
          error : function(result) {
        	  $('#loadMoreAfterResponseFailure').remove()
            $("<a href='javascript:void(0);' class='myButton' style='padding: 8px 15px;' id='loadMoreAfterResponseFailure'>loadMore</a>").insertBefore($('#placeHolder'));
            processing = false;
  		},
          async: false
      });
}
/* function showProductDetailsDialog(id){
	var productId=id;
	jQuery.ajax({
        url: "/review-aggregator-electronics/user/details",
        success: function (result) {
            if(result!=null){
            	 $('.detailsdialogbox').find('img').remove();
            	 $('.detailsdialogbox').html(result);
            }
        },
        error : function(result) {
			
        	alert("something went wrong!!! Try again");
		},
        async: false
    });
	
} */

</script>

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
<input type='hidden' id='latestCategory' value='<%=category%>'/>
<% 	
if(latestProductList!=null &&latestProductList.size()>0){

%>
<div class=trendingpannel>

	<div class=trendingpannelheader>
		<span class=frontviewheadings style="margin-left: 10px;"><%=categoryHeader%></span>
		<hr
			style="height: 1px; border: none; color: #808080; background-color: #808080;" />
	</div>
	<%
		
	    int count=4;
	    int incrementFactor=0;
		while(count-->0){
		for (int i = 0; i < 4; i++) {
			ProductMetaData obj=latestProductList.get(i+incrementFactor);
			String mainImageUrl=obj.getMainImageUrl();
	%>
	<div class=imageholder>
	<span class="userfavicon profileheartunselected" title="Add in my favourite list"data-productId='<%=obj.getProductId()%>' data-productName='<%=obj.getProductName()%>' data-category='<%=obj.getCategory()%>' style="float:right;  top:10px; right:5px; position:absolute;z-index:2"></span>
	     <img style="z-index:1;position:absolute; max-height: 100%;max-width: 100%; width: auto;height: auto;  top: 0;  bottom: 0;  left: 0;  right: 0;  margin: auto;" class="lazy" title="<%=obj.getProductName()%>" data-original="<%=mainImageUrl%>"
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
			ProductMetaData obj=latestProductList.get(i+incrementFactor);
	%>

	<div class='trendingdetails' style="margin-bottom:20px">
		<ul>
			<li><%=obj.getProductName()%></li>
			<li>Best buy&nbsp;:&nbsp;<b><%=obj.getBestBuyPrice()%></b></li>
			<li>Rating&nbsp;:&nbsp;<b><%=obj.getAvgRating()%>/5</b></li>
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
	incrementFactor+=4;
		}

	%>
	<!-- <div class=trendingpannelloadmorebutton>
		<a href="#" class="myButton">Load more..</a>

	</div> -->

</div>
<%} %>
<div id="placeHolder"></div>
<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
<div class="modal"></div>


