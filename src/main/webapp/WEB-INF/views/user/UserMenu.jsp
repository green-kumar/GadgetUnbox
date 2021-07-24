
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
String baseUrl = "http://localhost";
String mobileBrand[]={"Samsung","Sony","Apple","Google","Xiaomi","Nokia","Window","HP","Panasonic","Lava","Xolo","Videocon","Lenovo","Micromax","Karbonn","Oppo","HTC","Maxx","LG","Intex","Iball","Motorola","Philips","Onida","Spice","Sansui","BlackBerry","Huewei","Vivo","Asus","KYF"};
String mobileOs[]={"Android","IOS","Window","BlackBerry","Symbian","Tizen"};

String cameraBrand[]={"Canon","Nikon","Olympus","Leica","Pentax","Minolta","Sony","Fujifilm","Samsung","Kodak"};
String cameraType[]={"DSLR","360° Camera","PointShoot","Mirrorless","Action","Attachable"};


String tabletBrand[]={"Apple"," Samsung"," Micromax"," Huawei"," iBall"," Nokia"," Blackberry"," Sony"," Lenovo"," LG"," Karbonn"," Lava"};


String laptopBrand[]={"Apple","Lenovo","Alienware","ASUS","HP","Dell","Toshiba","Acer","MSI","Razer"};
String laptopOs[]={"Macintosh","Window","Ubuntu","Linux"};

String headphoneType[]={"In the Ear","Over the Ear","On the Ear"};
String headphoneBrand[]={"Sennheiser","Sony","JBL","Philips","Skullcandy","Beats","Panasonic","YAMAHA","iBall","Zebronics"};


%>
<%!
String userName=null;



%>
<%
userName=null;
userName=(String)request.getSession().getAttribute("userName");
/* if(userName!=null){
	if(userName.length()<12){
		int reqLength=12-(userName.length());
		
		for(int i=0;i<reqLength-1;i++){
				userName+="!!";
		}
	}else{
		userName=userName.substring(0, 12);
	}
} */

