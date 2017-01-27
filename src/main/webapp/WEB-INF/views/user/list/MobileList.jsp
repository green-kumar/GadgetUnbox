<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
</head>

<%!//List<String> reviewList;

List<ProductMetaData> sortedProductList;
int productCount;
String userEmail;
List<String> favProductList;
Map<String,Double> userProductRatingMap;
List<String> mobileBrandList;
List<String> mobileOSList;
List<String> mobileFeatureList;

%>
<%
	//String temp[] = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" };
     userEmail=(String)request.getSession().getAttribute("userEmail");
     favProductList=(List<String>)request.getAttribute("favProductList");
     userProductRatingMap=(Map<String,Double>)request.getAttribute("userProductRatingMap");

	//reviewList = new ArrayList<String>(Arrays.asList(temp));
	sortedProductList=(ArrayList<ProductMetaData>)request.getAttribute("sortedSmartPhoneList");
       if(sortedProductList != null){
    	   productCount=sortedProductList.size();
       }

    mobileBrandList = (List<String>)request.getAttribute("mobileBrandList");
    mobileOSList = (List<String>)request.getAttribute("mobileOSList");
    mobileFeatureList = (List<String>)request.getAttribute("mobileFeatureList");
       
%>

<div style="margin: 20px; margin-top: 60px">

	<span class="profileHeader"
		style="margin-top: 20px; margin-bottom: 20px">Top Rated
		Smartphone</span>

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
						<option value="ranking" selected="selected">ranking
						</option>
						<option value="price">price</option>
						<option value="releaseDate">release date</option>
						<option value="yourRating">your rating</option>
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
	<div class="rankingHeaderPannel"
		style="float: right; width: 700px; margin-top: 20px; margin-bottom: 20px">
		<table>
			<tr>
				<th width="400px"><span class="filterHeader">Rank & Name</span>
				</td>
				<th width="80px"><span class="filterHeader">Rating</span>
				</td>
				<th width="180px"><span class="filterHeader">Your Rating</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="productList">
	<%for(int i=0;i<productCount;i++){ %>
	<div class="ranklistitems">
		<div class="topreviewpannel" style="height: 100px">
			<div class="reviewimage"
				style="width: 50px; height: 70px; float: left; margin: 10px; margin-left: 5px; cursor: pointer">
				<img class="lazy"
					src="<%=sortedProductList.get(i).getMainImageUrl()%>"
					id="product-main-image"
					data-productId="<%=sortedProductList.get(i).getProductId()%>"
					data-category="<%=sortedProductList.get(i).getCategory()%>"
					data-avgrating="<%=sortedProductList.get(i).getAvgRating()%>"
					height="70px" width="50px"
					onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />'" />
			</div>
			<div class="reviewHeader"
				style="width: 350px; float: left; margin-left: 10px; margin-top: 20px;">
				<table>
					<tr>
						<td><span class="frontviewheadings" id="product-main-image"
							data-productId="<%=sortedProductList.get(i).getProductId()%>"
							data-category="<%=sortedProductList.get(i).getCategory()%>"
							data-avgrating="<%=sortedProductList.get(i).getAvgRating()%>"></span><span id = "data-ranking" data-ranking="<%=(i+1)%>"><%=(i+1)%></span><%=". "+sortedProductList.get(i).getProductName()%></td>
					</tr>
					<tr>
						<td><span id = "data-releaseDate" data-releaseDate="<%=sortedProductList.get(i).getReleaseDate()%>"><%=sortedProductList.get(i).getReleaseDate()%></span></td>
					</tr>
					<tr>
						<td>&#8377;<span id= "data-price" data-price="<%=sortedProductList.get(i).getBestBuyPrice()%>"><%=sortedProductList.get(i).getBestBuyPrice()%></span></td>
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
				<%=sortedProductList.get(i).getAvgRating()%>

			</div>
			<div class="rankfavicon"
				style="float: left; margin-top: 20px; cursor: pointer">
				
			<%if(userEmail!=null && userProductRatingMap!=null &&  userProductRatingMap.containsKey(sortedProductList.get(i).getProductId())){ %>
				<span class="rankStarIcon finalRating" data-productId='<%=sortedProductList.get(i).getProductId()%>'
					data-category="<%=sortedProductList.get(i).getCategory()%>" data-currentRating="<%=userProductRatingMap.get(sortedProductList.get(i))%>" ></span>
				<%}else{ %>
				<span class="rankStarIcon myRating"></span>
<%}%>
			</div>
			<div class="myRatings"
				style="width: 60px; float: left; margin-top: 25px; cursor: pointer">
				<%if(userEmail!=null && userProductRatingMap!=null &&  userProductRatingMap.containsKey(sortedProductList.get(i).getProductId())){ %>
				<span id= "data-yourRating" data-yourRating= "<%=userProductRatingMap.get(sortedProductList.get(i).getProductId())%>"  >
				<%=userProductRatingMap.get(sortedProductList.get(i).getProductId())%>
				</span>
				
				
				
				<%}else{ %>
				<span id= "data-yourRating" data-yourRating='0' >&nbsp;</span>
				<% }%>
				
				</div>
			<div class="myFavRank"
				style="width: 40px; float: left; margin-left: 10px; margin-top: 25px; cursor: pointer">
				<%if(userEmail!=null && favProductList !=null && favProductList.contains(sortedProductList.get(i).getProductId())){ %>
				<span class="userfavicon profileheartselected"
					title="remove from my favourite list"
					data-productId='<%=sortedProductList.get(i).getProductId()%>'
					data-productName='<%=sortedProductList.get(i).getProductName()%>'
					data-category='<%=sortedProductList.get(i).getCategory()%>'></span>
				<%}else{ %>
				<span class="userfavicon profileheartunselected"
					title="Add in my favourite list"
					data-productId='<%=sortedProductList.get(i).getProductId()%>'
					data-productName='<%=sortedProductList.get(i).getProductName()%>'
					data-category='<%=sortedProductList.get(i).getCategory()%>'></span>
             <%} %>
			</div>
		</div>



	</div>

	<%} %>
