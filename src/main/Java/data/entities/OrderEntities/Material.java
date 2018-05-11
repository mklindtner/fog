package data.entities.OrderEntities;

public class Material
{
	private String description;
	private int pricePrUnit, id;

	public Material(String description, int pricePrUnit, int id)
	{
		this.description = description;
		this.pricePrUnit = pricePrUnit;
		this.id = id;
	}

	public String getDescription()
	{
		return this.description;
	}

	public int getPricePrUnit()
	{
		return this.pricePrUnit;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
