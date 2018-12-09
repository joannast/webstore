<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author content="Joanna Stecz">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Produkty</title>
</head>
<body>
	<section class="container">
		<form:form  modelAttribute="newProduct" class="form-horizontal">
			<fieldset>
				<legend>Dodaj nowy produkt</legend>

				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Kod</label>
					<div class="col-lg-10">
						<form:input id="code" path="code" type="text" class="form:input-large"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Nazwa</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Cena</label>
					<div class="col-lg-10">
						<form:input id="price" path="price" type="text" class="form:input-large"/>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Obraz</label>
					<div class="col-lg-10">
						<form:input id="fileName" path="fileName" type="file" class="form:input-large"/>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Dodaj"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>