<%@ page import="data.entities.billOfMaterial.BillOfMaterial" %>
<%@ page import="data.entities.OrderEntities.Material" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
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
    HashMap billOfMaterial = (HashMap) sess.getAttribute("billOfMaterial");
    Material nails = (Material) billOfMaterial.get("nail");
    Material rafter = (Material) billOfMaterial.get("rafter");
    Material stake = (Material) billOfMaterial.get("stake");
    Material bracket = (Material) billOfMaterial.get("bracket");
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
    <tr>
        <td><%=rafter%>
        </td>
        <td><%=rafter.getDimensions().getHeight()%>
        </td>
        <td><%=rafter.getNumbers()%>
        </td>
        <td><%=rafter.getPackageType()%>
        </td>
        <td><%=rafter.getDescription()%>
        </td>
    </tr>
    <tr>
        <td><%=stake%>
        </td>
        <td><%=stake.getDimensions().getHeight()%>
        </td>
        <td><%=stake.getNumbers()%>
        </td>
        <td><%=stake.getPackageType()%>
        </td>
        <td><%=stake.getDescription()%>
        </td>
    </tr>
    <tr>
        <th>Beslag og Skruer</th>
    </tr>
    <tr>
        <td><%=nails%>
        </td>
        <td></td>
        <td><%=nails.getNumbers()%>
        </td>
        <td><%=nails.getPackageType()%>
        </td>
        <td><%=nails.getDescription()%>
        </td>
    </tr>
    <tr>
        <td><%=bracket%>
        </td>
        <td></td>
        <td><%=bracket.getNumbers()%>
        </td>
        <td><%=bracket.getPackageType()%>
        </td>
        <td><%=bracket.getDescription()%>
        </td>
    </tr>
</table>
</body>
</html>
