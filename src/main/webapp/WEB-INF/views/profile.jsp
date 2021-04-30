<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="utf-8">
<title>${user.name}'s Profile</title>
</head>

<body>
	<div class="container">
		<h1 class="display-3 text-center">${user.name}'s Profile</h1>
		<br><br>
		<div class="text-center">
			<img src="${profile.photoPath}" class="rounded"
				style="width: 500px; height: 500px" alt="photo">
		</div>
		<br><br><br>
		<dl class="row">

			<dt class="col-sm-3">Name</dt>
			<dd class="col-sm-9">${user.name}</dd>

			<dt class="col-sm-3">Username</dt>
			<dd class="col-sm-9">${user.username}</dd>
			
			<dt class="col-sm-3">Gender</dt>
			<dd class="col-sm-9">${user.gender}</dd>

			<dt class="col-sm-3">Interested in</dt>
			<dd class="col-sm-9">
				<c:if test="${profile.genderPreference == 'No preference'}">No preference</c:if>
				<c:if test="${profile.genderPreference == 'Male'}">Men</c:if>
				<c:if test="${profile.genderPreference == 'Female'}">Women</c:if>
			</dd>
			
			<dt class="col-sm-3">Birthday</dt>
			<dd class="col-sm-9">${dob}</dd>

			<dt class="col-sm-3">Location</dt>
			<dd class="col-sm-9">${location}</dd>

			<dt class="col-sm-3">Last Online</dt>
			<dd class="col-sm-9">${lastOnline}</dd>

			<dt class="col-sm-3">Biography</dt>
			<dd class="col-sm-9">${profile.bio}</dd>

			<dt class="col-sm-3">Tags</dt>
			<dd class="col-sm-9">
				<c:forEach items="${tags}" var="tag">${tag.name}<br>
				</c:forEach>
			</dd>
		</dl>
	</div>
</body>

<%@ include file="common/footer.jsp"%>

</html>
