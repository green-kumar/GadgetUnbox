<%@page import="com.planb.dao.camera.camera"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!List<camera> cameraList = null;
	String category = null;
	String Empty_msg = null;
	String css = null;%>
	<%
		cameraList = (List) request.getAttribute("cameraList");
		category = request.getAttribute("category").toString();
		Empty_msg = request.getAttribute("Empty_msg").toString();
		css = request.getAttribute("css").toString();
	%>
	<div class="container">
		<%
			if (!"".equals(Empty_msg)) {
		%>
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong><%=Empty_msg%></strong>
		</div>
		<%
			} else {
		%>
		<h1>All <span id="pageCategory"><%=category%></span><span id='productCount'><%=cameraList.size() %></span></h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>name</th>
					<th>brand</th>
					<th>bestBuyPrice</th>
					<th>mainImageUrl</th>
				</tr>
			</thead>

			<%
				for (camera camera : cameraList) {
			%>
			<spring:url value="<%=camera.getProductId()%>" var="productId" />
			<tr id="tr_${productId}">
				<td><%=camera.getProductId()%></td>
				<td><%=camera.getName()%></td>
				<td><%=camera.getBrand()%></td>
				<td><%=camera.getBestBuyPrice()%></td>
				<td><%=camera.getMainImageUrl()%></td>
				<td>

					<div id="emailoverlay_${productId}" class="email_dialog_overlay"></div>
					<div id="emaildialog_${productId}" class="email_dialog">
						<table class="emailtable" title="table">
							<tr>
								<th colspan="2" style="display: none;">&nbsp;</th>
							</tr>

							<tr>
								<td class="web_dialog_title">Full details!!</td>
								<td class="web_dialog_title align_right"><a href="#"
									class="emailbtnClose" id="${productId}">Close</a></td>
							</tr>
						</table>
						<div class="container1">
							<%=camera.toString()%>
						</div>
				 </div> 
					
					<spring:url value="/${category}/deleteProduct/${productId}"
						var="deleteUrl" /> <spring:url
						value="/${category}/updateProduct/${productId}" var="updateUrl" />

					<button class="btn btn-info" id="${productId}">View</button>
					<button class="btn btn-primary"
						onclick="location.href='${updateUrl}'">Update</button>
					<button class="btn btn-danger" id="${productId}">Delete</button>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>

	</div>




</body>
</html>