<%@ page import="data.entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/1/18
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    hej med dig
    <%
        Order order = (Order) request.getSession().getAttribute("order");
        List orders = (List) request.getSession().getAttribute("ordersWithoutShed");
        for(int i = 0; i < orders.size(); i++ ) {
        	Order currentOrder = (Order) orders.get(i);
        	out.println(currentOrder);
        }
    %>
</body>
</html>
