<%@page
	import="com.couchbase.client.deps.com.fasterxml.jackson.core.JsonFactory.Feature"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="org.springframework.util.StringUtils"%>
<%@page import="com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures"%>
<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.planb.dao.mobile.Mobile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<c:url value="/resources/usertheme/css/image-view.css" />" rel="stylesheet">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<head>
<%!List<String> topHeadlinesFeatures;
	/* String topHeadlinesFeatures[]={"Size(Storage capacity) : 32GB","Screen Size: 9.7 inch","Batter life: 10 hours",
			"Camera:10 megapixal","RAM:4GB","OS type:android jely bean","CPU Type: Octa-Core"}; */
	String marketStaus = "release in mar,2015";
	String releasesOS;
	String colors;
	String description = "";
	String productId;
	String category;
	String productName;
	String avgRating;
	int totalReviewCount;
	int totalPages; 
	List<String> restImageUrls;
	
	%>
<%
	Mobile mobileDetails = (Mobile) request.getAttribute("productDetail");
	List<ReviewDetails> reviewList = (ArrayList) request.getAttribute("allReview");
	avgRating = (String) request.getAttribute("avgRating");
	topHeadlinesFeatures = mobileDetails.getTopHeadlineFeatures();
	marketStaus = mobileDetails.getOverView().getMarketStaus();
	releasesOS = mobileDetails.getReleasedOS();
	colors = String.join(",", mobileDetails.getColors());
	productName = mobileDetails.getName();
	List<AdditionalFeatures> additionalFeatureList = mobileDetails.getAdditionalFeatures();
	productId = mobileDetails.getProductId();
	restImageUrls = mobileDetails.getRestImageUrl();
	category = "mobiles";
     
	if(mobileDetails.getUserReviewIds() !=null)
	totalReviewCount=mobileDetails.getUserReviewIds().size();
	
	if(totalReviewCount != 0 && totalReviewCount > 9){
		if((totalReviewCount%10) != 0 ){
			totalPages=(totalReviewCount/10)+1;
		}else{
			totalPages=(totalReviewCount/10);
		}
	}else if(totalReviewCount != 0 && totalReviewCount < 10){
		totalPages = 1;
	}
	
