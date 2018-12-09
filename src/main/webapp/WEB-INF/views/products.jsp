<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author content="Joanna Stecz">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

</head>
<body>
 
	<section class="container">
		<div class="row">
		<!-- iteracja po liscie produktow przy mocy znacznika c:forEach -->
			<c:forEach items="${products}" var="product">                  
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class = "thumbnail">
							<img class="Product" src="${pageContext.request.contextPath}/products/productImage?code=${product.code}" style="width:100px;height:100px;"/>
							<div class = "caption">
							<h3> Nazwa: ${product.name} </h3> 
							<p> Kod  : ${product.code} </p> 
							<p> Cena : ${product.price} PLN</p>
							<p>
							<a	href=" <spring:url value="/products/product?id=${product.code}" /> "
								class="btn btn-primary"> <span
								class="glyphicon-info-sign glyphicon" /></span> Szczegóły
							</a>
							</p>
						</div>
					</div>
					</div>
			</c:forEach>
			</div>
	</section>
</body>
</html>
