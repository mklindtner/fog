<%@ page import="logic.generators.SVGUtil" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fog Frontpage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
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
        <% if (request.getAttribute("error") != null) {%>
        <h4><%=request.getAttribute("error")%>
        </h4>
        <% }%>
    </div>
    <div class="form-group col-md-12">
        <h1><i>Send a order for a garage</i></h1>
    </div>
    <form method="post" action="indexCreateOrderUser">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="length">Length</label>
                <select class="form-control form-control-lg" id="length" name="length" required>
                    <option disabled>choose a value</option>
                    <option>480</option>
                    cm
                    <option>540</option>
                    cm
                    <option>600</option>
                    cm
                    <option>720</option>
                    cm
                    <option>780</option>
                    cm
                    <option>810</option>
                    cm
                    <option>910</option>
                    cm
                </select>
            </div>
            <div class="form-group col-md-6">
                <label for="width">Width</label>
                <select class="form-control form-control-lg" id="width" name="width" required>
                    <option disabled>choose a value</option>
                    <option>300</option>
                    cm
                    <option>360</option>
                    cm
                    <option>390</option>
                    cm
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6 col-md-6">
                <label for="height">Height</label>
                <select class="form-control form-control-lg" id="height" name="height" required>
                    <option disabled>choose a value</option>
                    <option>480</option>
                    cm
                    <option>540</option>
                    cm
                    <option>600</option>
                    cm
                    <option>720</option>
                    cm
                    <option>780</option>
                    cm
                    <option>810</option>
                    cm
                    <option>910</option>
                    cm
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label for="slope">slope</label>
                <select class="form-control form-control-lg" id="slope" name="slope" required>
                    <option disabled>choose a value</option>
                    <option>45</option>
                    cm
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label for="contactInformation">contact</label>
                <input type="email" class="form-control" name="contactInformation" id="contactInformation"
                       placeholder="email" value="indexUser@email.com">
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-4 col-md-3">
                <div class="btn-group-toggle" data-toggle="buttons" style="padding-top: 20px;">
                    <a class="btn btn-secondary active" id="checkShedTwo">WithShed</a>
                </div>
            </div>
            <div class="hidden" id="shedVisible">
                <div class="col-md-4 col-md-3">
                    <label for="shedWidth">shedWidth</label>
                    <select class="form-control form-control-lg" id="shedWidth" name="shedWidth" required>
                        <option>None</option>
                        <option>60</option>
                        cm
                    </select>
                </div>
                <div class="col-md-4 col-md-3">
                    <label for="shedLength">shedLength</label>
                    <select class="form-control form-control-lg" id="shedLength" name="shedLength" required>
                        <option>None</option>
                        <option>40</option>
                        cm
                    </select>
                </div>
                <div class="col-md-4 col-md-3">
                    <label for="hasFloor">withFloor</label>
                    <br/>
                    <input type="checkbox" checked data-toggle="toggle" id="hasFloor" name="hasFloor">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-12 col-md-12" style="padding-top: 20px">
                <button class="btn btn-primary" type="submit" name="clickCreate" id="clickCreate">create Order</button>
            </div>
        </div>
    </form>
</div>


<!--
<form method="get" action="TestServlet">
    <input type="submit" value="press me">
</form> -->
</body>

</html>
