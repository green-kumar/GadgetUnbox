<%!
String msg;

%>


<%

msg = (String)request.getAttribute("msg");


%>
<body>
<div class="planbFont"style="width:400px;height:200px;box-shadow: 0px 2px 5px 2px #888888; background: #f2f2f2; margin:auto;margin-top: 150px;">
       
       <div style="width:350px;height:150px;margin:auto;margin-top:30px;">
        <span style="color:#ce0100"><h3>
        <b><p>
        <%=msg%>
        </p>
        <a href="http://localhost/">Click here</a> for login or browsing latest trending gadgets.
        </b></h3>
        </span>
        </div>
        
            </div>
</body>
</html>