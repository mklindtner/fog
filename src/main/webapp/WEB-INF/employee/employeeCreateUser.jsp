<%@ page import="entities.userEntities.Employee" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/18/18
  Time: 11:05 PM
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
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form role="form" method="POST" action="createEmployee">
                <legend class="text-center">Create Employee</legend>
                <fieldset>
                    <legend>Account Details</legend>
                    <div class="form-group col-md-12">
                        <label for="username">Email/username</label>
                        <input type="email" class="form-control" name="username" id="username" placeholder="Email">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="employeeRole">Employee Role</label>
                        <select class="form-control form-control-lg" name="employeeRole" id="employeeRole" required>
                            <option>salgsmedarbejder</option>
                            <option>materialeAnsvarlig</option>
                        </select>
                    </div>
                    <!--
                    <div class="form-group col-md-6">
                        <label for="confirm_password">Confirm Password</label>
                        <input type="password" class="form-control" name="" id="confirm_password" placeholder="Confirm Password">
                    </div> -->
                </fieldset>

                <fieldset>
                    <div class="form-group col-md-6">
                        <input class="form-control form-control-lg" type="number" name="phoneNumber" id="phoneNumber" placeholder="phoneNumber" data-bind="value:replyNumber">
                    </div>

                    <div class="form-group col-md-12 hidden">
                        <label for="specify">Please Specify</label>
                        <textarea class="form-control" id="specify" name=""></textarea>
                    </div>
                </fieldset>

                <div class="form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">
                            Create employee
                        </button>
                        <a href="redirect?goToPage=employees&role=employee">go back</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
