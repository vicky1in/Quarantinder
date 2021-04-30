<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<%@ include file = "common/header.jsp" %>
<body>

<div class="container">
<h2>Meeting List</h2>
    <h4><i>There are ${totalrecord} meetings in your record: </i></h4>
<div class="table-responsive">
    <table class= "table table-hover table-fixed">
        <thead>
        <tr>
            <th>Meeting ID</th>
            <th>Topic</th>
            <th>Start Time</th>
            <th>Duration (Minutes)</th>
            <th>Meeting Join URL</th>
            <th></th>
        </tr>
        </thead>

        <c:forEach items="${list}" var="element">
            <tr>
                <td id = "meetingId"><a href = "/zoom/detail/${element.meetingId}" methods="GET"> ${element.meetingId} </a></td>
                <td id = "meetingTopic"> ${element.meetingTopic}</td>
                <td id = "meetingStartTime"> ${element.meetingStartTime}</td>
                <td id = "meetingDuration"> ${element.meetingDuration}</td>
                <td id = "meetingJoinURL"> ${element.meetingJoinURL}</td>
                <td><a href ="/zoom/delete/${element.meetingId}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h4 class="text-center">
        <a href="${pageContext.request.contextPath}/zoom/schedule" methods="GET">Schedule a New Meeting</a>
    </h4>
</div>
</div>
</body>

<%@ include file = "common/footer.jsp" %>
</html>