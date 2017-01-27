<%@page import="com.planb.inmemoery.service.GenderEnum"%>
<%@page import="com.planb.dao.user.UserDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
</head>
<body>
	<%!String userName;
	String userEmail;
	String userDOB = "dd-mm-yy";
	UserDetail userDetail;
	String genderMale = "";
	String genderFemale = "";
	String genderOther = "";
	GenderEnum genderEnum;%>
	<%
		userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			userDetail = (UserDetail) request.getAttribute("userDetail");
			userName = userDetail.getName();
			userDOB = userDetail.getDOB();

			genderEnum = userDetail.getGender();
			if (genderEnum != null && !genderEnum.equals(GenderEnum.MALE)) {
				if (genderEnum.equals(GenderEnum.FEMALE))
					genderFemale = "checked";
				else
					genderOther = "checked";

			} else {
				genderMale = "checked";
			}

		}
	%>

<div style="margin: 20px ;margin-top:60px">
	
	<span class="profileHeader" style="margin-top:20px;margin-bottom: 20px">My Profile</span>
	
	</div>
	<hr style="height: 2px; border: none; color: #808080; background-color: #808080;margin-bottom:10px" />

	
	<%
		if (userEmail == null) {
	%>
	
	<div style="margin: 20px ;">
		<span style="color: #e60000;font-family:Arial;font-size:15px;font-weight:bold;padding:12px 24px;text-decoration:none;text-shadow:0px 1px 0px #b23e35;">Sorry,You are
			not logged in.Please login first!!!</span>
	</div>
	<%
		} else {
	%>

	<input type="hidden" id="userName" value="<%=userName%>" />
	<input type="hidden" id="userEmail" value="<%=userEmail%>" />
	<input type="hidden" id="userDOB" value="<%=userDOB%>" />
	<input type="hidden" id="userGender"
		value="<%=(genderEnum != null) ? genderEnum.toString() : ""%>" />
		
		<div class="emailpannel" id="profileerrorpannel" style="width:300px;margin-left:30px;">
		<span class="errorspan" style="color:#e60000;"></span>
		<ul id="profileerrorul"></ul> 
		</div>

	<form id="registrationForm" name="registrationForm" action=""
		method="post">

		<div class="emailpannel" id="registrationpannel"
			style="margin-left: 50px;">
			
			<table>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td width="100px">Name</td>
					<td id="profileName"><%=userDetail.getName()%></td>
				</tr>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Email</td>
					<td id="profileEmail"><%=userDetail.getEmail()%></td>
				</tr>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>**************</td>
					<td><span class="profile_change_password">Change
							password</span></td>
				</tr>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>DOB</td>
					<td id="profileDOB"><%=userDetail.getDOB()%></td>
				</tr>
				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>
				</tr>

				<tr>
					<td>Gender</td>
					<td><input type="radio" id="profileGenderMale" name="gender"
						value="male" <%=genderMale%>>Male &nbsp;&nbsp;&nbsp;&nbsp;<input
						type="radio" id="profileGenderFemale" name="gender" value="female"
						<%=genderFemale%>>Female&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="radio" id="profileGenderOther" name="gender" value="other"
						<%=genderOther%>>Other</td>
				</tr>

				<tr>
					<td style="line-height: 30px" colspan=2>&nbsp;</td>

					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton" id="editProfile" style="padding: 8px 24px;">Edit</a></td>
						
					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton" id="cancelEditProfile"
						style="padding: 8px 24px; margin-left: 50px;display: none">Cancel</a></td>
					
					<td colspan=2 align=center><a href="javascript:void(0)"
						class="myButton" id="saveProfile"
						style="padding: 8px 24px;display: none;">Save</a></td>
						
						
					<td><span id="target"><img
							src="<c:url value="/resources/usertheme/images/load.gif" />"
							alt="..." style="display: none" /></img></span>
							
                        <span id="updateWait">
 <img src="<c:url value="/resources/usertheme/images/load.gif" />" alt="..."  style="display: none"/></img></span></td>
				</tr>

			</table>
		</div>
	</form>
	
<%
	}
%>

<!-- -------------Password change dialog box----------------------------- -->
					<div id="passwordBoxoverlay" class="passwordBox_dialog_overlay"></div>
	<div id="passwordBoxdialog" class="passwordBox_dialog">
	<div id="passworchangeHeader"
							style="float:left; width: 380px;height:30px;background-color:#e60000;color:#000000;font-family: 'Open Sans', sans-serif; font-size: 18px;color: White;">
	<div
								style="float:left">change password!!!</div>
							<div
								style="float:right">
								<span id="passwordBoxbtnClose"  style="cursor:pointer;">close</span>
							</div>
	</div>
	<div id="errorMsg" style="float:left; width: 380px;">
	
	</div>
	
   <table class="passwordBoxtable">
   <tr>
								<th colspan="2" style="display:none;">&nbsp;</th>
							</tr>
      <tr>
         <td colspan="2" style="padding-left: 15px;">
           <label for="oldPassword"> <b>Old password</b> </label>
         </td>
		 <td colspan="2" style="padding-left: 15px;">
		  <input type="password" id="oldPassword" name="oldPassword" value="">
          </td>
      </tr>
      <tr>
         <td colspan="2" style="padding-left: 15px;">
           <label for="newPassword"> <b>new password</b> </label>
         </td>
		  <td colspan="2" style="padding-left: 15px;">
		  <input type="password" id="newPassword" name="newPassword" value="">
          </td>
      </tr>
      <tr>
         <td colspan="2" style="padding-left: 15px;">
           <label for="confirmPassword"> <b>confirm password</b> </label>
         </td>
		 <td colspan="2" style="padding-left: 15px;">
		  <input type="password" id="confirmPassword" name="confirmPassword"
									value="">
          </td>
      </tr>
      <tr>
    
         <td colspan="2" style="padding-left: 15px;">
            <a href="#" class="myButton" id="passwordChangebutton"
									style="padding: 7px 17px;">change password</a>
         </td>
           <td colspan="2" style="padding-left: 15px;">
           <span id="passChangeTarget"><img
										src="<c:url value="/resources/usertheme/images/load.gif" />"
										alt="..." style="display: none" /></img></span>
         </td>
      </tr>
   </table>
</div>

<!-- -------------End Password change dialog box----------------------------- -->




				</body>
</html>