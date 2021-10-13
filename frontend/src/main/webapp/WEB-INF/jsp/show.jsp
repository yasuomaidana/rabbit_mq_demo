<%@ page import="com.sun.org.apache.xml.internal.resolver.helpers.FileURL" %><%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 12/10/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <title>Show items</title>
        <link rel="stylesheet" href="../../static/bootstrap/css/bootstrap.min.css">
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
                            <a class="nav-link px-2 text-secondary">Show data</a>
                        </li>
                        <li>
                            <a href="/register" class="nav-link px-2 text-white">Register new item</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="container">
                <h1>Welcome to show items page</h1>
            </div>
        </header>
        <main>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Dog's name</th>
                    <th scope="col">Race</th>
                    <th scope="col">Age [years:months]</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>12</td>
                    <td>1</td>
                    <td>
                        <button type="button" class="btn btn-primary rounded-circle"><i class="fas fa-pen-square"></i></button>
                        &nbsp;
                        <button type="button" class="btn btn-danger rounded-circle"><i class="fas fa-trash-alt"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
        </main>
    </body>
</html>
