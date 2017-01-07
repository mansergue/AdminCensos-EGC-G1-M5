<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<table>
	<tr>
		<th><spring:message code="dashboard.actions" /></th>
	</tr>
</table>

<!-- Número de censos abiertos.-->
<spring:message var="action1Header" code="dashboard.1" />
<jstl:out value="${action1Header}"></jstl:out>

<div id="openCensuses">
<display:table name="openCensuses" id="row"
	requestURI="/dashboard.do">
	<spring:message var="openCensusesHeader" code="dashboard.openCensuses" />
	<display:column title="${openCensusesHeader}">
		<jstl:out value="${row}" />
	</display:column>
	</display:table>
</div>