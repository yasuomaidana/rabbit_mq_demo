<%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 28/10/21
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Register items</title>
        <!-- CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/953c882221.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-start">
                    <img src="../../static/images/list.jpg.webp" class="d-none d-lg-block" style="height: 50px;">
                    <img src="../../static/images/list.jpg.webp" class="d-block d-lg-none" style="height: 2em;">
                    <span class="m-auto"></span>
                    <ul class="nav col-10 col-lg-auto mb-2 justify-content-center mb-md-0">
                        <li>
                            <a href="/show" class="nav-link px-2 text-white">Show data</a>
                        </li>
                        <li>
                            <a class="nav-link px-2 text-secondary">Register new item</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="container">
                <h1>It seems that something go wrong please</h1>
                <p>The cause seems to be: ${cause}</p>
            </div>
        </header>
    </body>
</html>
