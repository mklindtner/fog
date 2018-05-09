package data.entities.OrderEntities;

public class Material
{
	private int id, price, materialAmount, numbers;
	private String type, packageType, description, treatment;
	private MaterialDimensions dimensions;


	private Material(MaterialBuilder materialBuilder)
	{
		this.id = materialBuilder.id;
		this.price = materialBuilder.price;
		this.type = materialBuilder.type;
		this.dimensions = materialBuilder.dimensions;
		this.materialAmount = materialBuilder.materialAmount;
		this.packageType = materialBuilder.packageType;
		this.description = materialBuilder.description;
		this.treatment = materialBuilder.treatment;
	}

	public int getId()
	{
		return this.id;
	}

	public int getPrice()
	{
		return this.price;
	}

	public String getType()
	{
		return this.type;
	}

	public int getMaterialAmount()
	{
		return this.materialAmount;
	}

	public String getPackageType()
	{
		return this.packageType;
	}

	public MaterialDimensions getDimensions()
	{
		return this.dimensions;
	}

	public int getNumbers()
	{
		return this.numbers;
	}

	public void setNumbers(int numbers)
	{
		this.numbers = numbers;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreatment()
	{
		return this.treatment;
	}



	public static class MaterialBuilder
	{
		private int id, price, materialAmount;
		private String type, packageType, treatment, description;
		private MaterialDimensions dimensions;

		public MaterialBuilder(int id)
		{
			this.id = id;
		}

		public MaterialBuilder createRequiredMaterial(int price, String type, int materialAmount)
		{
			insertPrice(price);
			insertType(type);
			insertMaterialAmount(materialAmount);
			return this;
		}

		public MaterialBuilder createCompleteMaterial(
				int price, String type, int materialAmount, MaterialDimensions dimensions,
				String packageType, String treatment, String description)
		{
			insertPrice(price);
			insertType(type);
			insertMaterialAmount(materialAmount);
			insertOptionalDimensions(dimensions);
			insertOptionalPackageType(packageType);
			insertOptionalTreatment(treatment);
			insertOptionalDescription(description);
			return this;
		}

		public MaterialBuilder insertPrice(int price)
		{
			this.price = price;
			return this;
		}

		public MaterialBuilder insertType(String type)
		{
			this.type = type;
			return this;
		}

		public MaterialBuilder insertMaterialAmount(int materialAmount)
		{
			this.materialAmount = materialAmount;
			return this;
		}

		public MaterialBuilder insertOptionalDimensions(MaterialDimensions dimensions)
		{
			this.dimensions = dimensions;
			return this;
		}

		public MaterialBuilder insertOptionalPackageType(String packageType)
		{
			this.packageType = packageType;
			return this;
		}


		public MaterialBuilder insertOptionalTreatment(String treatment)
		{
			this.treatment = treatment;
			return this;
		}

		public MaterialBuilder insertOptionalDescription(String description)
		{
			this.description = description;
			return this;
		}

		public Material build()
		{
			return new Material(this);
		}
	}

	@Override public String toString() {
		if(treatment.equalsIgnoreCase("none"))
			return dimensions.dimensions() + "mm" + "." + type;
		return dimensions.dimensions() + "mm" + "." + treatment + "." + type;
	}
}
