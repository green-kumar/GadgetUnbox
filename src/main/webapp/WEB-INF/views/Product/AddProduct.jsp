<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
//Map<String,HashMap<String,ArrayList<String>>> productKeyList;
%>

<%

try{
//productKeyList=(Map)request.getAttribute("productDetailsMap");
String productKeyList=(String)request.getAttribute("test");
out.println(productKeyList);
/* for(Map.Entry<String,ArrayList<String>> mp :productKeyList.entrySet()){
	out.println(mp.getKey());
	ArrayList<String> al=mp.getValue();
	for(String str:al){
		out.print(str+",");
	}
} */
}catch(Exception e){
	
}
%>
<%-- <div class="shell">
		
		<div class="container">
			
			<div id="header" class="eGain eGainRS">
			   <div class="logo">
				<%=i18nentrypointResource.getString("L10N_HEADER_NAME", locale)%>
				</div>
				
				<div class="search">
					<form onsubmit="return  validateId()" action="jobstatus.jsp" method="get">
                        <input type="text"  id="jobid" class="field" name="jobid" value=<%=i18nentrypointResource.getString("L10N_JOB_ID", locale)%> title="Job id" onblur="onBlur(this)" onfocus="onFocus(this)">
						
						<input type="submit" class="search-btn" value="<%=i18nentrypointResource.getString("L10N_JOB_STATUS", locale)%>">
				    </form>
				</div>
				<div class="home"><a href="../../../web/custom/trisoft/packageupload.jsp"><%=i18nentrypointResource.getString("L10N_HOME_BUTTON", locale)%></a></div>
				<div class="home"><a href="utils/uploadxmltodb.jsp"><%=i18nentrypointResource.getString("L10N_XML_BUTTON", locale)%></a></div>
				<div class="cl">&nbsp;</div>
			</div>
<div id="pannel">
<form onsubmit="return  validate()" action="submitjob.jsp"  enctype="multipart/form-data" method="post">
   
           <table >

                 <tr><td width="160px"><%=i18nentrypointResource.getString("L10N_DEPARTMENT", locale)%></td><td><select multiple name="dept" id="dept" ><%
                 
                		  deptdatalist=getDept();
         		for (DepartmentData dd : deptdatalist)
         		{
         		%>
         		<option value=<%=dd.getDepttId()%>><%=dd.getDepttName()%></option>
         		
         		<% 
         		}
         		%>
             </select></td></tr>
	             <tr><td><label for="fdlevel1"><%=i18nentrypointResource.getString("L10N_FOLDER_LEVEL_1", locale)%></label></td><td ><select name="fdlevel1" id="fdlevel1" ><option>---------select---------</option></select></td> </tr>
				 <tr><td><label for="fdlevel2"><%=i18nentrypointResource.getString("L10N_FOLDER_LEVEL_2", locale)%></label></td><td ><select name="fdlevel2" id="fdlevel2" ><option>---------select---------</option></select></td></tr> 
                 <tr><td><label for="fdlevel3"><%=i18nentrypointResource.getString("L10N_FOLDER_LEVEL_3", locale)%></label></td><td ><select name="fdlevel3" id="fdlevel3" ><option>---------select---------</option></select></td></tr>
                 <tr><td><label for="fdlevel4"><%=i18nentrypointResource.getString("L10N_FOLDER_LEVEL_4", locale)%></label></td><td ><select name="fdlevel4" id="fdlevel4" ><option>---------select---------</option></select></td></tr>
                 <tr><td><label for="fdlevel5"><%=i18nentrypointResource.getString("L10N_FOLDER_LEVEL_5", locale)%></label></td><td ><select name="fdlevel5" id="fdlevel5" ><option>---------select---------</option></select></td></tr>   
                 <tr><td><label for="zippackage"><%=i18nentrypointResource.getString("L10N_PACKAGE", locale)%></label></td><td><input type="file" name="zippackage" id="zippackage" accept=".zip"></td></tr> 
                 <tr><td><label for="csumpackage"><%=i18nentrypointResource.getString("L10N_CHECKSUM", locale)%></label></td><td><input type="file" name="csumpackage" id="csumpackage" accept=".csum" ></td></tr>				 
                 
                 <tr><td colspan=2 align=center><input type="submit" id=subbutton name=subbutton value=<%=i18nentrypointResource.getString("L10N_SUBMIT", locale)%>></td></tr>
			
		  </table>
		  <div>
				<input type="hidden" name="folderLevel1" id="folderLevel1" value="">
				<input type="hidden" name="folderLevel2" id="folderLevel2" value="">
				<input type="hidden" name="folderLevel3" id="folderLevel3" value="">
				<input type="hidden" name="folderLevel4" id="folderLevel4" value="">
				<input type="hidden" name="folderLevel5" id="folderLevel5" value="">
				<input type="hidden" name="fivelevelfolderutility" id="fivelevelfolderutility" value="<%=prop.getProperty("FIVE_LEVEL_FOLDER_FLAG")%>">
		  </div>
				 
     </form>
     </div >

</div>
</div>
 --%>

</body>
</html>