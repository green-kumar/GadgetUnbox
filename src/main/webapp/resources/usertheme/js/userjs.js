/**
 * 
 */
/*$body = $("body");

$(document).on({
    ajaxStart: function() { $body.addClass("loading");    },
     ajaxStop: function() { $body.removeClass("loading"); }    
});*/
var listClickedIndex=0;
var active=true;
var serActive=true;
var spCount=1;
var month = {
		January : 01 ,
        February : 02 ,
        March : 03 ,
        April : 04 ,
        May : 05 ,
        June : 06 ,
        July : 07 ,
        August : 08 ,
        September : 09 ,
        October : 10 ,
        November : 11,
        December : 12 
};
var ajaxReq = null;

$(document).ready(function () {
	
	$("img.lazy").lazyload({
	    effect : "fadeIn"
	});

	// if user clicked on button, the overlay layer or the dialogbox, close the dialog    
    $('.detailsdialogoverlay').click(function () {        
        $('.detailsdialogoverlay, .detailsdialogbox').hide(); 
        $('.detailsdialogbox').html("");
        //window.history.pushState(null, null,$('#url').val());
        window.history.go(-1);
        return false;
    });
    
    // if user resize the window, call the same function again
    // to make sure the overlay fills the screen and dialogbox aligned to center    
    $(window).resize(function () {
        
        //only do it if the dialog box is not hidden
        if (!$('.detailsdialogbox').is(':hidden')) popup();        
    });  
    
	$('.userfavicon').bind({
		click : function(e) {
			var productName=$(this).attr('data-productName');
			var loggedin = 'false';
			loggedin = $('#loginflag').val();
			if (loggedin == 'false') {
				showEmailDialog();
				e.preventDefault();
				$(".errorspan").html("Please login for adding "+productName+" in your fav list.");
				return false;
			}
			
			var productId=$(this).attr('data-productId');
			var category=$(this).attr('data-category');
			
			if ($(this).hasClass('profileheartunselected')) {
				$(this).removeClass('profileheartunselected');
				$(this).addClass('profileheartselected');
				favProduct(productId,category,1);
		    } else {
		    	$(this).removeClass('profileheartselected');
		    	$(this).addClass('profileheartunselected');
		    	favProduct(productId,category,0);
		    }

			
		}

	});
	
	$('.rankStarIcon').click(function(){
		var productId=$(this).attr('data-productId');
		var currentRating=$(this).attr('data-currentRating');
		var category=$(this).attr('data-category');
	
		showpasswordBoxDialog();
		populateListRating(currentRating);
	});
	
	
	
	
	/*$(".rankrating span.rank").click(function(){*/
		$('.rankrating span.star').on('click',function(e){
		var listClickedIndex=$('.rankrating').index();
		var starCount=5-listClickedIndex;
		$('.rankrating span.star'+identifier).removeClass('filled');
    	for(var i=listClickedIndex;i<5;i++){
        	$('.rankrating span.star').eq(i).addClass('filled');
    	}		
		
	});
	
	$("#submitRate").click(function(){
		alert(ClickedIndex);
	});
	
	$(".SLI").live("click" , function(e){
		var totalPage= parseInt($('#maxPage').text());
		var spCount =parseInt($('#curPage').text());
		var q=$('#query').val();
		var category=$('#searchCategory').val();
		if($(this).text()== 'Next'){
			$("#"+spCount).removeClass("profileHeader");
			spCount=parseInt(spCount)+1;
			$("#"+spCount).addClass("profileHeader");
		}else if($(this).text()== 'Prev'){
			$("#"+spCount).removeClass("profileHeader");
			spCount=parseInt(spCount)-1;
			$("#"+spCount).addClass("profileHeader");
		}else{
			$("#"+spCount).removeClass("profileHeader");
			spCount=$(this).text();
			$("#"+spCount).addClass("profileHeader");
		}
		if(totalPage==1 ){
			$("#spPrev").css("visibility" ,"hidden");
			$("#spNext").css("visibility" ,"hidden");
		}else if(spCount ==1){
			$("#spPrev").css("visibility" ,"hidden");
			$("#spNext").css("visibility" ,"visible");
		}else if(spCount == totalPage){
			$("#spPrev").css("visibility" ,"visible");
			$("#spNext").css("visibility" ,"hidden");
		}else{
			$("#spPrev").css("visibility" ,"visible");
			$("#spNext").css("visibility" ,"visible");
		}
		$('#curPage').text(spCount);
		
		fetchPaginationSearchResult(q,category,spCount);
		
	});
	
	
	 $("input:checkbox.filterCheckbox").change(function() {
           var selectedBrand = [];
           var selectedOS = [];
           var selectedFeature = [];
           var selectedCategory = [];
           var brands="";
           var OS="";
           var feature="";
           var category="";
           var pageCount=1;
           
           var query = $('#query').val()
           
           $('#brandFilter input:checked').each(function() {
        	   selectedBrand.push($(this).attr('name'));
           });
           $('#OSFilter input:checked').each(function() {
        	   selectedOS.push($(this).attr('name'));
           });
           $('#featureFilter input:checked').each(function() {
        	   selectedFeature.push($(this).attr('name'));
           });
           
           $('#categoryFilter input:checked').each(function() {
        	   selectedCategory.push($(this).attr('name'));
           });

           

           var arrayLength = selectedBrand.length;
           for (var i = 0; i < arrayLength; i++) {
        	   brands=brands+selectedBrand[i]+","
           }
            arrayLength = selectedOS.length;
           for (var i = 0; i < arrayLength; i++) {
        	   OS=OS+selectedOS[i]+","
           }
            arrayLength = selectedFeature.length;
           for (var i = 0; i < arrayLength; i++) {
        	   feature=feature+selectedFeature[i]+","
           }
           
           arrayLength = selectedCategory.length;
           for (var i = 0; i < arrayLength; i++) {
        	   category=category+selectedCategory[i]+","
           }
           
           if(brands != "")
        	   brands=brands.substring(0, brands.length-1);
        	   
         if(OS!="")
        	OS=OS.substring(0, OS.length-1)
        	   
        	if(feature != "")
        		feature=feature.substring(0, feature.length-1)
        		
        		if(category != "")
        			category=category.substring(0, category.length-1)
        			
        	if(	query !=null){
        		populatedFilterdSearchResult(category,brands,query,pageCount);
        	}else{
           populatedFilterdResult(brands,OS,feature);
        	}
     }); 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 $('#feature').change(function() {
		  var sf = $(this).find(":selected").val();
		  var sfd = "data-"+sf;
		  var order = $('#order').find(":selected").val();
		  var html=$(".ranklistitems_").html();
		  html = '<div class="ranklistitems_">'+html+'</div>'
		  
		  sortDivs(sfd,order);
		  
		  $("#productList").append(html);
		  
		 // $(html).appendTo( $("#productList") );
		 
		  
		});
	 
	 $('#order').change(function() {
		  var order = $(this).find(":selected").val();
		  var sf = $('#feature').find(":selected").val();
		  var sfd = "data-"+sf;
		  sortDivs(sfd,order)
		});

	 
	 $("#autocomplete").on('keyup click',function(){
		 var q = $('#autocomplete').val().substring(0,20);
		 var cat = $('#searchCat').find(":selected").text();
		 fetchAutoSuggestItem(q,cat.toLowerCase());
	 });
	 
	 $("#autocompleteHeader").on('keyup click',function(){
		 var q = $('#autocompleteHeader').val().substring(0,20);
		 var cat = $('#searchCatHeader').find(":selected").text();
		 fetchAutoSuggestItemHeader(q,cat.toLowerCase());
	 });
	 
	 $(document).click(function(e) {
		 var autoSuggest=$('#autosuggest');
		 var searchAllResult=$('#searchAllResult');
		 var boldSearch=$('.boldSearch');
         var searchAllQ = $('.searchAllQ');
		 if (!autoSuggest.is(e.target)  && autoSuggest.has(e.target).length === 0) {
			 autoSuggest.hide();
              return true;			 
			    }
		 
		 if(searchAllResult.is(e.target) || boldSearch.is(e.target) || searchAllQ.is(e.target)){
			 
			 var searchAllQ= $('.searchAllQ').attr('data-q')
				 $("#autocomplete").val(searchAllQ);
				 $('#searchBarForm').submit();
		 }
	 });
	 
	 
	 $(document).click(function(e) {
		 var autoSuggest=$('#autosuggestHeader');
		 var searchAllResult=$('#searchAllResultHeader');
		 var boldSearch=$('.boldSearch');
         var searchAllQ = $('.searchAllQ');
		 if (!autoSuggest.is(e.target)  && autoSuggest.has(e.target).length === 0) {
			 autoSuggest.hide();
              return true;			 
			    }
		 
		 if(searchAllResult.is(e.target) || boldSearch.is(e.target) || searchAllQ.is(e.target)){
			 
			 var searchAllQ= $('.searchAllQ').attr('data-q')
				 $("#autocompleteHeader").val(searchAllQ);
				 $('#searchBarFormHeader').submit();
		 }
	 });
	 
	 
	 
	 
	 
	 
	 $('#searchCat').change(function() {
			var cat = $('#searchCat').find(":selected").text();
			 $("#category").val(cat);
			});
	 
	 $('#searchCatHeader').change(function() {
			var cat = $('#searchCatHeader').find(":selected").text();
			 $("#categoryHeader").val(cat);
			});
	 
	 
	 $('a#searchBarButton').bind({
			click : function(e) {
				var q = $('#autocomplete').val()
				if(q == null || q=='')
					return true;
				$('#searchBarForm').submit();
			}
	 });
	 
	 
	 $('a#searchBarButtonHeader').bind({
			click : function(e) {
				var q = $('#autocompleteHeader').val()
				if(q == null || q=='')
					return true;
				$('#searchBarFormHeader').submit();
			}
	 });
	
	
  });

