<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Log in with your account</title>
<!-- Bootstrap CSS 
      <link href="${contextPath}/common/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/common/css/common.css" rel="stylesheet"> -->

</head>

<body>

	<div class="container">
		<form method="POST" action="${contextPath}/login" class="form-signin">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> Username: <input name="username" type="text"
					class="form-control" placeholder="Username" autofocus="true" /> <br>
				<br> Password: <input name="password" type="password"
					class="form-control" placeholder="Password" /> <span>${error}</span>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <br> <br>

				<button class="btn btn-lg btn-primary btn-block" type="submit">Log
					In</button>
				<h4 class="text-center">
					<a href="${contextPath}/registration">Create an account</a>
				</h4>
			</div>
		</form>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>


</body>

<%@ include file="common/footer.jsp"%>

</html>
