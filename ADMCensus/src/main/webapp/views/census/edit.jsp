<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<div id="form-wrapper" class="myform">
	<div class="divCenter">
	
	
<form:form id="form" action="census/edit.do" modelAttribute="census">

	<form:hidden path="id" />
	<form:hidden path="version" />
<%-- 	<form:hidden path="tokenPropietario" /> --%>
	<form:hidden path="idVotacion"/>
	<form:hidden path="title"/>
	<form:hidden path="startDate"/>
	<form:hidden path="endDate"/>
	<form:hidden path="postaclCode"/>
	<form:hidden path="votoPorUsuario"/>
	<form:hidden path="usernameCreator"/>
	

	<select name="votes">
		<c:forEach var="vote" items="${votes}">
			<option value="${vote.titulo}"></option>
		</c:forEach>
	</select>
	
	<form:label path="tipo">
		<spring:message code="census.tipo" />:
	</form:label>
	<form:input path="tipo" />
	<form:errors cssClass="error" path="tipo" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="census.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="census.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');" />
	<br />

</form:form>

