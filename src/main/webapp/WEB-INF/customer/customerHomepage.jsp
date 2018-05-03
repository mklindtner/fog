<%@ page import="data.entities.userEntities.Customer" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/25/18
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
this is the homepage for customers after creating or logging in, Welcome
<%
    Customer customer = (Customer) request.getSession().getAttribute("customer");
    out.print(customer.getUsername());
%>!
</body>
</html>
