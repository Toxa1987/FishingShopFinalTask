<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="title" key="search.title"/>
<fmt:message var="login" key="login.login"/>
<fmt:message var="button" key="admin.search.button"/>
<fmt:message var="searchexp" key="admin.search.exp"/>
<!DOCTYPE html>
<html>
<head>
    <title> ${title} </title>
</head>
<body>
<jsp:include page="admin_header.jsp"/>

<c:url value="/ApiController?command=search_user_by_login_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center" style="width: 200px; padding-top: 200px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="login" name="login" placeholder="${login}" aria-describedby="exception" required>
            <c:if test="${wrong_search_user}">
                <div id="exception" class="form-text">${searchexp}</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">${button}</button>
    </form>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>