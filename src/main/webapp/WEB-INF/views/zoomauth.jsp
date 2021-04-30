<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Authentication successful</title>
</head>

<%@ include file = "common/header.jsp" %>
<body>
<div class = "container">
    <h2><i>${optionalAuthenticationMessage}</i></h2>
        <br>

    <div class="container-fluid" style="border:0px solid;">
        <div class="row">
            <div class="col-6" style="border:0px solid;" name="view">
                <div class="card" style="width: 25rem;">
                    <div class="card-body">
                        <h5 class="card-title">View Meetings</h5>
                        <p class="card-text">You can view a list of your zoom meetings.</p>
                        <a href="/zoom/meetings" class="btn btn-primary">View</a>
                    </div>
                </div>
            </div>

            <div class="col-6" style="border:0px solid;" name="multi_search">
                <div class="card" style="width: 25rem;">
                    <div class="card-body">
                        <h5 class="card-title">Schedule a New Meeting</h5>
                        <p class="card-text">You can schedule a new meeting via our site.</p>
                        <a href="/zoom/schedule" class="btn btn-primary">Schedule</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%@ include file = "common/footer.jsp" %>
</html>
