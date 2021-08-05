<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title> Login </title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:url value="/ApiController?command=login_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center" style="width: 200px; padding-top: 200px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="login" required>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="password"  name="password" placeholder="password" aria-describedby="exception" required>
            <c:if test="${wrong_login_or_password}">
            <div id="exception" class="form-text">Invalid login or password.</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Sign In</button>
    </form>
</div>
</body>
</html>