function fetchPaginationSearchResult(q,category,pageCount){
	 window.location = "/search?q="+q+"&category="+category+"&pageCount="+pageCount;
	
}

function fetchAutoSuggestItem(q,cat){
	// string is not empty and not just whitespace
	if (/\S/.test(q)) {
	
	if (ajaxReq != null) 
		ajaxReq.abort();
      ajaxReq = $.ajax({
        url: "/autosuggest/"+q+"/"+cat,
        dataType : 'json',
        success: function (data) {
        	if(data!=null){
        		
        		var res="";
        		for(var i=0;i<data.length;i++){ 
        		res=res+'<div class="ranklistitems" onclick="location.href=\'/search/details/'+data[i].producId+'?avgRating='+data[i].rating+'\'" style="width:400px;height:40px;border:none;cursor :pointer" ><div class="test" style="float: left; margin-left: 10px; margin-top: 10px;"><b>'+data[i].productName+'</b></div><div class="test" style="float: right; margin-right: 10px; margin-top: 15px;"><span style="color: #ff6666;">'+data[i].category.toLowerCase()+'</span></div></div>';
        		}
        		res=res+'<div class="ranklistitems" id="searchAllResult" style="width:400px;height:40px;border:none;cursor :pointer;background:#e6e6e6;" ><div class="test" style="float: left; margin-left: 10px; margin-top: 10px;"><b class="boldSearch">search all results for <span class ="searchAllQ" data-q="'+q+'">\"'+q+'\"</span></b></div></div>';
        		$('#autosuggest').css("display" , "block");
        		$('#autosuggest').html(res);
        	}else{
        		$('#autosuggest').css("display" , "none");
        	}
        },
    error : function(result) {
		
	}
        });
}
}


