<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<title>
    <tiles:insertAttribute name="title" ignore="true"/>
</title>



<head>

 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"   type="text/javascript"></script>
<script src="<c:url value="/resources/care/careJs.js" />"   type="text/javascript"></script>
<script src="<c:url value="/resources/care/care-config.js" />"   type="text/javascript"></script>
<link href="<c:url value="/resources/care/careStyle.css" />" rel="stylesheet">

<body>
	<div class="usershell">
		<div class="usercontainer">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<tiles:insertAttribute name="menu"></tiles:insertAttribute>
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		</div> 
	</div>
</body>
</html>