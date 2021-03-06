<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Atrybut http-equiv służy do określania treści nagłówka odpowiedzi HTTP dla dokumentu HTML,
 tzn. serwer HTTP pobiera treść tego atrybutu i na jej podstawie tworzy odpowiedź HTTP, -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author content="Joanna Stecz">
<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Produkty</title>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel heading">
						<h3 class="panel-title">Zaloguj się</h3>
				</div>
			  	<div class="panel-body">
			  	<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br />
					</div>
				</c:if>
				<form action="<c:url value="/j_spring_security_check"></c:url>" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="Nazwa użytkownika" name='j_username' type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Hasło" name='j_password'  type="password" value="">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Zaloguj">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>