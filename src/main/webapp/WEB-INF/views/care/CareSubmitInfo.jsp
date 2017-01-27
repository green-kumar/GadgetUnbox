<%!
String msg;
String msgDetails;
%>


<%

msg = (String)request.getAttribute("msg");
msgDetails = (String)request.getAttribute("msgDetails");

%>
<body>
<div id="careBody"></div>
<div id="careEditBody"></div>
<div id = "submitInfo" class="planbFont" style="width:700px;height:250px;box-shadow: 0px 2px 5px 2px #888888; background: #f2f2f2; margin:auto;margin-top: 20px;">
       
       <div style="width:650px;height:200px;margin:auto;margin-top:10px;overflow: scroll">
        <span style="color:#ce0100">
        <p><h3>
        <%=msg%></h3>
        </p>
        </span>
        <br>
        <br>
        <span>
        <p>
        <%=msgDetails%>
        </p>
        </span>
        </div>
        
            </div>
</body>
</html>