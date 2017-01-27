<%@page import="com.planb.care.dao.CareSearchResultDao"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%!
String category;
String orderBy;
String sortBy;
List<CareSearchResultDao> csrdList;
int totalCount;
String invalidMsg;
int pageNo;
int limit;
int startCount;

%>




<%

csrdList = (List<CareSearchResultDao>)request.getAttribute("csrdList");
category = (String)request.getAttribute("category");
orderBy = (String)request.getAttribute("orderBy");
sortBy = (String)request.getAttribute("sortBy");
totalCount = (int)request.getAttribute("totalCount");
invalidMsg = (String)request.getAttribute("invalidMsg");
pageNo = (int)request.getAttribute("page");
limit = (int)request.getAttribute("limit");
startCount = (pageNo*limit);

if(invalidMsg!=null){
	startCount=0;
}
	

%>

<script type="text/javascript">
$(".enginHeader").click(function(){
	 $(this).next("div.enginContent").slideToggle("fast");
});
$('a.detail_view_edit').bind({
	click : function(e) {
		var productId = e.target.id;
		var category = $("#resultCategory").text();
		createProduct(category,productId)
	}
});

</script>
<div class="enginHeader"  style="margin-top:30px">
<span class="profileHeader" > Result for <u><span id="resultCategory"><%=category%></span></u> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; SortBy: 
<%=sortBy %>&nbsp;&nbsp;|&nbsp;&nbsp;  OrderBy : <%=orderBy%>
 <%if(invalidMsg !=null){ %> 
&nbsp;&nbsp;|&nbsp;&nbsp;
(<%=invalidMsg %>)
<%}%>  
</span><span class="profileHeader" style="float:right;margin-right: 30px">Total items: <%=totalCount%></span>

</div>
<div class="enginContent" style="height:auto;">
<%if(csrdList == null || csrdList.size() == 0){ %>
<div style="margin: 20px;margin-bottom:15px"><span class="filterHeader" style="margin-top:20px;margin-bottom: 20px;margin-left:20px">No any product found for your query!!!</span></div>
<%}else{ %>
<table class="planbFont">
				<tr>
				    <td width=20>&nbsp;</td>
					<td width=300><u>Name</u>&nbsp;&nbsp;</td>
					<td><u>Id</u>&nbsp;&nbsp;</td>
					<td width=100><u>Brand</u>&nbsp;&nbsp;</td>
					<td width=100><u>Price</u>&nbsp;&nbsp;</td>
					<td width=150><u>created-on</u></td>
				</tr>
				<%for(int i = 0 ;i <csrdList.size();i++ ){ %>
				<%
				CareSearchResultDao csrd = csrdList.get(i);
				%>
				<tr>
					<td><%=(i+1+startCount)%>.</td>
					<td><%=csrd.getName()%></td>
					<td><%=csrd.getProductId()%></td>
					<td><%=csrd.getBrand()%></td>
					<td><%=csrd.getBestBuyPrice()%></td>
					<td><%=csrd.getCreatedOn()%></td>
					<td><a class="detail_view_edit" href="javascript:void(0)" id="<%=csrd.getProductId()%>">detail/edit</a></td>
					</tr>
				<%} %>
				
				</table>
				<%} %>
</div>