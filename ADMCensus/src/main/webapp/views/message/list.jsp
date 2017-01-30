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



<!-- Menu Folders -->

<div>

	<ul>
		<jstl:forEach var="folder" items="${folders}">
			<li><a href="message/list.do?folderId=${folder.id}"> <jstl:out
						value="${folder.name}" />
			</a></li>
		</jstl:forEach>
	</ul>

</div>

<!-- Listing messages -->

<div>

<a href="message/create.do"> <spring:message code="message.create" /></a><br>

<display:table pagesize="100" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestURI}" id="message">

	<!-- Action links -->

	<!-- Attributes -->
	<jstl:choose>
		<jstl:when test="${isInboxOrSpam}">
			<spring:message code="message.sender" var="senderMessage" />
			<display:column property="sender.name" title="${senderMessage}"
				sortable="false" />
		</jstl:when>
		<jstl:otherwise>
			<spring:message code="message.recipient" var="recipientMessage" />
			<display:column property="recipient.name" title="${recipientMessage}"
				sortable="false" />
		</jstl:otherwise>
	</jstl:choose>
	<spring:message code="message.subject" var="subjectMessage" />
	<display:column property="subject" title="${subjectMessage}"
		sortable="false" />

	<spring:message code="message.moment" var="momentMessage" />
	<display:column property="moment" title="${momentMessage}"
		sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="message.body" var="bodyMessage" />
	<display:column property="body" title="${bodyMessage}" sortable="false" />


	<display:column>
		<a href="message/delete.do?messageId=${message.id}"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')">
			<spring:message code="message.delete" />
		</a>
	</display:column>

</display:table>

</div>