<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>

<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>


<body>

	<div class="container" style="margin: 0 auto;">

		<form:form method="POST" modelAttribute="userForm" class="form-signin">
			<h2 class="form-signin-heading">Create your account</h2>

			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Name:
					<form:input type="text" path="name" class="form-control"
						placeholder="Name" autofocus="true"></form:input>
					<form:errors path="name"></form:errors>
				</div>
			</spring:bind>

			<br>


			<spring:bind path="username">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Username:
					<form:input type="text" path="username" class="form-control"
						placeholder="Username"></form:input>
					<form:errors path="username"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Email:
					<form:input type="text" path="email" class="form-control"
						placeholder="Email" autofocus="true"></form:input>
					<form:errors path="email"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="phonenumber">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Phone number:
					<form:input type="text" path="phonenumber" class="form-control"
						placeholder="Phonenumber" autofocus="true"></form:input>
					<form:errors path="phonenumber"></form:errors>
				</div>
			</spring:bind>

			<br>

			<spring:bind path="Gender">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<br> Gender: <br> Male
					<form:radiobutton path="Gender" value="Male" />
					Female
					<form:radiobutton path="Gender" value="Female" />
					Other
					<form:radiobutton path="Gender" value="Other" />
					<form:errors path="gender"></form:errors>
				</div>
			</spring:bind>



			<br>
			<br>

			<spring:bind path="dob">
				<div class="form-group ${status.error ? 'has-error' : ''}">

					<label for="dob">Enter Birthday:</label> <input type="date" path="dob"
						name="dob" id="date">
					<form:errors path="dob"></form:errors>
				</div>

			</spring:bind>



			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Password:
					<form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>

			<br>


			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					Confirm password:
					<form:input type="password" path="passwordConfirm"
						class="form-control" placeholder="Confirm your password"></form:input>
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#datepicker").datepicker();
		});
	</script>
</body>

<%@ include file="common/footer.jsp"%>

</html>