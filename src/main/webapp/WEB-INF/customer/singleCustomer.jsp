<%@ page import="data.entities.userEntities.Customer" %>
<%@ page import="logic.generators.SVGUtil" %>
<%@ page import="logic.generators.CalculateCarport" %>
<%@ page import="data.entities.OrderEntities.Order" %>
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
    <title>Title</title>
</head>
<body>
<%
    /*
    //Customer customer = (Customer) request.getAttribute("customer");
    HttpSession session1 = request.getSession();
    Integer width_int = (Integer) session1.getAttribute("width");
    Integer length_int = (Integer) session1.getAttribute("length");
    int width = width_int.intValue();
    int length = length_int.intValue();

    int startDistancePillar = 110;
    //SVGUtil carport = new SVGUtil(length, width);


    Integer shedLength_int = (Integer) session1.getAttribute("shedLength");
    Integer shedWidth_int = (Integer) session1.getAttribute("shedWidth");
    int shedLength = shedLength_int.intValue();
    int shedWidth = shedWidth_int.intValue();
*/
    //Order order = (Order) request.getSession().getAttribute("order");
    //out.print(order);
    //out.print(shedLength);
    //out.print(shedWidth);
%>
<form method="get" action="generateCarport">
    <input type="submit" value="see carport picture">
</form>
<form method="get" action="BillOfMaterial">
    <input type="submit" value="generate BillOfMaterial">
</form>
<form method="post" action="createOrder">
    <input type="submit" value="send order offer">
</form>
</body>
</html>
