<%@page import="java.util.ArrayList"%>
<%@page import="com.planb.search.dao.SearchProductDao"%>
<%@page import="java.util.List"%>
 <script type="text/javascript">
$(document).ready(function () {
	
	var totalPage= parseInt($('#filterMaxPage').val());
	var spCount=parseInt($('#filterSearchPageCount').val());
	 /* alert("totalPage"+totalPage+"spCount"+spCount); */ 
	$("#"+spCount).addClass("profileHeader");
	
	if(totalPage==1 ){
		$("#spPrev").css("visibility" ,"hidden");
		$("#spNext").css("visibility" ,"hidden");
	}else if(spCount ==1){
		$("#spPrev").css("visibility" ,"hidden");
		$("#spNext").css("visibility" ,"visible");
	}else if(spCount == totalPage){
		$("#spPrev").css("visibility" ,"visible");
		$("#spNext").css("visibility" ,"hidden");
	}else{
		$("#spPrev").css("visibility" ,"visible");
		$("#spNext").css("visibility" ,"visible");
	}
	
	
	 $(".SLI").live(function(e){
			var totalPage= parseInt($('#filterMaxPage').val());
			var spCount =parseInt($('#filterSearchPageCount').val());
			/* alert("totalPage"+totalPage);
			alert("spCount"+spCount); */
			var q=$('#query').val();
			if($(this).text()== 'Next'){
				$("#"+spCount).removeClass("profileHeader");
				spCount=parseInt(spCount)+1;
				$("#"+spCount).addClass("profileHeader");
			}else if($(this).text()== 'Prev'){
				$("#"+spCount).removeClass("profileHeader");
				spCount=parseInt(spCount)-1;
				$("#"+spCount).addClass("profileHeader");
			}else{
				$("#"+spCount).removeClass("profileHeader");
				spCount=$(this).text();
				$("#"+spCount).addClass("profileHeader");
			}
			 if(totalPage==1 ){
				$("#spPrev").css("visibility" ,"hidden");
				$("#spNext").css("visibility" ,"hidden");
			}else if(spCount ==1){
				$("#spPrev").css("visibility" ,"hidden");
				$("#spNext").css("visibility" ,"visible");
			}else if(spCount == totalPage){
				$("#spPrev").css("visibility" ,"visible");
				$("#spNext").css("visibility" ,"hidden");
			}else{
				$("#spPrev").css("visibility" ,"visible");
				$("#spNext").css("visibility" ,"visible");
			} 
			$('#curPage').text(spCount);
			
			var selectedBrand = [];
	           var selectedOS = [];
	           var selectedFeature = [];
	           var selectedCategory = [];
	           var brands="";
	           var OS="";
	           var feature="";
	           var category="";
	           
	           var query = $('#query').val()
	           
	           $('#brandFilter input:checked').each(function() {
	        	   selectedBrand.push($(this).attr('name'));
	           });
	           $('#OSFilter input:checked').each(function() {
	        	   selectedOS.push($(this).attr('name'));
	           });
	           $('#featureFilter input:checked').each(function() {
	        	   selectedFeature.push($(this).attr('name'));
	           });
	           
	           $('#categoryFilter input:checked').each(function() {
	        	   selectedCategory.push($(this).attr('name'));
	           });

	           

	           var arrayLength = selectedBrand.length;
	           for (var i = 0; i < arrayLength; i++) {
	        	   brands=brands+selectedBrand[i]+","
	           }
	            arrayLength = selectedOS.length;
	           for (var i = 0; i < arrayLength; i++) {
	        	   OS=OS+selectedOS[i]+","
	           }
	            arrayLength = selectedFeature.length;
	           for (var i = 0; i < arrayLength; i++) {
	        	   feature=feature+selectedFeature[i]+","
	           }
	           
	           arrayLength = selectedCategory.length;
	           for (var i = 0; i < arrayLength; i++) {
	        	   category=category+selectedCategory[i]+","
	           }
	           
	           if(brands != "")
	        	   brands=brands.substring(0, brands.length-1);
	        	   
	         if(OS!="")
	        	OS=OS.substring(0, OS.length-1)
	        	   
	        	if(feature != "")
	        		feature=feature.substring(0, feature.length-1)
	        		
	        		if(category != "")
	        			category=category.substring(0, category.length-1)
	        			
	        			populatedFilterdSearchResult(category,brands,query,spCount);
			
		});
	 function populatedFilterdSearchResult(category,brand,query,pageCount){

			if(serActive){
				active=false;
			jQuery.ajax({
		        url: "/search/filter?q="+query+"&brand="+brand + "&category="+category +"&pageCount="+pageCount ,
		        success: function (result) {
		            if(result!=null){
		            	$('#productList').html(result);
		            	serActive=true;
		            }
		        },
		        error : function(result) {
					
		        	alert("something went wrong!!! Try again");
		        	serActive=true;
				},
		        async: false
		    });
			}
			

			
			
		}
	
	
	
	
	
});


