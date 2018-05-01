package daoTest;

import data.MySqlConnector;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class CloudConnectionTest
{
	Connection cloudConnection;
	Connection cloudTestConnection;
	/*
	@Before
	public void setUp() throws DataAccessException, UserAccessException
	{
		//should i handle the exception or not?
		try {
			cloudTestConnection = MySqlConnector.createConnection("TEST");
			//cloudConnection = MySqlConnector.createConnection("APP");
		} catch ( DataAccessException dae) {
			throw new DataAccessException(dae);
		}
		//con = MySqlConnector.connectLocalMySql();

	}
/*
	@Test
	public void CloudConnection() {
		assertNotNull(cloudConnection);
	}

	@Test
	public void cloudTestConnection() {
		assertNotNull(cloudTestConnection);
	} */
}
