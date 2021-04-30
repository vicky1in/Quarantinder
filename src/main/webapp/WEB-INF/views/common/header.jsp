<%@ include file="include.jsp"%>

<%!int pageCount = 1;

	void addCount() {
		pageCount++;
	}%>

<%
	addCount();
%>

<form id="logoutForm" method="POST" action="${contextPath}/logout" style="position: absolute;">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="username" value="${pageContext.request.userPrincipal}" />


<c:choose>
	<c:when test="${not empty username }">
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<html>
<head>
<title>Quarantinder</title>
</head>

<!--  Bootstrap 

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" href="../../resources/stylesheet.css" /> -->


<!--  jQuery 
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script> -->


<body>
	<center>
		<br>
		<h2><a href="/welcome">Quarantinder</a></h2>
		<p>
			Social distance with
			<%=pageCount%>
			other lovesick people today.
		</p>
	</center>
	
	<br />
	<br />
</body>