function fetchAutoSuggestItemHeader(q,cat){
	// string is not empty and not just whitespace
	if (/\S/.test(q)) {
	
	if (ajaxReq != null) 
		ajaxReq.abort();
      ajaxReq = $.ajax({
        url: "/autosuggest/"+q+"/"+cat,
        dataType : 'json',
        success: function (data) {
        	if(data!=null){
        		
        		var res="";
        		for(var i=0;i<data.length;i++){ 
        		res=res+'<div class="ranklistitems" id="product-main-image" onclick="location.href=\'/search/details/'+data[i].producId+'?avgRating='+data[i].rating+'\'" style="width:400px;height:40px;border:none;cursor :pointer" ><div class="test" style="float: left; margin-left: 10px; margin-top: 10px;"><b>'+data[i].productName+'</b></div><div class="test" style="float: right; margin-right: 10px; margin-top: 15px;"><span style="color: #ff6666;">'+data[i].category.toLowerCase()+'</span></div></div>';
        		}
        		res=res+'<div class="ranklistitems" id="searchAllResult" style="width:400px;height:40px;border:none;cursor :pointer;background:#e6e6e6;" ><div class="test" style="float: left; margin-left: 10px; margin-top: 10px;"><b class="boldSearch">search all results for <span class ="searchAllQ" data-q="'+q+'">\"'+q+'\"</span></b></div></div>';
        		$('#autosuggestHeader').css("display" , "block");
        		$('#autosuggestHeader').html(res);
        	}else{
        		$('#autosuggestHeader').css("display" , "none");
        	}
        },
    error : function(result) {
		
	}
        });
}
}




