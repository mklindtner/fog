package data.entities.OrderEntities;

public class MaterialDimensions
{
	private int height, width, length;

	public MaterialDimensions(int height, int width, int length)
	{
		this.height = height;
		this.width = width;
		this.length = length;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getLength()
	{
		return this.length;
	}

	public String dimensions() {
		return width + "x" + length;
	}
}
