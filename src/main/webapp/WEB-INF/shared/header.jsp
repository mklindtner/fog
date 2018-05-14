<%@ page import="entities.userEntities.User" %>
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
            <% if((Customer) request.getSession().getAttribute("customer") != null) {%>
            <a class="navbar-brand" href="redirect?">Home</a>
            <a class="navbar-brand" href="redirect?goToPage=customerCreateUser&role=customer">signup</a>
            <a class="navbar-brand" href="#">create order</a>
            <% } %>
            <% if((Customer) request.getSession().getAttribute("customer") != null) {%>

            <% } %>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form method="get" action="login" class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" value="customer" name="username"
                           required>
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" value="123" name="password"
                           required>
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
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
