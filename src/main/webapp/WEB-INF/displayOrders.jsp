<%@ page import="data.entities.OrderEntities.Order" %><%--
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
    <%
        Order order = (Order) request.getAttribute("order");
        out.print(order);
    %>
</body>
</html>
