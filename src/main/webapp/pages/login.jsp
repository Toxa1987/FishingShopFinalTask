<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
    <title> Login </title>
</head>
<body>

<div >

    <c:if test="${error}">
        Invalid email or password.
    </c:if>

</div>
<c:url value="/ApiController?command=go_to_sing_in_command " var="var"/>
<form action="${var}" method="post">
    <div><label> login : <input type="text" name="login"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>