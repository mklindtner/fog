<%@ page import="data.entities.userEntities.Customer" %>
<%@ page import="data.entities.OrderEntities.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/8/18
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
    <%
        //HttpSession session1 = request.getSession();
        List customerOrders = (List) request.getAttribute("customerOrders");
        for(int i = 0; i < customerOrders.size(); i++ ) {
        	Order order = (Order) customerOrders.get(i);
            if( order.getStatus() == Order.Status.AVAILABLE) {
                out.print("pending " + "<br />");
                out.print(order + "<br />");
            }
            if( order.getStatus() == Order.Status.ACCEPTED) {
            	out.print("offer" + "<br />");
            	out.print(order + "<br />");
            }
        }
    %>
</body>
</html>
