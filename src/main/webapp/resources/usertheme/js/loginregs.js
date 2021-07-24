$(document).ready(function() {
	var loggedin = 'false';
	$(".logintext a").css("color", "#e60000");
	loggedin = $('#loginflag').val();
	$('a.userloginregs').bind({
		click : function(e) {
			if (loggedin == 'false') {
				showEmailDialog();
				e.preventDefault();
			}

		}

	});
	$('a#editProfile').bind({
		click : function(e) {
			$('a#editProfile').css("display","none");
		 	editProfile();
		 	$('a#cancelEditProfile').css("display","block");
		 	$('a#saveProfile').css("display","block");
		 	$("#profileerrorul").html("");
		 	$("#profileerrorpannel")
			.css("border", "0px");
		}

	});
	
	$('a#cancelEditProfile').bind({
		click : function(e) {
			$("a#editProfile").css("display","block");
			cancelEditProfile($("#userName").val(),$("#userEmail").val(),$("#userDOB").val(),$("#userGender").val());
		 	$('a#cancelEditProfile').css("display","none");
		 	$('a#saveProfile').css("display","none");
		 	$("#profileerrorul").html("");
		 	$("#profileerrorpannel")
			.css("border", "0px");
		 	document.getElementById("updateWait").getElementsByTagName("img")[0].style.display = "none";
		}

	});
	$('a#saveProfile').bind({
		click : function(e) {
			document.getElementById("updateWait").getElementsByTagName("img")[0].style.display = "block";
			saveProfile();
			document.getElementById("updateWait").getElementsByTagName("img")[0].style.display = "none";
			
		}

	});
	
	$('a.editreviewbutton').bind({
		click : function(e) {
			
			var identifier=this.getAttribute('data-identifier');
			$('a#editReview'+identifier).css("display","none");
		 	editReview(identifier);
		 	$('a#cancelEditReview'+identifier).css("display","block");
		 	$('a#saveReview'+identifier).css("display","block");
		 	$("#reviewerrorul"+identifier).html("");
		 	$("#reviewerrorpannel"+identifier)
			.css("border", "0px");
		}

	});
	
	$('a.canceleditreviewbutton').bind({
		click : function(e) {
			
			var identifier=this.getAttribute('data-identifier');
			$("a#editReview"+identifier).css("display","block");
			cancelEditReview(identifier);
		 	$('a#cancelEditReview'+identifier).css("display","none");
		 	$('a#saveReview'+identifier).css("display","none");
		 	$("#reviewerrorul+identifier").html("");
		 	$("#reviewerrorpannel+identifier")
			.css("border", "0px");
		 	document.getElementById("updateReviewWait"+identifier).getElementsByTagName("img")[0].style.display = "none";
		}

	});
	
	$('a.savereviewbutton').bind({
		click : function(e) {
			var identifier=this.getAttribute('data-identifier');
			var productId=this.getAttribute('data-productId');
			var productCategory=this.getAttribute('data-category');
			var reviewId=this.getAttribute('data-reviewId');
			document.getElementById("updateReviewWait"+identifier).getElementsByTagName("img")[0].style.display = "block";
			saveReview(identifier,productId,productCategory,reviewId);
			document.getElementById("updateReviewWait"+identifier).getElementsByTagName("img")[0].style.display = "none";
			
		}

	});
	
	$('.profilefavicon').bind({
		click : function(e) {
			
			var productId=$(this).attr('data-productId');
			var productName=$(this).attr('data-productName');
			var identifier=$(this).attr('data-identifier');
			var category=$(this).attr('data-category');
		
			showpasswordBoxDialog();
		
			
			
			if ($(this).hasClass('profileheartunselected')) {
				$("#favMessage").html("Are you want to add <span id='confirmRating' data-confirmProductId='"+productId+"' data-confirmCategory='"+category+"'   data-confirmIdentifier='"+identifier+"'  data-confirmCurrentClass='profileheartunselected' ><b>"+productName+"</b></span> into your fav list ?");
				
				    } else {
		    	
		    	$("#favMessage").html("Are you want to remove <span id='confirmFav' data-confirmProductId='"+productId+"' data-confirmCategory='"+category+"'   data-confirmIdentifier='"+identifier+"'  data-confirmCurrentClass='profileheartselected' ><b>"+productName+"</b></span> from your fav list ?");
		    	
		    }

			
		}

	});
	
	
	$("#yesUpdateFav").click(function() {
			
		var productId=$('#confirmFav').attr("data-confirmProductId");
		var category=$('#confirmFav').attr("data-confirmCategory");
		var identifier=$('#confirmFav').attr("data-confirmIdentifier");
		var currentClass=$('#confirmFav').attr("data-confirmCurrentClass");
		hidepasswordBoxDialog();
		
		if(currentClass == 'profileheartunselected'){
			$("#favIcon"+identifier).removeClass('profileheartunselected');
	    	$("#favIcon"+identifier).addClass('profileheartselected');
	    	favProduct(productId,category,1);
		}else{
			$("#favIcon"+identifier).removeClass('profileheartselected');
	    	$("#favIcon"+identifier).addClass('profileheartunselected');
	    	favProduct(productId,category,0);
		}
    	
	});
	
	$("#noUpdateFav").click(function(){
		hidepasswordBoxDialog();
	});
	
	
	/*$('.rating span.star').on('click',function(e){
		var productName=this.parentNode.parentNode.getAttribute('data-productName');
		var productId=this.parentNode.parentNode.getAttribute('data-productId');
		var reviewId=this.parentNode.parentNode.getAttribute('data-reviewId');
		var total=$(this).parent().children().length;
	    var identifier=this.parentNode.getAttribute('data-identifier');
	    var clickedIndex=$(this).index();
			showpasswordBoxDialog();
			$("#ratingMessage").html("Are you want to update the rating of <span id='confirmRating' data-confirmProductId='"+productId+"' data-confirmReviewId='"+reviewId+"' data-confirmIndex='"+clickedIndex+
					"' data-confirmIdentifier='"+identifier+"' ><b>"+productName+"</b></span>");
	
	
	
    });*/
	
	$("#yesUpdateRate").click(function() {
		var productId=$('#confirmRating').attr("data-confirmProductId");
		var reviewId=$('#confirmRating').attr("data-confirmReviewId");
		var identifier=$('#confirmRating').attr("data-confirmIdentifier");
		var clickedIndex=$('#confirmRating').attr("data-confirmIndex");
		hidepasswordBoxDialog();
		$('#ratingMessage').html("");
		var starCount=5-clickedIndex;
		$('.rating span.profile'+identifier).removeClass('filled');
    	for(var i=clickedIndex;i<5;i++){
        	$('.rating span.profile'+identifier).eq(i).addClass('filled');
    	}
    	
    	updateUserRating(reviewId,productId,starCount);
    	
	});
	
	$("#noUpdateRate").click(function(){
		hidepasswordBoxDialog();
	});
	

});

