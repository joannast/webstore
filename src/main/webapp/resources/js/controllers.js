/*kontroler oparty na AngularJS, zawiera 5 metod kontrolera interfejsu uzytkownika do komunikowania sie
 * z serwerem za pomoca ajaksowych zadan
 * Ajax (Asynchroniczny JavaScript oraz XML) jest techniką tworzenia aplikacji internetowej
 *  używaną po stronie klienckiej aplikacji w celu budowania asynchronicznej aplikacji */

var cartApp = angular.module('cartApp', []);
 
cartApp.controller('cartCtrl',  function ($scope, $http) {
	
	$scope.refreshCart = function(cartId) {
		  						$http.get('/webstore/rest/cart/'+$scope.cartId)
		  						 	 .success(function(data) {
		  						 		 		$scope.cart = data;
		  						 	 		});
		  					};
		  					
	$scope.clearCart = function() {
		  						$http.delete('/webstore/rest/cart/'+$scope.cartId)
		  							 .success($scope.refreshCart($scope.cartId));
		  						
	  					  };
	  					  
	$scope.initCartId = function(cartId) {
		  					$scope.cartId=cartId;
		  					$scope.refreshCart($scope.cartId);
	  						};
	  						
	  $scope.addToCart = function(code) {
		  
		  						$http.put('/webstore/rest/cart/add/'+code)
		  						 	 .success(function(data) {
		  						 		 		$scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
		  						 		 		alert("Produkt pomyślnie dodany do koszyka!");
		  						 	 		});
		  					};
	  $scope.removeFromCart = function(productId) {
		 
			  						$http.put('/webstore/rest/cart/remove/'+productId)
			  						 	 .success(function(data) {
			  						 		 	$scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
			  						 	 		});
			  					};
	  });