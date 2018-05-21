package logic.generators;

public class SVGUtil
{
	//stopler
	//rem
	//spær
	private int width, length;
	private final int startDistancePillar = 110;
	private StringBuilder sb;


	public SVGUtil(int length, int width)
	{
		this.length = length;
		this.width = width;
		sb = new StringBuilder();
	}

	public String createCarport()
	{
		mainBox();
		arrowsDefintion();
		arrowsCall();
		roofCanvas();
		roofMeasurements();
		rems();
		rafters();
		pillars();
		dashLines();
		return sb.toString();
	}

	private void mainBox()
	{
		sb.append("<SVG width=\"760\" height=\"600\" viewBox=\"0 0 " + width + " " + length + " \">");
	}

	private void arrowsDefintion()
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
		sb.append(arrowsDef);
	}

	private void arrowsCall()
	{
		String arrows = " <line x1=\"20%\" y1=\"95%\" x2=\"70%\" y2=\"95%\"\n" +
						"          style=\"stroke:#006600;\n" +
						"        \t    marker-start: url(#beginArrow);\n" +
						"               marker-end: url(#endArrow);\"/>\n" +
						"    <text x=40% y=98% text-anchor=\"middle\" fill=\"black\"> Width: " + width + "\n" +
						"    </text>\n" +
						"    <line x1=\"95%\" y1=\"10%\" x2=\"95%\" y2=\"80%\"\n" +
						"          style=\"stroke:#006600;\n" +
						"\t            marker-start: url(#beginArrow);\n" +
						"            marker-end: url(#endArrow);\"/>\n" +
						"    <text x=97% y=50% text-anchor=\"middle\" style=\"writing-mode: tb;\"> Length: " + length +
						"</text>";
		sb.append(arrows);
	}

	private void roofCanvas()
	{
		String roofCanvas = "<svg width=\"90%\" height=\"90%\">";
		sb.append(roofCanvas);
	}

	private void roofMeasurements()
	{
		String roofMeasurements = "<rect width=\"100%\" height=\"100%\" id=\"roof\"\n" +
								  "style=\"stroke:black; fill:none\"/>";
		sb.append(roofMeasurements);
	}

	private void rems()
	{
		String rems = " <rect x=\"0\" y=\"10%\" height=\"10\" width=\"" + width + "\" id=\"upperRem\"\n" +
					  "              style=\"stroke:black; fill:none\"/>\n" +
					  "        <rect x=\"0\" y=\"85%\" height=\"10\" width=\"" + width + "\" id=\"lowerRem\"\n" +
					  "              style=\"stroke:black; fill:none\"/>";
		sb.append(rems);
	}

	private void rafters()
	{
		String rafters = "";
		for (int x = 0; x <= width; x += 55) {
			rafters += "  <rect x=\"" + x + "\" y=\"0%\" height=\"100%\" width=\"10\" id=\"rafter\"\n" +
					   "style=\"stroke:black; fill:none\"/>";
		}
		sb.append(rafters);
	}

	private void pillars()
	{
		String pillars = "";
		if (width > 330) {
			for (int x = startDistancePillar; x <= width; x += 250) {
				pillars += " <rect x=\"" + x + "\" y=\"10%\" height=\"10\" width=\"10\" id=\"upperPillar\"\n" +
						   "              style=\"stroke:black; fill:black\"/>\n" +
						   "        <rect x=\"" + x + "\" y=\"85%\" height=\"10\" width=\"10\" id=\"lowerPillar\"\n" +
						   "              style=\"stroke:black; fill:black\"/>";
			}
		} else { //place 4 if less smaller than 330
			int rafterPosition = 0;
			for (int x = 0; x < 2; x++) {
				pillars += " <rect x=\"" + rafterPosition + "%\" y=\"10%\" height=\"10\" width=\"10\" " +
						   "id=\"upperPillar\"\n" + " style=\"stroke:black; fill:black\"/>\n" +
						   "<rect x=\"" + rafterPosition + "%\" y=\"85%\" height=\"10\" width=\"10\" " +
						   "id=\"lowerPillar\"\n" + " style=\"stroke:black; fill:black\"/>";
				rafterPosition = 73;
			}
		}
		sb.append(pillars);
	}

	private void dashLines()
	{
		String dashLines = "<line x1=\"12%\" y1=\"12%\" x2=\"81.5%\" y2=\"85.5%\"\n" +
						   "              style=\"stroke-dasharray: 2 2; stroke: blue; fill: none\"/>\n" +
						   "        <line x1=\"12%\" y1=\"85.5%\" x2=\"81.5%\" y2=\"12%\"\n" +
						   "              style=\"stroke-dasharray: 2 2; stroke: blue; fill: none\"/>";
		sb.append(dashLines);
	}



/* future reference: something to consider: width = max.(length, width), length = min.(length, width),
shed doesn't work as expected

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


        <rect x="<%=shedWidth%>" y="<%=shedLength%>" width="<%=shedWidth%>" height="<%=shedLength%>"
              stroke-dasharray="2, 2" style="stroke:black; stroke-width: 2; fill-opacity: 0;" />
    </svg>
</SVG>
         */
}
