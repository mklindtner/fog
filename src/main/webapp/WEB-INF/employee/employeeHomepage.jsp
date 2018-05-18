<%@ page import="entities.userEntities.Employee" %>
<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/26/18
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="css/orderList.css"></script>
</head>
<body>
<%@ include file="/WEB-INF/employee/employeeHeader.jsp" %>

<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="redirect?goToPage=employeeHomepage&role=employee">currentOrderCases</a>
        </li>

        <li role="presentation">
            <a href="orderCatalog?employeeChoice=ordersAvailable">ordersAvailable</a>
        </li>

        <li role="presentation">
            <a href="employees">See employees</a>
        </li>
    </ul>

    <div class="bd-example">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Height</th>
                <th scope="col">Width</th>
                <th scope="col">Length</th>
            </tr>
            <tr>
                <th scope="colgroup">OFFERS</th>
            </tr>
            </thead>
            <tbody>
            <%
                List employeeOrders = (List) request.getSession().getAttribute("employeeOrders");
                for (int i = 0; i < employeeOrders.size(); i++) {
                    Order order = (Order) employeeOrders.get(i);
                    if (order.getStatus() == Order.Status.OFFER) {
            %>
            <tr>
                <th scope="row"><%=order.getId()%>
                </th>
                <td><%=order.getHeight()%>
                </td>
                <td><%=order.getWidth()%>
                </td>
                <td><%=order.getLength()%>
                </td>
                <td>
                    <form method="get" action="employeeEditOffer">
                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                        <button class="btn btn-primary" type="submit">edit and send offer</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            <tr>
                <th scope="colgroup">ACCEPTED</th>
            </tr>
            <%
                for (int i = 0; i < employeeOrders.size(); i++) {
                    Order order = (Order) employeeOrders.get(i);
                    if (order.getStatus() == Order.Status.ACCEPTED) {
            %>
            <tr>
                <th scope="row"><%=order.getId()%>
                </th>
                <td><%=order.getHeight()%>
                </td>
                <td><%=order.getWidth()%>
                </td>
                <td><%=order.getLength()%>
                </td>
                <td>
                    <form method="post" action="sendOrder">
                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                        <button class="btn btn-primary" type="submit">conferm order is on the way</button>
                    </form>

                    <form method="get" action="billOfMaterial">
                        <input type="hidden" name="orderId" value="<%=order.getId()%>">
                        <button class="btn btn-primary" type="submit">see BillOfMaterial</button>
                    </form>

                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
