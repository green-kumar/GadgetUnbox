<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class=listHeader>
	listing 1
	<hr
		style="height: 1px; border: none; color: #808080; background-color: #808080;" />
</div>

<%
	for (int i = 1; i < 200; i++) {
		String divclass = "";
		if (i % 2 == 0) {
			divclass = "listitems";
		} else {
			divclass = "listitemseven";
		}
%>
<div class=<%=divclass%>>

	<div class=itemlisting>
		<ul>
			<li><%=i%>.</li>
			<li><a href="#"><img
				src="<c:url value="/resources/usertheme/images/m5.png" />"
				height=50px width=50px /></a></li>
			<li><a href="#">Samsung galaxy s4 android 5.3</a></li>
			<li>4.6/5</li>
			<li><a href="#">Details</a><a href="#">Reviews</a></li>
		</ul>
	</div>

	<%-- 	<table>
      <tr>
      <td><%=i%>.</td>
      <td><img src="<c:url value="/resources/usertheme/images/m5.png" />"  height=50px width=50px/></td>
      <td><a href="#"><b><h3>Samsung galaxy s4 android 5.3</h3></b></a></td>
       <td>rating</td>
      <td>
      
      <button type="submit"><span class="icon-heart"></span> Heart</button>
      </td>
      <td>

<fieldset class="rating<%=i%>">
    <input type="radio" id="star5" name="rating<%=i%>" value="5" /><label class = "full" for="star5" title="Awesome - 5 stars"></label>
    <input type="radio" id="star4half" name="rating<%=i%>" value="4 and a half" /><label class="half" for="star4half" title="Pretty good - 4.5 stars"></label>
    <input type="radio" id="star4" name="rating<%=i%>" value="4" /><label class = "full" for="star4" title="Pretty good - 4 stars"></label>
    <input type="radio" id="star3half" name="rating<%=i%>" value="3 and a half" /><label class="half" for="star3half" title="Meh - 3.5 stars"></label>
    <input type="radio" id="star3" name="rating<%=i%>" value="3" /><label class = "full" for="star3" title="Meh - 3 stars"></label>
    <input type="radio" id="star2half" name="rating<%=i%>" value="2 and a half" /><label class="half" for="star2half" title="Kinda bad - 2.5 stars"></label>
    <input type="radio" id="star2" name="rating<%=i%>" value="2" /><label class = "full" for="star2" title="Kinda bad - 2 stars"></label>
    <input type="radio" id="star1half" name="rating<%=i%>" value="1 and a half" /><label class="half" for="star1half" title="Meh - 1.5 stars"></label>
    <input type="radio" id="star1" name="rating<%=i%>" value="1" /><label class = "full" for="star1" title="Sucks big time - 1 star"></label>
    <input type="radio" id="starhalf" name="rating<%=i%>" value="half" /><label class="half" for="starhalf" title="Sucks big time - 0.5 stars"></label>
</fieldset>








</td>
      <td><a href="#" >Reviews</a></td>
       <td><a href="#" >Details</a></td>
      </tr>
      
      
      </table> --%>

</div>
<%
	}
%>

