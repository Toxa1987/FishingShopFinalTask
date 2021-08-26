<%@ page import="by.toxa.fishingshop.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="title" key="error.title"/>
<fmt:message var="head" key="error.head"/>
<fmt:message var="error_head" key="error.error"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<c:if test="${user.role== Role.GUEST or user.role== Role.USER }">
    <jsp:include page="../header.jsp"/>
</c:if>
<c:if test="${user.role== Role.MANAGER }">
    <jsp:include page="../manager/manager_header.jsp"/>
</c:if>
<c:if test="${user.role== Role.ADMIN}">
    <jsp:include page="../admin/admin_header.jsp"/>
</c:if>
<c:if test="${not empty pageContext.exception}">
    <c:set var="exception" value="${pageContext.exception}"/>
    <div class="container payment_window mb-5 pt-3 pb-5">
        <div class="container mt-5">
            <h1 class="ml-5">${head}</h1>
            <h3>${error_head} ${exception} </h3>
            <h3> Stack trace:</h3>
            <p style="font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 9pt;">
                <c:forEach var="element" items="${exception.stackTrace}">
                    ${element}
                    <br/>
                </c:forEach>
            </p>
        </div>
    </div>
</c:if>
<c:if test="${not empty requestScope.exception}">
    <c:set var="exception" value="${requestScope.exception}"/>
    <div class="container payment_window mb-5 pt-3 pb-5">
        <div class="container mt-5">
            <h1 class="ml-5">${head}</h1>
            <h3>${error_head} ${exception} </h3>
            <h3> Stack trace:</h3>
            <p style="font-family: Verdana, Arial, Helvetica, sans-serif;
    font-size: 9pt;">
                <c:forEach var="element" items="${exception.stackTrace}">
                    ${element}
                    <br/>
                </c:forEach>
            </p>
        </div>
    </div>
</c:if>
</body>
</html>
