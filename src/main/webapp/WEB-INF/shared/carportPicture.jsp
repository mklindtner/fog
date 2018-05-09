<%@ page import="logic.generators.SVGUtil" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 5/8/18
  Time: 6:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carport</title>
</head>
<body>
    <%
        HttpSession sess = request.getSession();
        Integer length = (Integer) sess.getAttribute("length");
        Integer width = (Integer) sess.getAttribute("width");
        SVGUtil carport = new SVGUtil(length.intValue(), width.intValue());
        out.print(carport.createCarport());
    %>
</body>
</html>
