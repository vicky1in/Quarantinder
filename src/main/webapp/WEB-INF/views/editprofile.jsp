<%@ include file="common/header.jsp"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Edit Your Profile</title>
</head>

<body>
	<div class="container">

		<h1 class="display-4 text-center">Edit Profile</h1>

		<form:form method="POST" modelAttribute="profileForm"
			class="form-signin" enctype="multipart/form-data">
			<br><br>
			<strong>Photo:</strong>
			<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile"
				name="image" accept="image/png, image/jpeg" /> <label
				class="custom-file-label" for="customFile">Choose file</label>
			</div>
			<br><br><br>
			<spring:bind path="locationId">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Location:</strong>
					<form:select path="locationId">
						<c:choose>
							<c:when test="${location.locationID != 0}">
								<option value="${location.locationID}" selected="true">${location.city}</option>
							</c:when>
						</c:choose>
						<form:options items="${locationList}" itemValue="locationID"
							itemLabel="city" />
					</form:select>
					<form:errors path="locationId"></form:errors>
				</div>
			</spring:bind>
			<br>
			<spring:bind path="genderPreference">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Interested in:</strong>
					<form:select path="genderPreference">
						<option value="No preference" selected="selected">No preference</option>
						<option value="Male">Men</option>
						<option value="Female">Women</option>
					</form:select>
				</div>
			</spring:bind>
			<br>
			<spring:bind path="minAgePreference">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Minimum age:</strong>
					<form:input type="number" path="minAgePreference" value="${profile.minAgePreference}" style="margin-bottom: 0;" min="18" max="100"></form:input>
					<form:errors path="minAgePreference"></form:errors>
                </div>
			</spring:bind>
			<br>
			<spring:bind path="maxAgePreference">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Maximum age:</strong>
					<form:input type="number" path="maxAgePreference" value="${profile.maxAgePreference}" min="18" max="100"></form:input>
					<form:errors path="maxAgePreference"></form:errors>
                </div>
			</spring:bind>
			<br>
			<spring:bind path="bio">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<strong>Biography:</strong>
					<form:input type="text" path="bio" class="form-control" style="padding-top: 20px; padding-bottom: 180px;"
						placeholder="e.g. hello i am a gamer" autofocus="true"
						value="${profile.bio}"></form:input>
					<form:errors path="bio"></form:errors>
				</div>
			</spring:bind>
			<br>
			<strong>Interests</strong>
			<br>
			<spring:bind path="tags">
				<div class="form-group  ${status.error ? 'has-error' : ''}">
					<form:checkboxes path="tags" items="${tags}" itemLabel="name" itemValue="tagID" delimiter="<br/>" 
						class="checkbox" /><br>
					<form:errors path="Tags"></form:errors>
				</div>
			</spring:bind>
			<br><br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>
	</div>
	<script type="application/javascript">
		
		
        $('input[type="file"]').change(function(e){
            var fileName = e.target.files[0].name;
            $('.custom-file-label').html(fileName);
        });
    
	
	</script>


</body>

<%@ include file="common/footer.jsp"%>

</html>
