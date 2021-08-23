<%@ page import="by.toxa.fishingshop.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>
<html>
<head>
    <title>404</title>
</head>
<body>
<ct:header role="${user.role}" var="var"/>
<jsp:include page="${var}"/>
<H1>Page not found</H1>
</body>
</html>
