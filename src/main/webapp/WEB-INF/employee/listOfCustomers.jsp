<%@ page import="entities.userEntities.Employee" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 6/8/18
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListOfCustomers</title>
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

        <li role="presentation">
            <%
                if (currEmployee.getRole().equals("CENTERCHEF") || currEmployee.getRole().equals("MATERIALEANSVARLIG")) {
            %>
            <a href="employeeCatalogMaterial">Materials Available</a>
            <% }%>
        </li>
    </ul>
    <div class="example">
        <b>Sorry we were unable to find "<%=request.getParameter("customerByUsername")%>", here's a list of our customers</b>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">email</th>
                <th scope="col">phone</th>
            </tr>
            </thead>
            <tbody>
            <%
                List customers = (List) request.getAttribute("customers");
                for (int i = 0; i < customers.size(); i++) {
                    Customer customer = (Customer) customers.get(i);
            %>
            <tr>
                <td>
                    <%=customer.getUsername()%>
                </td>
                <% if (customer.getPhone() != 0) { %>
                <td>
                    <%=customer.getPhone()%>
                </td>
                <% } else { %>
                <td>
                    <i>no phone</i>
                </td>
                <%}%>
                <% }%>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
