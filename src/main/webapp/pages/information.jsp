<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="information.message" var="message"/>
<fmt:message var="title" key="information.title"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<p>${message}</p>
<jsp:include page="footer.jsp"/>
</body>
</html>
