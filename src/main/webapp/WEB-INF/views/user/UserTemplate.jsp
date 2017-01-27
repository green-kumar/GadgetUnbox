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
<script src="<c:url value="/resources/usertheme/js/loginregs.js" />"   type="text/javascript"></script>
<script src="<c:url value="/resources/usertheme/js/userjs.js" />"   type="text/javascript"></script>
<script src="<c:url value="/resources/usertheme/js/app-config.js" />"   type="text/javascript"></script>
<link href="<c:url value="/resources/usertheme/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/usertheme/css/loginform.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.lazyload/1.9.1/jquery.lazyload.min.js"></script>

<body>
<%!
String isLoggedIn="false";
String userName;


%>
<%
isLoggedIn="false";
userName=(String)request.getSession().getAttribute("userName");
if(userName!=null){
	isLoggedIn="true";
}
%>
<input type="hidden" id="loginflag" value="<%=isLoggedIn%>">
	<div class="usershell">
		<div class="usercontainer">
			<tiles:insertAttribute name="header"></tiles:insertAttribute>
			<tiles:insertAttribute name="menu"></tiles:insertAttribute>
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		</div> 
	</div>
	<!-- login registratioin form -->
 <div id="emailoverlay" class="email_dialog_overlay"></div>
<div id="emaildialog" class="email_dialog">


	<div class="emailcontainer">
		<div class="emailheader">
			<div class="emaillogo">Login/Signup form</div>

		</div>
		<div class="emailpannel" id="errorpannel">
		<span class="errorspan" style="color:#e60000;"></span>
		<ul id="errorul">
		
				</ul> 
			</div>
			
			
			

				<div class="emailpannel " id="toppannel">
					<table>
						<tr>
							<td width="80"><span class='logintext'><a href="javascript:void(0)">Login</a></span></td>
							<td><span class='signuptext'><a href="javascript:void(0)">Signup</a></span></td>
						</tr>
					</table>
				</div>
				<form id="loginForm" name="loginForm"
					action="" method="post">
					<div class="emailpannel " id="loginpannel">
						<table>
						 <tr>
								<td bgcolor="#ffffff" style="line-height: 30px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Email<span style="color: red">*</span></td>
								<td><input id="loginemail" name="loginemail" type="text"
									style="width: 175px;" /></td>
							</tr>
							<tr>
								<td bgcolor="#ffffff" style="line-height: 10px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Password<span style="color: red">*</span></td>
								<td><input id="loginpassword" name="loginpassword"
									type="password" style="width: 175px;" /></td>
							</tr>
							<tr>
							<td>&nbsp;</td>
								<td><span style="color:#e60000;cursor: pointer;" onclick="recoverPassword();">forget password?</span></td>
							</tr>
							
							
							<tr>
								<td bgcolor="#ffffff" style="line-height: 30px" colspan=2>&nbsp;</td>
								</tr>
							<tr>
								<td colspan=2 align=center>
									
									<a href="javascript:void(0)" class="myButton" id="loginbutton" style="padding:8px 24px;" onclick="validateLoginForm(this.form);">Login</a>
									
									</td>
								<td><span id="target"><img src="<c:url value="/resources/usertheme/images/load.gif" />" alt="..."  style="display: none"/></img></span></td>

							</tr>
							<tr>
								<td bgcolor="#ffffff" style="line-height: 30px" colspan=2>&nbsp;</td>
							</tr>
							<tr class=seperator></tr>
							<tr>
								<td>Login using:</td>
							</tr>
							<tr>
								<td colspan=2 align=center><input type="button"
									style="background: #333399" id=subbutton name=subbutton
									value="facebook" /></td>
							</tr>
							<tr>
								<td bgcolor="#ffffff" style="line-height: 10px" colspan=2>&nbsp;</td>
							<tr>
								<td colspan=2 align=center><input type="button"
									style="background: #e60000" id=subbutton name=subbutton
									value="Google" /></td>
							</tr>


						</table>

					</div>
				</form>
				<form id="registrationForm" name="registrationForm"
					action="" method="post">

					<div class="emailpannel" id="registrationpannel"
						style="display: none">
						<table>
						 <tr>
								<td bgcolor="#ffffff" style="line-height: 30px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Your Name<span style="color: red">*</span></td>
								<td><input id="signupName" name="signupName" type="text"
									style="width: 175px;" /></td>
							</tr>
                           <tr>
								<td bgcolor="#ffffff" style="line-height: 10px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Your Email<span style="color: red">*</span></td>
								<td><input id="signupEmail" name="signupEmail" type="text"
									style="width: 175px;" /></td>
							</tr>
							 <tr>
								<td bgcolor="#ffffff" style="line-height: 10px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Choose Password<span style="color: red">*</span></td>
								<td><input id="signupPassword" name="signupPassword"
									type="password" style="width: 175px;" /></td>
							</tr>
							 <tr>
								<td bgcolor="#ffffff" style="line-height: 10px" colspan=2>&nbsp;</td>
							</tr>
							<tr>
								<td>Confirm Password<span style="color: red">*</span></td>
								<td><input id="signupPasswordAgain"
									name="signupPasswordAgain" type="password"
									style="width: 175px;" /></td>
							</tr>
							
							<tr>
								<td bgcolor="#ffffff" style="line-height: 30px" colspan=2>&nbsp;</td>
							<tr>

								<td colspan=2 align=center>
									<a href="javascript:void(0)" class="myButton" id="regsbutton" style="padding:8px 24px;" onclick="validateRegistrationForm(this.form);">Create account</a>
									
									</td>
								<td><span id="targetregs"><img src="<c:url value="/resources/usertheme/images/load.gif" />" alt="..."  style="display: none"/></img></span></td>

							</tr>

						</table>
					</div>
				</form>

			</div>
		</div> 
</body>
</html>