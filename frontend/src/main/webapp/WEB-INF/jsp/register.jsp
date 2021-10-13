<%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 12/10/21
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Register items</title>
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
                            <a href="/show" class="nav-link px-2 text-white">Show data</a>
                        </li>
                        <li>
                            <a class="nav-link px-2 text-secondary">Register new item</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="container">
                <h1>Welcome to register new item page</h1>
            </div>
        </header>
        <main>
            <div class="container mx-auto align-content-center bg-light mt-4 py-5">
                <form action="/register" method="post">
                    <div class="mx-auto">
                        <label for="name">Dog's name</label>
                        <input type="text" name="name" id="name">
                    </div>
                    <div class="mx-auto">
                        <input type="submit" value="Register new dog">
                    </div>
                    
                </form>
            </div>
        </main>
        <footer>

        </footer>
    </body>
</html>
