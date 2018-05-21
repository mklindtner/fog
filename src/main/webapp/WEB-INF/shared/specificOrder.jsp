<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="entities.userEntities.Customer" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/20/18
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<%@ include file="/WEB-INF/customer/customerHeader.jsp"%>
<div class="container">
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <%
                    HttpSession session1 = request.getSession();
                    Order order = (Order) session1.getAttribute("order");
                %>
                <h3 class="panel-title">Ordered by: <i><%= customer.getUsername() %></i>
                </h3>
            </div>
            <table class="table">
                <thead>
                <tr class="filters">
                    <th colspan="col">id</th>
                    <th colspan="col">length</th>
                    <th colspan="col">width</th>
                    <th colspan="col">height</th>
                    <th colspan="col">price</th>
                    <th colspan="col">slope</th>
                    <th colspan="col">status</th>
                    <th colspan="col">shedWidth</th>
                    <th colspan="col">shedLength</th>
                    <th colspan="col">shedHasFloor</th>
                </tr>
                <td>
                    <%=order.getId()%>
                </td>
                <td>
                    <%=order.getLength()%>
                </td>
                <td>
                    <%=order.getWidth()%>
                </td>
                <td>
                    <%=order.getHeight()%>
                </td>
                <td>
                    <%=order.getPrice()%>
                </td>
                <td>
                    <%=order.getSlope()%>
                </td>
                <td>
                    <%=order.getStatus()%>
                </td>
                <% if(order.getShed() != null) {
                %>
                <td>
                    <%=order.getShed().getLength()%>
                </td>
                <td>
                    <%=order.getShed().getWidth()%>
                </td>
                <td>
                    <%=order.getShed().getFloor()%>
                </td>
                <% }%>
                <td>
                    <form method="get" action="redirect">
                        <input type="hidden" name="goToPage" value="customerOrders">
                        <input type="hidden" name="role" value="customer">
                        <button class="btn btn-primary" type="submit">go back</button>
                    </form>
                </td>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
