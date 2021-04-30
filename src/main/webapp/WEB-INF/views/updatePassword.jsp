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
			<h1 class="display-4 text-center">Change Password</h1>
			<br><br>

			<spring:bind path="oldPasswordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Old Password:</strong>
					<form:input type="oldPasswordConfirm" path="oldPasswordConfirm"
						class="form-control" placeholder="Type old password"></form:input>
					<form:errors path="oldPasswordConfirm"></form:errors>
				</div>
			</spring:bind>

			<br>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>New Password:</strong>
					<form:input type="password" path="password" class="form-control"
						placeholder="Password"></form:input>
					<form:errors path="password"></form:errors>
				</div>
			</spring:bind>
			<br>

			<spring:bind path="passwordConfirm">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Confirm Password:</strong>
					<form:input type="password" path="passwordConfirm"
						class="form-control" placeholder="Confirm your password"></form:input>
					<form:errors path="passwordConfirm"></form:errors>
				</div>
			</spring:bind>
			<td><form:hidden path="id" value="${user.id}" /></td>
			<td><form:hidden path="email" value="${user.email}" /></td>
			<td><form:hidden path="name" value="${user.name}" /></td>
			<td><form:hidden path="gender" value="${user.gender}" /></td>
			<td><form:hidden path="phonenumber" value="${user.phonenumber}" /></td>
			<td><form:hidden path="Username" value="${user.username}" /></td>
			<td><form:hidden path="dob" value="${user.dob}" /></td>

			<br><br>
			<button class="btn btn-lg btn-primary btn-block" type="submit" name="update">Submit</button>

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-lg btn-danger btn-block " data-toggle="modal" data-target="#exampleModal">
				Delete Account
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Account Deletion Confirmation</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					</div>
					<div class="modal-body">
						Do you want to delete your account?
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-secondary" name="delete" onclick="location.href='/edit/deleteConfirm'">Delete Account</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">No, Take Me Back</button>
					</div>
				</div>
				</div>
			</div>
		</form:form>

		

	</div>
</body>

<%@ include file="common/footer.jsp"%>

</html>
