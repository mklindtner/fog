<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="entities.userEntities.Customer" %><%--
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
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <script src="/js/hidden.js"></script>
    <script>
        hideShedOption()
    </script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<%@ include file="/WEB-INF/customer/customerHeader.jsp" %>
<!-- this is createOrder-->
<!-- calls customer ref from customer headder -->

<div class="container">
    <ul class="nav nav-tabs">
        <li role="presentation" >
            <a href="redirect?goToPage=customerOrders&role=customer">Your Orders</a>
        </li>
        <li role="presentation" class="active">
            <a href="redirect?goToPage=customerHomepage&role=customer">Create Order </a>
        </li>
    </ul>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form role="form" method="post" action="createOrder">
                <legend class="text-center">Welcome <%=customer.getUsername()%></legend>
                <fieldset>
                    <legend>Customize your order</legend>
                    <div class="form-group col-md-6">
                        <label for="height">Height</label>
                        <select class="form-control form-control-lg" name="height" id="height" required>
                            <option disabled>choose a value</option>
                            <option>480</option>cm
                            <option>540</option>cm
                            <option>600</option>cm
                            <option>720</option>cm
                            <option>780</option>cm
                            <option>810</option>cm
                            <option>910</option>cm
                        </select>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="width">Width</label>
                        <select class="form-control form-control-lg" name="width" id="width" required>
                            <option disabled>choose a value</option>
                            <option>300</option>cm
                            <option>360</option>cm
                            <option>390</option>cm
                        </select>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="length">Length</label>
                        <select class="form-control form-control-lg" name="length" id="length" required>
                            <option disabled>choose a value</option>
                            <option>480</option>cm
                            <option>540</option>cm
                            <option>600</option>cm
                            <option>720</option>cm
                            <option>780</option>cm
                            <option>810</option>cm
                            <option>910</option>cm
                        </select>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="slope">Slope</label>
                        <select class="form-control form-control-lg" name="slope" id="slope" required>
                            <option disabled>choose a value</option>
                            <option>45</option>cm
                        </select>
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Shed Details</legend>
                    <div class="form-group col-md-4">
                        <label for="ShedWidth">shedWidth</label>
                        <select class="form-control form-control-lg" name="shedWidth" id="shedWidth" >
                            <option>None</option>
                            <option>45</option>cm
                        </select>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="shedLength">ShedLength</label>
                        <select class="form-control form-control-lg" name="shedLength" id="shedLength">
                            <option>None</option>
                            <option>45</option>cm
                        </select>
                    </div>

                    <div class="form-group col-md-4">
                        <label for="hasFloor">withFloor</label>
                        <br />
                        <input type="checkbox" checked data-toggle="toggle" id="hasFloor" name="hasFloor">
                    </div>


                </fieldset>

                <div class="form-group">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">
                            Send Order
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
