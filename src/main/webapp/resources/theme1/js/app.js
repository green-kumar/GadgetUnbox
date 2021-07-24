var electronicsApp = angular.module("electronicsApp", []);
electronicsApp.config(function($routeProvider) {

	  $routeProvider.
	      when('/', {
	        controller: 'SearchController',
	        templateUrl: 'views/search.html'
	      }).
	      when('/AddProduct/:category', {
	        controller: 'AddProductController',
	        templateUrl: 'views/AddProduct.html'
	      }).
	      when('/UpdateProduct/:category', {
	        controller: 'UpdateProductController',
	        templateUrl: 'views/UpdateProduct.html'
	      }).
	      when('/GetAllProduct/:category', {
	        controller: 'GetAllProductController',
	        templateUrl: 'views/thank-you.html'
	      }).
	      when('/GetAllCategory', {
	        controller: 'GetAllCategoryController',
	        templateUrl: 'views/GetAllCategory.html'
	      }).
	      when('/AddReview', {
	    	  controller: 'AddReviewController',
		      templateUrl: 'views/AddReview.html'
	      }).
	      when('/search', {
	    	  controller: 'searchController',
		        templateUrl: 'views/search.html'
	      }).
	      when('/login', {
	    	  controller: 'loginController',
		        templateUrl: 'views/login.html'
	      }).
	      when('/404',{
	    	  templateUrl: "views/404.html"
	      }).
	      otherwise({
	    	redirectTo: '/404'  
	      });
	});

