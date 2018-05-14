<%@ page import="entities.userEntities.Customer" %>
<%@ page import="entities.OrderEntities.Order" %>
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
<h3>Pending</h3><br />
<%
    List customerOrders = (List) request.getSession().getAttribute("customerOrders");
    for (int i = 0; i < customerOrders.size(); i++) {
        Order order = (Order) customerOrders.get(i);
        if (order.getStatus() == Order.Status.PENDING) {
            out.print(order + "<br />");
        }
    }
%>
<h3>Offers</h3> <br/>

<%
    for (int i = 0; i < customerOrders.size(); i++) {
        Order order = (Order) customerOrders.get(i);
        if (order.getStatus() == Order.Status.OFFER) {
            out.print(order + "<br />");
%>
<form method="post" action="customerAcceptOrder">
    <input type="hidden" name="orderId" value="<%=order.getId()%>">
    <input type="submit" value="accept offer">
</form>
<%
        }
    }
%>
<h3>Accepted Orders</h3> <br/>
<% for (int i = 0; i < customerOrders.size(); i++) {
    Order order = (Order) customerOrders.get(i);
    if (order.getStatus() == Order.Status.ACCEPTED) {
        out.print(order + "<br />");
%>
<form method="get" action="orderInformation">
    <input type="hidden" name="orderId" value=<%=order.getId()%>>
    <input type="submit" value="order information">
</form>
<%
        }
    }
%>
<h3>Orders on the way</h3> <br/>
<%
    for (int i = 0; i < customerOrders.size(); i++) {
        Order order = (Order) customerOrders.get(i);
        if (order.getStatus() == Order.Status.SEND) {
            out.print(order + "<br />");
%>
<form method="get" action="orderInformation">
    <input type="hidden" name="orderId" value=<%=order.getId()%>>
    <input type="submit" value="order information">
</form>
<%
        }
    }
%>
</body>
</html>
