<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/9/18
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Offers </h3> <br/>
<%
    List employeeOrders = (List) request.getSession().getAttribute("employeeOrders");
    for (int i = 0; i < employeeOrders.size(); i++) {
        Order order = (Order) employeeOrders.get(i);
        if (order.getStatus() == Order.Status.OFFER) {
            out.print(order + "<br />");
%>
<form method="get" action="employeeEditOffer">
    <input type="hidden" name="orderId" value="<%=order.getId()%>">
    <button class="btn btn-primary" type="submit" value="edit and send offer" />
</form>
<%
        }
    }
%>
<h3>Let customer know the order is on the way</h3> <br/>
<%
    for (int i = 0; i < employeeOrders.size(); i++) {
        Order order = (Order) employeeOrders.get(i);
        if (order.getStatus() == Order.Status.ACCEPTED) {
            out.print(order + "<br />");
%>
<form method="post" action="sendOrder">
    <input type="hidden" name="orderId" value="<%=order.getId()%>">
    <input type="submit" value="conferm Order is on the way">
</form>
<%
        }
    }
%>
</body>
</html>
