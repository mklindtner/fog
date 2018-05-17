package entities.OrderEntities;

public class Shed
{
	private int id, length, width;
	private boolean hasFloor;

	private Shed(ShedBuilder ShedBuilder) {
		this.id = ShedBuilder.id;
		this.length = ShedBuilder.length;
		this.width = ShedBuilder.width;
		this.hasFloor = ShedBuilder.hasFloor;
	}

	public int getId()
	{
		return this.id;
	}

	public int getLength() {
		return this.length;
	}

	public int getWidth() {
		return this.width;
	}

	public boolean getFloor() {
		return this.hasFloor;
	}

	public static class ShedBuilder
	{
		private int id, length, width;
		private boolean hasFloor;

		public ShedBuilder() {
		}

		public ShedBuilder insertLength(int length)
		{
			this.length = length;
			return this;
		}
		public ShedBuilder insertWidth(int width) {
			this.width = width;
			return this;
		}

		public ShedBuilder insertHasFloor(boolean hasFloor) {
			this.hasFloor = hasFloor;
			return this;
		}

		public ShedBuilder insertShedId(int id) {
			this.id = id;
			return this;
		}

		public Shed build() {
			return new Shed(this);
		}
	}

	@Override
	public String toString() {
		return "length: " + length + ", width: " + width + ", hasfloor: " + hasFloor;
	}
}
