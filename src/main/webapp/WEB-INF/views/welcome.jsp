<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>

</head>
<body>
	<div class="container">
		<br><br><br><br><br>
		<h1 class="display-1 text-center">Welcome, ${user.name}!</h1>
		<br><br>
		<blockquote class="blockquote text-center">
			<p class="mb-0">Click on the links from the sidebar on the left to begin exploring.</p>
		</blockquote>
		<br><br><br><br><br>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

	<script> if ("${error}" !==  ""){
		alert("${error}")
	}
		</script>
</body>

</html>
<%@ include file="common/footer.jsp"%>
