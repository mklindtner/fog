<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.userEntities.Employee" %><%--
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
    HttpSession session1 = request.getSession();
    String choice = (String) request.getAttribute("choice");
    Employee employee = (Employee) session1.getAttribute("employee");

    if (choice.equals("ordersWithoutShed")) {
        List list = (List) request.getAttribute("ordersWithoutShed");
        for (int i = 0; i < list.size(); i++) {
            Order order = (Order) list.get(i);
            out.print(order + "<br />");
        }
    }

    if (choice.equals("ordersAvailable")) {
        List list = (List) request.getAttribute("ordersAvailable");
        for (int i = 0; i < list.size(); i++) {
            Order order = (Order) list.get(i);
            out.print(order); %>
<form method="post" action="employeeChooseOrder">
    <input type="hidden" name="orderId" value="<%=order.getId()%>">
    <input type="submit" value="take this order">
</form>
<%  out.print("<br />"); }
}
%>
</body>
</html>
