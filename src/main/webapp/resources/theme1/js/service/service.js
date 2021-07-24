electronicsApp.service('categoryList', function() {
	var category=[
		'Smartphones',
		'Cameras',
		'Tablets',
		'Laptops'
	];
     var more=[
			"Audio divices",
			"VideoDivices",
			"XXXXX"
		];
     var footer=[
"Home",
"About",
"Services",
"Projects",
"Solutions",
"Jobs",
"Blog",
"Contacts"  
        ];
	this.getCategoryList=function(){
		return category;
	};
	this.getMoreList=function(){
		return more;
	};
	this.getFooterList=function(){
		return footer;
	};
});
electronicsApp.service('addProductService', function() {
	this.getRequiredFieldProduct=function($http,$scope,categary){
		 /*$http.get("http://localhost:8080/review-aggregator-electronics/electronics/fetchRequiredProductDescriptionKeys/"+categary).
		 success(function(data, status, headers, config) {
			 $scope.ProductKeyList = data;
			 $scope.status=status;
		 }).
		 error(function(data, status, headers, config) {
			 $scope.status=status;
		 });*/
	}
	
	
	
});

