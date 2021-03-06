<%@ page import="entities.billOfMaterial.BillOfMaterial" %>
<%@ page import="entities.OrderEntities.Material" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="entities.OrderEntities.OrderLine" %>
<%@ page import="entities.OrderEntities.Order" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/7/18
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BillOfMaterial</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2><i>The bill of material</i></h2>
<%
    HttpSession sess = request.getSession();
    List billOfMaterial = (List) sess.getAttribute("billOfMaterial");
    Integer totalPrice_int = (Integer) sess.getAttribute("totalPrice");
    Order order = (Order) sess.getAttribute("order");
    int totalPrice = totalPrice_int.intValue();

%>
<style>
    table {
        font-family: arial, sans-serif;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    th {
        background-color: #dddddd;
    }
</style>

<table>
    <tr>
        <td> Beskrivelse</td>
        <td> Længde</td>
        <td> Antal</td>
        <td> Enhed</td>
        <td> Beskrivelse</td>
    </tr>
    <tr>
        <th>Træ og TagPlader</th>
    </tr>
    <%
        for (int i = 0; i < billOfMaterial.size(); i++) {
            OrderLine orderLine = (OrderLine) billOfMaterial.get(i);
    %>
    <% if (orderLine.isTreeOrRoof()) { %>
    <tr>
        <td><%=orderLine.getFirstDescription()%>
        </td>
        <td><%=orderLine.getLength()%>
        </td>
        <td><%=orderLine.getAmount()%>
        </td>
        <td><%=orderLine.getUnit()%>
        </td>
        <td><%=orderLine.getSecondDescription()%>
        </td>
        <%if(sess.getAttribute("employee") != null) { %>
        <td>
            <form action="employeeEditBillOfMaterial" method="post">
                <input type="hidden" name="orderLineId" value="<%=orderLine.getId()%>">
                <input type="number" name="orderLineAmount">
                <button class="btn btn-primary" type="submit">Change amount</button>
            </form>
        </td>
        <% } %>
    </tr>
    <% }
    } %>
    <tr>
        <th>Beslag og Skruer</th>
    </tr>
    <%
        for (int i = 0; i < billOfMaterial.size(); i++) {
            OrderLine orderLine = (OrderLine) billOfMaterial.get(i);
            if (!orderLine.isTreeOrRoof()) { %>
    <tr>
        <td><%=orderLine.getFirstDescription()%>
        </td>
        <td></td>
        <td><%=orderLine.getAmount()%>
        </td>
        <td><%=orderLine.getUnit()%>
        </td>
        <td><%=orderLine.getSecondDescription()%>
        </td>
        <%if(sess.getAttribute("employee") != null) { %>
        <td>
            <form action="employeeEditBillOfMaterial" method="post">
                <input type="hidden" name="orderLineId" value="<%=orderLine.getId()%>">
                <input type="number" name="orderLineAmount">
                <button class="btn btn-primary" type="submit">Change amount</button>
            </form>
        </td>
        <%} %>
    </tr>
    <% }
    }%>
</table>
<%if(request.getSession().getAttribute("customer") != null) { %>
<form method="get" action="redirect">
    <input type="hidden" name="goToPage" value="customerOrders"/>
    <input type="hidden" name="role" value="customer"/>
    <div class="btn-group" role="group" aria-label="...">
        <button type="submit" class="btn btn-primary">Orders</button>
    </div>
</form>
<% } else {%>
<form method="get" action="redirect">
    <input type="hidden" name="goToPage" value="employeeHomepage"/>
    <input type="hidden" name="role" value="employee"/>
    <div class="btn-group" role="group" aria-label="...">
        <button type="submit" class="btn btn-primary">Orders</button>
    </div>
</form>
<% } %>
</body>
</html>
