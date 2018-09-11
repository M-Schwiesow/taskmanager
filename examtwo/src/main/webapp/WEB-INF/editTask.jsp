<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL core library -->
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- JSTL formatting library -->
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/bulma.css">
<title>Edit Task</title>
</head>
<body>
	<h1 class="title">Edit <c:out value="${task.description}"></c:out></h1>
	<form:form method="POST" action="/tasks/${task.id}/edit" modelAttribute="task">
		<p>
		<form:label path="description">Task
		<form:input path="description"/></form:label>
		</p>
		<form:errors path="description"/>
		<p>
		<form:label path="assignee">Assignee
		<form:select path="assignee">
			<c:forEach items="${users}" var="user">
				<c:if test="${user.tasks.size() < 3}">
					<form:option value="${user}" label="${user.name}"/>
				</c:if>
			</c:forEach>
				<c:if test="${task.assignee.tasks.size() == 3}">
					<form:option value="${task.assignee}" label="${task.assignee.name}"/>
				</c:if>
		</form:select></form:label>
		</p>
		<form:errors path="assignee"/>
		<p>
		<form:label path="priority">Priority
		<form:select path="priority">
			<form:option value="1" label="High"/>
			<form:option value="2" label="Medium"/>
			<form:option value="3" label="Low"/>
		</form:select></form:label>
		</p>
		<form:errors path="priority"/>
		<br>
		<input class="button is-rounded is-info" type="submit" value="Edit">
	</form:form>
</body>
</html>