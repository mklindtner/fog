package data.entities.userEntities;

public class unknownUser implements User
{
	@Override public String getUsername()
	{
		return null;
	}

	@Override public int getPhone()
	{
		return 0;
	}

	@Override public String getReg_date()
	{
		return null;
	}

	@Override public String getPassword()
	{
		return null;
	}
}
