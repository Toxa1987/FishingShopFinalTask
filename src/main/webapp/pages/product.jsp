<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${Product.name}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-xxl">
    <div class="col-8">
        <h2>${Product.name}</h2>
        <img src="data:image/jpeg;base64,${product.imageCode}" class="card-img-top"
             alt="rod">
        <p>${Product.price}</p>
        <p>${Product.description}</p>
        <p>
            <c:if test="${Product.name}!=0">
                In stock
            </c:if>
            <c:if test="${Product.name}!=0">
               Out of stock
            </c:if>
        </p>
<button type="button"></button>
    </div>
</div>
</body>
</html>