function updateUserRating(reviewId,productId,clickedIndex){
	jQuery.ajax({
		url : "/updateRating/"+reviewId+"/"+productId+"/"+clickedIndex,
		success : function() {
		},
		error : function() {

		},
	});
}


function editReview(identifier){

	var reviewHead="#reviewHead"+identifier
	var prosId="#reviewPros"+identifier;
	var consId="#reviewCons"+identifier;
	var rateId="#reviewRating"+identifier;

	$(reviewHead).attr('contenteditable','true');
	$(prosId).attr('contenteditable','true');
	$(consId).attr('contenteditable','true');
	$(rateId).attr('contenteditable','true');
	
	$(reviewHead).css("border", "1px solid #e60000");
	$(reviewHead).css("box-shadow","0 0 4px #e60000");
	
	$(prosId).css("border", "1px solid #e60000");
	$(prosId).css("box-shadow", "0 0 4px #e60000");
	$(consId).css("border", "1px solid #e60000");
	$(consId).css("box-shadow", "0 0 4px #e60000");
	$(rateId).css("border", "1px solid #e60000");
	$(rateId).css("box-shadow", "0 0 4px #e60000");
	$(reviewHead).focus();
	
	
	
}

function cancelEditReview(identifier){
	var reviewHead="#reviewHead"+identifier;
	var prosId="#reviewPros"+identifier;
	var consId="#reviewCons"+identifier;
	var rateId="#reviewRating"+identifier;
	$(reviewHead).attr('contenteditable','false');
	$(prosId).attr('contenteditable','false');
	$(prosId).attr('contenteditable','false');
	$(consId).attr('contenteditable','false');
	$(rateId).attr('contenteditable','false');
	
	$(reviewHead).css("border", "none");
	$(reviewHead).css("box-shadow","none");
	$(prosId).css("border", "none");
	$(prosId).css("box-shadow", "none");
	$(consId).css("border", "none");
	$(consId).css("box-shadow", "none");
	$(rateId).css("border", "none");
	$(rateId).css("box-shadow", "none");
}