%>
<div class="menudiv">
<div class="menu-wrapper">
	<ul class="nav">
		<li><a href='<%=baseUrl+"/latest/smartphone"%>' title = "smartphone">Smartphone</a>
			<div>
				<div class="nav-column">
				
				
				     <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/latest/smartphone"%>' title = "Latest Smartphone"><span class="topsubmenu">Latest Smartphone</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
					</div>
				<div class="nav-column">
				   <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/smartphone/list/top-rated-smartphone" %>' title = "Top rated Smartphone"><span class="topsubmenu">Top rated Smartphone</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
				
				</div>

				<div class="nav-column">
					<h3>Top rated Smartphone by OS</h3>
					<ul>
					
					<%for(int i=0;i<mobileOs.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<mobileOs.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/smartphone/list/top-rated-smartphone?OS="+mobileOs[j]%>' title = "Top rated <%=mobileOs[j]%> Smartphone"><%=mobileOs[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>

				<div class="nav-column">
					<h3>Top rated smartphone by brand</h3>
					<ul>
					<%for(int i=0;i<mobileBrand.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<mobileBrand.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/smartphone/list/top-rated-smartphone?brand="+mobileBrand[j]%>' title = "Top rated <%=mobileBrand[j]%> Smartphone"><%=mobileBrand[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>
			</div></li>
		<li><a href='<%=baseUrl+"/latest/camera"%>' title="Camera">Camera</a>
		
		<div>
				<div class="nav-column">
				     <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/latest/camera"%>' title = "Latest Camera" ><span class="topsubmenu">Latest Camera</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
					</div>
				<div class="nav-column">
				   <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/camera/list/top-rated-camera" %>' title = "Top rated Camera" ><span class="topsubmenu">Top rated Camera</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
				
				</div>

				<div class="nav-column">
					<h3>Top rated camera by type</h3>
					<ul>
					<%for(int i=0;i<cameraType.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<cameraType.length){ %>
					         <td width="260px"><a href='<%=baseUrl+"/camera/list/top-rated-camera?type="+cameraType[j]%>' title = "Top rated <%=cameraType[j]%> Camera"><%=cameraType[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>

				<div class="nav-column">
					<h3>Top rated camera by brand</h3>
					<ul>
					<%for(int i=0;i<cameraBrand.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<cameraBrand.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/camera/list/top-rated-camera?brand="+cameraBrand[j]%>' title = "Top rated <%=cameraBrand[j]%> Camera"><%=cameraBrand[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>
			</div>
		</li>
		<li><a href='<%=baseUrl+"/latest/tablet"%>'>Tablets</a>
		
		
		<div>
				<div class="nav-column">
				     <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/latest/tablet"%>' title = "Latest Tablet" ><span class="topsubmenu">Latest Tablets</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
					</div>
				<div class="nav-column">
				   <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/tablet/list/top-rated-tablet" %>' title = "Top rated Tablet" ><span class="topsubmenu">Top rated Tablets</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
				
				</div>

				

				<div class="nav-column">
					<h3>Top rated Tablets by brand</h3>
					<ul>
					<%for(int i=0;i<tabletBrand.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<tabletBrand.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/tablet/list/top-rated-tablet?brand="+tabletBrand[j]%>' title = "Top rated <%=tabletBrand[j]%> Tablet"><%=tabletBrand[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>
			</div>
		
		
		</li>
		<li><a href='<%=baseUrl+"/latest/laptop"%>' title="Laptop">Laptop</a>
		
		
					<div>
				<div class="nav-column">
				
				
				     <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/latest/laptop"%>' title = "Latest Laptop" ><span class="topsubmenu">Latest Laptop</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
					</div>
				<div class="nav-column">
				   <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/laptop/list/top-rated-laptop" %>' title = "Top rated Laptop" ><span class="topsubmenu">Top rated Laptop</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
				
				</div>

				<div class="nav-column">
					<h3>Top rated Laptop by OS</h3>
					<ul>
					
					<%for(int i=0;i<laptopOs.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<laptopOs.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/laptop/list/top-rated-laptop?OS="+laptopOs[j]%>' title = "Top rated <%=laptopOs[j]%> laptop"><%=laptopOs[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>

				<div class="nav-column">
					<h3>Top rated Laptop by brand</h3>
					<ul>
					<%for(int i=0;i<laptopBrand.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<laptopBrand.length){ %>
					         <td width="100px"><a href='<%=baseUrl+"/laptop/list/top-rated-laptop?brand="+laptopBrand[j]%>' title = "Top rated <%=laptopBrand[j]%> laptop"><%=laptopBrand[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>
			</div>
		
		
		
		
		
		</li>
		<li><a href='<%=baseUrl+"/latest/headphone"%>' title="Headphone">Headphone</a>
		
		
					<div>
				<div class="nav-column">
				
				
				     <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/latest/headphone"%>' title = "Latest Headphone" ><span class="topsubmenu">Latest Headphone</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
					</div>
				<div class="nav-column">
				   <ul>
						<li><table><tr><td width="160px" ><a href='<%=baseUrl+"/headphone/list/top-rated-headphone" %>' title = "Top rated Headphone" ><span class="topsubmenu">Top rated Headphone</span></a></td><td width="30px"></td>
						</tr></table></li>
					</ul>
				
				</div>

				<div class="nav-column">
					<h3>Top rated Haedphone by Type</h3>
					<ul>
					
					<%for(int i=0;i<headphoneType.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<headphoneType.length){ %>
					         <td width="100px"><a href='<%=baseUrl+"/headphone/list/top-rated-headphone?type="+headphoneType[j]%>' title = "Top rated <%=headphoneType[j]%> headphone"><%=headphoneType[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>

				<div class="nav-column">
					<h3>Top rated Headphone by brand</h3>
					<ul>
					<%for(int i=0;i<headphoneBrand.length;){ int j; %>
					     <%if(i%3==0){%>
					        <li><table><tr>
					        <%for(j=i;j<i+3;j++){%>
					        <%if(j<headphoneBrand.length){ %>
					         <td width="80px"><a href='<%=baseUrl+"/headphone/list/top-rated-headphone?brand="+headphoneBrand[j]%>' title = "Top rated <%=headphoneBrand[j]%> headphone"><%=headphoneBrand[j]%></a></td>
					         <%} %>
					         <%} %>
					       </tr></table> </li>
					       <%
					     i=j;  
					     } %>
					    
					     
					<%} %>
						
					</ul>
				</div>
			</div>
		</li>
	</ul>

</div>


<div class="userlogin">
    <ul class="userloginnav">
        <li><%if(userName == null) {%><a href="javascript:void(0)" class="userloginregs">Login/Signup</a><%}else{ %><div style="overflow:hidden;width:136"><a  href="javascript:void(0)"><%=userName%></a></div><%} %>
            <ul class="usersubs">
                <li><a href="/myprofile" class="userloginregs">Profile</a></li>
                <li><a href="/myfavs" class="userloginregs">Favourites</a></li>
                <li><a href="/myreviews" class="userloginregs">Reviews</a></li>
                <li><a href="/myratings" class="userloginregs">Ratings</a></li>
                <li><a href="javascript:void(0)" class="userloginregs" onclick="logout();">Logout</a></li>
            </ul>
        </li>
        
    </ul>
</div>
</div>
