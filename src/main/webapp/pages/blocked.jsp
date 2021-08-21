<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="title" key="blocked.title"/>
<fmt:message var="message" key="blocked.block"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container payment_window mb-5 pt-3 pb-5">
    <div class="container mt-5">
        <h1 class="ml-5">${message}</h1>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
