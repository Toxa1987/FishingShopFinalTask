<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.toxa.fishingshop.model.entity.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="user.data.title" var="title"/>
<fmt:message key="user.data.phone" var="phone"/>
<fmt:message key="user.data.activate" var="activate_user"/>
<fmt:message key="user.data.block" var="block_user"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<div class="container-sm" style="margin-bottom: 50px;">
    <div class="row align-items-center">
        <div class="col-2">
        </div>
        <div class="col-7">
            <h5>${user.lastName} ${user.name}. </h5>
            <p>${phone} ${user.phone}</p>
            <div>
                <c:if test="${user.status==Status.BLOCKED}">
                    <c:url value="/ApiController?command=change_user_status_command&id=${user.id}&status=activated"
                           var="activated_status"/>
                    <a href="${activated_status}" class="btn btn-primary">${activate_user}</a>
                </c:if>
                <c:if test="${user.status==Status.ACTIVATED}">
                    <c:url value="/ApiController?command=change_user_status_command&id=${user.id}&status=blocked"
                           var="blocked_status"/>
                    <a href="${blocked_status}" class="btn btn-primary">${block_user}</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