function editProfile(){
	 var divHtmlPn = $("#profileName").html();
	    var editableText = $("<input type='text' id='profileName'/>");
	    editableText.val(divHtmlPn);
	    $("#profileName").replaceWith(editableText);
	    editableText.focus();
	    // setup the blur event for this new textarea
	   // editableText.blur(editableTextBlurred);	
	
	    var divHtmlpe = $("#profileEmail").html();
	    var editableTextEmail = $("<input type='text' id='profileEmail'/>");
	    editableTextEmail.val(divHtmlpe);
	    $("#profileEmail").replaceWith(editableTextEmail);
	    
	    var divHtmldob = $("#profileDOB").html();
	    var editableTextdob = $("<input id='profileDOB'/>");
	    editableTextdob.val(divHtmldob);
	    $("#profileDOB").replaceWith(editableTextdob);
	    $("#profileDOB").datepicker({
	    	 min: 0,
	        changeMonth: true, 
	        changeYear: true, 
	        dateFormat: 'd MM, yy',
	        maxDate: 'today',
	        onSelect: function(dateText) {
	            $sD = new Date(dateText);
	            $("input#DateTo").datepicker();
	        }
	    });
	
	
	
}

function saveReview(identifier,productId,category,reviewId){
	var html = "";
	var reviewHeadline = $("#reviewHead"+identifier).text();
	var reviewPros = $("#reviewPros"+identifier).text();
	var reviewCons = $("#reviewCons"+identifier).text();
	var reviewRating = $("#reviewRating"+identifier).text();
	if (reviewHeadline == "") {
		html = "<li>Review headline can't be blank.</li>"
	}
	if (reviewRating == "") {
		html += "<li>Review rating can't be blank.</li>"
	}
	if (reviewRating != "" && isNaN(reviewRating)) {
		html += "<li>Rating must be numeric value.</li>"
	}
	if (reviewRating != "" && !isNaN(reviewRating)
			&& (reviewRating > 5 || reviewRating < 0)) {
		html += "<li>Rating must be between 0 to 5.</li>"
	}

	if (!(html == "")) {
		$("#reviewerrorul"+identifier).html(html);
		$("#reviewerrorpannel"+identifier).css("border", "2px solid #e60000");
		return false;
	}
	
	var reviewData = {
			reviewHeadline : reviewHeadline,
			reviewPros : reviewPros,
			reviewCons : reviewCons,
			rating : reviewRating,
			forProductId : productId,
			category : category,
			reviewId : reviewId
		};

		// alert(JSON.stringify(reviewData));
	$("a#saveReview"+identifier).unbind('click', false);
	
	jQuery.ajax({
		url : "/updateReview",
		type : 'post',
		dataType : 'text',
		contentType : 'application/json',
		mimeType : 'application/json',
		async :false,
		success : function(data) {
			$("#reviewerrorul"+identifier).html(
					"<li>-" + data + "</li>");
			$("#reviewerrorpannel"+identifier).css("border", "2px solid #e60000");
			$("a#saveReview"+identifier).unbind('click', false);
			
			$('a#editReview'+identifier).css("display","block");
		 	$('a#cancelEditReview'+identifier).css("display","none");
		 	$('a#saveReview'+identifier).css("display","none");
			
		 	
		 	cancelEditReview(identifier);
		},
		error : function() {
			
			$("a#saveReview"+identifier).unbind('click', false);
			$("#profileerrorul").html(
					"<li>-" + "Something went wrong.Try again!!!" + "</li>");
			$("#profileerrorpannel")
					.css("border", "2px solid #e60000");
			$('a#editReview'+identifier).css("display","block");
		 	$('a#cancelEditReview'+identifier).css("display","none");
		 	$('a#saveReview'+identifier).css("display","none");
			
		 	
		 	cancelEditReview(identifier);
		},

		data : JSON.stringify(reviewData)
	});
}

