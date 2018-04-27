package data.entities.OrderEntities;

public class Material
{
	private int id, price;
	private String typeSpecificaton, type;

	private Material(MaterialBuilder materialBuilder)
	{
		this.id = materialBuilder.id;
		this.price = materialBuilder.price;
		this.typeSpecificaton = materialBuilder.typeSpecificaton;
		this.type = materialBuilder.type;
	}

	public static class MaterialBuilder
	{
		private int id, price;
		private String typeSpecificaton, type;

		public MaterialBuilder(int id) {
			this.id = id;
		}

		public MaterialBuilder createRequiredMaterial(int price, String typeSpecificaton, String type)
		{
			insertPrice(price);
			insertType(type);
			insertTypeSpecification(typeSpecificaton);
			return this;
		}

		public MaterialBuilder insertPrice(int price) {
			this.price = price;
			return this;
		}

		public MaterialBuilder insertType(String type) {
			this.type = type;
			return this;
		}

		public MaterialBuilder insertTypeSpecification(String typeSpecificaton) {
			this.typeSpecificaton = typeSpecificaton;
			return this;
		}

		public Material build() {
			return new Material(this);
		}
	}
}
