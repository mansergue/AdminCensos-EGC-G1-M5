<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div id="form-wrapper" class="myform">
	<div class="divCenter">
		<form:form id="form" action="user/login.do" modelAttribute="userAccount">
			<form:hidden path="id" />
			<form:hidden path="version" />

			<form:label path="username">
				<spring:message code="user.username" />
			</form:label>
			<form:input path="username" />
			<form:errors class="error" path="username" />
			<br />

			<form:label path="password">
				<spring:message code="user.password" />
			</form:label>
			<form:password path="password" />
			<form:errors class="error" path="password" />
			<br />
			
			<input type="submit" value="<spring:message code="user.login" />" />

			<jstl:if test="${message != null}">
				<div class="error">
					<spring:message code="user.error" />
				</div>
			</jstl:if>
		</form:form>

	</div>
</div>
