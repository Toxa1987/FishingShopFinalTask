<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message var="title" key="start.title"/>
<fmt:message var="welcome" key="start.welcome"/>
<fmt:message var="goto_product" key="start.go_to_product_page"/>
<fmt:message var="previous" key="start.previous"/>
<fmt:message var="money" key="local.money.name"/>
<fmt:message var="next" key="start.next"/>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container-xxl">
        <div class="row align-items-center">
            <div class="col-7 "><h1>${welcome}</h1></div>
            <div class="col-5"><img class="w-100"
                                    src="https://www.ferra.ru/imgs/2020/07/20/08/4009507/0a678f1533d4e15478e48051c7f52fd872802e90.jpg"
                                    alt="logo"/></div>
        </div>
    </div>
</section>
<section>
    <div class="container-xxl">
        <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="row justify-content-between">
                        <c:forEach var="product" items="${productList}" begin="0" end="3">
                            <div class="col">
                                <div class="card w-100">
                                    <img src="data:image/jpeg;base64,${product.imageCode}" class="card-img-top"
                                         alt="rod">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.name}</h5>
                                        <p class="card-text">${product.price} ${money}</p>
                                        <c:url value="/ApiController?command=go_to_product_page&id=${product.id}" var="product_page"/>
                                        <a href="${product_page}" class="btn btn-primary">${goto_product}</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="carousel-item ">
                    <div class="row justify-content-between">
                        <c:forEach var="product" items="${productList}" begin="4" end="7">

                            <div class="col">
                                <div class="card w-100">
                                    <img src="data:image/jpeg;base64,${product.imageCode}" class="card-img-top"
                                         alt="rod">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.name}</h5>
                                        <p class="card-text">${product.price} ${money}</p>
                                        <c:url value="/ApiController?command=go_to_product_page&id=${product.id}" var="product_page"/>
                                        <a href="${product_page}" class="btn btn-primary">${goto_product}</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">${previous}</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">${next}</span>
                </button>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
