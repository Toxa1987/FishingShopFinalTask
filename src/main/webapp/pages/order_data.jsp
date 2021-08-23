<%@page import="by.toxa.fishingshop.model.entity.Role" %>
<%@page import="by.toxa.fishingshop.model.entity.OrderStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="customtag" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="order_data.title" var="title"/>
<fmt:message key="order_data.created" var="created"/>
<fmt:message key="order_data.customer" var="customer"/>
<fmt:message key="order_data.block.user" var="block"/>
<fmt:message key="order_data.cancel" var="cancel"/>
<fmt:message key="order_data.done.order" var="done"/>
<fmt:message key="local.money.name" var="money"/>
<fmt:message key="user.header.modal.summary_price" var="summ"/>
<fmt:message key="activation.placeholder.phone" var="phone"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<ct:header role="${user.role}" var="var"/>
<jsp:include page="${var}"/>
<div class="container-sm">
    <div class="row align-items-center">
        <div class="col-2">
        </div>
        <div class="col-7">
            <h5>${order.orderName}</h5>
            <p>${created} ${order.date}</p>
        </div>
    </div>
    <div class="row align-items-center">
        <div class="col-2">
        </div>
        <div class="col-7 ">
            <table class="table table-striped">
                <tbody>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <th scope="row"></th>
                        <td>
                                ${product.vendor}
                        </td>
                        <td>
                            <c:url value="/ApiController?command=go_to_product_page&id=${product.id}"
                                   var="product_page"/>
                            <a href="${product_page}">${product.name}</a>
                        </td>
                        <td>
                                ${product.price} ${money}
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th scope="row"></th>
                    <td> </td>
                    <td> ${summ}</td>
                    <td>${order.summaryPrice} ${money}</td>
                </tr>
                </tbody>
            </table>
            <div>
                <c:if test="${user.role==Role.MANAGER}">
                    <h5>${customer} </h5>
                    <p>${order_user.name} ${order_user.lastName} ${phone}: ${order_user.phone}</p>
                </c:if>
            </div>
            <div><c:if test="${order.status==OrderStatus.IN_PROCESS}">
                <c:url value="/ApiController?command=change_order_status_command&id=${order.id}&status=canceled"
                       var="canceled_status"/>
                <a href="${canceled_status}" class="btn btn-primary">${cancel}</a>
            </c:if>
                <c:if test="${user.role==Role.MANAGER and order.status==OrderStatus.IN_PROCESS}">
                    <c:url value="/ApiController?command=change_order_status_command&id=${order.id}&status=done"
                           var="done_status"/>
                    <a href="${done_status}" class="btn btn-primary">${done}</a>
                </c:if>
                <c:if test="${user.role==Role.MANAGER}">
                    <c:url value="/ApiController?command=blocked_user_command&id=${order_user.id}"
                           var="blocked"/>
                    <a href="${blocked}" class="btn btn-primary">${block}</a>
                </c:if>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
