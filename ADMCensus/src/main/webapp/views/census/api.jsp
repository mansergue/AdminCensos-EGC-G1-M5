<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

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



<!-- <div>
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
</div>  -->
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
<br><br>

<div>
	<p>
		<strong>Devuelve un censo con sus usuarios al preguntar por una votaci&oacute;n:</strong>
	</p>
	<p>
		<font size="5">
			/census/findCensusByVote.do?idVotacion="param"
		</font>
	</p>
	<p>
		param: Id de la votaci&oacute;n a consultar. Por ejemplo &quot;999&quot;.
	</p>
	<p>Se obtendr&aacute; una respuesta en formato JSON con todos los par&aacute;metros del censo buscado, siguiendo el siguiente formato: 
		<p align="center">
		{id: 6,
		</p>
		<p align="center">
			version: 0,
		</p>
		<p align="center">
		idVotacion: 999,
		</p>
		<p align="center"> 
		title: "Elecciones",
		</p>
		<p align="center">
		startDate:1455046682000,
		</p>
		<p align="center"> 
		endDate: 1465583882000, 
		</p>
		<p align="center">
		tipo: "cerrado",
		</p>
		<p align="center">
		postalCode:"12345",
		</p>
		<p align="center"> 
		comunidadAutonoma: "Andalucía",
		</p>
		<p align="center"> 
		usernameCreator: "aletormar",
		</p>
		<p align="center">
		votoPorUsuario: { 
		</p>
		<p align="center">
		aletormar: true, 
		</p>
		<p align="center">
		alerodrom: false } }
		</p>
</div>