</div>
</div>


<div class="rankFilter"
	style="float: left; width: 220; height: 900px; border-left: 2px solid #808080;">
	<div style="margin-top: 20px; margin-bottom: 20px; margin-left: 10px">
		<span class="profileHeader" style="padding: 8px 8px;"> Top
			Rated Smartphone by:</span>
	</div>

	<table>
		<tr>
			<td style="line-height: 30px" width="60px"><span
				class="filterHeader">Brand</span></td>
			<td width="60px"></td>
		</tr>
	</table>
	<div class="filterContainer" id="brandFilter">
	<%for(String brand: mobileBrandList){ %>
		<input type="checkbox" class="filterCheckbox" name='<%=brand%>'/> <%=brand %> <br />
		<%} %>
	</div>



	<table>
		<tr>
			<td style="line-height: 30px" width="60px"><span
				class="filterHeader">OS</span></td>
			<td width="60px"></td>
		</tr>
	</table>
	<div class="filterContainer" id="OSFilter">
	<%for(String os:mobileOSList){ %>
		<input type="checkbox" class="filterCheckbox" name = '<%=os %>'/> <%=os %><br />
		
		<%} %>

	</div>
	<table>

		<tr>
			<td style="line-height: 30px" width="60px"><span
				class="filterHeader">Feature</span></td>
			<td width="60px"></td>
		</tr>
	</table>
	<div class="filterContainer" id="featureFilter">
	<%for(String feature:mobileFeatureList){ %>
		<input type="checkbox" class="filterCheckbox" name= '<%=feature%>'/> <%=feature %> <br /> 
		<%} %>
		
	</div>



</div>

<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
<!-- -------------Confirm dialog box for change in rating----------------------------- -->
<div id="passwordBoxoverlay" class="passwordBox_dialog_overlay"></div>
<div id="passwordBoxdialog" class="passwordBox_dialog">
	<div id="passworchangeHeader"
		style="float: left; width: 380px; height: 30px; background-color: #e60000; color: #000000; font-family: 'Open Sans', sans-serif; font-size: 18px; color: White;">
		<div style="float: left;">Attention Required!!!</div>
		<div style="float: right;">
			<span id="passwordBoxbtnClose" style="cursor: pointer;">close</a>
		</div>
	</div>
	<div id="rankingMessage"
		style="float: left; margin: 20px; width: 300px; height: 70px; font-family: 'Open Sans', sans-serif; font-size: 18px; color: #000000;">

	</div>
	<div style="float: left; margin: 20px">

		<a href="javascript:void(0)" class="myButton" id="submitRate"
			style="padding: 7px 17px; margin-right: 30px; float: left">Submit</a> 


	</div>


</div>

<!-- -------------Confirm dialog box for change in rating----------------------------- -->
