<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bulma.css">
<title>Login Page</title>
</head>
<body>
	<div class="columns">
		<div class="column">
			<h1>Register</h1>
			
			<p><form:errors path="user.*"/></p>
			
			<form:form method="POST" action="/registration" modelAttribute="user">
				<p>
					<form:label path="name">Name:
					<form:input path="name"/></form:label>
				</p>
				<p>
					<form:label path="username">Email:
					<form:input path="username"/></form:label>
				</p>
				<p>
					<form:label path="password">Password:
					<form:password path="password"/></form:label>
				</p>
				<p>
					<form:label path="passwordConfirmation">Password Conf:
					<form:password path="passwordConfirmation"/></form:label>
				</p>
				<input class="button is-rounded is-info" type="submit" value="Register"/>
			</form:form>
		</div> <!-- close register column -->
	
		<div class="column">
			
			<!-- these two if tags display messages -->
			<c:if test="${logoutMessage != null }">
				<c:out value="${logoutMessage }"/>
			</c:if>
			<c:if test="${errorMessage != null }">
				<c:out value="${errorMessage }"/>
			</c:if>
			<h1>Login</h1>
			<form method="POST" action="/login">
				<p>
					<label for="username">Email:</label>
					<input type="text" id="username" name="username"/>
				</p>
				<p>
					<label for="password">Password:</label>
					<input type="password" id="password" name="password"/>
				</p>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input class="button is-rounded is-info" type="submit" value="Login"/>
			</form>
		</div><!-- close login column -->
	</div>
</body>
</html>