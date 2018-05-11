<%@ page import="entities.userEntities.Employee" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/25/18
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/materialize.min.css"/>
    <link rel="stylesheet" href="css/main.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <title>${title}</title>
</head>
<body>
<header>
    Welcome to the page displaying all Employees
</header>
    <div class="row">
        <form class=" col s12">
            <div class="row">
                <%
                    List employees = (List) request.getAttribute("employees");
                    Employee employee;
                    for(int i = 0; i < employees.size(); i++) {
                    	employee = (Employee) employees.get(i);
                        out.print(employee.getReg_date());
                    }
                %>
            </div>
        </form>
    </div>
</body>
</html>
