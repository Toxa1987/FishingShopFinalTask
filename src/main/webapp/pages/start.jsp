<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container-xxl">
        <div class="row align-items-center">
            <div class="col-7 "><h1>Welcome to fishing shop.</h1></div>
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
                                        <p class="card-text">${product.price}</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
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
                                        <h5 class="card-title">${product.productType}</h5>
                                        <p class="card-text">${product.description}</p>
                                        <a href="#" class="btn btn-primary">Go somewhere</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                        data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>
</section>
</body>

