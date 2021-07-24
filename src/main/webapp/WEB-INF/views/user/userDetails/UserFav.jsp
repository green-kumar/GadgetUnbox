<%@page import="com.planb.dao.user.UserFavProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script> -->
   
</head>
<body>
	<%!String userName;
	String userEmail;
	String productId="dummy";
	String productCategory="dummy";
	String reviewId="reviewId";
	List<UserFavProduct> userFavProductList;
	String isFavAvailable;
	int favProductCount;
	
	%>
	<%
	userEmail = (String) request.getSession().getAttribute("userEmail");
	userFavProductList=(List)request.getAttribute("userFavProductList");
	isFavAvailable = (String)request.getAttribute("isFavAvailable");
	if(userFavProductList!=null){
		favProductCount=userFavProductList.size();
	}
	%>

	<div style="margin: 20px; margin-top: 60px">

		<span class="profileHeader"
			style="margin-top: 20px; margin-bottom: 20px">My Favourites</span>

	</div>
	<hr
		style="height: 2px; border: none; color: #808080; background-color: #808080; margin-bottom: 20px" />


	<%
		if (userEmail == null) {
	%>

	<div style="margin: 20px;">
		<span
			style="color: #e60000; font-family: Arial; font-size: 15px; font-weight: bold; padding: 12px 24px; text-decoration: none; text-shadow: 0px 1px 0px #b23e35;">Sorry,You
			are not logged in.Please login first!!!</span>
	</div>
	<%
		} else {
			 if(isFavAvailable!=null && isFavAvailable.equalsIgnoreCase("false") ){ %>
			 <div style="margin: 20px;">
				<span
					style="color: #e60000; font-family: Arial; font-size: 15px; font-weight: bold; padding: 12px 24px; text-decoration: none; text-shadow: 0px 1px 0px #b23e35;">Sorry,You
					haven't faved any product yet.!!!</span>
			</div>
			 
	<%}else{
	for (int i = 0; i < favProductCount; i++) {
		
%>
	<div class="favlistitems">
	<div class="emailpannel" id="reviewerrorpannel<%=i%>" style="width:300px;margin-left:30px;color: #e60000">
		<span class="errorspan" style="color:#e60000;"></span>
		<ul id="reviewerrorul<%=i%>"></ul> 
		</div>

		<div class="topreviewpannel" style="height: 70px">
			<div class="reviewimage" style="width: 50px; height: 70px; float: left; margin: 10px;margin-left:25px;cursor:pointer">
				 <img  class= "lazy" data-original="<%=userFavProductList.get(i).getThumbnailImageUrl()%>" id="product-main-image" data-productId="<%=userFavProductList.get(i).getProductId()%>"
				data-category="<%=userFavProductList.get(i).getCategory()%>" data-avgrating="<%=userFavProductList.get(i).getAvgRatingforProduct()%>"
					height="70px" width="50px"  onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';" 
					/>
			</div>
			<div class="reviewHeader" style="float: left; margin-left: 10px;margin-top:20px;">
				<table>
					<tr>
						<td><span class="frontviewheadings" id="favHead<%=i%>"><%=userFavProductList.get(i).getProductName()%></span></td>
					</tr>
					<tr>
						<td><%=userFavProductList.get(i).getCreatedOn()%></i></td>
					</tr>
					
				</table>

			</div>
			<div class="userfavicon" style="float: right; margin-right:40px;margin-top:25px;cursor: pointer">
			<span class="profilefavicon profileheartselected" id="favIcon<%=i%>" data-identifier="<%=i%>"  data-category="<%= userFavProductList.get(i).getCategory()%>"  data-productId="<%=userFavProductList.get(i).getProductId()%>" data-productName="<%=userFavProductList.get(i).getProductName()%>"></span>
			 </div>
		</div>
	</div>

	<%
	}}
} %>
<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>

<!-- -------------Confirm dialog box for change in fav list----------------------------- -->
					<div id="passwordBoxoverlay" class="passwordBox_dialog_overlay"></div>
	<div id="passwordBoxdialog" class="passwordBox_dialog">
	<div id="passworchangeHeader" style="float:left; width: 380px;height:30px;background-color:#e60000;color:#000000;font-family: 'Open Sans', sans-serif; font-size: 18px;color: White;">
	<div
								style="float:left;">Attention Required!!!</div>
							<div
								style="float:right;">
								<span id="passwordBoxbtnClose" style="cursor: pointer;">close</a>
							</div>
	</div>
	<div id="favMessage" style="float:left;margin:20px;width:300px;height:70px; font-family: 'Open Sans', sans-serif; font-size: 18px;color: #000000;" >
	
	                                  <span id="favChangeTarget"><img
										src="<c:url value="/resources/usertheme/images/load.gif" />"
										alt="..." style="display: none" /></img></span>
    
	</div>
	<div style="float:left;margin:20px">
	
	<a href="javascript:void(0)" class="myButton" id="yesUpdateFav"
									style="padding: 7px 17px; margin-right:30px ;float:left">Yes</a>
									
	<a href="javascript:void(0)" class="myButton" id="NoUpdateFav"
									style="padding: 7px 17px; float:left">No</a>
	
	
	</div>
	
	
</div>

<!-- -------------Confirm dialog box for change in fav list----------------------------- -->



</body>
</html>