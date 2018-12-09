<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Dziękujemy </title>
</head>
<body>
	<section>
		
			<div class="container">
				<h1 class="alert alert-danger"> Zapraszamy ponownie!</h1>
				<p>Numer twojego zamówienia to:  ${order.orderId}</p>
				<p>Dane niezbędne do płatności prześlemy na twój adres e-mail.</p>
			</div>
		
	</section>

	<section>
		<div class="container">
			<p>
				<a href="<spring:url value="/products" />" class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span> produkty
				</a>
			</p>
		</div>
		
	</section>
</body>
</html>		