package data.entities.OrderEntities;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;

import java.util.ArrayList;
import java.util.List;

public class Order
{
	private int id, height, width, length, slope, price;
	private String          created_at;
	private Customer        customer;
	private Material        material;
	private Shed            shed;
	private Employee        employee;
	private Status          status;
	private List<OrderLine> orderlines;

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
		this.orderlines = new ArrayList<>();
	}

	public int getPrice()
	{
		return this.price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public List<OrderLine> getOrderlines()
	{
		return this.orderlines;
	}

	public void addToOrderLines(OrderLine orderline) {
		this.orderlines.add(orderline);
	}

	public enum Status  {
		PENDING, OFFER, ACCEPTED, SEND
	}

	public Status getStatus()
	{
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSlope()
	{
		return this.slope;
	}

	public void setSlope(int slope) {
		this.slope = slope;
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
			status = Status.PENDING;
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
