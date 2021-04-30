<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Change Password</title>

</head>

<body>
	<div class="container">
		<form:form method="POST" modelAttribute="user" class="form-signin">
			<h1 class="display-4 text-center">Update Account Details</h1>
			<br><br>

			<spring:bind path="name">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Name:</strong>
					<form:input type="text" path="name" class="form-control"
						placeholder="Name" autofocus="true"></form:input>
					<form:errors path="name"></form:errors>
				</div>
			</spring:bind>

			<br>


			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Email:</strong>
					<form:input type="text" path="email" class="form-control"
						placeholder="Email" autofocus="true"></form:input>
					<form:errors path="email"></form:errors>
				</div>
			</spring:bind>

			<br>


			<spring:bind path="phonenumber">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Phone Number:</strong>
					<form:input type="text" path="phonenumber" class="form-control"
						placeholder="Phonenumber"></form:input>
					<form:errors path="phonenumber"></form:errors>
				</div>
			</spring:bind>

			<spring:bind path="Gender">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<br> <strong>Gender:</strong> <br>
					Male <form:radiobutton path="Gender" value="Male" />
					Female <form:radiobutton path="Gender" value="Female" />
					Other <form:radiobutton path="Gender" value="Other" />
					<form:errors path="gender"></form:errors>
				</div>
			</spring:bind>

			<td><form:hidden path="id" value="${user.id}" /></td>
			<td><form:hidden path="Username" value="${user.username}" /></td>
			<td><form:hidden path="dob" value="${user.dob}" /></td>
			<td><form:hidden path="password" value="${user.password}" /></td>
			
			<br><br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>
	</div>
</body>

<%@ include file="common/footer.jsp"%>

</html>
