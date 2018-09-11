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
	<title>Task Manager</title>
</head>
<body>
	<div class="header">
		<h1 class="title">Welcome, ${currentUser.name}</h1>
		<a href="/tasks?desc">Priority Low - High</a>
		<a href="/tasks?asc">Priority High - Low</a>
		<a href="/login?logout">Logout</a>
	</div>
	<div class="section">
		<table class="table is-bordered">
			<tr>
				<th>Task</th>
				<th>Creator</th>
				<th>Assignee</th>
				<th>Priority</th>
			</tr>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td><a href="/tasks/${task.id}">${task.description}</a></td>
					<td>${task.creator.name}</td>
					<td>${task.assignee.name}</td>
					<td><c:choose>
						<c:when test="${task.priority == 1}">
							<span>High</span>
						</c:when>
						<c:when test="${task.priority == 2}">
							<span>Medium</span>
						</c:when>
						<c:otherwise>
							<span>Low</span>
						</c:otherwise>
					</c:choose></td>
				</tr>
			</c:forEach>
		</table>
		<a href="/tasks/new"><button class="button is-info is-rounded is-right">Create Task</button></a>
	</div>
</body>
</html>