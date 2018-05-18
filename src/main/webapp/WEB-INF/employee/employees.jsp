<%@ page import="entities.userEntities.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/25/18
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
        <li role="presentation"><a href="redirect?goToPage=employeeHomepage&role=employee">currentOrderCases</a>
        </li>

        <li role="presentation">
            <a href="orderCatalog?employeeChoice=ordersAvailable">ordersAvailable</a>
        </li>

        <li role="presentation" class="active">
            <a href="allEmployees">See employees</a>
        </li>
    </ul>
    <div class="bd-example">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">role</th>
                <th scope="col">Employee Username</th>
                <th scope="col">Phone</th>
            </tr>
            <%
                HttpSession currentSession = request.getSession();
                List employees = (List) currentSession.getAttribute("employees");
                Employee currentUser = (Employee) currentSession.getAttribute("employee");
                for (int i = 0; i < employees.size(); i++) {
                    Employee tmpEmployee = (Employee) employees.get(i);
            %>
            <tr>
                <th scope="row"><%=tmpEmployee.getRole()%>
                </th>
                <td>
                    <%=tmpEmployee.getUsername()%>
                </td>
                <td>
                    <%=tmpEmployee.getPhone()%>
                </td>
                <%if (tmpEmployee.getRole().equals("SALGSMEDARBEJDER") && currentUser.getRole().equals("CENTERCHEF")) { %>
                <td>
                    <form method="post" action="employeeEdit">
                        <input type="hidden" name="employeeId" value="<%=tmpEmployee.getId()%>">
                        <button class="btn btn-primary" type="submit">delete employee</button>
                    </form>
                </td>
                <% } %>
            </tr>

            <% } %>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
