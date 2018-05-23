package daoTest;

import data.MySqlConnector;
import data.dao.ShedDAO;
import data.exceptions.DataException;
import data.exceptions.ShedException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;

public class ShedDAOTest
{
	private ShedDAO shedDAO;

	@Before
	public void setup() throws DataException
	{
		shedDAO = new ShedDAO("TEST");
	}

	@Test(expected = ShedException.class)
	public void shedException() throws ShedException
	{
		shedDAO.createAndReturnShed(-5, -2, false);
	}
}
