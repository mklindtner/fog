package daoTest;

import data.MySqlConnector;
import data.MySQLDAO.*;
import data.exceptions.DataException;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConnectionTest
{

	private Connection appConnection, testConnection;
	private MaterialDAO materialDAO, materialDAOTest, materialDAOAPP;
	private OrderDAO orderDAO, orderDAOTest, orderDAOAPP;
	private OrderLineDAO orderLineDAO, orderLineDAOTest, orderLineAPP;
	private UserDAO userDAO, userDAOTest, userDAOAPP;
	private ShedDAO shedDAO, shedDAOTest, shedDAOAPP;

	@After
	public void resetConnector() {
		MySqlConnector.closeConnections();
	}

	@Test
	public void appConnection() throws DataException
	{
		appConnection = MySqlConnector.createConnection("APP");
		assertNotNull(appConnection);
	}


	@Test
	public void testConnection() throws DataException {
		testConnection = MySqlConnector.createConnection("TEST");
		assertNotNull(testConnection);
	}


	@Test
	public void materialDAOConnection() throws DataException {
		materialDAO = new MaterialDAO("APP");
		materialDAOTest = new MaterialDAO("TEST");
		materialDAOAPP = new MaterialDAO();
		assertNotNull(materialDAO.getCon());
		assertNotNull(materialDAOTest.getCon());
		assertNotNull(materialDAO.getCon());
	}

	@Test
	public void orderDAOConnection() throws DataException {
		orderDAO = new OrderDAO("APP");
		orderDAOTest = new OrderDAO("TEST");
		orderDAOAPP = new OrderDAO();
		assertNotNull(orderDAO.getCon());
		assertNotNull(orderDAOTest.getCon());
		assertNotNull(orderDAOAPP.getCon());
	}


	@Test
	public void orderLineDAOConnection() throws DataException {
		orderLineDAO = new OrderLineDAO("APP");
		orderLineDAOTest = new OrderLineDAO("TEST");
		orderLineAPP = new OrderLineDAO();
		assertNotNull(orderLineDAO.getCon());
		assertNotNull(orderLineDAOTest.getCon());
		assertNotNull(orderLineAPP.getCon());
	}


	@Test
	public void serviceDAOConnection() throws DataException {
		userDAO = new UserDAO("APP");
		userDAOTest = new UserDAO("TEST");
		userDAOAPP = new UserDAO();
		assertNotNull(userDAO.getCon());
		assertNotNull(userDAOTest.getCon());
		assertNotNull(userDAOAPP.getCon());
	}

	@Test
	public void shedDAOConnection() throws DataException {
		shedDAO = new ShedDAO("APP");
		shedDAOTest = new ShedDAO("TEST");
		shedDAOAPP = new ShedDAO();
		assertNotNull(shedDAO.getCon());
		assertNotNull(shedDAOTest.getCon());
		assertNotNull(shedDAOAPP.getCon());
	}

	@Test(expected = DataException.class)
	public void connectionException() throws DataException
	{
		MySqlConnector.createConnection("HULLABULLA");
	}

}
