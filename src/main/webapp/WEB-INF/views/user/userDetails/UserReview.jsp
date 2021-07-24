<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	
	%>
	<%
		userEmail = (String) request.getSession().getAttribute("userEmail");
	 isReviewAvailable = (String)request.getAttribute("isReviewAvailable");
	reviewList = (List<ReviewDetails>)request.getAttribute("reviewList");
	 if(reviewList != null)
	reviewCount = reviewList.size();  
	%>

	<div style="margin: 20px; margin-top: 60px">

		<span class="profileHeader"
			style="margin-top: 20px; margin-bottom: 20px">My Reviews</span>

	</div>
	<hr
		style="height: 2px; border: none; color: #808080; background-color: #808080; margin-bottom: 10px" />


	<%
		if (userEmail == null) {
	%>

	<div style="margin: 20px;">
		<span
			style="color: #e60000; font-family: Arial; font-size: 15px; font-weight: bold; padding: 12px 24px; text-decoration: none; text-shadow: 0px 1px 0px #b23e35;">Sorry,You
			are not logged in.Please login first!!!</span>
	</div>
	
	<%	} else {
			 if(isReviewAvailable!=null && isReviewAvailable.equalsIgnoreCase("false") ){ %>
				 <div style="margin: 20px;">
					<span
						style="color: #e60000; font-family: Arial; font-size: 15px; font-weight: bold; padding: 12px 24px; text-decoration: none; text-shadow: 0px 1px 0px #b23e35;">Sorry,You
						haven't submit any review yet.!!!</span>
				</div>
				 
		<%	 }else{
			
	for (int i =0; i< reviewCount ;i++) {
		
%>
	<div class="listitems" style="/* margin-top: 20px; */ border: 1px solid #b3b3b3;">
	<div class="emailpannel" id="reviewerrorpannel<%=i%>" style="width:300px;margin-left:30px;color: #e60000">
		<span class="errorspan" style="color:#e60000;"></span>
		<ul id="reviewerrorul<%=i%>" ></ul> 
		</div>

		<div class="topreviewpannel" style="height: 70px">
			<div class="reviewimage"
				style="width: 50px; height: 70px; float: left; margin: 10px;cursor:pointer">
				 <img  class= "lazy" data-original="<%=reviewList.get(i).getProductThumbNailImg()%>" id="product-main-image" data-productId="<%=reviewList.get(i).getForProductId()%>"
				data-category="<%=reviewList.get(i).getCategory()%>" data-avgrating="<%=reviewList.get(i).getAvgRatingForProduct()%>"
					height="70px" width="50px"  onError="this.src='<c:url value="/resources/usertheme/images/Alt_image.jpeg" />';" 
					/> 
			</div>
			<div class="reviewHeader" style="float: left; margin-left: 10px">
				<table>
					<tr>
						<td><span class="frontviewheadings" id="reviewHead<%=i%>"><%=reviewList.get(i).getReviewHeadline()%></span></td>
					</tr>
					<tr>
						<td><%=reviewList.get(i).getCreatedOn()%></td>
					</tr>
					<tr>
						<td><%=reviewList.get(i).getLike()%>&nbsp;&nbsp;likes</td>
					</tr>
				</table>

			</div>
		</div>

		<div class=detailspannelreview style="float: left; width: 880;">
			<table>
				<tr>
					<td width="70px"><b>Pros:</b></td>
					<td><div class="reviewsContains" id="reviewPros<%=i%>"
							style="width: 760px; font-family: 'Comic Sans MS', cursive, sans-serif;">
							<%=reviewList.get(i).getReviewPros()%>
						</div></td>
				</tr>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>

				<tr>
					<td><b>Cons:</b></td>
					<td><div class="reviewsContains" id="reviewCons<%=i%>"
							style="width: 760px; font-family: 'Comic Sans MS', cursive, sans-serif;">
							<%=reviewList.get(i).getReviewCons()%>
						</div></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>

				<tr>
					<td><b>Rating:</b></td>
					<td><div id="reviewRating<%=i%>" style="width: 40px">
							<%=reviewList.get(i).getRating()%>
						</div></td>
				</tr>

				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
			</table>

		</div>
		<div style="width: 800px; float: left;">
			<table>
				<tr>
				    <td colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton editreviewbutton" data-identifier="<%=i%>"
						id="editReview<%=i%>" style="padding: 8px 15px;">Edit&nbsp;&nbsp;Review</a></td>

					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton canceleditreviewbutton"
						id="cancelEditReview<%=i%>" data-identifier=<%=i%>
						style="padding: 8px 20px; margin-left: 50px; display: none">Cancel</a></td>

					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton savereviewbutton" id="saveReview<%=i%>"
						data-identifier="<%=i%>" data-productId= "<%=reviewList.get(i).getForProductId()%>"
						data-category="<%=reviewList.get(i).getCategory()%>" data-reviewId="<%=reviewList.get(i).getReviewId()%>"
						style="padding: 8px 20px; display: none;">Save</a></td>


					<td><span id="updateReviewWait<%=i%>"> <img
							src="<c:url value="/resources/usertheme/images/load.gif" />"
							alt="..." style="display: none" /></img></span></td>
				</tr>



			</table>

		</div>

	</div>

	<%
	}
			 }
} %>
	<div class="detailsdialogoverlay"></div>
<div id="centerpoint">
	<div class="detailsdialogbox"></div>
</div>
</body>
</html>