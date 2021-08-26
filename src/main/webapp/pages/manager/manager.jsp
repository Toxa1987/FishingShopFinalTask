<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="title" key="manager.title"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="manager_header.jsp"/>
<jsp:include page="../footer.jsp"/>
</body>
</html>
