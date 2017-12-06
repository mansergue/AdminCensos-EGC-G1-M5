<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!--  <link rel="stylesheet" href="styles/style.css"> -->

<div id="cssmenu">
	<ul>
	
		<security:authorize access="isAnonymous()">
			<li><a href="user/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a href="https://authb.agoraus1.egc.duckdns.org/register.php"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li class='has-sub'><a><spring:message code="master.page.listar" /></a>
			<ul>
				<li><a href="census/votesByUser.do"><spring:message code="master.page.census.activeVotes" /></a></li>
				<li><a href="census/getAllCensusByCreador.do"><spring:message code="master.page.census.byCreator" /></a></li>
				<li><a href="census/getCensusesToRegister.do"><spring:message code="master.page.census.listRegister" /></a></li>
			</ul>
			</li>
			<li><a href="https://recuento.agoraus1.egc.duckdns.org/views/crearVotacion.html"><spring:message code="master.page.votaciones" /></a></li>
			<li><a href="census/create.do"><spring:message code="master.page.census.create" /></a></li>
			<li><a href="message/listDefault.do"><spring:message code="master.page.issues" /></a></li>
			<li><a href="api/methods.do"><spring:message code="master.page.api" /></a></li>
			<li><a class="fNiv" href="dashboard/list.do"><spring:message code="master.page.dashboard" /></a></li>
			<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
		</security:authorize>
	
	</ul>
</div>