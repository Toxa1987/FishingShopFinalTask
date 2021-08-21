<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="signUp.title" var="title"/>
<fmt:message key="signUp.loginexp" var="loginexp"/>
<fmt:message key="signUp.confirm.password.placeholder" var="confirm_password"/>
<fmt:message key="signUp.passwordexp" var="passwordexp"/>
<fmt:message key="signUp.email.exp" var="emailexp"/>
<fmt:message key="signUp.button" var="button"/>
<fmt:message key="login.login" var="login"/>
<fmt:message key="login.password" var="password"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:url value="/ApiController?command=sign_up_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center"
          style="width: 200px; padding-top: 50px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="${login}"
                   aria-describedby="login_exception" required>
            <c:if test="${booked_login}">
                <div id="login_exception" class="form-text">${loginexp}</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="password" name="password"
                   placeholder="${password}"
                    required>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="confirm_password"
                   name="confirm_password" placeholder="${confirm_password}"
                   aria-describedby="exception" required>
            <c:if test="${invalid_passwords}">
                <div id="exception" class="form-text">${passwordexp}</div>
            </c:if>
        </div>

        <div class="mb-3">
            <input type="email" class="form-control form-control-sm" id="email" name="email" placeholder="email"
                   aria-describedby="email_exception" required>
            <c:if test="${invalid_email}">
                <div id="email_exception" class="form-text">${emailexp}</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">${button}</button>
       </form>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
