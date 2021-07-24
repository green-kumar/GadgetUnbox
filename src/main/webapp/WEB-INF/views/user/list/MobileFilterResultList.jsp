	
	
	
	
	
	<%@page import="com.planb.metadata.ProductMetaData"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%!//List<String> reviewList;

List<ProductMetaData> sortedProductList;
int productCount;
String userEmail;
List<String> favProductList;
Map<String,Double> userProductRatingMap;

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

       
%>
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
					/>
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
					data-category="<%=sortedProductList.get(i).getCategory()%>"  data-currentRating="<%=userProductRatingMap.get(sortedProductList.get(i))%>"></span>
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
				
				</div>			<div class="myFavRank"
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