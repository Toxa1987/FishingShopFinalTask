<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="search.title" var="title"/>
<fmt:message key="edit.product.vendor" var="vendor"/>
<fmt:message key="manager.search.exp" var="exp"/>
<fmt:message key="manager.search.button" var="button"/>
<!DOCTYPE html>
<html>
<head>
    <title> ${title} </title>
</head>
<body>
<jsp:include page="manager_header.jsp"/>

<c:url value="/ApiController?command=search_product_by_vendor_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center" style="width: 200px; padding-top: 200px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="vendor" name="vendor" placeholder="${vendor}" aria-describedby="exception" required>
            <c:if test="${wrong_search_product}">
                <div id="exception" class="form-text">${exp}</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">${button}</button>
    </form>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>