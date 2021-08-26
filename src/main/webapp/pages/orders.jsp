<%@ page import="by.toxa.fishingshop.model.entity.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="ct" uri="customtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="orders.title" var="title"/>
<fmt:message key="orders.button" var="button"/>
<fmt:message key="order_data.cancel" var="cancel"/>
<fmt:message key="local.money.name" var="money"/>

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
        <div class="col-7 ">
            <table class="table table-striped">
                <tbody>
                <c:forEach var="order" items="${order_list}">
                    <tr>
                        <th scope="row"></th>
                        <td>
                                ${order.date}
                        </td>
                        <td>
                                ${order.summaryPrice} ${money}
                        </td>
                        <td>
                            <c:if test="${order.status==OrderStatus.IN_PROCESS}">
                            <c:url value="/ApiController?command=change_order_status_command&id=${order.id}&status=canceled"
                                   var="status"/>
                            <a href="${status}" class="btn btn-primary">${cancel}</a>
                            </c:if>
                        </td>
                        <td>
                            <c:url value="/ApiController?command=get_order_data_command&id=${order.id}&user_id=${order.userId}"
                                   var="data"/>
                            <a href="${data}" class="btn btn-primary">${button}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
