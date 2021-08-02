<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Welcome</title>
</head>

<body>
<c:if test="${not_authenticated}">
    <c:url value="/ApiController?command=go_to_login_page_command" var="login"/>
    <a href="${login}">Sign in</a>
</c:if>

<div>
    <table>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td><img src="data:image/jpeg;base64,${product.imageCode}" height="200" width="200"/></td>
                <td>${product.description}</td>
                <td>${product.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
