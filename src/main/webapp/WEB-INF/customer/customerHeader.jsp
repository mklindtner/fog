<%@ page import="entities.userEntities.Customer" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/6/18
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="redirect?goToPage=customerOrders&role=customer">Home</a>
            <a class="navbar-brand" href="redirect?goToPage=customerHomepage&role=customer">create order</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <% Customer customer = (Customer) request.getSession().getAttribute("customer");
                if (request.getSession().getAttribute("employee") == null) {%>
            <form class="navbar-form navbar-right" method="get" action="logout">
                <div class="form-group">
                    <span class="text-light" style="color: #9d9d9d; font-size: 18px;"><%=customer.getUsername()%></span>
                </div>
                <form action="signout" method="get">
                    <button type="submit" class="btn btn-danger" id="signout">Sign Out</button>
                </form>
            </form>
            <% } else { %>
            <form class="navbar-form navbar-right" method="get" action="redirect">
                <input type="hidden" name="goToPage" value="employeeHomepage">
                <input type="hidden" name="role" value="employee">
                <div class="form-group">
                    <span class="text-light" style="color: #9d9d9d; font-size: 18px;">As:<%=customer.getUsername()%></span>
                </div>
                    <button type="submit" class="btn btn-danger" id="backToEmployee">Back to You</button>
            </form>
            <% } %>
        </div><!--/.navbar-collapse -->
    </div>
</nav>
<br/>
<br/>
<br/>
<br/>
<br/>
</body>
</html>
</div><!--/.navbar-collapse -->
