<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="manager.header.brand" var="brand"/>
<fmt:message key="manager.header.orders" var="orders"/>
<fmt:message key="manager.header.orders.canceled" var="cancel"/>
<fmt:message key="manager.header.orders.done" var="o_done"/>
<fmt:message key="manager.header.orders.in.process" var="process"/>
<fmt:message key="manager.header.set.product" var="set"/>
<fmt:message key="manager.header.product" var="product_header"/>
<fmt:message key="manager.header.search.by.name" var="search_name"/>
<fmt:message key="manager.header.search.by.vendor" var="search_vendor"/>
<fmt:message key="user.header.logout" var="logout"/>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
          integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="../../styles/style.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-xxl">
        <c:url value="/ApiController?command=go_to_manager_page_command" var="start"/>
        <a class="navbar-brand" href="${start}">${brand}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${orders}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <c:url var="in_process"
                               value="/ApiController?command=find_orders_by_status_command&status=in_process"/>
                        <li><a class="dropdown-item" href="${in_process}">${process}</a></li>
                        <c:url var="canceled"
                               value="/ApiController?command=find_orders_by_status_command&status=canceled"/>
                        <li><a class="dropdown-item" href="${canceled}">${cancel}</a></li>
                        <c:url var="done" value="/ApiController?command=find_orders_by_status_command&status=done"/>
                        <li><a class="dropdown-item" href="${done}">${o_done}</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${product_header}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:url var="set_product" value="/ApiController?command=go_to_edit_product_page_command"/>
                        <li><a class="dropdown-item" href="${set_product}">${set}</a></li>
                        <li><c:url value="/ApiController?command=go_to_search_product_by_vendor_page_command"
                                   var="search_by_vendor"/>
                            <a class="dropdown-item" href="${search_by_vendor}">${search_vendor}</a>
                        </li>
                        <li>
                            <c:url value="/ApiController?command=go_to_search_product_by_name_page_command"
                                   var="search_by_name"/>
                            <a class="dropdown-item" href="${search_by_name}">${search_name}</a>
                        </li>
                    </ul>
                </li>

            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                            class="fas fa-user"></i></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:url value="/ApiController?command=log_out_command" var="log_out"/>
                        <li><a class="dropdown-item" href="${log_out}">${logout}</a></li>
                    </ul>
                </li>
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
<c:url value="/ApiController?command=change_locale_command" var="var"/>
<form id="localeForm" action="${var}" method="post">
</form>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
