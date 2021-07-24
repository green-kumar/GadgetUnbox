<%@page import="com.planb.dao.admin.AdminSessionDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<title>
    <tiles:insertAttribute name="title" ignore="true"/>
</title>
<!-- <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->



<head>
<script src="<c:url value="/resources/js/jquery.js" />"   type="text/javascript"></script>
<script src="<c:url value="/resources/js/packageupload.js" />"   type="text/javascript"></script>
<script src="<c:url value="/resources/js/login.js" />"   type="text/javascript"></script>

<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/hello.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/loginform.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/menu_style.css" />"
	rel="stylesheet">
</head>
<body>
 <%!
boolean isLoggedIn;
AdminSessionDetail adminSessionDetail;

%>
<%
adminSessionDetail=(AdminSessionDetail)request.getSession().getAttribute("adminData");
out.print(adminSessionDetail);
if(adminSessionDetail!=null){
	isLoggedIn=adminSessionDetail.isLoggedIn();
}
%>
<input type="text" id="loginflag" value="<%=isLoggedIn%>"/>
	<div id="admin_emailoverlay" class="email_dialog_overlay"></div>
	<div id="admin_emaildialog" class="email_dialog">
		<table class="emailtable" title="table">
			<tr>
				<th colspan="2" style="display: none;">&nbsp;</th>
			</tr>

			<tr>
				<td class="web_dialog_title">Attention Required!!</td>
				<td class="web_dialog_title align_right"><a href="#" class="testclose"
					id="emailbtnClose">Close</a></td>
			</tr>
		</table>
		<div class="admin_container">
			 <div id="header" class="eGain eGainRS">
				<div class="logo">Login form</div>
			</div>
			   <form id="loginForm" name="loginForm" action="/Home/SubmitSubscription" method="post">
				<div class="pannel " id="loginpannel">
					<table>
                        <tr>
							<td>Name :</td>
							<td><input id="adminName" name="adminName" type="text"
								style="width: 175px;" /></td>
						</tr>
						<tr>
							<td>Email :</td>
							<td><input id="loginemail" name="loginemail" type="text"
								style="width: 175px;" /></td>
						</tr>
						<tr>
							<td>Password :</td>
							<td><input id="loginpassword" name="loginpassword"
								type="password" style="width: 175px;" /></td>
						</tr>
						<tr>
							<td colspan=2 align=center><input type="button" id=subbutton name=subbutton onclick="validateLoginForm(this.form);" value="Login"/></td>
							<td><span id="target">....</span></td>

						</tr>

					</table>
				</div>
              </form>
		</div>
	</div>





	<!-- wrapper -->
	<div id="wrapper">
		<H1>Header</H1>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<H1>Menu</H1>
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		<div class="shell">
			<!-- container -->
			<div class="container">
				<H1>BODY</H1>
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
				<H1>footer</H1>
				<tiles:insertAttribute name="footer"></tiles:insertAttribute>
			</div>
			<!-- end of container -->
		</div>
		<!-- end of shell -->
	</div>
	<!-- end of wrapper -->
</body>
</html>