function cancelEditProfile(userName,userEmail,userDOB,userGender){
	
	var divHtmlPn = $("#profileName").html();
    var editableText = $("<td id='profileName'></td>");
    $("#profileName").replaceWith(editableText);
    $("#profileName").text(userName);

    var divHtmlpe = $("#profileEmail").html();
    var editableTextEmail = $("<td id='profileEmail'></td>");
    $("#profileEmail").replaceWith(editableTextEmail);
    $("#profileEmail").text(userEmail);
    
    var divHtmldob = $("#profileDOB").html();
    var editableTextdob = $("<td id='profileDOB'></td>>");
    $("#profileDOB").replaceWith(editableTextdob);
    $("#profileDOB").text(userDOB);
    
    var gender=userGender;

	$("#profileGenderMale").prop("checked", false);
	$("#profileGenderFemale").prop("checked", false);
	$("#profileGenderOther").prop("checked", false);
    
    if(gender != ""){
    	if(gender == "male"){
         	$("#profileGenderMale").prop("checked", true);
    }else if(gender == "female"){
       	   $("#profileGenderFemale").prop("checked", true);
    }else{
    	$("#profileGenderOther").prop("checked",true);
    }
    }
}

function saveProfile(){
	
	var isProfileEdited=false;
	
	var html = "";
	var updatedName = $("#profileName").val();
	var updatedEmail = $("#profileEmail").val();
	var updatedDOB = $("#profileDOB").val();
	var updatedGender = $('input[name=gender]:checked').val();
	
	if (updatedName == "") {
		html = "<li>-name field can't be blank.</li>"
	}

	if (updatedEmail == "") {
		html += "<li>-email field can't be blank.</li>";
	}
	if (updatedDOB == "") {
		html += "<li>-DOB field can't be blank.</li>"
	}
	
	if (updatedEmail != "" && !emailValidator(updatedEmail)) {
		html += "<li>-email format is not valid.</li>"
	}
	if (!(html == "")) {
		$("#profileerrorul").html(html);
		$("#profileerrorpannel").css("border", "2px solid #e60000");
		return false;
	}
	
	if(updatedName != $("#userName").val()){
		isProfileEdited=true;
	}
	if(!isProfileEdited && updatedEmail != $("#userEmail").val()){
		isProfileEdited=true;
	}
	if(!isProfileEdited && updatedDOB != $("#userDOB").val()){
		isProfileEdited=true;
	}
	
	if(!isProfileEdited && updatedGender != $("#userGender").val()){
		isProfileEdited=true;
	}
	
	if(!isProfileEdited){
		$("#profileerrorul").html("<li>Your profile have not beed updated.</li>");
		$("#profileerrorpannel").css("border", "2px solid #e60000");
		return false;
	}
	
	
	
	
	//$("#updateWait").getElementsByTagName("img")[0].style.display = "block";
	
	$('#saveProfile').bind('click', false);
	//disable save button
	
	
	var updatedProfileData = {
		updatedName : updatedName,
		updatedEmail : updatedEmail,
		updatedDOB : updatedDOB,
		updatedGender : updatedGender

	};

	alert(JSON.stringify(updatedProfileData));

	$
			.ajax({
				url : "/accountUpdate",
				type : 'post',
				dataType : 'text',
				contentType : 'application/json',
				async : false,
				success : function(data) {
					if (data != null) {
						 
							$("#profileerrorul").html(
									"<li>-" + data + "</li>");
							$("#profileerrorpannel")
									.css("border", "2px solid #e60000");
							$('#saveprofile').unbind('click', false);
							$("a#editProfile").css("display","block");
							$('a#cancelEditProfile').css("display","none");
						 	$('a#saveProfile').css("display","none");
						 	
						 	cancelEditProfile(updatedName,updatedEmail,updatedDOB,updatedGender);

						}

					

				},
				error : function() {
					$('#saveProfile').unbind('click', false);
					$("#profileerrorul").html(
							"<li>-" + "Something went wrong.Try again!!!" + "</li>");
					$("#profileerrorpannel")
							.css("border", "2px solid #e60000");
				},

				data : JSON.stringify(updatedProfileData)
			});


	
	
	
	
	
	
	
	
	
	
}