%>
<!-- <script src = "http://code.jquery.com/jquery-1.10.2.js"></script> -->
<script type="text/javascript">
	var pageCount=1;
	var clickedIndex=0;
	/* var modal = document.getElementById('myModal');
	var img = document.getElementsByClassName('productRestImage');
	var modalImg = document.getElementById("img01");
	var captionText = document.getElementById("caption"); */
	$(document).ready(function() {
		$(".tabs-menu li:eq(0)").addClass('current');
    	$('#tab-1').show();
		$(".tabs-menu a").click(function(event) {
			event.preventDefault();
			$(this).parent().addClass("current");
			$(this).parent().siblings().removeClass("current");
			var tab = $(this).attr("href");
			$(".tab-content").not(tab).css("display", "none");
			$(tab).fadeIn();
		});

		var loggedin = 'false';
		loggedin = $('#loginflag').val();
		$("#writereviewbutton").click(function(e) {
			 $("#writereviewbuttonpannel").css("display", "none");
			if (loggedin == 'false') {
				showEmailDialog();
				e.preventDefault();
				$(".errorspan").html("Please login for adding a review.");
				return false;
			}
			
			$("#emptyReview").css("display", "none");
			$(".reviewform").slideToggle('slow');
		});

		$("a.review_pagination_latest").click(function(event) {
			var productId = $("#productId").val();
			$(".topRatedReviewPlaceholder").css("display", "none");
			$(".latestReviewPlaceholder").css("display", "block");
			$(".latest_review_pages").css("display", "block");
			
			$(".review_pagination_latest").css("background-color", "#e60000");
			$(".review_pagination_top_rated").css("background-color", "#ff6666");
			
			loadLatestReview(1, productId);
		});

		$("a.review_pagination_top_rated").click(function(event) {
			$(".latest_review_pages").css("display", "none");
			$(".latestReviewPlaceholder").css("display", "none");
			$(".topRatedReviewPlaceholder").css("display", "block");
			
			$(".review_pagination_latest").css("background-color", "#ff6666");
			$(".review_pagination_top_rated").css("background-color", "#e60000");

		});

		$('.fetchReview').click(function() {
			
		    var totalPages = $("#totalPages").val();
			var count = $(this).text();
			var numCount=parseInt(count);
			if(numCount == 1){
				$('a#page_prev').css("display","none");
				$('a#page_next').css("display","block");
			}else if(numCount == totalPages){
				$('a#page_prev').css("display","block");
				$('a#page_next').css("display","none");
			}else{
				$('a#page_prev').css("display","block");
				$('a#page_next').css("display","block");
			}
			
			$('a#page_'+numCount).addClass("active");
			$('a#page_'+pageCount).removeClass("active");
			
			fetchReviewFun(numCount);
		});
		
		$('.fetchPrevReview').click(function() {
			fetchPrevReviewFun();
		});
		
		$('.fetchnextReview').click(function() {
			fetchNextReviewFun();
		});
		
		
		$('.rating span.profilereviewRate').bind({
			click:function(e){
		    	var total=$(this).parent().children().length;
		    	clickedIndex=$(this).index();
		    	$('.rating span.profilereviewRate').removeClass('filled');
		    	for(var i=clickedIndex;i<total;i++){
		        	$('.rating span.profilereviewRate').eq(i).addClass('filled');
		    	}
			}
		    });
		
		
		
		
		/* $('.productRestImage').click(function() {
		    modal.style.display = "block";
		    modalImg.src = this.src;
		    captionText.innerHTML = this.alt;
		    var h = $(window).height();
		    var w = $(window).width()
		    $('.modal').css("height" , h);
		    $('.modal').css("width" , w);
		    
		   /*  $('.modal-content').css("height" , h);
		    $('.modal-content').css("width" , w); */
		   
		    
		/* });

		
		
		$('.close').click(function(){
			  modal.style.display = "none";
			
		}); */
		
		
		

	});

	function fetchReviewFun(count) {
		pageCount = count;
		var productId = $("#productId").val();
		loadLatestReview(pageCount, productId)
	}
	function fetchPrevReviewFun() {
		 var totalPages = $("#totalPages").val();
		var productId = $("#productId").val();
		
		if(totalPages == pageCount){
			$('a#page_next').css("display","block");
		}
		$('a#page_'+pageCount).removeClass("active");
		pageCount = (pageCount-1);
		
		
		if(pageCount == 1){
			$('a#page_prev').css("display","none");
		}
		$('a#page_'+pageCount).addClass("active");
		
		
		loadLatestReview(pageCount, productId)
	}
	function fetchNextReviewFun() {
		 var totalPages = $("#totalPages").val();
		var productId = $("#productId").val();
		
		if(pageCount == 1){
			$('a#page_prev').css("display","block");
		}
		$('a#page_'+pageCount).removeClass("active");
		pageCount = (pageCount+1);
		if(totalPages == pageCount){
			$('a#page_next').css("display","none");
		}
		$('a#page_'+pageCount).addClass("active");
		
		loadLatestReview(pageCount, productId)
	}
	function loadLatestReview(startFrom, productId) {
		var j = 0;
		 var totalPages = $("#totalPages").val();
		 var totalReviewCount = $("#totalReviewCount").val();
		jQuery.ajax({
			url : "/latest/" + productId + "/review/" + startFrom,
			success : function(result) {
				///^/ -->regex for test string is html or not
				if (result != "") {
					
					var start = ((startFrom - 1)*10)+1;
					
					var end = start + 9;
					
					if(startFrom == totalPages){
						end = totalReviewCount;
					}
					
					
					$(".latest_review_page_info_begin").text(start);
					$(".latest_review_page_info_end").text(end);

					$("#latestReviewHtmlPlaceholdr").html(result);
					
				}
			},
			error : function(result) {
				alert("something went wrong!!! Try again");
			},
			async : false
		});
	}

	var SlideWidth = 150;
	var SlideSpeed = 500;

	$(document).ready(function() {
		// set the prev and next buttons display
		SetNavigationDisplay();
	});

	function CurrentMargin() {
		// get current margin of slider
		var currentMargin = $("#slider-wrapper").css("margin-left");

		// first page load, margin will be auto, we need to change this to 0
		if (currentMargin == "auto") {
			currentMargin = 0;
		}

		// return the current margin to the function as an integer
		return parseInt(currentMargin);
	}

	function SetNavigationDisplay() {
		// get current margin
		var currentMargin = CurrentMargin();

		// if current margin is at 0, then we are at the beginning, hide previous
		if (currentMargin == 0) {
			$("#PreviousButton").hide();
		} else {
			$("#PreviousButton").show();
		}

		// get wrapper width
		var wrapperWidth = $("#slider-wrapper").width();

		// turn current margin into postive number and calculate if we are at last slide, if so, hide next button
		if ((currentMargin * -1) == (wrapperWidth - SlideWidth)) {
			$("#NextButton").hide();
		} else {
			$("#NextButton").show();
		}
	}

	function NextSlide() {
		// get the current margin and subtract the slide width
		var newMargin = CurrentMargin() - SlideWidth;

		// slide the wrapper to the left to show the next panel at the set speed. Then set the nav display on completion of animation.
		$("#slider-wrapper").animate({
			marginLeft : newMargin
		}, SlideSpeed, function() {
			SetNavigationDisplay()
		});
	}

	function PreviousSlide() {
		// get the current margin and subtract the slide width
		var newMargin = CurrentMargin() + SlideWidth;

		// slide the wrapper to the right to show the previous panel at the set speed. Then set the nav display on completion of animation.
		$("#slider-wrapper").animate({
			marginLeft : newMargin
		}, SlideSpeed, function() {
			SetNavigationDisplay()
		});
	}

	function submitReview() {

		var html = "";
		var reviewHeadline = $("#reviewHeadline").val();
		var reviewPros = $("#reviewPros").val();
		var reviewCons = $("#reviewCons").val();
		var productId = $("#productId").val();
		var category = $("#category").val();
		if (reviewHeadline == "") {
			html = "<li>Review headline can't be blank.</li>"
		}
	
		if (clickedIndex == 0) {
			html += "<li>Rating field can'c be blank.</li>"
		}

		if (!(html == "")) {
			$("#reviewMassageul").html(html);
			return false;
		}

		$(".reviewform").slideToggle('fast');
		$("#writereviewbuttonpannel").css("display", "none");
		$("#reviewMassageul")
				.html(
						"<li><Span style='font-size:25px;'>Thanks for your review!!!!</span></li>");
		var reviewData = {
			reviewHeadline : reviewHeadline,
			reviewPros : reviewPros,
			reviewCons : reviewCons,
			rating : clickedIndex,
			forProductId : productId,
			category : category
		};

		/* alert(JSON.stringify(reviewData)); */
		jQuery.ajax({
			url : "/submitReview",
			type : 'post',
			dataType : 'json',
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
			},
			error : function() {
			},

			data : JSON.stringify(reviewData)
		});
	}
