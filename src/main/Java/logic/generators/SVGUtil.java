package logic.generators;

public class SVGUtil
{
	//stopler
	//rem
	//spær
	int width, height;
	final int startDistancePillar = 110;

	public SVGUtil(int length, int width)
	{
		this.height = length;
		this.width = width;
	}

	public String createCarport()
	{
		StringBuilder sb = new StringBuilder();
		return sb.append(mainBox()).toString();
	}

	private String mainBox()
	{
		String boxMeasurement = "<SVG width=\"760\" height=\"600\" viewBox=\"0 0 " + height + " " + width + " \">";
		return boxMeasurement + arrowsDefintion();
	}

	private String arrowsDefintion()
	{
		String arrowsDef = " <defs>\n" +
						   "        <marker id=\"beginArrow\"\n" +
						   "                markerWidth=\"9\" markerHeight=\"9\"\n" +
						   "                refX=\"0\" refY=\"4\"\n" +
						   "                orient=\"auto\">\n" +
						   "            <path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: #000000s;\"/>\n" +
						   "        </marker>\n" +
						   "        <marker id=\"endArrow\"\n" +
						   "                markerWidth=\"9\" markerHeight=\"9\"\n" +
						   "                refX=\"8\" refY=\"4\"\n" +
						   "                orient=\"auto\">\n" +
						   "            <path d=\"M0,0 L8,4 L0,8 L0,0\" style=\"fill: #000000;\"/>\n" +
						   "        </marker>\n" +
						   "    </defs>";
		return arrowsDef + arrowsCall();
	}

	private String arrowsCall()
	{
		String arrows = " <line x1=\"20%\" y1=\"95%\" x2=\"70%\" y2=\"95%\"\n" +
						"          style=\"stroke:#006600;\n" +
						"        \t    marker-start: url(#beginArrow);\n" +
						"               marker-end: url(#endArrow);\"/>\n" +
						"    <text x=40% y=98% text-anchor=\"middle\" fill=\"black\"> Length: " + height + "\n" +
						"    </text>\n" +
						"    <line x1=\"95%\" y1=\"10%\" x2=\"95%\" y2=\"80%\"\n" +
						"          style=\"stroke:#006600;\n" +
						"\t            marker-start: url(#beginArrow);\n" +
						"            marker-end: url(#endArrow);\"/>\n" +
						"    <text x=97% y=50% text-anchor=\"middle\" style=\"writing-mode: tb;\"> Width: " + width + "</text>";
		return arrows + roofCanvas();
	}

	private String roofCanvas()
	{
		String roofCanvas = "<svg width=\"90%\" height=\"90%\">";
		return roofCanvas + roofMeasurements();
	}

	private String roofMeasurements()
	{
		String roofMeasurements = "<rect width=\"100%\" height=\"100%\" id=\"roof\"\n" +
								  "style=\"stroke:black; fill:none\"/>";
		return roofMeasurements + rems();
	}

	private String rems()
	{
		String rems = " <rect x=\"0\" y=\"10%\" height=\"10\" width=\"" + width + "\" id=\"upperRem\"\n" +
					  "              style=\"stroke:black; fill:none\"/>\n" +
					  "        <rect x=\"0\" y=\"85%\" height=\"10\" width=\"" + width + "\" id=\"lowerRem\"\n" +
					  "              style=\"stroke:black; fill:none\"/>";
		return rems + rafters();
	}

	private String rafters()
	{
		String rafters = "";
		for (int x = 0; x <= width; x += 55) {
			rafters += "  <rect x=\"" + x + "\" y=\"0%\" height=\"100%\" width=\"10\" id=\"rafter\"\n" +
					   "style=\"stroke:black; fill:none\"/>";
		}
		return rafters + Pillars();
	}

	private String Pillars()
	{
		String pillars = "";
		for (int x = startDistancePillar; x <= width; x += 250) {
			pillars += " <rect x=\"" + x + "\" y=\"10%\" height=\"10\" width=\"10\" id=\"upperPillar\"\n" +
					   "              style=\"stroke:black; fill:black\"/>\n" +
					   "        <rect x=\"" + x + "\" y=\"85%\" height=\"10\" width=\"10\" id=\"lowerPillar\"\n" +
					   "              style=\"stroke:black; fill:black\"/>";
		}
		return pillars + dashLines();
	}

	private String dashLines()
	{
		String dashLines = "<line x1=\"12%\" y1=\"12%\" x2=\"81.5%\" y2=\"85.5%\"\n" +
						   "              style=\"stroke-dasharray: 2 2; stroke: blue; fill: none\"/>\n" +
						   "        <line x1=\"12%\" y1=\"85.5%\" x2=\"81.5%\" y2=\"12%\"\n" +
						   "              style=\"stroke-dasharray: 2 2; stroke: blue; fill: none\"/>";
		return dashLines;
	}



/* future reference: something to consider: width = max.(length, width), length = min.(length, width)
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
    <text x=40% y=98% text-anchor="middle" fill="black"> Width: <%= width %>
    </text>
    <line x1="95%" y1="10%" x2="95%" y2="80%"
          style="stroke:#006600;
	            marker-start: url(#beginArrow);
            marker-end: url(#endArrow);"/>
    <text x=97% y=50% text-anchor="middle" style="writing-mode: tb;"> Length: <%= length %>
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
 */
}
