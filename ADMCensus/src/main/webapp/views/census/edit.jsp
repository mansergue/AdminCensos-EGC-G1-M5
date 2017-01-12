<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<form:form id="form" action="census/create.do" modelAttribute="census"
	method="POST">

	<form:hidden path="id" />
	<form:hidden path="version" />



	<form:label path="title">
		<spring:message code="census.votes" />:
	</form:label>

	<form:select path="title">
		<form:option label="-----" value="0" />
		<form:options items="${votes}" itemLabel="titulo" itemValue="titulo" />
	</form:select>
	<br />

	<form:label path="tipo">
		<spring:message code="census.tipo" />:
	</form:label>
	<form:select path="tipo">
		<form:option label="Abierto" value="Abierto" />
		<form:option label="Cerrado" value="Cerrado" />
	</form:select>
	<br />


	<input type="submit" name="save"
		value="<spring:message code="census.save" />"  /> 
 		 &nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="census.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>

