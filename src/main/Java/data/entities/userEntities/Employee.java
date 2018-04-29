package data.entities.userEntities;

public class Employee implements User
{
	private String username, password, reg_date, role;
	private int phone, id;

	private Employee(EmployeeBuilder employeeBuilder)
	{
		this.username = employeeBuilder.username;
		this.password = employeeBuilder.password;
		this.role = employeeBuilder.role;
		this.reg_date = employeeBuilder.reg_date;
		this.phone = employeeBuilder.phone;
		this.id = employeeBuilder.id;
	}

	public static class EmployeeBuilder {
		private String username, password, role, reg_date;
		private int phone, id;

		public EmployeeBuilder(int id, String reg_date) {
			this.reg_date = reg_date;
			this.id = id;
		}

		public EmployeeBuilder createSimpleEmployee(String username, String password, String role, int phone) {
			insertUsername(username);
			insertPassword(password);
			insertRole(role);
			insertPhone(phone);
			return this;
		}

		private EmployeeBuilder insertUsername(String username)
		{
			this.username = username;
			return this;
		}


		private EmployeeBuilder insertPassword(String password)
		{
			this.password = password;
			return this;
		}

		private EmployeeBuilder insertRole(String role)
		{
			this.role = role;
			return this;
		}


		private EmployeeBuilder insertPhone(int phone)
		{
			this.phone = phone;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getRole()
	{
		return this.role;
	}

	public String getReg_date()
	{
		return this.reg_date;
	}

	public int getPhone()
	{
		return this.phone;
	}

	@Override
	public String toString() {
		return username;
	}

	public int getId()
	{
		return this.id;
	}

	@Override public String getPassword()
	{
		return this.password;
	}
}
