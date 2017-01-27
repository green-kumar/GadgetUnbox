electronicsApp.controller('electronicsController', function($scope,categoryList) {
	
	$scope.category=categoryList.getCategoryList();
    $scope.more= categoryList.getMoreList();
    $scope.footer=categoryList.getFooterList();
    
 
});
electronicsApp.controller('AddProductController', function($scope,$http,addProductService,$routeParams) {
	addProductService.getRequiredFieldProduct($scope,$http,$routeParams.category)
	
});