function sortDivs(sfd,order){
	 var sortedDivs = $("div.ranklistitems").sort(function (a, b) {
		 if(sfd == "data-price" || sfd == "data-ranking" || sfd == "data-yourRating" || sfd == "data-relevence"){
		 if(order == "asc"){
	        return $(a).find("#"+sfd).attr(sfd) - $(b).find("#"+sfd).attr(sfd);
		 }else{
			 return $(b).find("#"+sfd).attr(sfd) - $(a).find("#"+sfd).attr(sfd);
		 }
		 }
		 
		 if(sfd == "data-releaseDate"){
			 
			 var date1 = $(a).find("#"+sfd).attr(sfd);
			    var varx = date1.split(',');
			   var var0 = varx[0].split(' ')[1];
			   var var1= varx[0].split(' ')[0];
			   var var2 = varx[1];
			   
			   var1=month[var1];
			   var0=var0.trim();
			   
			    date1 = new Date(var2, var1 - 1, var0);
			    
			    var date2 = $(b).find("#"+sfd).attr(sfd);
			      var  vary = date2.split(',');
			      var var10 = vary[0].split(' ')[1];
			   var var11= vary[0].split(' ')[0];
			   var var12 = vary[1];
			   
			   var11=month[var11];
			   var10=var10.trim();
			   var12=var12.trim();
			    date2 = new Date(var12, var11 - 1, var10);
			    
			    if(order == 'asc'){
			    	return date1 > date2;
			    }else{
			    	return date2 > date1;
			    }
			 
			 
		 }
		 
	    });
	    $("#productList").html(sortedDivs);
	  
	
	
}

function populatedFilterdSearchResult(category,brand,query,pageCount){

	if(serActive){
		serActive=false;
	jQuery.ajax({
        url: "/search/filter?q="+query+"&brand="+brand + "&category="+category +"&pageCount="+pageCount ,
        success: function (result) {
            if(result!=null){
            	$('#productList').html(result);
            	serActive=true;
            }
        },
        error : function(result) {
			
        	alert("something went wrong!!! Try again");
        	serActive=true;
		},
        async: false
    });
	}
	

	
	
}

function populatedFilterdResult(brand,OS,feature){
	if(active){
		active=false;
	jQuery.ajax({
        url: "/mobile/list/top-rated-smartphone/filter?brand="+brand + "&OS="+OS + "&feature="+feature,
        success: function (result) {
            if(result!=null){
            	$('#productList').html(result);
            	active=true;
            }
        },
        error : function(result) {
			
        	alert("something went wrong!!! Try again");
        	active=true;
		},
        async: false
    });
	}
	
}

function populateListRating(currentRating){
	var html="";
	var count=0;
	if(typeof currentRating != 'undefined'){
		count=currentRating;
		html="Update your rating!!!</br>"
	}else{
		html="provide your rating!!!</br>"
	}
	
	
		
	 html+="<div class='listRating' style='float: left;margin-top:25px; cursor:pointer'><span class='rating rankrating' >";
	for(var j=0;j<5;j++){ 
	      if(j<(5-count)){
	    	  html+="<span class='star rank' title='ratingTitleMap.get(4-j)'></span>";
	}else{
		html+="<span class='star rank filled' title='ratingTitleMap.get(4-j)'></span>";
	}
	}
html+="</span></div>";

$("#rankingMessage").html(html);
}



function favProduct(productId,category,flag){
	jQuery.ajax({
		url : "/favProduct/"+category+"/"+flag+"/"+productId,
		success : function() {
		},
		error : function() {

		},
	});
}

