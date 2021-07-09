<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles/styles1.css">
</head>
<body>
<h2>List of users</h2>
<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>Email</th>
            <th>Password</th>
            <th>Name</th>
            <th>
                Last name
            </th>
            <th>Phone</th>
            <th>Role</th>
            <th>Status</th>
        </tr>
        <c:forEach var="user" items = "${usersList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>${user.role}</td>
                <td>${user.status}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
