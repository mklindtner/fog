package data.entities.userEntities;

public class Customer implements User
{
	private String username, password, reg_date;
	private int phone, id;

	public Customer(String username, String password, String reg_date, int phone, int id)
	{
		this.username = username;
		this.password = password;
		this.reg_date = reg_date;
		this.phone = phone;
		this.id = id;
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getReg_date()
	{
		return this.reg_date;
	}

	@Override
	public String toString() {
		return username + ", " + reg_date;
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
}