/*jQuery(document).ajaxStart(function(){
 document.getElementById("targetregs").getElementsByTagName("img")[0].style.display="block";
 document.getElementById("target").getElementsByTagName("img")[0].style.display="block";
 });
 jQuery(document).ajaxStop(function(){
 document.getElementById("targetregs").getElementsByTagName("img")[0].style.display="none";
 document.getElementById("target").getElementsByTagName("img")[0].style.display="none";
 });*/

function showEmailDialog() {
	$("#errorul").html("");
	$(".errorspan").html("");
	$("#emailoverlay").show();
	$("#emaildialog").fadeIn(300);
	/* **case when registered successfully and again click on login/sighup lik**  */
	$(".logintext a").css("color", "#e60000");
	$(".signuptext a").css("color", "#000000");
	$("#loginpannel").css("display", "block");
	$("#registrationpannel").css("display", "none");
	/*  end  */
	$("#emailoverlay").click(function() {
		hideEmailDialog();
	});
}
function hideEmailDialog() {
	$("#emailoverlay").hide();
	$("#emaildialog").fadeOut(300);
}

$(document).ready(function() {
	$("#emailbtnClose").click(function() {
		hideEmailDialog();
	})

});
$(document).ready(function() {
	$('.logintext a').click(function() {
		$(".logintext a").css("color", "#e60000");
		$("#loginpannel").css("display", "block");
		$("#registrationpannel").css("display", "none");
		$(".signuptext a").css("color", "#000000");
	});

	$('.signuptext a').click(function() {
		$(".signuptext a").css("color", "#e60000");
		$("#loginpannel").css("display", "none");
		$("#registrationpannel").css("display", "block");
		$(".logintext a").css("color", "#000000");
	});

});

