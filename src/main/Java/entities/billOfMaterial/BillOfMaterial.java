package entities.billOfMaterial;

public class BillOfMaterial
{
	private int nails, stakes, brackets, rafters;
	private int height, width, length;

	private BillOfMaterial(BillOfMaterialBuilder billOfMaterialBuilder) {
		this.nails = billOfMaterialBuilder.nails;
		this.stakes = billOfMaterialBuilder.stakes;
		this.brackets = billOfMaterialBuilder.brackets;
		this.rafters = billOfMaterialBuilder.rafters;
		this.height = billOfMaterialBuilder.height;
		this.width = billOfMaterialBuilder.width;
		this.length = billOfMaterialBuilder.length;
	}

	public int getNails()
	{
		return this.nails;
	}

	public int getStakes()
	{
		return this.stakes;
	}

	public int getBrackets()
	{
		return this.brackets;
	}

	public int getRafters()
	{
		return this.rafters;
	}

	public int lengthOfStake() {
		return length + 10;
	}



	public static class BillOfMaterialBuilder {
		private int nails, stakes, brackets, rafters;
		private int height, width, length;
		public BillOfMaterialBuilder(int height, int width, int length ) {
			this.height = height;
			this.width = width;
			this.length = length;
		}

		public BillOfMaterialBuilder insertRequiedNails(int nails) {
			this.nails = nails;
			return this;
		}

		public BillOfMaterialBuilder insertRequiredStakes(int stakes) {
			this.stakes = stakes;
			return this;
		}

		public BillOfMaterialBuilder insertRequiredBrackets(int brackets) {
			this.brackets = brackets;
			return this;
		}

		public BillOfMaterialBuilder insertRequiredRafters(int rafters) {
			this.rafters = rafters;
			return this;
		}

		public BillOfMaterial build() {
			return new BillOfMaterial(this);
		}
	}

	@Override public String toString() {
		return "stakes: " + stakes + ", rafters: " + rafters + ", brackets: " + brackets + ", nails: " + nails;
	}
}
