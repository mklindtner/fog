<%@ page import="data.entities.userEntities.Customer" %>
<%@ page import="logic.generators.SVGUtil" %>
<%@ page import="logic.generators.CalculateCarport" %><%--
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
    //Customer customer = (Customer) request.getAttribute("customer");
    Integer width_int = (Integer) request.getAttribute("width");
    Integer length_int = (Integer) request.getAttribute("length");
    int width = width_int.intValue();
    int length = length_int.intValue();
    SVGUtil carport = new SVGUtil(length, width);

%>
<%out.print(carport.createCarport()); //generates an image of a carport%>

</body>
</html>
