<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message var="title" key="activation.title"/>
<fmt:message var="placeholder_name" key="activation.placeholder.name"/>
<fmt:message var="name_ex" key="activation.incorrect.name"/>
<fmt:message var="placeholder_last_name" key="activation.placeholder.last_name"/>
<fmt:message var="last_name_ex" key="activation.incorrect.last_name"/>
<fmt:message var="placeholder_phone" key="activation.placeholder.phone"/>
<fmt:message var="phone_ex" key="activation.incorrect.phone"/>
<fmt:message var="phone_message" key="activation.phone.message"/>
<fmt:message var="button" key="activation.button"/>

<html>
<head>
    <title>${title}</title>
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
                <input type="text" class="form-control form-control-sm" id="name" name="name" placeholder="${placeholder_name}"
                       required aria-describedby="empty_name">
                <c:if test="${name_is_empty}">
                    <div id="empty_name" class="form-text">${name_ex}.</div>
                </c:if>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm" id="last_name" name="last_name"
                       placeholder="${placeholder_last_name}" aria-describedby="empty_last_name" required>
                <c:if test="${last_name_is_empty}">
                    <div id="empty_last_name" class="form-text">${last_name_ex}</div>
                </c:if>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control form-control-sm" id="phone"
                       name="phone" placeholder="${placeholder_phone}"
                       aria-describedby="phone_text" required pattern="(\+375)(29|25|44|33|17)[\d]{7}">

                <div id="phone_text" class="form-text">
                    <c:if test="${invalid_phone}">
                        ${phone_ex}
                    </c:if>
                    ${phone_message}
                </div>
            </div>

            <button type="submit" class="btn btn-primary">${button}</button>
        </form>
    </div>

<jsp:include page="footer.jsp"/>
</body>
</html>
