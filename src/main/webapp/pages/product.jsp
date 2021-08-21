<%@ page import="by.toxa.fishingshop.model.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="product.in.stock" var="in_stock"/>
<fmt:message key="product.out.stock" var="out_stock"/>
<fmt:message key="product.set.cart" var="set_cart"/>
<fmt:message key="product.update" var="update_product"/>
<fmt:message key="product.nothing" var="message"/>
<fmt:message key="local.money.name" var="money"/>
<fmt:message key="product.vendor" var="vendor"/>
<html>
<head>
    <title>${Product.name}</title>
</head>
<body>
<ct:header role="${user.role}" var="var"/>
<jsp:include page="${var}"/>
<div class="container-sm">
    <c:if test="${empty requestScope.find_nothing}">
        <div class="col-8">
            <h2>${product.name}</h2>
            <h3>${vendor} ${product.vendor}</h3>
            <img class="w-50" src="data:image/jpeg;base64,${product.imageCode}" class="card-img-top"
                 alt="rod">
            <p>price ${product.price} ${money}</p>
            <p>${product.description}</p>
            <p>
                <c:if test="${product.numberInStock>0}">
                    ${in_stock}
                </c:if>
                <c:if test="${product.numberInStock==0}">
                    ${out_stock}
                </c:if>
            </p>
            <c:if test="${user.role==Role.USER}">
                <c:if test="${product.numberInStock>0}">
                    <c:url value="/ApiController?command=set_to_cart_command&id=${product.id}" var="set"/>
                    <a href="${set}" class="btn btn-primary">${set_cart}</a>
                </c:if>
            </c:if>
            <c:if test="${user.role==Role.MANAGER}">
                <c:url value="/ApiController?command=get_product_to_update_command&id=${product.id}" var="update"/>
                <a href="${update}" class="btn btn-primary">${update_product}</a>
            </c:if>
        </div>
    </c:if>
    <c:if test="${find_nothing}">
        <div class="col-8">
           <h5> ${message} </h5>
        </div>
        </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
