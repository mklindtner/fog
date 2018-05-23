package daoTest;

import ServiceClasses.ServiceMethods;
import ServiceClasses.ServiceSeed;
import data.MySqlConnector;
import data.dao.ServiceDAO;
import data.exceptions.*;
import entities.OrderEntities.Material;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Executable;
import java.sql.Connection;

import static junit.framework.TestCase.assertEquals;

public class ServiceDAOTest
{
	Connection con;

	@Before
	public void setup() throws DataException, MaterialException, OrderException, UserException, ShedException
	{
		ServiceSeed.establishConnections();
		ServiceSeed.populateTables();
		con = MySqlConnector.createConnection("TEST");
	}

	@After
	public void tearDown() throws DataException
	{
		ServiceSeed.eraseTablesAndCloseConnection();
		ServiceSeed.resetLists();
	}

	@Test(expected = OrderException.class)
	public void customerByIdOrderException() throws DataException, OrderException
	{
		ServiceDAO.getCustomerById(-2, con);
	}

	@Test(expected = UserException.class)
	public void getRoleException() throws UserException
	{
		ServiceDAO.getRole(-2, con);
	}

	@Test(expected = ShedException.class)
	public void shedByIdException() throws ShedException
	{
		ServiceDAO.shedById(-2, con);
	}

	@Test(expected = MaterialException.class)
	public void materialByIdException() throws MaterialException
	{
		ServiceDAO.materialById(-2, con);
	}

	@Test
	public void materialById() throws MaterialException
	{
		Material actualMaterial = ServiceDAO.materialById(ServiceSeed.getMaterials().get(0).getId(), con);
		Material expected = ServiceMethods.expectedFirstMaterial();
		assertEquals(expected, actualMaterial);
	}


}
