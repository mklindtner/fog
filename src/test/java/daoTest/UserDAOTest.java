package daoTest;

import data.MySqlConnector;
import data.exceptions.DataAccessException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import static org.junit.Assert.*;

public class UserDAOTest
{
	Connection con;

	@Before
	public void setUp() throws DataAccessException
	{
		con = MySqlConnector.connectLocalTestMysql(); //should i handle the exception or not?
		//con = MySqlConnector.connectLocalMySql();
	}

	@Test
	public void checkConnection() {
		assertNotNull(con);
	}








}
