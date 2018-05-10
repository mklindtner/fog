<%@ page import="data.entities.OrderEntities.Order" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/9/18
  Time: 12:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Order order = (Order) request.getSession().getAttribute("order");
    %>
    <form method="post" action="employeeOfferOrder">
        <input type="number" name="height" value="<%=order.getHeight()%>" required> height
        <input type="number" name="width" value="<%=order.getWidth()%>" required> width
        <input type="number" name="length" value="<%=order.getLength()%>" required> length
        <input type="number" name="slope" value="<%=order.getSlope()%>" required> slope
        <input type="number" name="price" value="<%=order.getPrice()%>" required> price
        <input type="submit" value="send offer">
    </form>
</body>
</html>
