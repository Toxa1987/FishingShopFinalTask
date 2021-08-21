<%@ page import="by.toxa.fishingshop.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
<ct:header role="${user.role}" var="var"/>
<jsp:include page="${var}"/>
<div class="container payment_window mb-5 pt-3 pb-5">
    <div class="container mt-5">
        <h1 class="ml-5">${locale_error_head}!</h1>
        <p>${exception.message} ${exception}</p>
    </div>
</div>
</body>
</html>
