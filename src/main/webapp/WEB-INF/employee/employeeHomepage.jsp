<%@ page import="data.entities.userEntities.Employee" %><%--
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
        <input type="submit" value="see all orders withoutShed">
        <input type="hidden" name="employeeChoice" value="ordersWithoutShed">
    </form>

    <form method="get" action="orderCatalog">
        <input type="submit" value="see available orders">
        <input type="hidden" name="employeeChoice" value="ordersAvailable">
    </form>

</body>
</html>
