package entities.OrderEntities;

import java.util.Objects;

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

	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Material material = (Material) o;
		return pricePrUnit == material.pricePrUnit &&
			   id == material.id &&
			   Objects.equals(description, material.description);
	}

	@Override public int hashCode()
	{

		return Objects.hash(description, pricePrUnit, id);
	}
}
