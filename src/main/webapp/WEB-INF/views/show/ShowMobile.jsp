<%@page import="com.planb.dao.mobile.Mobile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
Mobile mobile=null;
String categary="";
String css="";
String msg="";
String productDetail;


%>
<%
try{
mobile=(Mobile)request.getAttribute("mobile");
css=request.getAttribute("css").toString();
msg=request.getAttribute("msg").toString();
categary=request.getAttribute("categary").toString();
}catch(Exception e){
	
}



%>
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Product Detail</h1>
	<br/>
	<h3>Category:<h3><%=categary %>
	<br />

   
     <div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10"><%=mobile.getProductId()%></div>
	</div>
	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10"><%=mobile.getName()%></div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Mobile Features</label>
		<div class="col-sm-10"><%=mobile.toString()%></div>
	</div>

    <spring:url value="/${category}/updateProduct/${mobile.productId}" var="updateUrl" />
   <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
	
</div>

</body>
</html>