<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<c:url value="/ApiController?command=sing_up_page_command" var="var"/>
<form action="${var}" method="post">
    <div><label> Login : <input type="text" name="login"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Email : <input type="email" name="email"/> </label></div>
    <div><input type="submit" value="Sing Up"/></div>
    <div><input type="reset" value="Clear"></div>
</form>
</body>
</html>
