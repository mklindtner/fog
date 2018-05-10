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

create order
<form method="post" action="createOrder">
    <input type="number" name="height" value="0" required> height
    <input type="number" name="width" value="280" required> width
    <input type="number" name="length" value="250" required> length
    <input type="number" name="slope" value="45" required> slope
    <input type="number" name="shedWidth" value="200" > shedWith
    <input type="number" name="shedLength" value="300" > shedLength
    <input type="submit" value="insert order">
</form>

<form method="get" action="customerOrders">
    <input type="submit" value="your orders">
</form>
</body>
</html>
