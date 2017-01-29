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
	<br>
	
	<form:label path="comunidadAutonoma">
		<spring:message code="census.comunidadAutonoma" />:
	</form:label>
	<form:select path="comunidadAutonoma">
		<form:option label="Anadalucía" value="Anadalucía" />
		<form:option label="Andalucía" value="Andalucía" />
		<form:option label="Aragón" value="Aragón" />
		<form:option label="Principado de Asturias" value="Principado de Asturias" />
		<form:option label="Baleares" value="Baleares" />
		<form:option label="Canarias" value="Canarias" />
		<form:option label="Cantabria" value="Cantabria" />
		<form:option label="Castilla-La Mancha" value="Castilla-La Mancha" />
		<form:option label="Castilla y León" value="Castilla y León" />
		<form:option label="Cataluña" value="Cataluña" />
		<form:option label="Extremadura" value="Extremadura" />
		<form:option label="Galicia" value="Galicia" />
		<form:option label="La Rioja" value="La Rioja" />
		<form:option label="Comunidad de Madrid" value="Comunidad de Madrid" />
		<form:option label="Región de Murcia" value="Región de Murcia" />
		<form:option label="Comunidad Foral de Navarra" value="Comunidad Foral de Navarra" />
		<form:option label="País Vasco" value="País Vasco" />
		<form:option label="Comunidad Valenciana" value="Comunidad Valenciana" />
	</form:select>
	<br>

	<form:label path="tipo">
		<spring:message code="census.tipo" />:
	</form:label>
	<form:select path="tipo">
		<form:option label="Abierto" value="Abierto" />
		<form:option label="Cerrado" value="Cerrado" />
	</form:select>
	<br>

	<input type="submit" name="save"
		value="<spring:message code="census.save" />"  /> 
 		 &nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="census.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br>

</form:form>