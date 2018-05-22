package entities.userEntities;

import java.util.Objects;

public class Customer implements User
{
	private String username, password, reg_date;
	private int phone, id;



	private Customer(CustomerBuilder customerBuilder)
	{
		this.username = customerBuilder.username;
		this.password = customerBuilder.password;
		this.reg_date = customerBuilder.reg_date;
		this.phone = customerBuilder.phone;
		this.id = customerBuilder.id;
	}

	public static class CustomerBuilder
	{
		private String username, password, reg_date;
		private int phone, id;

		public CustomerBuilder(int id, String reg_date) {
			this.id = id;
			this.reg_date = reg_date;
		}

		public CustomerBuilder createSimpleCustomer(String username, String password, int phone) {
			insertUsername(username);
			insertPassword(password);
			insertPhone(phone);
			return this;
		}

		public CustomerBuilder insertUsername(String username) {
			this.username = username;
			return this;
		}

		public CustomerBuilder insertPassword(String password) {
			this.password = password;
			return this;
		}

		public CustomerBuilder insertPhone(int phone) {
			this.phone = phone;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getReg_date()
	{
		return this.reg_date;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public int getPhone()
	{
		return this.phone;
	}

	@Override public String getPassword()
	{
		return this.password;
	}

	@Override public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return phone == customer.phone &&
			   Objects.equals(username, customer.username) &&
			   Objects.equals(password, customer.password);
	}

	@Override public int hashCode()
	{

		return Objects.hash(username, password, reg_date, phone);
	}

	@Override
	public String toString() {
		return "username: " + username + ", phone: " + phone;
	}
}
