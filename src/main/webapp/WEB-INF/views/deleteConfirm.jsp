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
        <br><br><br><br><br>
        <h1 class="display-4 text-center">Are you sure you want to delete your account?</h1>
        <br><br>
        <blockquote class="blockquote text-center">
            <p class="mb-0">This action is irreversible.</p>
        </blockquote>
        <br><br><br><br><br>
        <div class="container text-center">
            <form:form method="POST">
                <button type="button" class="btn btn-secondary btn-lg" name="delete" onclick="location.href='/edit/updatePassword'">No, I've changed my mind.</button>
                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                <button type="submit" class="btn btn-primary btn-lg">Yes, delete my account.</button>
            </form:form>
        </div>
        <br><br><br><br><br>
	</div>
</body>

<%@ include file="common/footer.jsp"%>

</html>
