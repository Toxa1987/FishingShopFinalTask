<%@ page import="by.toxa.fishingshop.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="title" key="error404.title"/>
<fmt:message var="message" key="error404.message"/>

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
<H1>${message}</H1>
</body>
</html>
