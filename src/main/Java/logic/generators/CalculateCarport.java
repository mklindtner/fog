package logic.generators;

public class CalculateCarport
{
	private int length, width, singleLinepillars = 2;
	private final int MAX_DIST_PILLARS = 250; //in cm

	public CalculateCarport(int length, int width) {
		this.width = width;
		this.length = length;
	}

	public int procentageDistancePillars() {
		int amountOfRafters = width / 55;
		return (width / amountOfRafters) / 100;
	}








	/*

	public int calculateSingleLinePillars(int line) {
		int ekstra_added_pillars = singleLinepillars;
		if(MAX_DIST_PILLARS < line )
			ekstra_added_pillars += line / MAX_DIST_PILLARS;
		return ekstra_added_pillars;
	}

	public int findRafterSpace(int width) {
		return width / calculateAmountOfRafters(width);
	}

	public int calculateAmountOfRafters(int width) {
		return width / 250;
	}


	//calculate thsi later
	private int calculateOffSetOfRafters() {
		return 0;
	}
	 */

}
