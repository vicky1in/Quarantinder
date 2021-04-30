<%@ include file = "common/header.jsp" %>

<html>
<head>
<title>Suggested Matches</title>
</head>

<body>
	<div class="container" style=" margin:0 auto;" >
        <h3>Find a Match</h3>
        <br>
            <form action="suggestions" method="GET">
                <div class="form-group">
                    <label for="sLocation">Location:</label>
                    <select name="sLocation" id="sLocation">
                        <option value=""></option>
                        <option value="nearest">Nearest to me</option>
                        <c:forEach items="${locations}" var="location">
                            <option value="${location.city}">${location.city}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary btn-sm">Update</button>
                </div>
            </form>
        <div class="list-group list-group-flush mt-3 mb-5">
            <c:if test="${empty suggestions}">
                <p>Sorry, we can't find any suggestions based on your preferences.</p>
            </c:if>
            <c:forEach items="${suggestions}" var="suggestion"> 
                <div class="list-group-item flex-column align-items-start back">
                    <div class="row mt-2 mb-2">
                        <div class="col-3"> 
                        <a href="/profile/${suggestion.key.user.id}">
                            <img src="${suggestion.key.photoPath}" class="rounded-circle" alt="photo" style="width:80%;height:auto;">
                        </a>
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col-md">
                                    <div class="row">
                                        <h5 class="mb-1">${suggestion.key.user.name}</h5>
                                    </div>
                                    <div class="row">
                                        <p>Rating: +${suggestion.value}</p>
                                    </div>
                                </div>
                                <div class="col-md-auto">
                                    <form:form id="match-request-form" method="POST" action="/suggestions/match/${suggestion.key.user.id}" class="form-inline">
                                        <button class="btn btn-primary">Match</button>
                                    </form:form>
                                </div>
                                <div class="col-md-auto">
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
                                                    <p>Are you sure you want to block ${suggestion.key.user.name}?</p>
                                                    <form>
                                                        <div class="form-group">
                                                            <label for="reason" class="col-form-label">Reason (optional):</label>
                                                            <textarea type="text" class="form-control" id="reason"></textarea>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    <form:form id="match-block-form" method="POST" action="/suggestions/block/${suggestion.key.user.id}" class="form-inline">
                                                        <button class="btn btn-primary">Yes</button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <p class="mb-1">${suggestion.key.bio}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>

<%@ include file = "common/footer.jsp" %>

</html>