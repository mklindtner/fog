<%@ page import="data.entities.userEntities.Customer" %>
<%@ page import="logic.generators.SVGUTil" %>
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
    Customer customer = (Customer) request.getAttribute("customer");

    Integer width_int = (Integer) request.getAttribute("width");
    Integer length_int = (Integer) request.getAttribute("length");
    int width = width_int.intValue();
    int length = width_int.intValue();
    CalculateCarport calculateCarport = new CalculateCarport(length, width);
    final int startDistancePillar = 110;
    int rafterDistance = calculateCarport.procentageDistancePillars();

%>
<SVG width="760" height="600" viewBox="0 0 <%=length%> <%=width%> ">
    <defs>
        <marker id="beginArrow"
                markerWidth="9" markerHeight="9"
                refX="0" refY="4"
                orient="auto">
            <path d="M0,4 L8,0 L8,8 L0,4" style="fill: #000000s;"/>
        </marker>
        <marker id="endArrow"
                markerWidth="9" markerHeight="9"
                refX="8" refY="4"
                orient="auto">
            <path d="M0,0 L8,4 L0,8 L0,0" style="fill: #000000;"/>
        </marker>
    </defs>
    <line x1="20%" y1="95%" x2="70%" y2="95%"
          style="stroke:#006600;
        	    marker-start: url(#beginArrow);
               marker-end: url(#endArrow);"/>
    <text x=40% y=98% text-anchor="middle" fill="black"> Length: <%= length %>
    </text>
    <line x1="95%" y1="10%" x2="95%" y2="80%"
          style="stroke:#006600;
	            marker-start: url(#beginArrow);
            marker-end: url(#endArrow);"/>
    <text x=97% y=50% text-anchor="middle" style="writing-mode: tb;"> Width: <%= width %>
    </text>
    <svg width="90%" height="90%">
        <rect width="100%" height="100%" id="roof"
              style="stroke:black; fill:none"/>
        <rect x="0" y="10%" height="10" width="<%=width%>" id="upperRem"
              style="stroke:black; fill:none"/>
        <rect x="0" y="85%" height="10" width="<%=width%>" id="lowerRem"
              style="stroke:black; fill:none"/>
        <%for (int x = 0; x <= width; x += 55) {%>
        <rect x="<%= x %>" y="0%" height="100%" width="10" id="rafter"
              style="stroke:black; fill:none"/>
        <%}%>
        <%for (int x = startDistancePillar; x <= width; x += 250) { %>
        <rect x="<%= x %>" y="10%" height="10" width="10" id="upperPillar"
              style="stroke:black; fill:black"/>
        <rect x="<%= x %>" y="85%" height="10" width="10" id="lowerPillar"
              style="stroke:black; fill:black"/>
        <%}%>
        <line x1="12%" y1="12%" x2="81.5%" y2="85.5%"
              style="stroke-dasharray: 2 2; stroke: blue; fill: none"/>
        <line x1="12%" y1="85.5%" x2="81.5%" y2="12%"
              style="stroke-dasharray: 2 2; stroke: blue; fill: none"/>
    </svg>
</SVG>
</body>
</html>
