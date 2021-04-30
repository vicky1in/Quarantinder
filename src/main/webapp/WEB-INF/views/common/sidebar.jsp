<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- https://codepen.io/andgatjens/pen/XWJPddE -->

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">

</head>
<body>
<div id="wrapper">

	<!-- Sidebar -->
	<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<li class="sidebar-brand"><a href="/welcome"> Quarantinder </a></li>
			<li><a href="/suggestions">Find a Match</a></li>
			<li><a href="/matches">Matches</a></li>
			<li><a href="/blog/posts">Blog</a></li>
			<li>
				<div class="dropdown">
					<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Profile
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="/profile">View</a>
						<a class="dropdown-item" href="/editprofile">Edit</a>
					</div>
				</div>
			</li>
			<li>
				<div class="dropdown">
					<a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Account
					</a>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="/edit/updateDetails">Details</a>
						<a class="dropdown-item" href="/edit/updatePassword">Security</a>
						<a class="dropdown-item" href="/blocks">Blocked Users</a>
					</div>
				</div>
			</li>
			<li><a href="/zoom">Video Chatting</a></li>
			<c:choose>
				<c:when test="${not empty username }">
					<br><li><a class="btn btn-primary text-white" style="width: 248px; padding-right: 30px;" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>
	<!-- /#sidebar-wrapper -->

	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>