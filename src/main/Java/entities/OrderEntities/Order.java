package entities.OrderEntities;

import entities.userEntities.Customer;
import entities.userEntities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order
{
	private int id, height, width, length, slope, price;
	private String          created_at;
	private Customer        customer;
	private Shed            shed;
	private Employee        employee;
	private Status          status;
	private List<OrderLine> orderLines;

	private Order(OrderBuilder orderBuilder)
	{
		this.id = orderBuilder.id;
		this.height = orderBuilder.height;
		this.width = orderBuilder.width;
		this.length = orderBuilder.length;
		this.slope = orderBuilder.slope;
		this.status = orderBuilder.status;
		this.customer = orderBuilder.customer;
		this.created_at = orderBuilder.created_at;
		this.shed = orderBuilder.shed;
		this.price = orderBuilder.price;
		this.orderLines = new ArrayList<>();
	}

	public int getPrice()
	{
		return this.price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public List<OrderLine> getOrderLines()
	{
		return this.orderLines;
	}

	public void addToOrderLines(OrderLine orderline)
	{
		this.orderLines.add(orderline);
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public enum Status
	{
		PENDING, OFFER, ACCEPTED, SEND
	}

	public Status getStatus()
	{
		return this.status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}


	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getLength()
	{
		return this.length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getSlope()
	{
		return this.slope;
	}

	public void setSlope(int slope)
	{
		this.slope = slope;
	}

	public Customer getCustomer()
	{
		return this.customer;
	}

	public void setCustomerId(int id) {
		this.customer.setId(id);
	}

	public String getCreated_at()
	{
		return this.created_at;
	}

	public void setshed(Shed shed)
	{
		this.shed = shed;
	}

	public Shed getShed()
	{
		return this.shed;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public int fullPriceOfOrder() {
		int amount = 0;
		for(OrderLine orderLine: orderLines) {
			amount += orderLine.getPriceForOrderLine();
		}
		return 0;
	}

	public static class OrderBuilder
	{
		private int id, height, width, length, slope, price;
		private String   created_at;
		private Customer customer;
		private Shed     shed;
		private Status   status;

		public OrderBuilder(int id, String created_at)
		{
			this.id = id;
			this.created_at = created_at;
		}

		public OrderBuilder()
		{

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

		public OrderBuilder insertOptionalPrice(int price)
		{
			this.price = price;
			return this;
		}

		public OrderBuilder insertRequiredCreationDate(String created_at)
		{
			this.created_at = created_at;
			return this;
		}

		public Order build()
		{
			return new Order(this);
		}
	}

	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		//should check for orderLines too
		return height == order.height &&
			   width == order.width &&
			   length == order.length &&
			   slope == order.slope &&
			   price == order.price &&
			   Objects.equals(customer, order.customer) &&
			   Objects.equals(shed, order.shed) &&
			   Objects.equals(employee, order.employee) &&
			   status == order.status;
	}


	@Override public String toString()
	{
		return "customer: " + customer.getUsername() + ", status: " +
			   status + ", height: " + height + ", width: " + width + ", length: " + length + ", price:" + price;
	}

	@Override public int hashCode()
	{

		return Objects.hash(height, width, length, slope, price, customer, shed, employee, status, orderLines);
	}
}
