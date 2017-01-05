<%@page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="user/entry.do" modelAttribute="userAccount">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="username">
		<spring:message code="user.username" />:
	</form:label>
	<form:input path="username" />
	<form:errors class="error" path="username" />

	<form:label path="password">
		<spring:message code="user.password" />:
	</form:label>
	<form:password path="password" />
	<form:errors class="error" path="password" />

	<button type="submit" name="save">
		<spring:message code="user.login" />
	</button>

</form:form>