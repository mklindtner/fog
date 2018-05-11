<%@ page import="entities.userEntities.Customer" %>
<%@ page import="logic.generators.SVGUtil" %>
<%@ page import="logic.generators.CalculateCarport" %>
<%@ page import="entities.OrderEntities.Order" %>
<%@ page import="logic.generators.BillOfMaterialsCalculator" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 4/25/18
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CustomerOrderInformation</title>
</head>
<body>
<form method="get" action="generateCarport">
    <input type="submit" value="see carport picture">
</form>
<form method="get" action="billOfMaterial">
    <input type="submit" value="generate BillOfMaterial">
</form>
</body>
</html>
