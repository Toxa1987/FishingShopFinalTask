<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div>
    <p>${exception.getMessage}</p>
    <p>${exception.printStackTrace}</p>
</div>
</body>
</html>
