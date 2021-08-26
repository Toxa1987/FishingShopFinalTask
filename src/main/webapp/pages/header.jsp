<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="brand" key="user.header.brand"/>
<fmt:message var="categories" key="user.header.categories"/>
<fmt:message var="rods" key="user.header.rod"/>
<fmt:message var="lines" key="user.header.lines"/>
<fmt:message var="spinnings" key="user.header.spinning_rods"/>
<fmt:message var="accesories" key="user.header.accessories"/>
<fmt:message var="reels" key="user.header.reels"/>
<fmt:message var="about" key="user.header.about_us"/>
<fmt:message var="placeholder" key="user.header.placeholder"/>
<fmt:message var="search_button" key="user.header.search"/>
<fmt:message var="authentication" key="user.header.authentication"/>
<fmt:message var="registretion" key="user.header.registration"/>
<fmt:message var="my_orders" key="user.header.my_orders"/>
<fmt:message var="logout" key="user.header.logout"/>
<fmt:message var="products" key="user.header.modal.products"/>
<fmt:message var="clearr" key="user.header.modal.clear"/>
<fmt:message var="make_purchase" key="user.header.modal.make_purchase"/>
<fmt:message var="summary_price" key="user.header.modal.summary_price"/>
<fmt:message var="money" key="local.money.name"/>





<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
          integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="../styles/style.css"/>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-xxl">
            <c:url value="/ApiController?command=start_page_command" var="start"/>
            <a class="navbar-brand" href="${start}">${brand}</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            ${categories}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:url value="/ApiController?command=get_products_by_type_command&type=reel" var="reel"/>
                            <c:url value="/ApiController?command=get_products_by_type_command&type=rod" var="rod"/>
                            <c:url value="/ApiController?command=get_products_by_type_command&type=line" var="line"/>
                            <c:url value="/ApiController?command=get_products_by_type_command&type=spinning"
                                   var="spinning"/>
                            <c:url value="/ApiController?command=get_products_by_type_command&type=accessories"
                                   var="accessories"/>
                            <li><a class="dropdown-item" href="${reel}">${reels}</a></li>
                            <li><a class="dropdown-item" href="${rod}">${rods}</a></li>
                            <li><a class="dropdown-item" href="${line}">${lines}</a></li>
                            <li><a class="dropdown-item" href="${spinning}">${spinnings}</a></li>
                            <li><a class="dropdown-item" href="${accessories}">${accesories}</a></li>

                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item ">
                        <c:url value="/ApiController?command=go_to_search_product_by_name_page_command"
                               var="search_by_name"/>
                        <a class="nav-link" href="${search_by_name}"><i class="fas fa-search"></i> </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                                class="fas fa-user"></i></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:if test="${not_authenticated}">
                                <c:url value="/ApiController?command=go_to_login_page_command" var="login"/>
                                <li><a class="dropdown-item" href="${login}">${authentication}</a></li>
                                <c:url value="/ApiController?command=go_to_sign_up_page_command" var="sing_up"/>
                                <li><a class="dropdown-item" href="${sing_up}">${registretion}</a></li>
                            </c:if>
                            <c:if test="${authenticated}">
                                <c:url value="/ApiController?command=log_out_command" var="log_out"/>
                                <li><a class="dropdown-item" href="${log_out}">${logout}</a></li>
                                <c:url value="/ApiController?command=go_to_user_orders_page_command" var="orders"/>
                                <li><a class="dropdown-item" href="${orders}">${my_orders}</a></li>
                            </c:if>
                        </ul>
                    </li>
                    <c:if test="${authenticated}">
                        <li class="nav-item ">
                            <a class="nav-link " data-bs-toggle="modal" data-bs-target="#cart" href="#"><i
                                    class="fas fa-shopping-cart"></i></a>
                            <div class="modal fade" id="cart" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header bg-secondary text-white">
                                            <h5 class="modal-title" id="ModalLabel">${products}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table table-striped">
                                                <tbody>
                                                <c:forEach var="product" items="${cart.products}">
                                                    <tr>
                                                        <th scope="row"><i class="far fa-circle"></i></th>
                                                        <td>
                                                            <c:url value="/ApiController?command=go_to_product_page&id=${product.id}"
                                                                   var="product_page"/>
                                                            <a href="${product_page}">${product.name}</a>
                                                        </td>
                                                        <td>${product.productType}</td>
                                                        <td>${product.price} ${money}</td>
                                                    </tr>
                                                </c:forEach>
                                                <th scope="row"><i class="fas fa-coins"></i></th>
                                                <td>${summary_price} ${cart.summaryPrice} ${money}</td>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer">
                                            <c:url value="/ApiController?command=clear_cart_command" var="clear"/>
                                            <c:url value="/ApiController?command=purchase_command" var="purchase"/>
                                            <a href="${clear}" class="btn btn-primary"> ${clearr} </a>
                                            <a href="${purchase}" class="btn btn-primary">${make_purchase}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" ><i class="fas fa-globe-europe"></i></a>
                        <ul class="dropdown-menu" >
                            <li>
                                <button form="localeForm" type="submit" name="locale" value="ru">
                                    <img class="image-header-dropdown" src="/img/ru.png"/> RU
                                </button>
                            </li>
                            <li>
                                <button form="localeForm" type="submit" name="locale" value="en">
                                    <img class="image-header-dropdown" src="/img/gb.png"/> EN
                                </button>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<c:url value="/ApiController?command=change_locale_command" var="var"/>
<form id="localeForm" action="${var}" method="post">
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>