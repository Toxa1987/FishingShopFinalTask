<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="search.title" var="title"/>
<fmt:message key="search.button" var="button"/>
<fmt:message key="search.placeholder" var="placeholder"/>

<!DOCTYPE html>
<html>
<head>
    <title> ${title} </title>
</head>
<body>
<ct:header role="${user.role}" var="page"/>
<jsp:include page="${page}"/>

<c:url value="/ApiController?command=search_product_by_product_name_command" var="var"/>
<div class="container">
    <form action="${var}" method="post" class="mx-auto row justify-content-center align-items-center"
          style="width: 200px; padding-top: 200px;">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="name" name="name" placeholder="${placeholder}"
                   required>
        </div>
        <button type="submit" class="btn btn-primary">${button}</button>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>