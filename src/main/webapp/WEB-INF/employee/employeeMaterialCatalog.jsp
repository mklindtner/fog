<%@ page import="entities.userEntities.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.OrderEntities.Material" %>
<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/19/18
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/employee/employeeHeader.jsp" %>
<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation">
            <a href="redirect?goToPage=employeeHomepage&role=employee">currentOrderCases</a>
        </li>

        <li role="presentation">
            <a href="orderCatalog?employeeChoice=ordersAvailable">ordersAvailable</a>
        </li>

        <li role="presentation">
            <% Employee currEmployee = (Employee) request.getSession().getAttribute("employee");
                if (currEmployee.getRole().equals("CENTERCHEF")) {
            %>
            <a href="allEmployees">See employees</a>
            <% }%>
        </li>

        <li role="presentation" class="active">
            <%
                if (currEmployee.getRole().equals("CENTERCHEF") || currEmployee.getRole().equals("MATERIALEANSVARLIG")) {
            %>
            <a href="employeeCatalogMaterial">Materials Available</a>
            <% }%>
        </li>

    </ul>
    <div class="bd-example">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">PricePrUnit</th>
                <th scope="col">description</th>
            </tr>
            </thead>
            <tbody>
            <%
                List allMaterials = (List) request.getSession().getAttribute("allMaterials");
                for (int i = 0; i < allMaterials.size(); i++) {
                    Material material = (Material) allMaterials.get(i);
            %>
            <tr>
                <th scope="row"><%=material.getId()%>
                <td>
                    <%=material.getPricePrUnit()%>
                </td>
                <td>
                    <%=material.getDescription()%>
                </td>
            </tr>

            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
