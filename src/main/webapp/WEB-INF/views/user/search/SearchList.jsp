<%@page import="com.planb.search.dao.SearchProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">




</head>

<%!

List<SearchProductDao> SL ;
String query;
String searchCategory;
int searchPageCount=0;
List<String> category;
List<String> brand;
int totalSerRes=0;
int  pageCount=0;


%>
<%
     SL=(List<SearchProductDao>)request.getAttribute("searchResultList");
     query = (String)request.getAttribute("query");
     searchCategory = (String)request.getAttribute("searchCategory");
     category = (List<String>)request.getAttribute("caterory");
     brand = (List<String>)request.getAttribute("brand");
     if(SL == null){
    	 SL = new ArrayList<SearchProductDao>();
     }
     if(request.getAttribute("totalSerRes") != null)
    	 totalSerRes=(int)request.getAttribute("totalSerRes");
     
     if(request.getAttribute("searchPageCount") != null)
    	 searchPageCount=(int)request.getAttribute("searchPageCount");
        
            
%>

<div style="margin: 20px; margin-top: 60px">
<input type="hidden" id="query" value="<%=query%>">
<input type="hidden" id="searchCategory" value="<%=searchCategory%>">
<input type="hidden" id="searchPageCount" value="<%=searchPageCount%>">
	<span class="profileHeader"
		style="margin-top: 20px; margin-bottom: 20px">
		<%if(SL.size() == 0){ %>
		Opps !!! No result for "<%=query%>"
		<%}else{ %>
		Search result for "<%=query%>"
		<%} %>
		</span>

</div>
<hr
	style="height: 2px; border: none; color: #808080; background-color: #808080; margin-bottom: 20px" />

<div class="rankingPannel" style="float: left; width: 700px;">
	<div class="rankingSortpannel" style="float: right; width: 340px;">
		<table>
			<tr>
				<td style="line-height: 20px" colspan=4><span>Sort</span></td>
			</tr>
			<tr>
				<td width="55px"><span>Feature</span></td>
				<td width="55px"><select name="sort" id="feature">
						<option value="ranking" selected="selected">relevence
						</option>
						<option value="price">price</option>
						<option value="yourRating">rating</option>
						<option value="releaseDate">release date</option>
				</select></td>
				<td width="15px">&nbsp;</td>
				<td width="55px"><span>Order</span></td>
				<td width="55px"><select id="order">
						<option value="asc" selected="selected">ascending</option>
						<option value="des">descending</option>
				</select></td>
			</tr>
		</table>
	</div>


	<div id="productList">
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
					onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';" />
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
	<ul class="SUL">
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
	<%} %>
</div>
</div>

<%if(SL.size() > 0){ %>
<div class="rankFilter"
	style="float: left; width: 220; height: 900px; border-left: 2px solid #808080;">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 24px">
		<span class="profileHeader" style="padding: 8px 8px;"> Filter</span>
	</div>

	<table>
		<tr>
			<td style="line-height: 30px" width="60px"><span
				class="filterHeader">Category</span></td>
			<td width="60px"></td>
		</tr>
	</table>
	<div class="filterContainer" id="categoryFilter">
	<%for(String cat: category){ %>
		<input type="checkbox" class="filterCheckbox" name='<%=cat%>'/> <%=cat %> <br />
		<%} %>
	</div>



	<table>
		<tr>
			<td style="line-height: 30px" width="60px"><span
				class="filterHeader">Brand</span></td>
			<td width="60px"></td>
		</tr>
	</table>
	<div class="filterContainer" id="brandFilter">
	<%for(String brand:brand){ %>
		<input type="checkbox" class="filterCheckbox" name = '<%=brand %>'/> <%=brand %><br />
		
		<%} %>

	</div>
	



</div>
<%} %>
<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
<script type="text/javascript">
$(document).ready(function () {
	
	var totalPage= parseInt($('#maxPage').text());
	var spCount=parseInt($('#searchPageCount').val());
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
});


</script>