</script>
<style type="text/css">
#container {
	width: 150px;
	overflow: hidden;
}

#slider-wrapper {
	width: 450px;
	/* background: #987978; */
}

.slide {
	width: 150px;
	height: 160px;
	overflow: hidden;
	float: left;
	margin: 10 auto;
}

.imgslidebutton {
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 13px;
	color: #000000;
}

/* a:HOVER {
	 color: #fcfcfc;
     background-color: #e60000; 
	color: #e60000;
} */

.detailspanneltopfeatures ul {
	margin: 0;
	padding: 15;
	list-style-type: none;
}

.detailspanneltopfeatures ul li {<!--
	display: inline; -->
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 18px;
	margin-top: 5px;
}

.detailspanneltopfeaturesnext ul {
	margin: 0;
	padding: 15;
	list-style-type: none;
	text-align: center;
}

.detailspanneltopfeaturesnext ul li {<!--
	display: inline; -->
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 30px;
	margin-top: 5px;
}

.form-style-1 {
	margin: 10px auto;
	max-width: 400px;
	padding: 20px 12px 10px 20px;
	font: 18px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

.form-style-1 li {
	padding: 0;
	display: block;
	list-style: none;
	margin: 10px 0 0 0;
}

.form-style-1 label {
	margin: 0 0 3px 0;
	padding: 0px;
	display: block;
	font-weight: bold;
}

.form-style-1 input[type=text], .form-style-1 input[type=date],
	.form-style-1 input[type=datetime], .form-style-1 input[type=number],
	.form-style-1 input[type=search], .form-style-1 input[type=time],
	.form-style-1 input[type=url], .form-style-1 input[type=email],
	textarea, select {
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	border: 2px solid #BEBEBE;
	padding: 7px;
	margin: 0px;
	-webkit-transition: all 0.30s ease-in-out;
	-moz-transition: all 0.30s ease-in-out;
	-ms-transition: all 0.30s ease-in-out;
	-o-transition: all 0.30s ease-in-out;
	outline: none;
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-weight: bold;
	font-size: 15px;
}

.form-style-1 input[type=text]:focus, .form-style-1 input[type=date]:focus,
	.form-style-1 input[type=datetime]:focus, .form-style-1 input[type=number]:focus,
	.form-style-1 input[type=search]:focus, .form-style-1 input[type=time]:focus,
	.form-style-1 input[type=url]:focus, .form-style-1 input[type=email]:focus,
	.form-style-1 textarea:focus, .form-style-1 select:focus {
	-moz-box-shadow: 0 0 8px #e60000;
	-webkit-box-shadow: 0 0 8px #e60000;
	box-shadow: 0 0 8px #e60000;
	border: 1px solid #e60000;
}

.form-style-1 .field-divided {
	width: 49%;
}

.form-style-1 .field-long {
	width: 100%;
}

.form-style-1 .field-select {
	width: 100%;
}

.form-style-1 .field-textarea {
	height: 150px;
}

.form-style-1 input[type=submit], .form-style-1 input[type=button] {
	background: #ff6666;
	padding: 8px 15px 8px 15px;
	border: none;
	color: #fff;
}

.form-style-1 input[type=submit]:hover, .form-style-1 input[type=button]:hover
	{
	background: #e60000;
	box-shadow: none;
	-moz-box-shadow: none;
	-webkit-box-shadow: none;
}

.form-style-1 .required {
	color: red;
}

/***********tech specification column************/
#wrap {
	width: 700px;
	/*margin:0 auto;*/
	margin-left: 15px;
}