function emailValidator(loginMail) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\ ".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA -Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(loginMail);

}
function validateLoginForm() {
	var html = "";
	var loginEmail = $("#loginemail").val();
	var loginPassword = $("#loginpassword").val();

	if (loginEmail == "") {
		html = "<li>-email field can't be blank.</li>";
	}
	if (loginPassword == "") {
		html += "<li>-password field can't be blank.</li>"
	}
	if (loginEmail != "" && !emailValidator(loginEmail)) {
		html += "<li>-email format is not valid.</li>"
	}
	if (!(html == "")) {
		$("#errorul").html(html);
		$("#errorpannel").css("border", "1px solid #e60000");
		return false;
	}
	var loginData = {
		email : loginEmail,
		password : loginPassword
	};
	$("#errorul").html(html);
	//document.getElementById("target").getElementsByTagName("img")[0].style.display="block";
	$('#loginbutton').bind('click', false);
	//disable login button

	$
			.ajax({
				url : "/accountLogin",
				type : 'post',
				dataType : 'json',
				async : false,
				contentType : 'application/json',
				mimeType : 'application/json',
				success : function(data) {
					if (data != null) {
						if (data.errorMsg == 'success') {
							//return true;
							window.location.reload();
						} else if (data.errorMsg == 'email not verified!!!') {

							$("#loginpannel").css("display", "none");
							$("#errorpannel").css("margin", "30px");
							$("#errorpannel")
									.css("border", "1px solid #e60000");
							$("#errorul")
									.html(
											"<li>"
													+ data.errorMsg
													+ "</li><li>Click below to resend account verification link.</li><li><a href='javascript:void(0)' onclick='resendLink();'>Resend verification link</a></li>");
							$('#loginbutton').unbind('click', false);

						} else {
							$("#errorul").html(
									"<li>-" + data.errorMsg + "</li>");
							$("#errorpannel")
									.css("border", "1px solid #e60000");
							$('#loginbutton').unbind('click', false);

						}

					}

				},
				error : function() {

					$('#target').html("something went wrong!!!Try again.");
					$('#loginbutton').unbind('click', false);
				},

				data : JSON.stringify(loginData)
			});
}
function validateRegistrationForm() {

	var html = "";
	var name = $("#signupName").val();
	var regiserEmail = $("#signupEmail").val();
	var password = $("#signupPassword").val();
	var rePassword = $("#signupPasswordAgain").val();

	if (name == "") {
		html = "<li>-name field can't be blank.</li>"
	}

	if (regiserEmail == "") {
		html += "<li>-email field can't be blank.</li>";
	}
	if (password == "") {
		html += "<li>-password field can't be blank.</li>"
	}
	if (password != "" && password != rePassword) {
		html += "<li>-password doesn't match.</li>"
	}
	if (regiserEmail != "" && !emailValidator(regiserEmail)) {
		html += "<li>-email format is not valid.</li>"
	}
	if (!(html == "")) {
		$("#errorul").html(html);
		$("#errorpannel").css("border", "1px solid #e60000");
		return false;
	}
	$("#errorul").html(html);
	document.getElementById("targetregs").getElementsByTagName("img")[0].style.display = "block";
	$('#regsbutton').bind('click', false);
	//disable registration button
	var registrationData = {
		name : name,
		email : regiserEmail,
		password : password
	};


	$
			.ajax({
				url : "/accountRegister",
				type : 'post',
				dataType : 'json',
				contentType : 'application/json',
				mimeType : 'application/json',
				async : false,
				success : function(data) {
					if (data != null) {
						if (data.errorMsg == 'success') {
							$("#registrationpannel").css("display", "none");
							var regSucc = "<li>Thanks for registration!!!</li><li> A Mail have been sent to your registered email.Please click on link inside mail to activate your account.</li>";
							$("#errorpannel").css("margin", "30px");
							$("#errorul").html(regSucc);
						} else {
							$("#errorul").html(
									"<li>-" + data.errorMsg + "</li>");
							$("#errorpannel")
									.css("border", "1px solid #e60000");
							$('#regsbutton').unbind('click', false);

						}

					}

				},
				error : function() {
					$('#regsbutton').unbind('click', false);
					$('#targetregs').html("Something went wrong.Try again!!!");
				},

				data : JSON.stringify(registrationData)
			});

}
function resendLink() {
	var loginEmail = $("#loginemail").val();
	alert("hello");
	jQuery
			.ajax({
				url : "/resendActivationLink?resendEmail=" + loginEmail,
				success : function(result) {
					if (result == 'success') {
						$(".errorspan").html("");
						var regSucc = "<li> A Mail have been sent to your registered email.Please click on link inside mail to activate your account.</li>";
						$("#errorpannel").css("margin", "30px");
						$("#errorul").html(regSucc);
					}
					;
				},
				error : function() {

					$(".errorspan").html("something went wrong!!! Try again.");
				},
				async : false
			});
}
function recoverPassword() {
	var srcvar = "<c:url value='/resources/usertheme/images/load.gif' />";
	$("#errorul").html("");
	$("#loginpannel").css("display", "none");
	var html = "<table><tr><tr><td bgcolor='#ffffff' style='line-height: 20px' colspan=2><b>Recover password:</b></td></tr></tr><tr><td bgcolor='#ffffff' style='line-height: 30px' colspan=2>Enter your registered email.</td></tr><tr><td width='80px'>Email:</td><td><input id='registeredemail' name='registeredemail' type='text' style='width: 175px;' /></td></tr><tr><td bgcolor='#ffffff' style='line-height: 20px' colspan=2>&nbsp;</td></tr><tr><td colspan=2 align=center><a href='#' class='myButton' id='passwordRecoverButton' style='padding:8px 24px;' onclick='sendRecoveredPassword();'>SEND EMAIL</a></td><td><span id='recoverPasswordTarget'><img src='' alt=''  style='display: block'/></img></span></td></tr><table>"
	$("#errorpannel").css("margin", "30px");
	$("#errorul").html(html);
}
function sendRecoveredPassword() {
	var registeredemail = $("#registeredemail").val();
	var html = "";
	if (registeredemail == "") {
		html += "<li>email field can't be blank.</li>";
	}
	if (registeredemail != "" && !emailValidator(registeredemail)) {
		html += "<li>email format is not valid.</li>"
	}
	if (!(html == "")) {
		$(".errorspan").html(html);
		return false;
	}

	jQuery
			.ajax({
				url : "/recoverPassword?recoverPasswordEmail="
						+ registeredemail,
				success : function(result) {
					if (result == 'success') {
						var regSucc = "<li>Your password have been sent to your registered email.</li>";
						$("#errorpannel").css("margin", "30px");
						$("#errorul").html(regSucc);
					} else {
						$(".errorspan").html(
								"Sorry.Account with this email not exist!!!");
					}
				},
				error : function() {
					$(".errorspan").html("something went wrong!!! Try again.");

				},
				async : false
			});
}
function logout() {
	var loggedin = 'false';
	loggedin = $('#loginflag').val();
	if(loggedin == 'false')
		return;
	jQuery.ajax({
		url : "/logout",
		success : function(result) {
			window.location.reload();
		},
		error : function() {
			window.location.reload();
		},
		async : false
	});
}
function componentFromStr(numStr, percent) {
	var num = Math.max(0, parseInt(numStr, 10));
	return percent ? Math.floor(255 * Math.min(100, num) / 100) : Math.min(255,
			num);
}

