<%@page import="com.planb.dao.admin.AdminSessionDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
AdminSessionDetail adminSessionDetail;

%>
<%
adminSessionDetail=(AdminSessionDetail)request.getSession().getAttribute("adminData");
out.print(adminSessionDetail);

%>
Welcome
 <%if(adminSessionDetail!=null && adminSessionDetail.isLoggedIn()) {%>
 <%=adminSessionDetail.getName()%><a href="/review-aggregator-electronics/adminlogout">logout</a>
 <%}else{ %>
 Guest  <a href="/review-aggregator-electronics/adminlogin">login</a>
 <%} %>
</body>
</html>