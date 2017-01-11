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

<!-- Censo con más votos-->
<spring:message var="action2Header" code="dashboard.2" />
<jstl:out value="${action2Header}"></jstl:out>

<div id="censusMoreVotes">
<display:table name="censusMoreVotes" id="row"
	requestURI="/dashboard.do">
	<spring:message var="censusMoreVotesHeader" code="dashboard.censusMoreVotes" />
	<display:column title="${censusMoreVotesHeader}">
		<jstl:out value="${row}" />
	</display:column>
	</display:table>
</div>

<!-- Censo con menos votos-->
<spring:message var="action3Header" code="dashboard.3" />
<jstl:out value="${action3Header}"></jstl:out>

<div id="censusLessVotes">
<display:table name="censusLessVotes" id="row"
	requestURI="/dashboard.do">
	<spring:message var="censusLessVotesHeader" code="dashboard.censusLessVotes" />
	<display:column title="${censusLessVotesHeader}">
		<jstl:out value="${row}" />
	</display:column>
	</display:table>
</div>