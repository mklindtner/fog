package logic.generators;

public class SVGUTil
{
	//stopler
	//rem
	//spær
	int width, height;
	public SVGUTil(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public String createCarport() {
		String svgBoxStart = "<SVG width=\"200%\" height=200% viewBox=\"0 0 780 600\">";
		String svgBoxEnd = "<SVG/>";
		return svgBoxStart + roof() + svgBoxEnd;

	}

	private String roof()
	{
		return "<rect x=\"0\" y=\"0\" height=\"" + height + "\" width=\" " + width + "\"\n" +
		"style=\"stroke:#000000; fill: white \"/>";
	}

}
