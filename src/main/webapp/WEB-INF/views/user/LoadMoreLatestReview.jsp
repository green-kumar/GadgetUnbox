<%@page import="java.util.ArrayList"%>
<%@page import="com.planb.dao.review.ReviewDetails"%>
<%@page import="java.util.List"%>
<%!
List<ReviewDetails> reviewList;
%>
<%
reviewList=(ArrayList)request.getAttribute("reviewDetailsList");
%>


				<%
				    if(reviewList != null){
					for (ReviewDetails reviewDetails : reviewList) {
				%>
				<div class='detailspannelbottom latestReviewPlaceholder'>
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
				<%}}
				%>