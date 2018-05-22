<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../css/orderList.css"></script>
</head>
<div class="container">
    <!-- Jumbotron -->
    <div class="jumbotron">
        <h1><i class="fa fa-frown-o red"></i> Error  Page</h1>
        <p class="lead">We couldn't find what you're looking for on <em><span id="display-domain"></span></em>.</p>
        <button type="submit" class="btn btn-primary">Go back to homepage</button>
    </div>
</div>
<div class="container">
    <div class="body-content">
        <div class="row">
            <div class="col-md-12">
                <h2>What happened?</h2>
                <p class="lead"><%=request.getAttribute("error")%></p>
            </div>
        </div>
    </div>
</div>