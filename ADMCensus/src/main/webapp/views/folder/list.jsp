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

<a href="folder/create.do"> <spring:message code="folder.create" /></a>

<!-- Listing folders -->
<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="folders" requestURI="${requestURI}" id="folder">

	<!-- Action links -->

	<!-- Attributes -->
	<spring:message code="folder.name" var="nameFolder" />
	<display:column property="name" title="${nameFolder}" sortable="false" />


	<display:column>
		<jstl:if
			test="${folder.name!='inbox' && folder.name!='outbox' && folder.name!='trashbox' && folder.name!='spambox'}">
			<a href="folder/edit.do?folderId=${folder.id}"> <spring:message
					code="folder.edit" />
			</a>
		</jstl:if>
	</display:column>

	<display:column>
		<jstl:if
			test="${folder.name!='inbox' && folder.name!='outbox' && folder.name!='trashbox' && folder.name!='spambox'}">
			<a href="folder/delete.do?folderId=${folder.id}"
				onclick="return confirm('<spring:message code="folder.confirm.delete" />')">
				<spring:message code="folder.delete" />
			</a>
		</jstl:if>
	</display:column>

</display:table>