<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="login.login" var="locale_login"/>
<fmt:message key="login.password" var="locale_password"/>
<fmt:message key="login.title" var="locale_title"/>
<fmt:message key="login.exception" var="locale_exception"/>
<fmt:message key="login.button" var="locale_button_name"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> ${locale_title} </title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:url value="/ApiController?command=login_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center" style="width: 200px; padding-top: 200px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="${locale_login}" required>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="password"  name="password" placeholder="${locale_password}" aria-describedby="exception" required>
            <c:if test="${wrong_login_or_password}">
            <div id="exception" class="form-text">${locale_exception}</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">${locale_button_name}</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>