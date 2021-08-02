<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<c:url value="/ApiController?command=activate_command" var="var"/>

    <div class="container">
        <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center"
              style="width: 400px; padding-top: 50px;">
            <div><input type="number" name="id" value="${user.id}" hidden/></div>
            <div><input type="text" name="login" value="${user.login}" hidden/></div>
            <div><input type="password" name="password" value="${user.password}" hidden/></div>
            <div><input type="email" name="email" value="${user.email}" hidden/></div>
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm" id="name" name="name" placeholder="name"
                       required aria-describedby="empty_name">
                <c:if test="${name_is_empty}">
                    <div id="empty_name" class="form-text">Name can not be empty.</div>
                </c:if>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm" id="last_name" name="last_name"
                       placeholder="last_name" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm" id="phone"
                       name="phone" placeholder="phone"
                       aria-describedby="phone_text" required pattern="(\+375)(29|25|44|33|17)[\d]{7}">

                <div id="phone_text" class="form-text">
                    <c:if test="${invalid_phone}">
                        Incorrect phone number.
                    </c:if>
                    Phone should be like +37529123456789.
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Activate account</button>
        </form>
    </div>
</body>
</html>
