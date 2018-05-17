package daoTest;

import data.MySqlConnector;
import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.OrderLineDAO;
import data.dao.UserDAO;
import data.exceptions.DataException;
import entities.OrderEntities.OrderLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

public class ConnectionTest
{
	private Connection appConnection, testConnection;
	private MaterialDAO materialDAO, materialDAOTest;
	private OrderDAO orderDAO, orderDAOTest;
	private OrderLineDAO orderLineDAO, orderLineDAOTest;
	private UserDAO userDAO, userDAOTest;
	private ShedDAO shedDAO, shedDAOTest;

	@After
	public void resetConnection() {
		materialDAO = null;
		materialDAOTest = null;
		orderDAO = null;
		orderDAOTest = null;
		orderLineDAO = null;
		orderLineDAOTest = null;
		userDAO = null;
		userDAOTest = null;
		shedDAO = null;
		shedDAOTest = null;
	}


	@Test
	public void CloudConnection() throws DataException
	{
		appConnection = MySqlConnector.createConnection("APP");
		assertNotNull(appConnection);
	}

	@Test
	public void cloudTestConnection() throws DataException {
		testConnection = MySqlConnector.createConnection("TEST");
		assertNotNull(testConnection);
	}

	@Test
	public void materialDAOConnection() throws DataException {
		materialDAO = new MaterialDAO("APP");
		materialDAOTest = new MaterialDAO("TEST");
		assertNotNull(materialDAO.getCon());
		assertNotNull(materialDAOTest.getCon());
	}

	@Test
	public void orderDAOConnection() throws DataException {
		orderDAO = new OrderDAO("APP");
		orderDAOTest = new OrderDAO("TEST");
		assertNotNull(orderDAO.getCon());
		assertNotNull(orderDAOTest.getCon());
	}


	@Test
	public void orderLineDAOConnection() throws DataException {
		orderLineDAO = new OrderLineDAO("APP");
		orderLineDAOTest = new OrderLineDAO("TEST");
		assertNotNull(orderLineDAO.getCon());
		assertNotNull(orderLineDAOTest.getCon());
	}


	@Test
	public void serviceDAOConnection() throws DataException {
		userDAO = new UserDAO("APP");
		userDAOTest = new UserDAO("TEST");
		assertNotNull(userDAO.getCon());
		assertNotNull(userDAOTest.getCon());
	}

	@Test
	public void shedDAOConnection() throws DataException {
		shedDAO = new ShedDAO("APP");
		shedDAOTest = new ShedDAO("TEST");
		assertNotNull(shedDAO.getCon());
		assertNotNull(shedDAOTest.getCon());
	}
}
