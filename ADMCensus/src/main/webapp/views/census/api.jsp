<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>API</title>
</head>
<body>


	<p>
		<strong>Consultar si un usuario puede votar en una
			determinada votacion:</strong>
	</p>
	<br>

	<p>
		<big><big>/census/canVote.do?idVotacion="param1"
				&username= "param2" </big></big>
	</p>

	<p>param1: Id de la votación a consultar. Por ejemplo "23".</p>
	<p>
		param2: Nombre del usuario sobre el que se efectua la comprobación.<br>
		Por ejemplo "user1".
	</p>

	<p>Se obtendrá una respuesta en formato JSON de tipo
		{"result":"no"} o {"result":"si"}</p>
	<br><br>
	<p>
		<strong>Actualiza el estado de una votacion:</strong>
	</p>

	<p>
		<big><big>/census/updateUser.do?idVotacion="param1"
				&tipoVotacion= "param2" &username= "param3" </big></big>
	</p>

	<p>param1: Id de la votación a consultar. Por ejemplo "23".</p>
	<p>
		param2: Tipo de votacion. Admite dos valores "abierto" o "cerrado".<br>
		
	</p>
	<p>
		param3: Nombre del usuario sobre el que se efectua la comprobación.<br>
		Por ejemplo "user1".
	</p>

	<p>Se obtendrá una respuesta en formato JSON de tipo
		{"result":"no"} o {"result":"si"}</p>
</body>
</html>

<strong>Dictamina si se puede borrar una votación:</strong>
	</p>

	<p>
		<big><big>/census/canDelete.do?idVotacion="param1"
				&username= "param2" </big></big>
	</p>

	<p>param1: Id de la votación a consultar. Por ejemplo "23".</p>
	<p>
		param2:  Nombre del usuario sobre el que se efectua la comprobación..<br>
		
	</p>
	

	<p>Se obtendrá una respuesta en formato JSON de tipo
		{"result":"no"} o {"result":"si"}</p>
</body>
</html>