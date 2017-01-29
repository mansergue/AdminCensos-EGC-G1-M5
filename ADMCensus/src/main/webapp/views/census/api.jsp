<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div>
	<p>
		<strong>Consultar si un usuario puede votar en una determinada votaci&oacute;n:</strong>
	</p>
	<p>
		<font size="5">
			/census/canVote.do?idVotacion=&quot;param1&quot; &amp;username=&quot;param2&quot;
		</font>
	</p>
	<p>param1: Id de la votaci&oacute;n a consultar. Por ejemplo "23".</p>
	<p>
		param2: Nombre del usuario sobre el que se efectua la comprobaci&oacute;n.<br>
		Por ejemplo &quot;user1&quot;.
	</p>
	<p>Se obtendr&aacute; una respuesta en formato JSON de tipo {&quot;result&quot;:&quot;no&quot;} o {&quot;result&quot;:&quot;si&quot;}</p>
</div>
<br><br>
<div>
	<p>
		<strong>Actualiza el estado de una votaci&oacute;n:</strong>
	</p>
	<p>
		<font size="5">
			/census/updateUser.do?idVotacion=&quot;param1&quot; &amp;tipoVotacion=&quot;param2&quot; &amp;username=&quot;param3&quot;
		</font>
	</p>
	<p>
		param1: Id de la votaci&oacute;n a consultar. Por ejemplo &quot;23&quot;.
	</p>
	<p>
		param2: Tipo de votaci&oacute;n. Admite dos valores &quot;abierto&quot; o &quot;cerrado&quot;.
	</p>
	<p>
		param3: Nombre del usuario sobre el que se efectua la comprobaci&oacute;n.<br>
		Por ejemplo &quot;user1&quot;.
	</p>
	<p>
		Se obtendr&aacute; una respuesta en formato JSON de tipo {&quot;result&quot;:&quot;no&quot;} o {&quot;result:&quot;si&quot;}
	</p>
</div>
<br><br>
<div>
	<p>
		<strong>Dictamina si se puede borrar una votaci&oacute;n:</strong>
	</p>
	<p>
		<font size="5">
			/census/canDelete.do?idVotacion="param1" &amp;username= &quot;param2&quot;
		</font>
	</p>
	<p>
		param1: Id de la votaci&oacute;n a consultar. Por ejemplo &quot;23&quot;.
	</p>
	<p>
		param2:  Nombre del usuario sobre el que se efectua la comprobaci&oacute;n.
	</p>
	<p>
		Se obtendr&aacute; una respuesta en formato JSON de tipo {&quot;result&quot;:&quot;no&quot;} o {&quot;result&quot;:&quot;si&quot;}
	</p>
</div>