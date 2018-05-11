<%@ page import="entities.billOfMaterial.BillOfMaterial" %>
<%@ page import="entities.OrderEntities.Material" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="entities.OrderEntities.OrderLine" %><%--
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
</head>
<body>
<h2><i>The bill of material</i></h2>
<%
    HttpSession sess = request.getSession();
    List billOfMaterial = (List) sess.getAttribute("billOfMaterial");
    Integer totalPrice_int = (Integer) sess.getAttribute("totalPrice");
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
    </tr>
    <% }
    }%>
</table>
</body>
</html>
