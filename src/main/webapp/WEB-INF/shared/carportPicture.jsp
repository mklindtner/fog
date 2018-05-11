<%@ page import="logic.generators.SVGUtil" %>
<%@ page import="entities.OrderEntities.Order" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/8/18
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carport</title>
</head>
<body>
    <%
        HttpSession sess = request.getSession();
        Order order = (Order) sess.getAttribute("order");
        SVGUtil carport = new SVGUtil(order.getLength(), order.getWidth());
        out.print(carport.createCarport());
    %>
</body>
</html>
