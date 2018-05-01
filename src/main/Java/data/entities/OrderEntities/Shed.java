package data.entities.OrderEntities;

public class Shed
{
	private int id, length, width, height;
	private boolean hasFloor;

	private Shed(ShedBuilder ShedBuilder) {
		this.id = ShedBuilder.id;
		this.length = ShedBuilder.length;
		this.width = ShedBuilder.width;
		this.height = ShedBuilder.height;
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

	public int getHeight() {
		return this.height;
	}

	public boolean getFloor() {
		return this.hasFloor;
	}

	public static class ShedBuilder
	{
		private int id, length, width, height;
		private boolean hasFloor;

		public ShedBuilder(int id, boolean hasFloor) {
			this.hasFloor = hasFloor;
			this.id = id;
		}

		public ShedBuilder insertMinimumRequiredShed(int length, int width, int height) {
			insertRequiredHeight(height);
			insertRequiredLength(length);
			insertRequiredWidth(width);
			return this;
		}
		public ShedBuilder insertRequiredLength(int length)
		{
			this.length = length;
			return this;
		}
		public ShedBuilder insertRequiredWidth(int width) {
			this.width = width;
			return this;
		}
		public ShedBuilder insertRequiredHeight(int height) {
			this.height = height;
			return this;
		}
		public ShedBuilder insertHasFloor(boolean hasFloor) {
			this.hasFloor = hasFloor;
			return this;
		}

		public Shed build() {
			return new Shed(this);
		}
	}

	@Override
	public String toString() {
		return "height: " + height + ", length: " + length + ", width: " + width + ", hasfloor: " + hasFloor;
	}
}