</script>



<%!

List<SearchProductDao> SL ;

String query;
int searchPageCount=0;
int totalSerRes=0;
int maxPage=0;

int  pageCount=0;

%>

<%
     SL=(List<SearchProductDao>)request.getAttribute("searchResultList");
if(SL == null){
	 SL = new ArrayList<SearchProductDao>();
}


if(request.getAttribute("totalSerRes") != null){
	 totalSerRes=(int)request.getAttribute("totalSerRes");
	 maxPage = totalSerRes%10==0? (totalSerRes/10):(totalSerRes/10+1);
}

if(request.getAttribute("searchPageCount") != null)
	 searchPageCount=(int)request.getAttribute("searchPageCount");
     
       
%>
	
	
	
	<input type="hidden" id="filterMaxPage" value="<%=maxPage%>">
<input type="hidden" id="filterSearchPageCount" value="<%=searchPageCount%>">
	<%for(int i=0;i<SL.size();i++){ %>
	<div class="ranklistitems">
		<div class="topreviewpannel" style="height: 140px">
			<div class="reviewimage"
				style="width: 120px; height: 120px; float: left; margin: 10px; margin-left: 5px; cursor: pointer">
				<img class="lazy"
					<%-- src="<%=SL.get(i).getMainImageUrl()%>" --%>
					src="http://res.cloudinary.com/planb/image/upload/v1477769444/xz4dq37pmi9y8lb5tgdr.jpg"
					id="product-main-image"
					data-productId="<%=SL.get(i).getProducId()%>"
					data-category="<%=SL.get(i).getCategory()%>"
					data-avgrating="<%=SL.get(i).getRating()%>"
					height="120px" width="120px"
					<%-- onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />'" --%> />
			</div>
			<div class="reviewHeader"
				style="width: 350px; float: left; margin-left: 10px; margin-top: 20px;">
				<table>
					<tr>
						<td><span style="cursor: pointer" class="frontviewheadings" id="product-main-image"
							data-productId="<%=SL.get(i).getProducId()%>"
							data-category="<%=SL.get(i).getCategory()%>"
							data-avgrating="<%=SL.get(i).getRating()%>"><span id = "data-ranking" data-ranking="<%=(i+1)%>"></span><%=SL.get(i).getProductName()%></span></td>
					</tr>
					<tr>
						<td><span id = "data-releaseDate" data-releaseDate="<%=SL.get(i).getReleaseDate()%>"><%=SL.get(i).getReleaseDate()%></span></td>
					</tr>
					<tr>
						<td>&#8377;<span id= "data-price" data-price="<%=SL.get(i).getBestBuyPrice()%>"><%=SL.get(i).getBestBuyPrice()%></span></td>
					</tr>

				</table>

			</div>
			<div class="rankfavicon"
				style="float: left; margin-top: 22px; cursor: pointer">
				<span class="rankStarIcon finalRating" id="rankStarIcon<%=i%>"
					data-identifier="<%=i%>"></span>
			</div>
			<div class="finalRatings"
				style="width: 100px; float: left; margin-top: 25px; cursor: pointer">
			<span id= "data-yourRating" data-yourRating= "<%=SL.get(i).getRating()%>"  >	<%=SL.get(i).getRating()%>

			</div>
			
			
			
		</div>



	</div>

	<%} %>
	
	<%if(SL.size() > 0){ %>
	
	<div class="ranklistitems_">
	<div class="topreviewpannel" style="height: 50px">
	<div class="sp" style="margin-left: 20px;margin-top:20px">
	<ul class="filterSUL">
	 <%
    pageCount =totalSerRes%10==0? (totalSerRes/10):(totalSerRes/10+1);
  %>
	<li  class="SLI_"><span style="margin-left:20px">Page <span id=curPage><%=searchPageCount%></span> of <span id=maxPage><%=pageCount%></span></span></li>
  <li class="SLI"><span id="spPrev" class="profileHeader" style="padding: 6px 12px;">Prev</span></li>
  <li class="SLI"><span id="1"  style="padding: 6px 12px">1</span></li>
	<%if(pageCount>1){for(int i=2;i<pageCount+1;i++){%>
       <li class="SLI"><span id="<%=i%>" style="padding: 6px 12px"><%=i%></span></li>
     <%}%>
	 <li class="SLI"><span id="spNext" class="profileHeader" style="padding: 6px 12px">Next</span></li>
    <%}%>
</ul> 
	</div>
	
	</div>
	</div>
	<%}else{%>
	<div class="ranklistitems_">
	<div class="topreviewpannel" style="height: 80px">
	<span class="profileHeader"
		style="margin-top: 20px;margin-bottom: 0px;float:left;margin-left:20px"">
		Opps !!! No result for filter
		
		</span>
	</div>
	</div>
	
	
	
	<%}%>