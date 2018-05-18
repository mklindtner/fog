package entities.OrderEntities;

import java.util.Objects;

public class OrderLine
{
	private int id, amount, length, priceForOrderLine, materialId, orderId;
	private String firstDescription, secondDescription, unit;
	private boolean isTreeOrRoof;

	private OrderLine(OrderLineBuilder orderLineBuilder)
	{
		this.amount = orderLineBuilder.amount;
		this.unit = orderLineBuilder.unit;
		this.length = orderLineBuilder.length;
		this.secondDescription = orderLineBuilder.secondDescription;
		this.firstDescription = orderLineBuilder.firstDescription;
		this.id = orderLineBuilder.id;
		this.priceForOrderLine = orderLineBuilder.priceForOrderLine;
		this.isTreeOrRoof = orderLineBuilder.isTreeOrRoof;
		this.materialId = orderLineBuilder.materialId;
		this.orderId = orderLineBuilder.orderId;
		this.id = orderLineBuilder.id;
	}

	public String getFirstDescription()
	{
		return this.firstDescription;
	}

	public boolean isTreeOrRoof()
	{
		return this.isTreeOrRoof;
	}

	public int getMaterialId()
	{
		return this.materialId;
	}

	public void setMaterialId(int materialId)
	{
		this.materialId = materialId;
	}

	public int getOrderId()
	{
		return this.orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public int getId()
	{
		return this.id;
	}


	public static class OrderLineBuilder
	{
		private int id, amount, length, priceForOrderLine, materialId, orderId;
		private String firstDescription, secondDescription, unit;
		private boolean isTreeOrRoof;

		public OrderLineBuilder insertAmount(int amount)
		{
			this.amount = amount;
			return this;
		}

		public OrderLineBuilder insertUnit(String unit)
		{
			this.unit = unit;
			return this;
		}

		public OrderLineBuilder insertLength(int length)
		{
			this.length = length;
			return this;
		}

		public OrderLineBuilder insertFirstDescription(String firstDescription)
		{
			this.firstDescription = firstDescription;
			return this;
		}

		public OrderLineBuilder insertSecondDescription(String secondDescription)
		{
			this.secondDescription = secondDescription;
			return this;
		}

		public OrderLineBuilder insertPriceForOrderLine(int priceForOrderLine)
		{
			this.priceForOrderLine = priceForOrderLine;
			return this;
		}

		public OrderLineBuilder insertIsTreeOrRoof(boolean istreeOrRoof)
		{
			this.isTreeOrRoof = istreeOrRoof;
			return this;
		}

		public OrderLineBuilder insertOrderId(int orderId)
		{
			this.orderId = orderId;
			return this;
		}

		public OrderLineBuilder insertMaterialId(int materialId)
		{
			this.materialId = materialId;
			return this;
		}

		public OrderLineBuilder insertOrderLineId(int id) {
			this.id = id;
			return this;
		}

		public OrderLine build()
		{
			return new OrderLine(this);
		}
	}


	public int getPriceForOrderLine()
	{
		return this.priceForOrderLine;
	}

	public void setPriceForOrderLine(int priceForOrderLine)
	{
		this.priceForOrderLine = priceForOrderLine;
	}

	public int getAmount()
	{
		return this.amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public String getUnit()
	{
		return this.unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public int getLength()
	{
		return this.length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getSecondDescription()
	{
		return this.secondDescription;
	}

	public void setSecondDescription(String secondDescription)
	{
		this.secondDescription = secondDescription;
	}

	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderLine orderLine = (OrderLine) o;
		return amount == orderLine.amount &&
			   length == orderLine.length &&
			   priceForOrderLine == orderLine.priceForOrderLine &&
			   isTreeOrRoof == orderLine.isTreeOrRoof &&
			   Objects.equals(firstDescription, orderLine.firstDescription) &&
			   Objects.equals(secondDescription, orderLine.secondDescription) &&
			   Objects.equals(unit, orderLine.unit);
	}

	@Override public int hashCode()
	{
		return Objects.hash(amount, length, priceForOrderLine, materialId, orderId, firstDescription, secondDescription, unit, isTreeOrRoof);
	}

	@Override public String toString()
	{
		return "amount: " + amount + ", length: " + length + ", priceForOrderLine: " + priceForOrderLine
			   + ", firstDescription: " + firstDescription + ", secondDescription: " + secondDescription + ", unit: " + unit + ", isTreeOrRoof: " + isTreeOrRoof;
	}
}
