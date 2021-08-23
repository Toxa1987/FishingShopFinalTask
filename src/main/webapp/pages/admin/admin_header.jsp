<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message var="brand" key="admin.header.brand"/>
<fmt:message var="create" key="admin.header.create"/>
<fmt:message var="find_user" key="admin.header.find.user"/>
<fmt:message var="find_by_login" key="admin.header.find.user.by.login"/>
<fmt:message var="logout" key="user.header.logout"/>

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
        <c:url value="/ApiController?command=go_to_admin_page_command" var="start"/>
        <a class="navbar-brand" href="${start}">${brand}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <c:url value="/ApiController?command=go_to_edit_manager_page_command" var="manager"/>
                    <a class="nav-link" href="${manager}">${create}</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${find_user}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                        <c:url var="by_login"
                               value="/ApiController?command=go_to_search_user_by_login_command"/>
                        <li><a class="dropdown-item" href="${by_login}">${find_by_login}</a></li>
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
