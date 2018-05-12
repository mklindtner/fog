<%@ page import="entities.userEntities.Employee" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/26/18
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    this is the employee homepage, get to work
    <%
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        out.print(emp.getUsername());
    %>!
    <form method="get" action="orderCatalog">
        <input type="hidden" name="employeeChoice" value="ordersWithoutShed">
        <input type="submit" value="see all orders withoutShed">
    </form>

    <form method="get" action="orderCatalog">
        <input type="hidden" name="employeeChoice" value="ordersAvailable">
        <input type="submit" value="see available orders">
    </form>

    <form method="get" action="employeeCurrentOrders">
        <input type="submit" value="display your current order-cases">
    </form>

    <form method="get" action="employees">
        <input type="submit" value="see employees">
    </form>
</body>
</html>
