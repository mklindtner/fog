package daoTest;

import data.MySqlConnector;
import data.exceptions.DataException;

import java.sql.Connection;

public class ShedDAO
{
	private Connection con;

	public ShedDAO(String connectionSelection) throws DataException {
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public ShedDAO() throws DataException {
		con = MySqlConnector.createConnection("APP");
	}

	public Connection getCon()
	{
		return this.con;
	}
}
