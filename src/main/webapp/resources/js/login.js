$(document).ready(function() {
	$('.btn-info').bind({
		click : function() {
			showProductViewDialog(this.id);

		}

	}),
	$('.btn-danger').bind({
		click : function() {
			if (confirm('Are you sure you want to delete this from the database?')) {
				$("#tr_"+this.id).hide();
				deleteProductMobile(this.id);
			} else {
			    // Do nothing!
			}
			

		}

	})

});



function showProductViewDialog(id) {
	$("#emailoverlay_"+id).show();
	$("#emaildialog_"+id).fadeIn(300);
	$("#emailoverlay_"+id).click(function() {
		hideProductViewDialog(id);
	});
}
function hideProductViewDialog(id) {
	$("#emailoverlay_"+id).hide();
	$("#emaildialog_"+id).fadeOut(300);
}

$(document).ready(function() {
	$(".emailbtnClose").click(function() {
		hideProductViewDialog(this.id);
	})
	
});



//******Activating admin login box**************//
$(document).ready(function() {
	 var temp=$('#loginflag').val();
	$('a.planb').click(function(e) {
		if(temp == 'false'){
			alert('hello');
			showEmailDialog();
			e.preventDefault();
		}
           
 
	})

})
function showEmailDialog() {
	$("#admin_emailoverlay").show();
	$("#admin_emaildialog").fadeIn(300);
	$("#admin_emailoverlay").click(function() {
		hideEmailDialog();
	});
}
function hideEmailDialog() {
	$("#admin_emailoverlay").hide();
	$("#admin_emaildialog").fadeOut(300);
}

$(document).ready(function() {
	$(".testclose").click(function() {
		hideEmailDialog();
	})
	
});
/*
$(document).ready(function(){
    $('.logintext').click(function(){
    
   $("#loginpannel").css("display","block");
             $("#registrationpannel").css("display","none");
    });
    
    $('.signuptext').click(function(){
    
   $("#loginpannel").css("display","none");
             $("#registrationpannel").css("display","block");
    });


});

function emailValidator(loginMail){
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\ ".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA -Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(loginemail);

}*/
function deleteProductMobile(id){
	var productId=id;
	alert(productId)
	var category = $("#pageCategory").text();
	alert(category);
	jQuery.ajax({
        url: "/review-aggregator-electronics/"+category+"/deleteProduct/"+productId,
        success: function (result) {
            if (result == 'success') alert("product deleted successfully");
           alert( $('#productCount').val());
        },
        error : function() {
			
        	alert("something went wrong!!! Try again");
		},
        async: false
    });
	
}

function validateLoginForm(){
	
	
   var loginName=$('#adminName').val();
	var loginEmail=$("#loginemail").val();
	var loginPassword=$("#loginpassword").val();
	
	
/*	if(!emailValidator(loginEmail))
		return false;*/
	
	var loginData = {
			name:loginName,
            email: loginEmail,
            password:loginPassword
                  };
	alert(JSON.stringify(loginData));
        $('#target').html('sending..');

        $.ajax({
            url: "/review-aggregator-electronics/adminloginajax",
            type: 'post',
            contentType: 'application/json',
            mimeType: 'application/json',
        	  
            success: function (data) {
                $('#target').html("login status:"+data);
                if(data==null){
                	 $('#target').html("Something went wrong!!! Try again") 	
                }else if(data.errorMsg  == 'success'){
                	 window.location.reload();
                 }else{
                	 $('#target').html(data.errorMsg) 
                 }
                
               
            },
            error : function() {
    			
    			 $('#target').html("something went wrong!!! Try again");
    		},
           
    		data: JSON.stringify(loginData)
        });
}
/*function validateRegistrationForm(){
	

	var name=$("#signupName").val();
	var regiserEmail=$("#signupEmail").val();
	var password=$("#signupPassword").val();
	
	
	
	var registrationData = {
			name:name,
			email:regiserEmail,
			password:password
                  };
    
    alert(JSON.stringify(registrationData));
   

        $('#target1').html('sending..');
        alert('sending....');

        $.ajax({
            url: "/PlanB/register",
            type: 'get',
            dataType: 'json',
            success: function (data) {
            	alert('*******response from server****'+data.register);
                $('#target').html(data.register);
            },
            error : function() {
    			
    			 $('#target1').html("Error in retrieving portal details");
    		},
    		
             data: {registrationData:JSON.stringify(registrationData)}
        });
	
	
	
	
}*/


