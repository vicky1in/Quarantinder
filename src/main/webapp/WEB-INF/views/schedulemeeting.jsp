<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<%@ include file = "common/header.jsp" %>
<body>
  <div class="container">
    <h2>Schedule a video chat</h2>
    <form:form method="POST" modelAttribute="scheduleForm" class="form-signin">
    <spring:bind path="topic">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        Meeting name:
        <form:input type="text" path="topic" class="form-control"
                    placeholder="topic of the video chat" autofocus="true"></form:input>
        <form:errors path="topic"></form:errors>
      </div>
    </spring:bind>

    <spring:bind path="meetingDate">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        Choose your preferred date:
        <form:input type="text" path="meetingDate" class="form-control"
                    placeholder="e.g. 2020-11-29" autofocus="true"></form:input>
        <form:errors path="meetingDate"></form:errors>
      </div>
    </spring:bind>

    <spring:bind path="meetingTime">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        Choose your preferred start time:
        <form:input type="text"  path="meetingTime" class="form-control"
                    placeholder="e.g. '13:00:00'" autofocus="true"></form:input>
        <form:errors path="meetingTime"></form:errors>
      </div>
    </spring:bind>

    <spring:bind path="duration">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        Enter meeting duration (in minutes):
        <form:input type="text" path="duration" class="form-control"
                    placeholder="e.g. 45" value = "60" autofocus="true"></form:input>
        <form:errors path="duration"></form:errors>
      </div>
    </spring:bind>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  </form:form>
  </div>
</body>

<%@ include file = "common/footer.jsp" %>

</html>
