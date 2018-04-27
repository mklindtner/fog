<%@ page import="data.entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/27/18
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    welcome to the employees ordersPage where you can see all orders and click on them too! (in the near future)
    <%
        List list = (List) request.getAttribute("ordersWithoutShed");
        for(int i = 0; i < list.size(); i++) {
        	Order order = (Order) list.get(i);
        	out.print(order);
        }

    %>
</body>
</html>
