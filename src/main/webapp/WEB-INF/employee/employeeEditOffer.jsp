<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="entities.userEntities.Employee" %><%--
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
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<%@ include file="/WEB-INF/employee/employeeHeader.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form role="form" method="post" action="employeeOfferOrder">
                <legend class="text-center">Send Offer to Customer</legend>
                <% Order order = (Order) request.getSession().getAttribute("order"); %>
                <fieldset>
                    <legend>Order Details</legend>
                    <div class="form-group col-md-6">
                        <label for="height">Height</label>
                        <input type="number" class="form-control" name="height" id="height"
                               value="<%=order.getHeight()%>">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="width">Width</label>
                        <input type="number" class="form-control" name="width" id="width" value="<%=order.getWidth()%>">
                    </div>


                    <div class="form-group col-md-6">
                        <label for="length">Length</label>
                        <input type="number" class="form-control" name="length" id="length"
                               value="<%=order.getLength()%>">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="slope">Slope</label>
                        <input type="number" class="form-control" name="slope" id="slope" value="<%=order.getSlope()%>">
                    </div>

                    <div class="form-group col-md-6">
                        <label for="price">Price</label>
                        <input type="number" class="form-control" name="price" id="price" value="<%=order.getPrice()%>">
                    </div>

                </fieldset>
                <% if (order.getShed() != null) {%>
                <fieldset>
                    <legend>Shed Details</legend>
                    <div class="form-group col-md-4">
                        <label for="shedLength">shedLength</label>
                        <input type="number" class="form-control" name="shedLength" id="shedLength"
                               value="<%=order.getShed().getWidth()%>">
                    </div>

                    <div class="form-group col-md-4">
                        <label for="shedWidth">shedWidth</label>
                        <input type="number" class="form-control" name="shedLength" id="shedWidth"
                               value="<%=order.getShed().getLength()%>">
                    </div>

                    <div class="form-group col-md-4">
                        <label for="hasFloor">withFloor</label>
                        <br/>
                        <input type="checkbox" checked data-toggle="toggle" id="hasFloor" name="hasFloor">
                    </div>

                    <div class="form-group col-md-12 hidden">
                        <label for="specify">Please Specify</label>
                        <textarea class="form-control" id="specify" name=""></textarea>
                    </div>
                </fieldset>
                <% }%>
                <div class="form-group">
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary">
                            Send Offer to Customer
                        </button>
                    </div>
                    <div class="col-md-6">
                        <form method="get" action="redirect">
                            <input type="hidden" name="goToPage" value="employeeHomepage">
                            <input type="hidden" name="role" value="employee">
                            <button type="submit" class="btn btn-primary">
                                Go Back
                            </button>
                        </form>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
