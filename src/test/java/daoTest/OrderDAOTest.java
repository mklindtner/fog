package daoTest;

import ServiceClasses.ServiceMethods;
import ServiceClasses.ServiceSeed;
import data.MySQLDAO.OrderDAO;
import data.exceptions.*;
import entities.OrderEntities.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OrderDAOTest
{
	OrderDAO orderDAO;
	private final int    USER_PHONE    = 1234567;
	private final String USER_PASSWORD = "123";

	@Before
	public void setUp() throws DataException, UserException, OrderException, ShedException, MaterialException
	{
		ServiceSeed.establishConnections();
		ServiceSeed.populateTables();
		orderDAO = new OrderDAO("TEST");

	}

	@After
	public void tearDown() throws DataException
	{
		ServiceSeed.eraseTablesAndCloseConnection();
		ServiceSeed.resetLists();
	}

	@Test
	public void firstOrder() throws OrderException
	{
		List<Order> ordersActual = orderDAO.allOrders();
		assertEquals(ordersActual.get(0), ServiceMethods.expectedFirstOrder());
		assertEquals(ordersActual.size(), 6);
	}

	@Test
	public void customerOrders() throws OrderException
	{
		List<Order> ordersActual         = orderDAO.allOrders();
		int         userId               = orderDAO.findCustomerIdByOrder(ordersActual.get(0));
		List<Order> customerOrdersActual = orderDAO.ordersOfCustomer(userId);
		assertEquals(customerOrdersActual.get(0), ServiceMethods.expectedFirstOrder());
	}

}
