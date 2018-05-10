<%@ page import="data.entities.userEntities.Customer" %>
<%@ page import="data.entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/8/18
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<%
    //TODO: add if's for rest of states
    List customerOrders = (List) request.getSession().getAttribute("customerOrders");
    for (int i = 0; i < customerOrders.size(); i++) {
        Order order = (Order) customerOrders.get(i);
        if (order.getStatus() == Order.Status.OFFER) {
            out.print(order); %>
<form method="post" action="customerAcceptOrder">
    <input type="hidden" name="orderId" value="<%=order.getId()%>">
    <input type="submit" value="accept offer">
</form>
<%
        }
    }
%>
</body>
</html>