function rgbToHex(rgb) {
	var rgbRegex = /^rgb\(\s*(-?\d+)(%?)\s*,\s*(-?\d+)(%?)\s*,\s*(-?\d+)(%?)\s*\)$/;
	var result, r, g, b, hex = "";
	if ((result = rgbRegex.exec(rgb))) {
		r = componentFromStr(result[1], result[2]);
		g = componentFromStr(result[3], result[4]);
		b = componentFromStr(result[5], result[6]);

		hex = "0x"
				+ (0x1000000 + (r << 16) + (g << 8) + b).toString(16).slice(1);
	}
	return hex;
}
$(document).ready(function() {
	var loggedin = 'false';
	loggedin = $('#loginflag').val();
	$('.fa-heart').bind({
		click : function(e) {
			if (loggedin == 'false') {
				showEmailDialog();
			}
			/*var id=this.id;*/
			var col = $('.fa-heart').css('color');
			var hex = rgbToHex(col);
			if (hex == "0xb3b3b3") {
				$('.fa-heart').css("color", "#e60000");
			} else {
				$('.fa-heart').css("color", "#b3b3b3");
			}
		}

	});

});

function showpasswordBoxDialog() {
	$("#passwordBoxoverlay").show();
	$("#passwordBoxdialog").fadeIn(300);
	$("#passwordBoxoverlay").click(function() {
		hidepasswordBoxDialog();
	});
}
function hidepasswordBoxDialog() {
	$("#passwordBoxoverlay").hide();
	$("#passwordBoxdialog").fadeOut(300);
}

$(document).ready(function() {
	$("#passwordBoxbtnClose").click(function() {
		hidepasswordBoxDialog();
	});
	$(".profile_change_password").click(function() {
		$("#errorMsg").html("");
		$(".passwordBoxtable").css("display","block");
		showpasswordBoxDialog();
	});

	$("#passwordChangebutton").click(function() {
		changePassword();

	});
	
	
});

function changePassword(){
	var superPrefix="<table>";
	var prefix =   "<tr><td colspan='4' style='padding-left: 15px;'><span class='pass_err_msg' style='color:#e60000'>";
	var suffix="</span></td></tr>";
	var superSuffix="<table>";

	var html = "";
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	var confirmPassword = $("#confirmPassword").val();

	if (oldPassword == "") {
		html = prefix+"oldPassword field can't be blank."+suffix;
	}
	if (newPassword == "") {
		html += prefix+"newPassword field can't be blank."+suffix;
	}
	
	if (confirmPassword == "") {
		html += prefix+"confirmPassword field can't be blank."+suffix;
	}
	
	
	
	if (newPassword != "" && newPassword!=""&& newPassword!=confirmPassword) {
		html += prefix+"password missmatch."+suffix;
	}
	if (!(html == "")) {
		$("#errorMsg").html(superPrefix+html+superSuffix);
		return false;
	}
	
	document.getElementById("passChangeTarget").getElementsByTagName("img")[0].style.display="block";
	$('.profile_change_password').bind('click', false);
	//disable login button

	$
			.ajax({
				url : "/changePassword/"+oldPassword+"/"+newPassword,
				type : 'get',
				async : false,
				success : function(data) {
					if (data != null) {
						if (data == 'success') {
							$(".passwordBoxtable").css("display","none");
							$("#errorMsg").html(superPrefix+prefix+"password updated successfully"+suffix+superSuffix);
							
						} else{
							$("#errorMsg").html(superPrefix+prefix+data+suffix+superSuffix);
							$('.profile_change_password').unbind('click', false);
						}
					}

				},
				error : function() {

					$("#errorMsg").html(superPrefix+prefix+"Something went wrong!!!Try again."+suffix+superSuffix);
					$('.profile_change_password').unbind('click', false);
				},

			});

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