#left_col {
	float: left;
	width: 250px;
	margin-top: 20px;
	word-wrap: break-word;
}

#right_col {
	float: left;
	width: 400px;
	margin-top: 20px;
	word-wrap: break-word;
	/* remove overflow of text if length of text more than div width|break down to below line*/
}

.review_pagination_top_rated {
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 13px;
	text-decoration: none;
	padding: .2em 1em;
	color: #fcfcfc;
	background-color: #e60000;
	margin-left: 20px;
}

.review_pagination_latest {
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 13px;
	text-decoration: none;
	padding: .2em 1em;
	color: #fcfcfc;
	background-color: #ff6666;
	margin-left: 40px;
}

ul.pagination {
	display: inline-block;
	padding: 0;
	margin: 0;
	float:left;
}

ul.pagination li {
	display: inline;
}

ul.pagination li a {
	color: black;
	float: left;
	padding: 8px 16px;
	font-family: Helvetica, Arial, sans-serif;
	font-weight: bold;
	font-size: 13px;
	text-decoration: none;
	margin-left: 20px;
}

ul.pagination li a.active {
	background-color: #e60000;
	color: white;
}

ul.pagination li a:hover:not (.active ) {
	background-color: #e60000;
	color: white;
}

.filterHeader{
	background-color:#ff6666;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:8px 14px;
	text-decoration:none;
	text-shadow:0px 1px 0px #b23e35;
}
</style>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" id="productId" value="<%=productId%>">
	<input type="hidden" id="category" value="<%=category%>">
	<input type="hidden"  id="totalPages" value="<%=totalPages%>">
	<input type="hidden"  id="totalReviewCount" value="<%=totalReviewCount%>">
	
	<div id="tabs-container">
		<ul class="tabs-menu">
			<li><a href="#tab-1">Details</a></li>
			<li><a href="#tab-2">Tech Specifications</a></li>
			<li><a href="#tab-3">Reviews</a></li>
		</ul>
		<div class="tab">
			<!-- Tab content 1 -->
			<div id="tab-1" class="tab-content" style="display:'block'">
				<div class=detailspanneltop>
					<div class="detailspanneltopimages"
						style="float: left; width: 200px; height: 200px; margin: 20px;">

						<div id="container">
						<%if(!CollectionUtils.isEmpty(restImageUrls)) {
						  String altImage = "/resources/usertheme/images/Alt_image.jpeg"; 
						  String restImageUrl1=  (restImageUrls.size()>1 && restImageUrls.get(0)!=null )?restImageUrls.get(1):altImage;
						  String restImageUrl2= (restImageUrls.size()>1 && restImageUrls.get(1)!=null) ?restImageUrls.get(1):altImage;
						  String restImageUrl3=  (restImageUrls.size()>2 && restImageUrls.get(2)!=null) ?restImageUrls.get(1):altImage;
						
						
						
						%>
							<!-- OUTTER WRAPPER -->
							<div id="slider-wrapper">
								<!-- SLIDE 1 -->
								<div id="slide1" class="slide">
									<img src="<c:url value="<%=restImageUrl1%>" />"
										alt="Slide 1" width="150" height="150" style="float: left;" />


								</div>
								<!-- SLIDE 2 -->
								<div id="slide2" class="slide">
									<img src="<c:url value="<%=restImageUrl2%>" />"
										alt="Slide 2" width="150" height="150" style="float: left;" />


								</div>
								<!-- SLIDE 3 -->
								<div id="slide3" class="slide">
									<img src="<c:url value="<%=restImageUrl3%>" />"
										alt="Slide 3" width="150" height="150" style="float: left;" />


								</div>
							</div>
							<%} %>
						</div>
						<!--- NAVIGATION BUTTONS -->
						<a href="javascript:void(0)" onclick="PreviousSlide()"
							id="PreviousButton" class="imgslidebutton"
							style="margin-right: 10px; margin-left: 20px;"> Prev</a><a
							href="javascript:void(0)" onclick="NextSlide()" id="NextButton"
							class="imgslidebutton" style="margin-left: 20px;">Next </a>






					</div>
					<div class="detailspanneltopfeatures"
						style="float: left; width: 300px; margin: 20px">
						<ul>
							<li><span style="font-size: 22"><b><%=productName%></b></span></li>
						</ul>
						<ul style="list-style: initial;">	
							
							<%
								for (String str : topHeadlinesFeatures) {
							%>
							<li><b><%=str%></b></li>
							<%
								}
							%>
							<%
								if (marketStaus != null && !"".equals(marketStaus)) {
							%>
							<li><b>Market Status:<%=marketStaus%></b></li>
							<%
								}
							%>

							<%
								if (releasesOS != null && !"".equals(releasesOS)) {
							%>
							<li><b>Releases OS:<%=releasesOS%></b></li>
							<%
								}
							%>
							<%
								if (colors != null && !"".equals(colors)) {
							%>
							<li><b>Colors:<%=colors%></b></li>
							<%
								}
							%>
						</ul>


					</div>
					<div class="detailspanneltopfeaturesnext"
						style="float: left; width: 200px; height: 200px; margin: 20px">
						<ul >
							<li style="float:left"><b>Rating:&nbsp;<%=avgRating%></b></li>
							<li style="float:left"><b>BestBuy:&nbsp;<%=mobileDetails.getBestBuyPrice()%></b></li>
						</ul>
					</div>
				</div>
				<!-- Outline:mobile.overview.descrption|  -->
				<%
					if (!StringUtils.isEmpty(mobileDetails.getOverView().getDescription())) {
				%>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Outline</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription></div>
					<p style="font-family: 'Open Sans', sans-serif; font-size: 18px"><%=mobileDetails.getOverView().getDescription()%></p>
				</div>


				<%
					}
				%>

				<%
					for (AdditionalFeatures additionalFeatures : additionalFeatureList) {
				%>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;"><%=additionalFeatures.getName()%></span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription></div>
					<p style="font-family: 'Open Sans', sans-serif; font-size: 18px"><%=additionalFeatures.getValue()%></p>
				</div>
				<%
					}
				%>
			</div>

			<!-- Tab content 2 -->
			<div id="tab-2" class="tab-content">
				<%-- <%for(int i=0;i<4;i++){ %> --%>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Overview</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>

						<%
							if (mobileDetails != null && mobileDetails.getOverView() != null) {
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getReleaseDate())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Release Date:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getReleaseDate()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getMarketStaus())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Market Status:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getMarketStaus()%></div>
						</div>
						<%
							}
						%>

						<%
							if (!StringUtils.isEmpty(mobileDetails.getPhoneType())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Phone Type:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getPhoneType()%></div>
						</div>
						<%
							}
						%>

						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getReleaseOS())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Released OS:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getReleaseOS()%></div>
						</div>
						<%
							}
						%>


						<%
							if (!StringUtils.isEmpty(mobileDetails.getRAM())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>RAM:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getRAM()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getProcessor())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Processor:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getProcessor()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getColors())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Available Color:</b>
							</div>
							<div id="right_col"><%=String.join(",", mobileDetails.getColors())%></div>
						</div>
						<%
							}
						%>


						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getPhoneWeight())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Phone Weight:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getPhoneWeight()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getDimension())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Dimension:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getDimension()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getOverView().getBodyMaterial())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Body Material:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getBodyMaterial()%></div>
						</div>
						<%
							}
						%>
						<%
							}
						%>

					</div>
				</div>

				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Camera</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>


						<%
							if (mobileDetails != null && mobileDetails.getCamera() != null) {
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getCamera().getRearCameraInMegaPixalWithFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Main Camera</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>

						<%
							for (String feature : mobileDetails.getCamera().getRearCameraInMegaPixalWithFeatures()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col"><%=feature.split(":")[0]%></div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>

						<%
							}
									}
									;
						%>

						<%
							}
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getCamera().getFrontcameraInMegaPixalWithFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Front Camera</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>
						<%
							for (String feature : mobileDetails.getCamera().getFrontcameraInMegaPixalWithFeatures()) {
										String temp[] = feature.split(":");

										if (temp.length == 2) {
											String temp1 = temp[0];
											String temp2 = temp[1];
						%>
						<div id="wrap">
							<div id="left_col"><%=temp1%></div>
							<div id="right_col"><%=temp2%></div>
						</div>
						<%
							}
									}
									;
						%>


						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getCamera().getVideoResolution())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Video Resolution:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getCamera().getVideoResolution()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getCamera().getOtherCameraFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Other Camera Features</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>

						<%
							for (String feature : mobileDetails.getCamera().getOtherCameraFeatures()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col"><%=feature.split(":")[0]%></div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}
									}
									;
						%>

						<%
							}
						%>
						<%
							}
						%>




					</div>
				</div>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Display</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>
						<%
							if (mobileDetails != null && mobileDetails.getDisplay() != null) {
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getDisplay().getScreenSize())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Screen Size:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getDisplay().getScreenSize()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getDisplay().getScreenResolution())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Screen Resolution:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getDisplay().getScreenResolution()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getDisplay().getScreenMaterial())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Screen Material:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getDisplay().getScreenMaterial()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getDisplay().getDisplayTechnology())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Display Technology:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getOverView().getDimension()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getDisplay().getOtherDisplayFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Other Display Features</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>
						<%
							for (String feature : mobileDetails.getDisplay().getOtherDisplayFeatures()) {

										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col"><%=feature.split(":")[0]%></div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>


						<%
							}
									}
									;
						%>

						<%
							}
						%>
						<%
							}
						%>




					</div>
				</div>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Battery</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>

						<%
							if (mobileDetails != null && mobileDetails.getBattery() != null) {
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getBattery().getTalkTime())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Talk Time:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getBattery().getTalkTime()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getBattery().getBatteryTechnology())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Battery Technology:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getBattery().getBatteryTechnology()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getBattery().getBatteryCapacity())) {
						%>

						<div id="wrap">
							<div id="left_col">
								<b>Battery Capacity:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getBattery().getBatteryCapacity()%></div>
						</div>
						<%
							}
						%>



						<%
							}
						%>



					</div>
				</div>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">Storage</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>


						<%
							if (mobileDetails != null && mobileDetails.getStorage() != null) {
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getStorage().getInternalStorage())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Internal Storage:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getStorage().getInternalStorage()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getStorage().getExpendableStorageFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Expendable StorageFeatures</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>
						<%
							for (String feature : mobileDetails.getStorage().getExpendableStorageFeatures()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><%=feature.split(":")[0]%></b>
							</div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}

									}
									;
						%>


						<%
							}
						%>

						<%
							}
						%>

					</div>
				</div>
				<div class=detailspannelbottom>
					<div class=detailspannelbottomheader>
						<span class=frontviewheadings style="margin-left: 10px;">ConnectivityAndFeatures</span>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>

						<%
							if (mobileDetails != null && mobileDetails.getConnectivityAndFeatures() != null) {
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getNetworkFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Network Features</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>
						<%
							for (String feature : mobileDetails.getConnectivityAndFeatures().getNetworkFeatures()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><%=feature.split(":")[0]%></b>
							</div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}
									}
									;
						%>
						<%
							}
						%>
						<%
							if (!StringUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getDualSimSupportabilty())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Dual Sim Supportabilty:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getConnectivityAndFeatures().getDualSimSupportabilty()%></div>
						</div>
						<%
							}
						%>

						<%
							if (!StringUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getSimCardSize())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>Dual Sim Supportabilty:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getConnectivityAndFeatures().getSimCardSize()%></div>
						</div>
						<%
							}
						%>

						<%
							if (!StringUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getBluetoothVersion())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b>BluetoothVersion:</b>
							</div>
							<div id="right_col"><%=mobileDetails.getConnectivityAndFeatures().getBluetoothVersion()%></div>
						</div>
						<%
							}
						%>
						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getChargingOptions())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Charging Options</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>

						<%
							for (String feature : mobileDetails.getConnectivityAndFeatures().getChargingOptions()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><%=feature.split(":")[0]%></b>
							</div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}
									}
									;
						%>
						<%
							}
						%>

						<%
							if (!CollectionUtils.isEmpty(mobileDetails.getConnectivityAndFeatures().getSensors())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Sensors</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>

						<%
							for (String feature : mobileDetails.getConnectivityAndFeatures().getSensors()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><%=feature.split(":")[0]%></b>
							</div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}
									}
									;
						%>


						<%
							}
						%>
						<%
							if (!CollectionUtils
										.isEmpty(mobileDetails.getConnectivityAndFeatures().getOthersConnectivityFeatures())) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><u>Other Connectivity Features</u></b>
							</div>
							<div id="right_col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</div>

						<%
							for (String feature : mobileDetails.getConnectivityAndFeatures().getOthersConnectivityFeatures()) {
										if (feature.split(":").length == 2) {
						%>
						<div id="wrap">
							<div id="left_col">
								<b><%=feature.split(":")[0]%></b>
							</div>
							<div id="right_col"><%=feature.split(":")[1]%></div>
						</div>
						<%
							}
									}
									;
						%>

						<%
							}
						%>

						<%
							}
						%>


					</div>
				</div>

				<%-- <%} %> --%>
			</div>
			<!-- Tab content 3 -->
			<div id="tab-3" class="tab-content">

				<div class=trendingpannelloadmorebutton id="writereviewbuttonpannel">
					<a href="javaScript:void(0)" class="myButton" id="writereviewbutton">Write a
						review</a>

				</div>
				<div class=reviewMassagePannel
					style="float: left; margin-left: 280px; width: 700px; color: #e60000;">
					<ul id="reviewMassageul">
					</ul>

				</div>
				<div class="reviewform" style="width: 880px; display: none">
					<form action="">
						<ul class="form-style-1">
							<li><label>Add review for <%=productName%>:
							</label></li>
							<li><label>Review headline: <span class="required">*</span></label>
								<input type="text" name="field1" class="field-divided"
								id="reviewHeadline" placeholder='Headline for your review' /></li>
							<li><label>Pros: </label> <textarea
									placeholder='Strengths of <%=productName%>' name="field5"
									id="reviewPros" class="field-long field-textarea"></textarea></li>
							<li><label>Cons: </label> <textarea
									placeholder='Weaknesses of <%=productName%>' name="field5"
									id="reviewCons" class="field-long field-textarea"></textarea></li>
							<li><label>Rating <span class="required">*</span>
							
							
				<div class="reviewRatings" style="cursor:pointer">
		<span class="rating" data-identifier='reviewRate'>
		<span class="star profilereviewRate" title="Excellent"></span>
		<span class="star profilereviewRate" title="Very Good"></span>
		<span class="star profilereviewRate" title="Good"></span>
		<span class="star profilereviewRate" title="Fair"></span>
		<span class="star profilereviewRate" title="Poor"></span>
        </span>
			 
			 </div>
							
							
							
							
							</label></li>


							<li><input type="button" value="Submit"
								onclick="submitReview()" style="cursor: pointer;" /></li>
						</ul>
					</form>
				</div>
				 <%if(!CollectionUtils.isEmpty(reviewList)){ %>
				<div class="reviewPagination" style="width: 880px;">
					<a href="javascript:void(0)" class='review_pagination_top_rated'
						id="topRatedReview">Top Rated review</a> <a
						href="javascript:void(0)" class='review_pagination_latest'
						id="latestReview">Latest review</a>
					<div class="latest_review_pages"
						style="float: left; width: 880px; margin-top: 15px; display: none">
						<ul class="pagination">
							<%
								if (totalPages > 1) {
							%>
							<li><a href="javascript:void(0)" class="fetchPrevReview" id="page_prev"
								style="cursor: pointer; 
								display:none">«</a></li>

							<%
								
								} 
							  for (int i = 1; i <= totalPages; i++) {
								if (i == 1) {
							%>
							<li><a class="active fetchReview" href="javascript:void(0)" id="page_<%=i%>"
								 style="cursor: pointer;"><%=i%></a></li>
							<%
								} else {
							%>
							<li><a href="javascript:void(0)" class="fetchReview" id="page_<%=i%>"
								style="cursor: pointer;"><%=i%></a></li>
							<%
								}
								}
								if (totalPages > 1) {
							%>
							  
							<li><a href="javascript:void(0)" class="fetchNextReview" id="page_next"
								style="cursor: pointer;">»</a></li>
							<%
								}
							%>
						</ul>
						<div class="lat_rev_info_div" style="float:left; width:880px;margin-top:15px;margin-bottom:30px;margin-left:10px">
						<b><span class=latest_review_page_info_begin>1&nbsp;</span></b> 
						to <b><span class=latest_review_page_info_end>10&nbsp;</span></b>&nbsp;of total <b><%=totalReviewCount%></b>&nbsp;reviews:
						</div>
					</div>
				</div>

				<%
				  
					for (ReviewDetails reviewDetails : reviewList) {
				%>
				<div class='detailspannelbottom topRatedReviewPlaceholder'>
					<div class=detailspannelbottomheader style="height: 80px;">
						<span class=frontviewheadings style="margin-left: 0px;"><%=reviewDetails.getReviewHeadline()%>
						</span><br> by:<b><%=reviewDetails.getByUserId()%></b>, created on:<b><%=reviewDetails.getCreatedOn()%></b>,
						modified on:<b><%=reviewDetails.getModifiedOn()%></b><br>
						total likes:<b><%=reviewDetails.getLike()%></b>
						<hr
							style="height: 1px; border: none; color: #808080; background-color: #808080;" />
					</div>
					<div class=detailspannelbottomdescription>
						<table>
							<tr>
								<td width="70px"><b>Pros:</b></td>
								<td><div class="reviesContains"
										style="width: 500px; font-family: 'Comic Sans MS', cursive, sans-serif;">
										Loremipsum
										<%=reviewDetails.getReviewPros()%></div></td>
							</tr>
							<tr>
								<td style="line-height: 30px" colspan=2>&nbsp;</td>
							</tr>

							<tr>
								<td><b>Cons:</b></td>
								<td><div class="reviesContains"
										style="width: 500px; font-family: 'Comic Sans MS', cursive, sans-serif;">
										dolor sit amet, consectetur adipiscing elit. Aliquam sit
										<%=reviewDetails.getReviewCons()%></div></td>
							</tr>
							<tr>
								<td style="line-height: 10px" colspan=2>&nbsp;</td>
							</tr>

							<tr>
								<td><b>Rating:</b></td>
								<td><%=reviewDetails.getRating()%>/5</td>
							</tr>




						</table>

					</div>
				</div>
				<%}}else{ %>
				 <div id="emptyReview" style="margin: 20px;margin-bottom:15px"><span class="filterHeader" style="margin-top:20px;margin-bottom: 20px;margin-left:20px">No any Review found!!!Add review for <%=mobileDetails.getName()%></span></div>
				<%} %>
				
				<div id="latestReviewHtmlPlaceholdr" style="float:left;width:880px">
				<!-- <div class='detailspannelbottom latestReviewPlaceholder'>
					########latestReviewPlaceholder########</div> -->
					
			</div>
		</div>
	</div>
<!-- 	<!-- The Modal -->
<!-- <div id="myModal" class="modal">
  <span class="close">&times;</span>
  <img class="modal-content" id="img01">
  <div id="caption"></div>
</div> -->
</body>
</html>