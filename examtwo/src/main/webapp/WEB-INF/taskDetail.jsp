<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/bulma.css">
<title><c:out value="${task.description}"></c:out></title>
</head>
<body>
	<h1 class="title">Task: <c:out value="${task.description}"></c:out></h1>
	
	<p>Creator: <c:out value="${task.creator.name}"></c:out></p>
	<p>Assignee: <c:out value="${task.assignee.name}"></c:out></p>
	<p>Priority: <c:choose>
						<c:when test="${task.priority == 1}">
							<span>High</span>
						</c:when>
						<c:when test="${task.priority == 2}">
							<span>Medium</span>
						</c:when>
						<c:otherwise>
							<span>Low</span>
						</c:otherwise>
					</c:choose></p>
	<c:if test="${task.creator == currentUser}">
		<a href="/tasks/${task.id}/edit"><button class="button is-info is-rounded">Edit</button></a>
		
		<a href="/tasks/${task.id}/delete"><button class="button is-info is-rounded">Delete</button></a>
	</c:if>
	<br>
	<c:if test="${task.assignee == currentUser}">
		<a href="/tasks/${task.id}/delete"><button class="button is-primary is-rounded">Completed</button></a>
	</c:if>
</body>
</html>