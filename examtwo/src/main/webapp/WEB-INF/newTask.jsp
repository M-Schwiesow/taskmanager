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
<title>Create Task</title>
</head>
<body>

	<h1 class="title">Create a new task</h1>
	<form:form method="POST" action="/tasks/new" modelAttribute="task">
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
		<input class="button is-rounded is-info" type="submit" value="Create">
	</form:form>
</body>
</html>