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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%
    HttpSession sess = request.getSession();
    Order order = (Order) sess.getAttribute("order");
    SVGUtil carport = new SVGUtil(order.getLength(), order.getWidth());
    out.print(carport.createCarport());
%>
<div class="container">
    <form method="get" action="redirect">
        <input type="hidden" name="goToPage" value="customerOrders"/>
        <input type="hidden" name="role" value="customer"/>
        <div class="btn-group" role="group" aria-label="...">
            <button type="submit" class="btn btn-primary">Orders</button>
        </div>
    </form>
</div>

</body>
</html>
