package data.entities.OrderEntities;

public class Material
{
	private String description;
	private int pricePrUnit;

	public Material(String description, int pricePrUnit)
	{
		this.description = description;
		this.pricePrUnit = pricePrUnit;
	}

	public String getDescription()
	{
		return this.description;
	}

	public int getPricePrUnit()
	{
		return this.pricePrUnit;
	}
}
