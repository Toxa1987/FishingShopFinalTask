<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="money" key="local.money.name"/>
<fmt:message var="title" key="find_products.title"/>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-sm">
    <div class="row align-items-center">
        <div class="col-2">
        </div>
        <div class="col-7 ">
            <table class="table table-striped">
                <thead>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <th scope="row"></th>
                        <td>
                            <img src="data:image/jpeg;base64,${product.imageCode}"
                                 style="width: 120px; height: 120px;"/>
                        </td>
                        <td>
                            <c:url value="/ApiController?command=go_to_product_page&id=${product.id}"
                                   var="product_page"/>
                            <a href="${product_page}">${product.name}</a>
                        </td>
                        <td>${product.price} ${money}</td>
                    </tr>
                </c:forEach></thead>
                <tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
