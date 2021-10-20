<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 14/10/21
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <h1>Welcome to register new item page</h1>
            </div>
        </header>
        <main>
            <%
                Boolean errorIn = request.getAttribute("Error")==null;
                HashMap<String,String> prevInfo = (HashMap<String, String>) request.getAttribute("Error");
            %>
            <div class="container mx-auto col-6 align-content-center bg-light mt-4 py-5">
                <div class="d-<%= errorIn?"none":"block"%> text-center text-warning"><b class="display-6">Please fill all the sections</b></div>
                <form action="/register" method="post">
                    <label for="dogsname" class="form-label">Dog's race</label>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1"><i class="fas fa-dog"></i></span>
                        <input type="text" class="form-control" placeholder="Dog's name" aria-label="Dog's name" id="dogsname" aria-describedby="basic-addon1" name="name" required
                               value="<%= errorIn? "":prevInfo.get("name")%>">
                    </div>
                    <div class="row g-2 mb-3">
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="text" class="form-control" id="race" placeholder="Dog's race" name="race" required
                                       value="<%= errorIn? "":prevInfo.get("race")%>">
                                <label for="race">Dog's race</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="number" min="0" class="form-control" id="age" placeholder="Dog's age [years]" name="age">
                                <label for="age">Dog's age</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-auto text-center">
                        <button type="submit" class="btn btn-primary mb-3"><i class="fas fa-save"></i> Register new dog</button>
                    </div>

                </form>
            </div>
        </main>
    </body>
</html>