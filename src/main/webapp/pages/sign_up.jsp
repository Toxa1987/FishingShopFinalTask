<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:url value="/ApiController?command=sign_up_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center"
          style="width: 200px; padding-top: 50px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="login"
                   aria-describedby="login_exception" required>
            <c:if test="${booked_login}">
                <div id="login_exception" class="form-text">Login is booked.</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="password" name="password"
                   placeholder="password"
                   aria-describedby="exception" required>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="confirm_password"
                   name="confirm_password" placeholder="confirm password"
                   aria-describedby="password_exception" required>
            <c:if test="${invalid_passwords}">
                <div id="exception" class="form-text">Passwords must be identical.</div>
            </c:if>
        </div>

        <div class="mb-3">
            <input type="email" class="form-control form-control-sm" id="email" name="email" placeholder="email"
                   aria-describedby="email_exception" required>
            <c:if test="${invalid_email}">
                <div id="email_exception" class="form-text">Incorrect email.</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Sign up</button>
       </form>
</div>
</body>
</html>
