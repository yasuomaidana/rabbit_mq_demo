<%@ page import="dto.Dog" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 14/10/21
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <title>Show items</title>
        <!-- CSS only -->
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
                <%
                    List<Dog> dogs = (List<Dog>) request.getAttribute("dogs");
                    String row = "<tr><th scope=\"row\">%d</th><td>%s</td><td>%s</td><td>%d</td></tr>";
                    int i =0;
                    for(Dog dog: dogs){
                        i++;
                        out.print(String.format(row,i,dog.getName(),dog.getRace(),dog.getAge()));
                    }
                %>
                </tbody>
            </table>
        </main>
    </body>
</html>