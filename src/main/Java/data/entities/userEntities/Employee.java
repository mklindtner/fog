package data.entities.userEntities;

public class Employee implements User
{
	private String username, password, reg_date, role;
	private int phone, id;

	/**
	 * username must be email
	 * @param username
	 * @param password
	 * @param phone
	 * @param role
	 * @param reg_date
	 * @param id
	 */
	public Employee(String username, String password, int phone, String role, String reg_date, int id)
	{
		this.username = username;
		this.password = password;
		this.role = role;
		this.reg_date = reg_date;
		this.phone = phone;
		this.id = id;
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
