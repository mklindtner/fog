<%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/13/18
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form role="form" method="POST" action="createCustomer">
                <legend class="text-center">Register</legend>
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
                    <!--
                    <div class="form-group col-md-6">
                        <label for="confirm_password">Confirm Password</label>
                        <input type="password" class="form-control" name="" id="confirm_password" placeholder="Confirm Password">
                    </div> -->
                </fieldset>

                <fieldset>
                    <legend>Optional Details</legend>
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
                            Register
                        </button>
                        <a href="index.jsp">Already have an account?</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
