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
		<form:form  modelAttribute="newOrder" class="form-horizontal">
			<fieldset>
				<legend>Podaj dane do wysyłki</legend>

			

				<div class="col-lg-10" style = "display: none">
					<form:input id="cart.cartId" path="cart.cartId" type="text" class="form:input-large"/>
				</div>

	
<table>
<caption align="center">Dane do wysyłki</caption>

			<div class="form-group">

<tr>
<td>
				<label class="control-label col-lg-10" for="name">Nazwisko i imię</label>
</td>
<td>				
				<div class="col-lg-10">
					<form:input id="customer.name" path="customer.name" type="text" class="form:input-large"/>
				</div>
</td>				
</tr>

<tr>
<td><label class="control-label col-lg-10" for="phoneNumber">Numer telefonu</label></td>
<td><div class="col-lg-10">
					<form:input id="$customer.phoneNumber" path="customer.phoneNumber" type="text" class="form:input-large"/>
				</div></td>								
</tr>

<tr>
<td><label class="control-label col-lg-10" for="shippingDetail">Adres e-mail</label></td>
<td><div class="col-lg-10">
					<form:input id="$customer.email" path="customer.email" type="text" class="form:input-large"/>
				</div></td>								
</tr>

<tr>

<table>
<caption align="center">Dane adresowe</caption>

<tr>
<td> <label class="control-label col-lg-10" for="areaName">Nazwa miasta</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.areaName" path="customer.billingAddress.areaName" type="text" class="form:input-large"/>
				</div> </td>
</tr>

<tr>
<td> <label class="control-label col-lg-10" for="zipCode">Kod pocztowy</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.zipCode" path="customer.billingAddress.zipCode" type="text" class="form:input-large"/>
				</div> </td>
</tr>

<tr>
<td> <label class="control-label col-lg-10" for="streetName">Nazwa ulicy</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.streetName" path="customer.billingAddress.streetName" type="text" class="form:input-large"/>
				</div> </td>
</tr>

<tr>
<td> <label class="control-label col-lg-10" for="doorNo">Numer domu / mieszkania</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.doorNo" path="customer.billingAddress.doorNo" type="text" class="form:input-large"/>
				</div> </td>
</tr>

<tr>
<td> <label class="control-label col-lg-10" for="state">Województwo</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.state" path="customer.billingAddress.state" type="text" class="form:input-large"/>
				</div> </td>
</tr>

<tr>
<td> <label class="control-label col-lg-10" for="country">Kraj</label> </td>
<td> <div class="col-lg-10">
					<form:input id="customer.billingAddress.country" path="customer.billingAddress.country" type="text" class="form:input-large"/>
				</div> </td>
</tr>
								
</table>

</tr>


</table>

			</div>
			
						
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value ="Zamów i zapłać"/>
					</div>
				</div>
				
			</fieldset>
		</form:form>
	</section>
</body>
</html>