$(document).on('click','#product-main-image',function(e){
	
	var productId=this.getAttribute('data-productId');
	var category=this.getAttribute('data-category')
	var avgRating=this.getAttribute('data-avgRating')
	//since detail tab need to show/focus
	var detailId=1;
	showProductDetailsDialog(detailId,productId,category,avgRating);
	e.preventDefault();
 
}); 

$(document).on('click','a.btn-info',function(e){
	var productId=this.getAttribute('data-productId');
	var category=this.getAttribute('data-category')
	var avgRating=this.getAttribute('data-avgRating')
	showProductDetailsDialog(this.id,productId,category,avgRating);
	e.preventDefault();
}); 
/*jQuery.fn.center = function () {
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
                                                $(window).scrollTop()) + 90 +"px");
    
    return this;
}*/
function showProductDetailsDialog(id,productId,category,avgRating){
	   
	var ajaxUrl = "/"+category+"/details/"+productId;
	  window.history.pushState(null, null, ajaxUrl);
		 $('.detailsdialogbox').show();
	 $('.detailsdialogbox').css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
             $(window).scrollTop()) + 90 +"px");
	 var maskHeight = $(document).height()+$('.detailsdialogbox').height(); 
	    var maskWidth = $(window).width();
		  $('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth}).show();

	var showTab=$("#"+id).text();
	if(showTab == "" || showTab == 1){
		showTab = 'Details';
	}
	jQuery.ajax({
        url: ajaxUrl,
        headers: {"X-custom-Header": "popup"},
        success: function (result) {
            if(result!=null){
            	popup(result);
            	if(showTab == 'Details'){
              $(".tabs-menu li:eq(0)").addClass('current');
            	$('#tab-1').show();
            }else{
            	$(".tabs-menu li:eq(2)").addClass('current');
            	$('#tab-3').show();
            	$(".tabs-menu li:eq(0)").removeClass('current');
            	$('#tab-1').hide();
            }
            }
        },
        error : function(result) {
        	$('.detailsdialogoverlay').css("display" , "none");
        	$('.detailsdialogbox').css("display" , "none");
        	alert("something went wrong!!! Try again");
        	
		},
        async: false
    });
	
}
//Popup dialog
function popup(message) {
        
    // get the screen height and width  
    /*var maskHeight = $(document).height();  
    var maskWidth = $(window).width();
    
    // calculate the values for center alignment
    var dialogTop =  (maskHeight/3) - ($('#dialog-box').height());  
    var dialogLeft = (maskWidth/2) - ($('#dialog-box').width()/2); 
    
    // assign values to the overlay and dialog box
    $('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth}).show();
    $('.detailsdialogbox').css({top:dialogTop, left:dialogLeft}).show();
    $('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth});
    $('.detailsdialogbox').css({top:dialogTop, left:dialogLeft});*/
    $('.detailsdialogbox').find('img').remove();
    // display the message
    $('.detailsdialogbox').html(message);
            
}


/*$(document).ready(function() {
	$('a.btn-info').bind({
		click : function(e) {
			//$('.detailsdialogoverlay').show();
			var maskHeight = $(document).height(); 
		    var maskWidth = $(window).width();
		    //alert(maskHeight+","+maskWidth);
			  $('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth}).show();
			 $('.detailsdialogbox').show();
			//$body.addClass("loading");
			//var maskHeight = $(document).height();  
		    //var maskWidth = $(window).width();
		    
		    // calculate the values for center alignment
		    //var dialogTop =  (maskHeight/3) - ($('.detailsdialogbox').height());  
		    //var dialogLeft = (maskWidth/2) - ($('.detailsdialogbox').width()/2); 
		    
		    // assign values to the overlay and dialog box
		    //$('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth}).show();
		    //$('.detailsdialogbox').css({top:dialogTop, left:dialogLeft}).show();
		    
		    //$('.detailsdialogbox').css({position:'center'}).show();
		    //$('.detailsdialogoverlay').css({height:maskHeight, width:maskWidth});
		    //$('.detailsdialogbox').css({top:dialogTop, left:dialogLeft});
			
			//$('.detailsdialogoverlay').show();
			//$('.detailsdialogbox').fadeIn(300);
			//$('<img src="url(../images/loading.gif)" />').appendTo(".detailsdialogbox");
			showProductDetailsDialog(this.id);
			e.preventDefault();
		}

	});

});*/