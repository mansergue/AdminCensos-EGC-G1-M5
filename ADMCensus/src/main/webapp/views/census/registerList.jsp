<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" name="censuses"
	requestURI="${requestURI}" id="row">


	<!-- Attributes -->
	<spring:message code="census.token_propietario" var="usernameCreator" />
	<display:column property="usernameCreator" title="${usernameCreator}" sortable="true" />

	<spring:message code="census.votacio.name" var="title" />
	<display:column property="title" title="${title}"
		sortable="true" />

	<spring:message code="census.fecha.inicio" var="startDate" />
	<display:column title="${startDate}" sortable="true">
		<fmt:formatDate value="${row.startDate}"
			pattern="dd/MM/yyyy" />
		<br />
	</display:column>

	<spring:message code="census.fecha.fin" var="endDate" />
	<display:column title="${endDate}" sortable="true">
		<fmt:formatDate value="${row.endDate}" pattern="dd/MM/yyyy" />
		<br />
	</display:column>

	<spring:message code="census.number.person" var="sizeHeader" />
	<display:column title="${sizeHeader}" sortable="true">
		<jstl:out value="${row.votoPorUsuario.size() }"></jstl:out>
	</display:column>

	<spring:message code="census.register" var="registerHeader" />
	<display:column title="${registerHeader}" sortable="false">
		<a href="census/registerUser.do?censusId=${row.id }"><spring:message
				code="census.register"></spring:message></a>
	</display:column>


</display:table>
<br />
<acme:cancel url="welcome/index.do" code="census.back" />