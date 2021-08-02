<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Page</title>
</head>
<body>
    <c:url var="var" value="/ApiController?command=go_to_edit_product_page_command"/>
    <<a href="${var}">edit product</a>
</body>
</html>
