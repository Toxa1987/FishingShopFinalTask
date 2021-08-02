<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
          integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="../styles/style.css"/>
    <title>Welcome</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-xxl">
            <c:url value="/ApiController?command=start_page_command" var="start"/>
            <a class="navbar-brand" href="${start}">Fishing shop</a>
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
                            Categories
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Reels</a></li>
                            <li><a class="dropdown-item" href="#">Rods</a></li>
                            <li><a class="dropdown-item" href="#">Lines</a></li>
                            <li><a class="dropdown-item" href="#">Spinning rods</a></li>
                            <li><a class="dropdown-item" href="#">Accessories</a></li>

                        </ul>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="#">About Us</a>
                    </li>
                </ul>
                <form class="d-flex" action="/ApiController?command=search_product">
                    <input class="form-control me-2" type="search" placeholder="Find product" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"><i
                                class="fas fa-user"></i></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:if test="${not_authenticated}">
                                <c:url value="/ApiController?command=go_to_login_page_command" var="login"/>
                                <li><a class="dropdown-item" href="${login}">Authentication</a></li>
                                <c:url value="/ApiController?command=go_to_sign_up_page_command" var="sing_up"/>
                                <li><a class="dropdown-item" href="${sing_up}">Registration</a></li>
                            </c:if>
                            <c:if test="${authenticated}">
                                <c:url value="/ApiController?command=log_out_command" var="log_out"/>
                                <li><a class="dropdown-item" href="${log_out}">Logout</a></li>
                                <c:url value="/ApiController?command=go_to_sign_up_page_command" var="sing_up"/>
                                <li><a class="dropdown-item" href="${sing_up}">Registration</a></li>
                            </c:if>
                        </ul>
                        <c:if test="${authenticated}">
                    <li class="nav-item ">
                        <a class="nav-link " data-bs-toggle="modal" data-bs-target="#cart" href="#"><i
                                class="fas fa-shopping-cart"></i></a>
                        <div class="modal fade" id="cart" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header bg-secondary text-white">
                                        <h5 class="modal-title" id="ModalLabel">Products.</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th scope="col">Product</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Cost</th>
                                                <th scope="col">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>Mark</td>
                                                <td>Otto</td>
                                                <td>@mdo</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">2</th>
                                                <td>Jacob</td>
                                                <td>Thornton</td>
                                                <td>@fat</td>
                                            </tr>
                                            <tr>
                                                <th scope="row">3</th>
                                                <td>Larry </td>
                                                <td>Bird</td>
                                                <td>@twitter</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>