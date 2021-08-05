<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.toxa.fishingshop.entity.ManufactureType" %>
<%@ page import="by.toxa.fishingshop.entity.ProductType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:url value="/ApiController?command=edit_new_product_command" var="edit"/>

<form action="${edit}" method="post" id="product" enctype="multipart/form-data">
    <label for="vendor">Vendor</label>
    <input type="text" name="vendor" id="vendor"><br/>
    <label for="name">Product name</label>
    <input type="text" name="name" required id="name">
    <label for="manufacture">Manufacture</label>
    <select name="manufacture" required id="manufacture">
        <c:forEach items="<%=ManufactureType.values()%>" var="manufacture">
            <option value="${manufacture.value}">${manufacture.value}</option>
        </c:forEach>
    </select><br/>
    <label for="productType">Type of product</label>
    <select name="type" required id="productType">
        <c:forEach items="<%=ProductType.values()%>" var="productType">
            <option value="${productType.type}">${productType.type}</option>
        </c:forEach>
    </select><br/>
    <label for="description">Description</label>
    <input type="text" name="description" id="description"><br/>
    <label for="price">Price</label>
    <input type="number" name="price" id="price"><br/>
    <label for="numberInStock">Number of products on stock</label>
    <input type="number" name="numberInStock" id="numberInStock"><br/>
    <input type="file" name="content" height="130"><br/>
    <input type="submit" value="Load new product">
</form>
</body>
</html>
