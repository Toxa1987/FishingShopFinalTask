<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.toxa.fishingshop.model.entity.ManufactureType" %>
<%@ page import="by.toxa.fishingshop.model.entity.ProductType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="edit.product.title" var="title"/>
<fmt:message key="edit.product.vendor" var="vendor"/>
<fmt:message key="edit.product.vendorexp" var="vendorexp"/>
<fmt:message key="edit.product.name" var="name"/>
<fmt:message key="edit.product.nameexp" var="nameexp"/>
<fmt:message key="edit.product.manufacture" var="manufacture"/>
<fmt:message key="edit.product.description" var="description"/>
<fmt:message key="edit.product.number.in.stock" var="number_in_stock"/>
<fmt:message key="edit.product.number.in.stockexp" var="number_in_stockexp"/>
<fmt:message key="edit.product.upload" var="upload"/>
<fmt:message key="edit.product.uploadexp" var="uploadexp"/>
<fmt:message key="edit.product.button" var="button"/>
<fmt:message key="edit.product.priceexp" var="priceexp"/>
<fmt:message key="edit.product.type" var="type"/>
<fmt:message key="edit.product.type.accessories" var="accessories"/>
<fmt:message key="edit.product.type.rod" var="rod"/>
<fmt:message key="edit.product.type.spinning" var="spinning"/>
<fmt:message key="edit.product.type.line" var="line"/>
<fmt:message key="edit.product.type.reel" var="reel"/>
<fmt:message key="edit.product.price" var="price"/>
<fmt:message key="edit.product.message" var="message"/>
<html>
<head>
    <title>${title}</title>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="manager_header.jsp"/>
<c:url value="/ApiController?command=edit_new_product_command" var="edit"/>

<div class="container">
    <form action="${edit}" method="post" class="mx-auto row justify-content-center align-items-center"
          style="width: 400px; padding-top: 50px;" id="product" enctype="multipart/form-data">
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="vendor" name="vendor" placeholder="${vendor}"
                   required aria-describedby="empty_vendor" pattern="^[\da-zA-Zа-яА-Я][\da-zA-Zа-яА-Я-]{1,34}"
                   value="${product.vendor}">
            <c:if test="${vendor_exception}">
                <div id="empty_vendor" class="form-text">${vendorexp}</div>
            </c:if>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="product_name" name="name"
                   placeholder="${name}" required aria-describedby="empty_product_name"
                   pattern="^[a-zA-Zа-яА-Я][\da-zA-Zа-яА-Я-\s]{1,49}" value="${product.name}">
            <c:if test="${product_name_exception}">
                <div id="empty_product_name" class="form-text">${nameexp}</div>
            </c:if>
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text" for="type">${type}</label>
            <select class="form-select" id="type" name="type" required>
                <option value="${ProductType.ROD}" <c:if
                        test="${ProductType.ROD==product.type}"> selected </c:if>>${rod}</option>
                <option value="${ProductType.SPINNING}"<c:if
                        test="${ProductType.SPINNING==product.type}"> selected </c:if>>${spinning}</option>
                <option value="${ProductType.LINE}"<c:if
                        test="${ProductType.LINE==product.type}"> selected </c:if>>${line}</option>
                <option value="${ProductType.REEL}"<c:if
                        test="${ProductType.REEL==product.type}"> selected </c:if>>${reel}</option>
                <option value="${ProductType.ACCESSORIES}"<c:if
                        test="${ProductType.ACCESSORIES==product.type}"> selected </c:if>>${accessories}</option>
            </select>
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text" for="manufacture">${manufacture}</label>
            <select class="form-select" id="manufacture" name="manufacture" required>
                <c:forEach items="<%=ManufactureType.values()%>" var="manufactures">
                    <option value="${manufactures.value}"<c:if
                            test="${manufacture==product.manufacture}"> selected </c:if>>${manufactures.value}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <textarea class="form-control " id="description" name="description" placeholder="${description}"
                      required></textarea>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control form-control-sm" id="price" name="price" placeholder="${price}e"
                   required aria-describedby="empty_price" pattern="^(0|[1-9]\d*)([.]\d{2})?" value="${product.price}">
            <div id="empty_price" class="form-text">
                ${message}
                <c:if test="${price_exception}">
                    ${priceexp}
                </c:if>
            </div>
        </div>
        <div class="mb-3">
            <input type="number" class="form-control form-control-sm" id="number_in_stock" name="number_in_stock"
                   min="0" placeholder="${number_in_stock}" required value="${product.numberInStock}" aria-describedby="empty_number_in_stock">
            <c:if test="${number_in_stock_exception}">
                <div id="number_in_stock" class="form-text">${number_in_stockexp}</div>
            </c:if>
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text" for="inputGroupFile01">${upload}</label>
            <input type="file" class="form-control" id="inputGroupFile01" name="content" height="130"
                   aria-describedby="image_exp" size="16777216" accept="image/*" required>
            <c:if test="${image_exception}">
                <div id="image_exp" class="form-text">${uploadexp}</div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">${button}</button>
    </form>
</div>
</body>
</html>












