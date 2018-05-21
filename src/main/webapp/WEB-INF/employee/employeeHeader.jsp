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
            <a class="navbar-brand" href="redirect?goToPage=employeeHomepage&role=employee">Home</a>
            <a class="navbar-brand" href="redirect?goToPage=employeeOrderCatalog&role=employee">current Orders</a>
            <form method="get" action="employeeAsCustomer" class="navbar-form navbar-right" _lpchecked="1">
                <div class="form-group">
                    <input style="width:200px;margin-top:4px;margin-right:50px" type="text"
                           class="input-sm form-control" name="customerByUsername" id="customerByUsername" placeholder="find customer">
                </div>
            </form>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>
            <form class="navbar-form navbar-right" method="get" action="logout">
                <div class="form-group">
                    <span class="text-light" style="color: #9d9d9d; font-size: 18px;"><%=employee.getUsername()%></span>
                </div>
                <form action="get" method="signout">
                    <button type="submit" class="btn btn-danger" id="signout">Sign Out</button>
                </form>
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
