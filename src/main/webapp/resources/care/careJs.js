var ajaxSearch=null;
var ajaxAuto=null;
var ajaxCreate=null;
var ajaxDelete=null;
$(document).ready(function(){
    $(".enginHeader").click(function(){
    	 $(this).next("div.enginContent").slideToggle("fast");
        //$("#enginContent").slideToggle("fast");
    });
    
   
    
    $('input[type="checkbox"]').on('change', function() {
    	   $(this).siblings('input[type="checkbox"]').not(this).prop('checked', false);
    	   var ischecked= $('.catCheckbox').is(':checked');
           if(!ischecked)
          	 $('.opsCheckbox').prop('checked', false);
    	});
    
    
	 $("input:checkbox.opsCheckbox").change(function() {
		 if ($("#categoryFilter input:checkbox:checked").length == 0)
		 {
			 $('.opsCheckbox').prop('checked', false);
		     alert("please select category");
		     return false;
		 }
		 
		 
		 if($(this).val()== "create"){
			 $('#brand').prop('readonly', true);
			 $('#name').prop('readonly', true);
			 $('#id').prop('readonly', true);
			 $('#feature').prop('disabled', true);
			 $('#order').prop('disabled', true);
			 $('#limit').prop('disabled', true);
			 $('#page').prop('readonly', true);
		 }else if($(this).val()== "view" ){
			 $('#brand').prop('readonly', false);
			 $('#name').prop('readonly', false);
			 $('#id').prop('readonly', false);
			 $('#feature').prop('disabled', false);
			 $('#order').prop('disabled', false);
			 $('#limit').prop('disabled', false);
			 $('#page').prop('readonly', false);
		 }else if($(this).val()== "update" || $(this).val()== "delete"){
			 $('#brand').prop('readonly', false);
			 $('#name').prop('readonly', false);
			 $('#id').prop('readonly', false);
			 $('#feature').prop('disabled', true);
			 $('#order').prop('disabled', true);
			 $('#limit').prop('disabled', false);
			 $('#page').prop('readonly', false);
		 }
		 
		 
		 
		 
		 
	 });
	 
	 
	 $("#brand").on('keyup',function(e){
		 var dataList = "#autoComplet"+e.target.id;
 		$(dataList).html("");
		 var b = $('#brand').val().substring(0,30);
		 var n = $('#name').val().substring(0,30);
		 var c = $('.catCheckbox:checked').val();
		 if(b!=null || n!=null)
			 fetchAutoSuggestItem(b,n,c,e.target.id)
	 });
	 
	 $("#name").on('keyup',function(e){
		 var dataList = "#autoComplet"+e.target.id;
	 		$(dataList).html("");
		 var b = $('#brand').val().substring(0,30);
		 var n = $('#name').val().substring(0,30);
		 var c = $('.catCheckbox:checked').val();
		 if(b!=null || n!=null)
		 fetchAutoSuggestItem(b,n,c,e.target.id)
	 });
	
	 
	 
	
	 
	 $("#enginButton").click(function(){
		 var category = $('.catCheckbox:checked').val();
		 var ops = $('.opsCheckbox:checked').val();
		 
		 if(category== undefined && ops == undefined){
			 alert("please select category and operation");
			 return true;
			 
		 }else if(ops == undefined){
			 alert("please select operation");
			 return true;
			 
		 }
		 
			 
		 
		 var brand = $('#brand').val().substring(0,30);
		 var name = $('#name').val().substring(0,30);
		 var productId = $("#id").val();
		 var sortBy = $("#feature option:selected").val();
		 var orderBy =  $("#order option:selected").val();
		 var limit = $("#limit option:selected").val();
		 var page = $("#page").val();
		 if(ops == "create"){
			 createProduct(category,"");
		 }else{
			 getCareSearch(brand,name,productId,sortBy,orderBy,category,page,limit);
		 }
    });
	 
	
	 
	
	 
	 


});


function createProduct(category,productId){
	$('#targetCare').css("display" , "block");
	if (ajaxCreate != null) 
		ajaxCreate.abort();
	   ajaxCreate = $.ajax({
        url: careBaseUrl+"/care/create/"+category+"?id="+productId,
        success: function (result) {
        	$('#targetCare').css("display" , "none");
        	if(result!=null){
        		$('#submitInfo').css("display" , "none");
        		 if(productId == ""){
        		$('#careBody').html(result);
        		$('#careEditBody').html("");
        		 }else{
        			
        			 $('#careEditBody').html(result);
        		 }
        	}
        },
    error : function(result) {
    	$('#targetCare').css("display" , "none");
    	alert("something went wrong!!! Try again");
	}
        });

	
}





function fetchAutoSuggestItem(brand,name,category,id){
	
	if (ajaxAuto != null) 
		ajaxAuto.abort();
	ajaxAuto = $.ajax({
        url: careBaseUrl+"/care/autoComplete?b="+brand+"&n="+name+"&c="+category+"&req="+id,
        dataType : 'json',
        success: function (data) {
        	if(data!=null){
        		var dataList = "#autoComplet"+id;
        		
        		var opt="";
        		$(dataList).html(opt)
       		 for(var i=0;i<data.length && i<10 ;i++)
       		  opt=opt+"<option value=\'"+data[i]+"\'>";
       		 $(dataList).html(opt)
        		       	}else{
        		$(dataList).css("display" , "none");
        	}
        },
    error : function(result) {
    	
    	$('#targetCare').css("display" , "none");
    	/*alert("something went wrong!!! Try again");*/
	
		
	}
        });

	
	
}

function getCareSearch(brand,name,id,sortBy,orderBy,category,page,limit){
	$('#targetCare').css("display" , "block");
	if (ajaxSearch != null) 
		ajaxSearch.abort();
      ajaxSearch = $.ajax({
        url: careBaseUrl+"/care/search?b="+brand+"&n="+name+"&id="+id+"&sB="+sortBy+"&oB="+orderBy+"&c="+category+"&p="+page+"&l="+limit,
        success: function (data) {
        	$('#targetCare').css("display" , "none");
        	$('#submitInfo').css("display" , "none");
        	if(data!=null){
        		$('#careBody').html(data);
        		$('#careEditBody').html("");
        	}
        },
    error : function(data) {
    	$('#targetCare').css("display" , "none");
    	alert("something went wrong!!! Try again");
	}
        });
}


function deleteProduct(category,productId){
	$('#targetEditCare').css("display" , "block");
	if (ajaxDelete != null) 
		ajaxDelete.abort();
	ajaxDelete = $.ajax({
        url: careBaseUrl+"/care/delete/"+category+"/"+productId,
        success: function (data) {
        	$('#submitInfo').css("display" , "none");
        	$('#targetEditCare').css("display" , "none");
        	if(data!=null){
        		$('#careEditBody').html(data);
        	}
        },
    error : function(data) {
    	$('#targetEditCare').css("display" , "none");
    	alert("something went wrong!!! Try again");
	}
        });
}














