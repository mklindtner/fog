<%@ page import="logic.generators.SVGUtil" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Basic HTML File</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/hidden.js"></script>
    <script>
        hideShedOption()
    </script>
</head>
<body>
<%@ include file="WEB-INF/shared/header.jsp" %>
<div class="container">
    <div class="form-row">
        <div class="form-group col-md-12">
            <img src="img/carport_fog.JPG">
        </div>
    </div>
</div>
<div class="container">
    <div class="form-group col-md-12">
        <h1><i>Send a order for a garage</i></h1>
    </div>
    <form method="post" action="createOrder">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="lengthChoice">Length</label>
                <select class="form-control form-control-lg" id="lengthChoice">
                    <option>choose a value</option>
                    <option>240cm</option>
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="widthChoice">Width</label>
                <select class="form-control form-control-lg" id="widthChoice">
                    <option>choose a value</option>
                    <option>240cm</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6 col-md-6">
                <label for="heightChoice">Height</label>
                <select class="form-control form-control-lg" id="heightChoice">
                    <option>choose a value</option>
                    <option>240cm</option>
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label for="slopeChoice">slope</label>
                <select class="form-control form-control-lg" id="slopeChoice">
                    <option>choose a value</option>
                    <option>45cm</option>
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label for="contactInformation">contact</label>
                <input type="email" class="form-control" id="contactInformation" placeholder="email"
                       required>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-4 col-md-4">
                <div class="btn-group-toggle" data-toggle="buttons" style="padding-top: 20px;">
                    <a class="btn btn-secondary active" id="checkShedTwo">WithShed</a>
                </div>
            </div>
            <div class="hidden" id="shedVisible">
                <div class="col-md-4 col-md-4">
                    <label for="shedWidth">shedWidth</label>
                    <select class="form-control form-control-lg" id="shedWidth">
                        <option>choose a value</option>
                        <option>60cm</option>
                    </select>
                </div>
                <div class="col-md-4 col-md-4">
                    <label for="shedLength">shedLength</label>
                    <select class="form-control form-control-lg" id="shedLength">
                        <opton>choose a value</opton>
                        <option>40cm</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-12 col-md-12" style="padding-top: 20px">
                <button class="btn btn-primary" type="submit">create Order</button>
            </div>
        </div>
    </form>
</div>

<form method="get" action="TestServlet">
    <input type="submit" value="press me">
</form>


<!-- rem, spær, stolper -->
<!--
<h3>Login User</h3>
<form method="get" action="login">
    <input type="text" name="username" value="empTest" required>
    <input type="password" name="password" value="123" required>
    <input type="submit" value="login">
</form>
<form method="get" action="login">
    <input type="text" name="username" value="customer" required>
    <input type="password" name="password" value="123" required>
    <input type="submit" value="login">
</form>
<h3>Create user</h3>
<form method="post" action="createCustomer">
    <input type="text" name="username" value="test" required>
    <input type="text" name="password" value="123" required>
    <input type="number" name="phone" value="213456" required>
    <input type="submit" value="create User">
</form>
<form method="get" action="getCustomer">
    <input type="text" name="username" value="testTwo">
    <input type="submit" value="findCustomer">
</form>
<form method="get" action="redirect">
    <input type="hidden" name="goToPage" value="customerCreateUser">
    <input type="hidden" name="role" value="customer">
    <input type="submit" value="go to create user">
</form>
-->
</body>

</html>
