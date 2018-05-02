package daoTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.UserDAO;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.ShedException;
import data.exceptions.UserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OrderDAOTest
{
	OrderDAO orderDAO;

	@Before
	public void setUp() throws DataException, UserException, OrderException, ShedException,
							   SQLException
	{
		//con = MySqlConnector.createConnection("TEST");
		//con.createStatement().execute("DELETE FROM orders");
		ServiceSeed.establishConnections();
		ServiceSeed.populateTables();
		orderDAO = new OrderDAO("TEST");

	}

	@After
	public void tearDown() throws DataException
	{
		try {
			ServiceSeed.eraseTables();
			ServiceSeed.resetLists();
		} catch (SQLException throwSql) {
			throw new DataException();
		}
	}

	@Test
	public void firstOrder() throws OrderException, DataException, UserException {
		List<Order> orders = orderDAO.allOrdersWithoutShed();
		assertEquals(orders.get(0), expectedOrder());
	}

	private Order expectedOrder() throws DataException, UserException, OrderException
	{
		//this is garbage
		UserDAO     userDAO     = new UserDAO();
		MaterialDAO materialDAO = new MaterialDAO();
		Customer    customer    = userDAO.customerByUsername("testUser1");
		//List<Order> expected = new ArrayList<>();
		Order order = new  Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
				.createOrderWithoutShed(10, 10, 10, customer, 35, materialDAO.getMaterialById(1) )
				.build();
		return order;
	}

}
