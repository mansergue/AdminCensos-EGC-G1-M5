<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="api.casos" />
<br>

<div id="openCensuses">
<display:table name="openCensuses" id="row">
	<display:column title="Consultar si un usuario puede votar en una determinada votacion:">
		<div>
			<p>
				<big><big>/census/canVote.do?idVotacion="param1" &username= "param2" </big></big>
			</p>
			<p>param1: Id de la votación a consultar.
			<br>
			Por ejemplo "23".</p>
			<p>param2: Nombre del usuario sobre el que se efectua la comprobación.
			<br>
			Por ejemplo "user1".</p>
			<p>Se obtendrá una respuesta en formato JSON de tipo {"result":"no"} o {"result":"si"}</p>
		</div>
	</display:column>
</display:table>
</div>
<br>
<div id="openCensuses">
<display:table name="openCensuses" id="row">
	<display:column title="Actualiza el estado de una votacion:">
		<div>
			<p>
				<big><big>/census/updateUser.do?idVotacion="param1" &tipoVotacion= "param2" &username= "param3" </big></big>
			</p>
			
			<p>param1: Id de la votación a consultar. Por ejemplo "23".</p>
			<p>param2: Tipo de votacion. Admite dos valores "abierto" o "cerrado".<br>
			</p>
			<p>param3: Nombre del usuario sobre el que se efectua la comprobación.<br>
			Por ejemplo "user1".
			</p>
			<p>Se obtendrá una respuesta en formato JSON de tipo {"result":"no"} o {"result":"si"}</p>
		</div>
	</display:column>
</display:table>
</div>