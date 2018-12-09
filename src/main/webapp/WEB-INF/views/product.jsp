<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author content="Joanna Stecz">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<!-- Poniżej dołączenie biblioteki AngularJS do widoku produktu -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<!-- odwołanie do skryptu zdefiniowanego w pliku controllers.js -->
	<script src="/webstore/resource/js/controllers.js"></script>

<title>Opis produktu</title>
</head>
<body>

	<section class="container" ng-app="cartApp">
		<div class="row">
		<div class="col-md-5">
	
	
	<h3> <img class="Product" src="${pageContext.request.contextPath}/products/productImage?code=${product.code}" style="width:300px;height:300px;"/></h3>
	
</div>
		
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>
					<strong>Identyfikator produktu: </strong><span class="label label-warning">${product.code}</span>
				</p>
				
				<c:if test = "${product.prodcount > 0}">  
					<h4>Cena produktu: ${product.price} PLN</h4>
					<h4>Na stanie: ${product.prodcount} sztuk</h4>
					
					<p ng-controller="cartCtrl">
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.code}')"> 
					<span class="glyphicon-shopping-cart glyphicon"></span> Zamów teraz </a> </p>
					
		      	</c:if>
		      	
				<c:if test = "${product.prodcount <= 0}">
					<h4>Produkt niedostępny</h4>
		      	</c:if>
				
				
				
					
					<a href="<spring:url value="/cart" />" class="btn btn-default">
						<span class="glyphicon-hand-right glyphicon"></span> Koszyk
					</a>

				<!-- implementacja hiperlaczado strony prezentujacej inforamcje o produktach -->
 					<a href="<spring:url value="/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Powrót
					</a>

				

			</div>
		</div>
	</section>
</body>
</html>
