<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

String category[]={"Mobile","Camera","Tablet","Laptop","Headphone","User"};
String Operation[]={"View","Create","Update","Delete"};


%>
<script type="text/javascript">
/* $(".enginHeader").click(function(){
	 $(this).next("div.enginContent").slideToggle("fast");
}); */
</script>

<div class="enginHeader" id = "enginHeader">
<span class="profileHeader" >Engin</span>
</div>
<div class="enginContent" id="enginContent">
	   <div class="category" style="float:left ;margin-left:40px;margin-top:5px;">
	       <div style="margin: 20px;margin-bottom:15px"><span class="filterHeader" style="margin-top:20px;margin-bottom: 20px;margin-left:20px">Category</span></div>
		
			<div class="filterContainer planbFont" id="categoryFilter">
			<%for(String cat: category){ %>
				<input type="checkbox" class="catCheckbox" name="groupCategory" value="<%=cat.toLowerCase() %>"/> <%=cat %> <br />
				<%} %>
				
			</div>
	
	  </div>
	   <div class="operation" style="float:left ;margin-left:40px;margin-top:5px">
	   <div style="margin: 20px ;margin-bottom:15px"><span class="filterHeader" style="margin-top:20px;margin-bottom: 20px;margin-left:20px">Operation</span></div>
		
			<div class="filterContainer planbFont" id="operationFilter">
			<%for(String ops: Operation){ %>
				<input type="checkbox" class="opsCheckbox" name="groupOperation" value="<%=ops.toLowerCase()%>"/> <%=ops %> <br />
				<%} %>
			</div>
	   
	   </div>
	   <div class="initiator" style="width:600px;float:left ;margin-left:60px;margin-top:5px">
	<div style="margin: 20px ;margin-bottom:15px;margin-left:60px"><span class="filterHeader" style="margin-top:20px;margin-bottom: 20px;margin-left:20px">Criteria</span></div>
	  <div class="filterContainer_ criteria">
			<table class="planbFont">
				<tr>
					<td>Brand</td>
					<td><input id="brand" list="autoCompletbrand" type="text" class="style-1" placeholder="product brand">
					<datalist id="autoCompletbrand">
					</datalist>				
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;Sort by</td>
					<td><select name="sort" id="feature">
						<option value="default" selected="selected">created-on
						</option>
						<option value="alphabetically">alphabetically</option>
						<option value="price">price</option>
						
						
						</select></td>
				
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" list="autoCompletname" id="name" class="style-1" placeholder="product name" >
					<datalist id="autoCompletname">
   
                   </datalist>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;Order by</td>
					<td ><select id="order">
						<option value="-1" selected="selected">descending</option>
						<option value="1">ascending</option>
				</select></td>
		
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
					<td>ID</td>
					<td><input type="text" list="autoCompleteId" id="id" placeholder="product id">
					<datalist id="autoCompleteid"></datalist>
					
					</td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;Limit</td>
					<td ><select id="limit">
						<option value="20" selected="selected">20</option>
						<option value="40">40</option>
						<option value="60" >60</option>
						<option value="80">80</option>
						<option value="100">100</option>
						
				</select></td>
				<td>&nbsp;&nbsp;Page</td>
					<td ><input type="text"  id="page" value = "0" style="width: 50px"></td>
				</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				<tr>
								</tr>
				<tr>
					<td style="line-height: 10px" colspan=2>&nbsp;</td>
				</tr>
				

			</table>
			<table>
			 <tr>
					
					<td><a href="javascript:void(0)"
						class="myButton" id="enginButton"
						style="padding: 8px 20px">Submit</a></td>
						
						
					<td><span><img id="targetCare"
							src="<c:url value="/resources/usertheme/images/load.gif" />"
							alt="..." style="display: none" /></img><span></span></td>
				</tr>
				</table>
</div>
</div>
</div>