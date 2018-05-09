package data.entities.OrderEntities;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;

public class Order
{
	private int id, height, width, length, slope;
	private String   created_at;
	private Customer customer;
	private Material material;
	private Shed     shed;
	private Employee employee;
	private Status status;

	/**
	 * i choose not to implement an interface for the builders as the interface would be to abstract to be useful
	 * @param orderBuilder
	 */

	private Order(OrderBuilder orderBuilder)
	{
		this.id = orderBuilder.id;
		this.height = orderBuilder.height;
		this.width = orderBuilder.width;
		this.length = orderBuilder.length;
		this.slope = orderBuilder.slope;
		this.material = orderBuilder.material;
		this.status = orderBuilder.status;
		this.customer = orderBuilder.customer;
		this.created_at = orderBuilder.created_at;
		this.shed = orderBuilder.shed;
	}
	public enum Status  {
		AVAILABLE, TAKEN, SEND, ACCEPTED
	}

	public Status getStatus()
	{
		return this.status;
	}


	public int getHeight()
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getLength() {
		return this.length;
	}

	public int getSlope()
	{
		return this.slope;
	}

	public Customer getCustomer()
	{
		return this.customer;
	}

	public Material getMaterial()
	{
		return this.material;
	}

	public String getCreated_at()
	{
		return this.created_at;
	}

	public Shed getShed()
	{
		return this.shed;
	}

	public int getId() {
		return this.id;
	}

	public static class OrderBuilder
	{
		private int id, height, width, length, slope;
		private String   created_at;
		private Customer customer;
		private Shed     shed;
		private Material material;
		private Status status;

		public OrderBuilder(int id, String created_at)
		{
			this.id = id;
			this.created_at = created_at;
		}

		public OrderBuilder() {

		}

		public OrderBuilder createOrderWithoutShed(int height, int width, int length, Customer customer, int slope)
		{
			insertRequiredHeight(height);
			insertRequiredWidth(width);
			insertRequiredLength(length);
			insertRequiredSlope(slope);
			insertRequiredCustomer(customer);
			status = Status.AVAILABLE;
			this.shed = null;
			return this;
		}



		public OrderBuilder insertRequiredHeight(int height)
		{
			this.height = height;
			return this;
		}

		public OrderBuilder insertRequiredWidth(int width)
		{
			this.width = width;
			return this;
		}


		public OrderBuilder insertRequiredLength(int length)
		{
			this.length = length;
			return this;
		}
		public OrderBuilder insertRequiredSlope(int slope)
		{
			this.slope = slope;
			return this;
		}

		public OrderBuilder insertRequiredCustomer(Customer customer)
		{
			this.customer = customer;
			return this;
		}

		public OrderBuilder insertRequiredMaterial(Material material)
		{
			this.material = material;
			return this;
		}

		public OrderBuilder insertOptionalShed(Shed shed)
		{
			this.shed = shed;
			return this;
		}

		public OrderBuilder insertOptionalStatus(Status status)
		{
			this.status = status;
			return this;
		}

		public Order build()
		{
			return new Order(this);
		}
	}

	@Override public String toString()
	{
		return "id: " + id + ", customer: " + customer.getUsername() + ", status: " +
			   status + ", height: " + height + ", width: " + width + ", length: " + length;
	}
}
