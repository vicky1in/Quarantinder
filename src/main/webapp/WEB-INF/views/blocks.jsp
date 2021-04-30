<%@ include file="common/header.jsp" %>

<html>
<head>
<title>Blocked Users</title>
</head>

<body>
	<div class="container" style=" margin:0 auto;" >
        <h1>Blocked Users</h1>
        <div class="list-group list-group-flush mt-3 mb-5">
        <c:if test="${empty blocks}">
            <p>Oh it looks like you haven't blocked anyone yet.</p>
        </c:if>
        <c:forEach items="${blocks}" var="block">
            <div class="list-group-item flex-column align-items-start">
                <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">${block.blockee.name}</h5>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#unblockModal">Unblock</button>
                    <div class="modal fade" id="unblockModal" tabindex="-1" role="dialog" aria-labelledby="unblockModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Unblock User</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Are you sure you want to unblock ${block.blockee.name}?</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                    <form:form id="block-cancel-form" method="POST" action="/blocks/cancel/${block.blockID}">
                                        <button class="btn btn-primary">Yes</button>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>

<%@ include file = "common/footer.jsp" %>

</html>
