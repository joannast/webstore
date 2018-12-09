<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Sorry </title>
</head>
<body>
	<section>
		
			<div class="container">
				<h1 class="alert alert-danger"> Ups! cos poszło nie tak</h1>
				<p>Niestety ktoś kupił ten produkt przed Tobą</p>
				<p>Zapraszamy do zakupu innych misiów w naszym sklepie</p>
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