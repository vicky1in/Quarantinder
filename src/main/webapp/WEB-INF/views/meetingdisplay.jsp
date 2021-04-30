<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Display Meeting Details</title>
</head>

<%@ include file = "common/header.jsp" %>
<body>
<div class="container">
    <h2>Meeting Detail</h2>
    <h6><i><u> ${optionalMessage}</u></i></h6>
    <br>
    <dl class="row">
        <dt class="col-sm-3">Meeting ID</dt>
        <dd class="col-sm-9">${meetingID}</dd>

        <dt class="col-sm-3">Start Meeting URL</dt>
        <dd class="col-sm-9" style="word-break: break-all"><a href="${startURL}">Click here to start meeting</a></dd>

        <dt class="col-sm-3">Meeting Topic</dt>
        <dd class="col-sm-9">${topic}</dd>

        <dt class="col-sm-3">Meeting Start Time</dt>
        <dd class="col-sm-9">${startTime}</dd>

        <dt class="col-sm-3">Meeting Duration (minutes)</dt>
        <dd class="col-sm-9">${duration}</dd>

        <dt class="col-sm-3">Created At</dt>
        <dd class="col-sm-9">${createdAt}</dd>

        <dt class="col-sm-3">Invite URL</dt>
        <dd class="col-sm-9" style="word-break: break-all"><a href="${meetingURL}">${meetingURL}</a></dd>

        <dt class="col-sm-3">Meeting Invitation</dt>
        <dd class="col-sm-9" style="word-break: break-all">
            ${meetingInvitation} <br><br>

        <button class="btn btn-secondary" data-clipboard-text="${meetingInvitationString}">
            Click to Copy Invitation
        </button>
        </dd>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.4/clipboard.min.js"></script>
        <script>
            new ClipboardJS('.btn');
        </script>


    </dl>
    <h4 class="text-center">
        <a href="${pageContext.request.contextPath}/zoom/meetings" methods="GET">Back to Meeting List</a>
    </h4>
</div>
</body>

<%@ include file = "common/footer.jsp" %>
</html>
