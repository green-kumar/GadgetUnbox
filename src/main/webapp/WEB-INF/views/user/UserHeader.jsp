<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
	<div class="logo"
		style="float: left; width: 120px; height: 70px; margin-left: 20px; margin-top: 10; margin-bottom: 10px">
		<a href="http://localhost/"><img
			src="<c:url value="/resources/usertheme/images/planblogo.png"/>"
			width="120" height="70"></img></a>
	</div>
	  <div class="searchHeader" style="float: left; margin-left: 60px; margin-top: 30px;width:700px;">
	
	
	
	
	
	    <div class=searchfilter style="margin-top: 0px;margin-left: 0px;float:left;">
		<select id="searchCatHeader">
			<option selected>All</option>
			<option>Mobile</option>
			<option>Camera</option>
			<option>Tablet</option>
			<option>Laptop</option>
			<option>Headphone</option>
		</select>
	</div>
	<div class="searchfieldheader" style="float:left;">
	  <form name="frontMainSearchForm" id="searchBarFormHeader" action="/search" method="GET">
		<input type="text" name="q" autocomplete="off" class="biginput" id="autocompleteHeader"
			placeholder="search for any gadgets..."/><input type="submit" style="height: 0px; width: 0px; border: none; padding: 0px;" hidefocus="true" />
			 <a href="javascript:void(0)"
						class="myButton" id="searchBarButtonHeader" style="padding: 9px 24px;"><img style="display: none" alt="" height ="20";width="20"; src="<c:url value="/resources/usertheme/images/load.gif" />"/><img dispaly="none" alt="" height ="20";width="20"; src="<c:url value="/resources/usertheme/images/search_icon_white.png" />"/></a>
	 
	  <input style="height: 0px; width: 0px; border: none; padding: 0px;" hidefocus="true" type="hidden" id="categoryHeaderHeader" name="category" value="all">
	</form>
	</div>
	 <div id="autosuggestHeader" style=" border: 1px solid #b3b3b3;position: relative;display: none;width: 400px;left: 122px;opacity: 1;overflow: hidden;  z-index:1000;"></div>
 	
	
    </div> 


</div>
<div class="push" style="margin-bottom: 90px"></div>
