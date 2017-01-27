<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
</head>
<body>
	<%!String userName;
	String userEmail;
	String productId;
	String productCategory;
	String reviewId;
	List<ReviewDetails> reviewList;
	String isReviewAvailable; 
	int reviewCount;
	Map<Integer,String> ratingTitleMap;
	
	%>
	<%
		userEmail = (String) request.getSession().getAttribute("userEmail");
	 isReviewAvailable = (String)request.getAttribute("isReviewAvailable");
	 ratingTitleMap=(HashMap<Integer,String>)request.getAttribute("ratingTitleMap");
	reviewList = (List<ReviewDetails>)request.getAttribute("reviewList");
	 if(reviewList != null)
	reviewCount = reviewList.size();  
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
			 if(isReviewAvailable!=null && isReviewAvailable.equalsIgnoreCase("false") ){ %>
				 <div style="margin: 20px;">
					<span
						style="color: #e60000; font-family: Arial; font-size: 15px; font-weight: bold; padding: 12px 24px; text-decoration: none; text-shadow: 0px 1px 0px #b23e35;">Sorry,You
						haven't provide any rating yet.!!!</span>
				</div>
				 
		<%}else{
	for (int i = 0; i < reviewCount; i++) {
		
%>
	<div class="favlistitems">
	<div class="emailpannel" id="reviewerrorpannel<%=i%>" style="width:300px;margin-left:30px;color: #e60000">
		<span class="errorspan" style="color:#e60000;"></span>
		<ul id="reviewerrorul<%=i%>"></ul> 
	</div>

		<div class="topreviewpannel" style="height: 70px">
			<div class="reviewimage" style="width: 50px; height: 70px; float: left; margin: 10px;margin-left:25px;cursor:pointer">
				 <img  class= "lazy" data-original="<%=reviewList.get(i).getProductThumbNailImg()%>" id="product-main-image" data-productId="<%=reviewList.get(i).getForProductId()%>"
				data-category="<%=reviewList.get(i).getCategory()%>" data-avgrating="<%=reviewList.get(i).getAvgRatingForProduct()%>"
					height="70px" width="50px"  onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';" 
					/>
			</div>
			<div class="reviewHeader" style="width: 375px;float: left; margin-left: 10px;margin-top:20px;">
				<table>
					<tr>
						<td><span class="frontviewheadings" id="favHead<%=i%>"><%=reviewList.get(i).getProductName()%></span></td>
					</tr>
					<tr>
						<td><%=reviewList.get(i).getCreatedOn()%></td>
					</tr>
					
				</table>

			</div>
			<div class="userRatings" style="float: right; margin-right: 10px;margin-top:25px; cursor:pointer" data-productName="<%=reviewList.get(i).getProductName()%>"
			data-productId="<%=reviewList.get(i).getForProductId()%>" data-reviewId="<%=reviewList.get(i).getReviewId()%>">
					<span class="rating" data-identifier='<%=i%>'>
					<%for(int j=0;j<5;j++){ 
						int ratingStar=(int)reviewList.get(i).getRating();
					      if(j<(5-ratingStar)){
					           %>
					<span class="star profile<%=i%>" title="<%=ratingTitleMap.get(4-j) %>"></span>
					<%}else{ %>
					<span class="star profile<%=i%> filled" title="<%=ratingTitleMap.get(4-j) %>"></span>
					<%}} %>
        </span>
			 
			 </div>
		</div>

	


	</div>

	<%
	}
} 
	}%>
	<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
<!-- -------------Confirm dialog box for change in rating----------------------------- -->
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
	<div id="ratingMessage" style="float:left;margin:20px;width:300px;height:70px; font-family: 'Open Sans', sans-serif; font-size: 18px;color: #000000;" >
	
	                                  <span id="ratingChangeTarget"><img
										src="<c:url value="/resources/usertheme/images/load.gif" />"
										alt="..." style="display: none" /></img></span>
    
	</div>
	<div style="float:left;margin:20px">
	
	<a href="javascript:void(0)" class="myButton" id="yesUpdateRate" data-productId=""
									style="padding: 7px 17px; margin-right:30px ;float:left">Yes</a>
									
	<a href="javascript:void(0)" class="myButton" id="noUpdateRate"
									style="padding: 7px 17px; float:left">No</a>
	
	
	</div>
	
	
</div>

<!-- -------------Confirm dialog box for change in rating----------------------------- -->
</body>
</html>