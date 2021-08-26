<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="login.login" var="login"/>
<fmt:message key="login.password" var="password"/>
<fmt:message key="signUp.confirm.password.placeholder" var="confirm_password"/>
<fmt:message key="activation.placeholder.name" var="name"/>
<fmt:message key="activation.placeholder.last_name" var="last_name"/>
<fmt:message key="activation.placeholder.phone" var="phone"/>
<fmt:message key="admin.create.manager.button" var="button"/>
<fmt:message key="admin.create.title" var="title"/>
<fmt:message key="signUp.loginexp" var="loginexp"/>
<fmt:message var="name_ex" key="activation.incorrect.name"/>
<fmt:message var="last_name_ex" key="activation.incorrect.last_name"/>
<fmt:message var="phone_ex" key="activation.incorrect.phone"/>
<fmt:message var="phone_message" key="activation.phone.message"/>
<fmt:message var="passwordexp" key="signUp.passwordexp"/>
<fmt:message var="emailexp" key="signUp.email.exp"/>
<fmt:message key="singUp.login.invalid" var="login_invalid_exp"/>
<fmt:message key="singUp.password.invalid" var="password_invalid_exp"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>
<c:url value="/ApiController?command=edit_manager_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center"
          style="width: 200px; padding-top: 50px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="${login}"
                   aria-describedby="login_exception" required value="${user.login}" pattern="[a-zA-zА-Яа-я\d]{4,20}">
            <c:if test="${booked_login}">
                <div id="login_exception" class="form-text">${loginexp}</div>
                ${login_invalid_exp}
            </c:if>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="password" name="password"
                   placeholder="${password}" aria-describedby="exception_password" required
                   pattern="[a-zA-zА-Яа-я\d]{5,20}">
            <div id="exception_password" class="form-text">${password_invalid_exp}</div>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control form-control-sm" id="confirm_password"
                   name="confirm_password" placeholder="${confirm_password}"
                   aria-describedby="exception" required>
            <c:if test="${invalid_passwords}">
                <div id="exception" class="form-text">${passwordexp}</div>
            </c:if>
         </div>
        <div class="mb-3">
            <input type="email" class="form-control form-control-sm" id="email" name="email" placeholder="email"
                   aria-describedby="email_exception" required value="${user.email}"
                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$">
            <c:if test="${invalid_email}">
                <div id="email_exception" class="form-text">${emailexp}</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="name" name="name"
                   placeholder="${name}"
                   required aria-describedby="empty_name" value="${user.name}" pattern="[a-zA-Zа-яА-я]{2,20}">
            <c:if test="${invalid_name}">
                <div id="empty_name" class="form-text">${name_ex}.</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="last_name" name="last_name"
                   placeholder="${last_name}" aria-describedby="empty_last_name" value="${user.lastName}"
                   required pattern="[a-zA-Zа-яА-Я]{2,20}">
            <c:if test="${invalid_last_name}">
                <div id="empty_last_name" class="form-text">${last_name_ex}</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="phone"
                   name="phone" placeholder="${phone}"
                   aria-describedby="phone_text" required pattern="(\+375)(29|25|44|33|17)[\d]{7}"
                   value="${user.phone}">

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
<jsp:include page="../footer.jsp"/>
</body>
</html>
