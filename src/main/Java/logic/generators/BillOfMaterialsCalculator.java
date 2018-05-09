package logic.generators;

import data.dao.MaterialDAO;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.MaterialDimensions;
import data.entities.billOfMaterial.BillOfMaterial;
import data.exceptions.DataException;
import data.exceptions.MaterialException;

import java.util.*;

public class BillOfMaterialsCalculator
{
	private int length, width, height, totalPrice = 0;
	private final double TREEWIDTH            = 0.97; //mm
	private final int    MINIMUMSTAKEDISTANCE = 360;
	private final int    RAFTERDISTANCE       = 55;
	private final int    REMS                 = 2;
	private final int    STAKESTARTPOSITION   = 110;
	private final int    STAKEDISTANCE        = 250;
	private final int    BRACKETS_EACH_STAKE  = 2; //beslag, dette re antaget, ikke sikkert
	private final double REMHEIGHT            = 1.95;
	private final double REMWIDTH             = 0.45;
	private final int    hulbånd              = 2;
	private final int    galjeScrews          = 8;
	private final int    screwsForOneBracket  = 9;
	private MaterialDAO materialDAO;
	Map<String, Material> materials;
	//ide: match hver søgning med en type i basen og få deres dimension mv ud.

	public BillOfMaterialsCalculator(int height, int width, int length) throws DataException
	{
		this.length = length;
		this.width = width;
		this.height = height; //height of rafter too, have hole for spær
		materialDAO = new MaterialDAO("APP");
		materials = new HashMap<>();
	}

	public Map<String, Material> createCarportListWithoutShed() throws MaterialException
	{
		setValues();
		return materials;
	}

	public int caportPrice() { //chosen so no conucrrentmodficationError occur
		/*Iterator it = materials.entrySet().iterator();
		while(it.hasNext()) {

		}*/
		for(Material material: materials.values()) {
			totalPrice += material.getPrice();
		}
		return totalPrice;
	}

	private void setValues() throws MaterialException
	{
		Material nail  = findMaterial("Skruer", 5, 60);
		nail.setNumbers(nailsAmount());
		materials.put("nail", nail);

		Material rafter = findMaterial("Brædt", 25, 200);
		rafter.setNumbers(rafterAmount());
		setHeight(rafter.getDimensions());
		materials.put("rafter", rafter);

		Material stake  = findMaterial("Stolpe", 97, 97);
		stake.setNumbers(stakeAmount());
		setHeight(stake.getDimensions());
		materials.put("stake", stake);

		Material bracket = findMaterial("beslagsskruer", 4, 50);
		bracket.setNumbers(bracketAmount());
		materials.put("bracket", bracket);
		//Material rafter_different = materialDAO.materialByTypeWidthLength("Brædt", 25, 125);
	}

	private Material findMaterial(String type, int width, int length) throws MaterialException {
		return  materialDAO.materialByTypeWidthLength(type, width, length);
	}


	private int stakeAmount()
	{
		int stakeAmount = 0;
		if (width < 360)
			return 4;
		for (int i = STAKESTARTPOSITION; i < width; i += STAKEDISTANCE + TREEWIDTH)
			stakeAmount += 1;
		return stakeAmount;
	}

	private int rafterAmount()
	{
		int rafterAmount = 0;
		if (width < MINIMUMSTAKEDISTANCE)
			return 4; //the amount of rafters for 4 stakes
		for (int x = rafterAmount; x < width; x += RAFTERDISTANCE + TREEWIDTH) {
			rafterAmount += 1;
		}
		return rafterAmount;
	}

	private int bracketAmount() //190 mm
	{
		int bracketAmount = 4; //extra brackets
		for (int x = 0; x < stakeAmount(); x++)
			bracketAmount += BRACKETS_EACH_STAKE;
		return bracketAmount; //left and right
	}

	private int nailsAmount()
	{
		int galje        = 4 * galjeScrews;
		int bracketNails = 0;
		for (int x = 0; x < rafterAmount(); x++)
			bracketNails = screwsForOneBracket * bracketAmount(); //beslagsskruer
		return bracketNails + galje; //tbc need to calculate different kind of screws etc.
	}

	private int nailsForBracket()
	{
		return 0; //implement me
	}

	private void setHeight(MaterialDimensions materialDimensions)
	{
		materialDimensions.setHeight(height + 10);
	}
}
