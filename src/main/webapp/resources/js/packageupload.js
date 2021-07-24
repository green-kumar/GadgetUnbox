
/*$(document).ready(function(){
 
   
  
	$('#dept').bind('change',function(){
	var firstlevelid=$("#dept option:selected").val();
	if(firstlevelid == "---------select---------")
	{
	 $('#fdlevel1').html("<option>---------select---------</option>");
					  $('#fdlevel2').html("<option>---------select---------</option>");
					   $('#fdlevel3').html("<option>---------select---------</option>");
					    $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
	}
	
	
	    
		var param = 'firstlevelid=' + firstlevelid ;
		$.ajax({
		    type: "POST",
		    url: "../../../web/custom/trisoft/utils/firstlevelfolder.jsp",
		    data: param,
		        success: function(msg){
		        
					 $('#fdlevel1').html("<option>---------select---------</option>");
					  $('#fdlevel2').html("<option>---------select---------</option>");
					   $('#fdlevel3').html("<option>---------select---------</option>");
					    $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
				    $('#fdlevel1').append(msg);
		            
		        }
		    });
		});
		
		$('#fdlevel1').bind('change',function(){
		var parentfolderid=$("#fdlevel1 option:selected").val();
	  if(parentfolderid == "---------select---------")
	{
	 
					  $('#fdlevel2').html("<option>---------select---------</option>");
					   $('#fdlevel3').html("<option>---------select---------</option>");
					    $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
						 $("#folderLevel1").val("");
						  $("#folderLevel2").val("");
						   $("#folderLevel3").val("");
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
	}else{
	  var param = 'parentfolderid=' + parentfolderid ;
		$.ajax({
		    type: "POST",
		    url: "../../../web/custom/trisoft/utils/subfolder.jsp",
		    data: param,
		        success: function(msg){
		          
					  $('#fdlevel2').html("<option>---------select---------</option>");
					   $('#fdlevel3').html("<option>---------select---------</option>");
					    $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
				    $('#fdlevel2').append(msg);
				   
		            
		        }
		    });
			$("#folderLevel1").val($("#fdlevel1 option:selected").text());
			$("#folderLevel2").val("");
						   $("#folderLevel3").val("");
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
			}
	});
	
	$('#fdlevel2').bind('change',function(){
	var parentfolderid=$("#fdlevel2 option:selected").val();
	   if(parentfolderid == "---------select---------")
	{
					   $('#fdlevel3').html("<option>---------select---------</option>");
					    $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
						 $("#folderLevel2").val("");
						   $("#folderLevel3").val("");
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
	}else{
	  var param = 'parentfolderid=' + parentfolderid ;
		$.ajax({
		    type: "POST",
		    url: "../../../web/custom/trisoft/utils/subfolder.jsp",
		    data: param,
		        success: function(msg){
		         
					  $('#fdlevel3').html("<option>---------select---------</option>");
					      $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
				    $('#fdlevel3').append(msg);
				    
		            
		        }
		    });
			$("#folderLevel2").val($("#fdlevel2 option:selected").text());
			
						   $("#folderLevel3").val("");
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
			}
	});
	$('#fdlevel3').bind('change',function(){
	var parentfolderid=$("#fdlevel3 option:selected").val();
	     if(parentfolderid == "---------select---------")
	{
	
				 $('#fdlevel4').html("<option>---------select---------</option>");
						 $('#fdlevel5').html("<option>---------select---------</option>");
						 $("#folderLevel3").val("");
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
	}else{
	  var param = 'parentfolderid=' + parentfolderid ;
		$.ajax({
		    type: "POST",
		    url: "../../../web/custom/trisoft/utils/subfolder.jsp",
		    data: param,
		        success: function(msg){
		          
					  $('#fdlevel4').html("<option>---------select---------</option>");
					 
						 $('#fdlevel5').html("<option>---------select---------</option>");
				    $('#fdlevel4').append(msg);
				    
		            
		        }
		    });
			$("#folderLevel3").val($("#fdlevel3 option:selected").text());
		
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
			}
	});
	
	$('#fdlevel4').bind('change',function(){
	var parentfolderid=$("#fdlevel4 option:selected").val();
	   if(parentfolderid == "---------select---------")
	{
	 
						 $('#fdlevel5').html("<option>---------select---------</option>");
						
						    $("#folderLevel4").val("");
							 $("#folderLevel5").val("");
	}else{
	  var param = 'parentfolderid=' + parentfolderid ;
		$.ajax({
		    type: "POST",
		    url: "../../../web/custom/trisoft/utils/subfolder.jsp",
		    data: param,
		        success: function(msg){
					  $('#fdlevel5').html("<option>---------select---------</option>");
				    $('#fdlevel5').append(msg);
				    
		            
		        }
		    });
			$("#folderLevel4").val($("#fdlevel4 option:selected").text());
			
							 $("#folderLevel5").val("");
			}
	});
	$('#fdlevel5').bind('change',function(){
		$("#folderLevel5").val($("#fdlevel5 option:selected").text());
	});
	
});*/
function validate(){
var fivelevelfolderutility=document.getElementById('fivelevelfolderutility').value;

var j=0;

var deparments = document.getElementById('dept');
	for(var i=0;i<deparments.options.length;i++){
		    if(deparments.options[i].selected){
		          j++;
			     }
			   }
			   	
var fdlevel1=document.getElementById('fdlevel1');
var fdlength1=0;
var fdselected1="";
for(var i=0;i<fdlevel1.options.length;i++){
		      fdlength1++;
			  if(fdlevel1.options[i].selected){
		          fdselected1=fdlevel1.options[i].text;
			     }
			  }
				
var fdlevel2=document.getElementById('fdlevel2');
var fdlength2=0;
var fdselected2="";
for(var i=0;i<fdlevel2.options.length;i++){
		      fdlength2++;
			  if(fdlevel2.options[i].selected){
		          fdselected2=fdlevel2.options[i].text;
			     }
			  }

			     			   

				
var fdlevel3=document.getElementById('fdlevel3');
var fdlength3=0;
var fdselected3="";
for(var i=0;i<fdlevel3.options.length;i++){
		      fdlength3++;
			  if(fdlevel3.options[i].selected){
		          fdselected3=fdlevel3.options[i].text;
			     }
			    }

var fdlevel4=document.getElementById('fdlevel4');
var fdlength4=0;
var fdselected4="";
for(var i=0;i<fdlevel4.options.length;i++){
		      fdlength4++;
			  if(fdlevel4.options[i].selected){
		          fdselected4=fdlevel4.options[i].text;
			     }
			    }	
				
var fdlevel5=document.getElementById('fdlevel5');
var fdlength5=0;
var fdselected5="";
for(var i=0;i<fdlevel5.options.length;i++){
		      fdlength5++;
			  if(fdlevel5.options[i].selected){
		          fdselected5=fdlevel5.options[i].text;
			     }
			    }					

				
	   

 var uploadedzipfile = document.getElementById("zippackage").value; 
  var extension = uploadedzipfile.substr(uploadedzipfile.lastIndexOf('.') + 1).toLowerCase();
  var uploadedzipfile1=document.getElementById("csumpackage").value;
  var extension1 = uploadedzipfile1.substr(uploadedzipfile1.lastIndexOf('.') + 1).toLowerCase();
  
   var fname=uploadedzipfile.substr(0,uploadedzipfile.lastIndexOf('.')).toLowerCase();
  var fname1 = uploadedzipfile1.substr(0,uploadedzipfile1.lastIndexOf('.')).toLowerCase();
  
  
 
  
  if(j == 0){
        alert('Select atleast one department');
         return false;
	}else if(fdlength1 > 1 && fdselected1 == "---------select---------" )
	{
	       alert('sub folders exist in lower level folder ,please select folder from folder level 1');
	       return false;
	}else if(fdlength2 > 1 && fdselected2 == "---------select---------"  )
	     { 
		   alert('sub folders exist in lower level folder ,please select folder from folder level 2');
	      return false;
	}else if(fdlength3 > 1 && fdselected3 == "---------select---------"  )
	     { 
		    alert('sub folders exist in lower level folder ,please select folder from folder level 3');
	        return false;
		  
	   
	}else if(fdlength4 > 1 && fdselected4 == "---------select---------"  )
	     { 
		   alert('sub folders exist in lower level folder ,please select folder from folder level 4');
	       return false;
		  
	}else if(fdlength5 > 1 && fdselected5 == "---------select---------"  )
	     { 
		   alert('sub folders exist in lower level folder ,please select folder from folder level 5');
	      return false;
		  
	}else if(uploadedzipfile == "")
	   {
	    alert('please upload package');
	     return false;
	}else if(uploadedzipfile1 == "")
	  {
	  alert('please upload csum file');
	       return false;
	  
	}else if(extension != "zip")
       { 
       alert('Invalid file Format at package file uplpoader. Only zip file are allowed.');
      //document.getElementById("upload").value=" ";
       return false;

	}else if(extension1 != "csum"){
          alert('Invalid file Format at checksum file uploader. Only checksum file are allowed.');
           //document.getElementById("upload").value=" ";
          return false;
     }else if(fname != fname1)
      {    
            alert('package and checksum files name should be same!!');
           return false;
    }else if(fivelevelfolderutility == "Y" &&  (fdlength2 == 1 || fdlength3 == 1 || fdlength4 == 1 || fdlength5 == 1)){
	 
	  alert('It is mandatory to select five level folders for package upload');
	 return false;
	 }else{

      return true;
    } 


}
function validateId(){
var id=document.getElementById("jobid").value;
//alert(id);
var bool=isNaN(id);
if(bool == true)
{
alert('enter correct Job Id');
return false;
  }
}
function validatexml(){
 var uploadedxmlfile = document.getElementById("xmlpackage").value; 
 var xmlextension=uploadedxmlfile.substr(uploadedxmlfile.lastIndexOf('.') + 1).toLowerCase();
  if(xmlextension != "xml"){
	 
	 alert('Invalid file Format at xml file uploader. Only xml file are allowed.');
	    return false;
	
	}
}

function onBlur(element) {
    if (element.value == '') {
        element.value = element.defaultValue;
    }
}
function onFocus(element) {
    if (element.value == element.defaultValue) {
        element.value = '';
    }
}

	