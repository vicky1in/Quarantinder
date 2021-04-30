<html>
<head>
<title>Matches</title>
</head>

<%@ include file = "common/header.jsp" %>


<body>
	<div class="container" style=" margin:0 auto;" >
        <h3>Matches</h3>
        <div class="list-group list-group-flush mt-3 mb-5">
        <c:if test="${empty acceptedMatches}">
            <p>Oh looks like you haven't made any matches yet.</p>
        </c:if>
        <c:forEach items="${acceptedMatches}" var="match"> 
            <c:if test="${match.userA.username != pageContext.request.userPrincipal.name}">
                <div class="list-group-item flex-column align-items-start">
                    <div class="row mt-2 mb-2">
                        <div class="col-3"> 
                        <a href="/profile/${match.userA.id}">
                            <img src="${match.userA.profile.photoPath}" class="rounded-circle" alt="photo" style="width:80%;height:auto;">
                        </a>
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col">
                                    <h5 class="mb-1">${match.userA.name}</h5>
                                </div>
                                <div class="col-md-auto">
                                    <a href="/blog/visit/${match.userA.id}">
                                        <button class="btn btn-primary">Blog</button>
                                    </a>
                                </div>

                                <div class="col-md-auto">
                                    <a href="/messaging?id=${match.userA.username}">
                                        <button class="btn btn-primary">Message</button>
                                    </a>
                                </div>
                                <div class="col-md-auto">
                                    <!-- Unmatch Modal -->
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#unmatchModal">Unmatch</button>
                                    <div class="modal fade" id="unmatchModal" tabindex="-1" role="dialog" aria-labelledby="unmatchModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Unmatch User</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure you want to unmatch ${match.userA.name}?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    <form:form id="match-cancel-form" method="POST" action="/matches/cancel/${match.matchID}">
                                                        <button class="btn btn-primary">Yes</button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Block Modal -->
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#blockModal">Block</button>
                                    <div class="modal fade" id="blockModal" tabindex="-1" role="dialog" aria-labelledby="blockModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Block User</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure you want to block ${match.userA.name}?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    <form:form id="match-block-form" method="POST" action="/matches/block/${match.userA.id}" class="form-inline">
                                                        <button class="btn btn-primary" type="submit">Yes</button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p class="mb-1">${match.userA.profile.bio}</p>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${match.userB.username != pageContext.request.userPrincipal.name}">
                <div class="list-group-item flex-column align-items-start">
                    <div class="row mt-2 mb-2">
                        <div class="col-3"> 
                        <a href="/profile/${match.userB.id}">
                            <img src="${match.userB.profile.photoPath}" class="rounded-circle" alt="photo" style="width:80%;height:auto;">
                        </a>
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col">
                                    <h5 class="mb-1">${match.userB.name}</h5>
                                </div>
                                <div class="col-md-auto">
                                    <a href="/blog/visit/${match.userB.id}">
                                        <button class="btn btn-primary">Blog</button>
                                    </a>
                                </div>
                                <div class="col-md-auto">
                                    <a href="/messaging?id=${match.userB.username}">
                                        <button class="btn btn-primary">Message</button>
                                    </a>
                                </div>
                                <div class="col-md-auto">
                                    <!-- Unmatch Modal -->
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#unmatchModal">Unmatch</button>
                                    <div class="modal fade" id="unmatchModal" tabindex="-1" role="dialog" aria-labelledby="unmatchModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Unmatch User</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure you want to unmatch ${match.userB.name}?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    <form:form id="match-cancel-form" method="POST" action="/matches/cancel/${match.matchID}">
                                                        <button class="btn btn-primary">Yes</button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Block Modal -->
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#blockModal">Block</button>
                                    <div class="modal fade" id="blockModal" tabindex="-1" role="dialog" aria-labelledby="blockModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Block User</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Are you sure you want to block ${match.userB.name}?</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    <form:form id="match-block-form" method="POST" action="/matches/block/${match.userB.id}" class="form-inline">
                                                        <button class="btn btn-primary">Yes</button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p class="mb-1">${match.userB.profile.bio}</p>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        </div>

        <h3>Requests</h3>
        <h5>Pending Action</h5>
        <div class="list-group list-group-flush mt-3 mb-5">
        <c:if test="${empty receivedMatches}">
            <p>Oh looks like you haven't received any new matches yet.</p>
        </c:if>
        <c:forEach items="${receivedMatches}" var="match">
            <div class="list-group-item flex-column align-items-start">
                <div class="row mt-2 mb-2">
                    <div class="col-3"> 
                    <a href="/profile/${match.userA.id}">
                        <img src="${match.userA.profile.photoPath}" class="rounded-circle" alt="photo" style="width:80%;height:auto;">
                    </a>
                    </div>
                    <div class="col-9">
                        <div class="row">
                            <div class="col">
                                <h5 class="mb-1">${match.userA.name}</h5>
                            </div>
                            <div class="col-md-auto">
                                <form:form id="match-accept-form" method="POST" action="/matches/accept/${match.matchID}">
                                    <button class="btn btn-primary">Accept</button>
                                </form:form>
                            </div>
                            <div class="col-md-auto">
                                <form:form id="match-decline-form" method="POST" action="/matches/cancel/${match.matchID}">
                                    <button class="btn btn-primary">Decline</button>
                                </form:form>
                            </div>
                        </div>
                        <p class="mb-1">${match.userA.profile.bio}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>

        <h5>Sent</h5>
        <div class="list-group list-group-flush mt-3 mb-5">
        <c:if test="${empty sentMatches}">
            <p>Oh it looks like you haven't got any outstanding match requests.</p>
        </c:if>
        <c:forEach items="${sentMatches}" var="match"> 
            <div class="list-group-item flex-column align-items-start">
                <div class="row mt-2 mb-2">
                    <div class="col-3"> 
                    <a href="/profile/${match.userB.id}">
                        <img src="${match.userB.profile.photoPath}" class="rounded-circle" alt="photo" style="width:80%;height:auto;">
                    </a>
                    </div>
                    <div class="col-9">
                        <div class="row">
                            <div class="col">
                                <h5 class="mb-1">${match.userB.name}</h5>
                            </div>
                            <div class="col-md-auto">
                                <form:form id="match-request-cancel-form" method="POST" action="/matches/cancel/${match.matchID}">
                                    <button class="btn btn-primary">Cancel</button>
                                </form:form> 
                            </div>
                        </div>
                        <p class="mb-1">${match.userB.profile.bio}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</body>

<%@ include file = "common/footer.jsp" %>

<script>
		$(function() {
			$("#datepicker").datepicker();
		});
</script>

</html>
