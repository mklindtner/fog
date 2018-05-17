package daoTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.UserDAO;
import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
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
	public void setUp() throws DataException, UserException, OrderException, ShedException, MaterialException,
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
		Shed shed = new Shed.ShedBuilder()
			 	.insertWidth(5)
				.insertLength(5)
				.insertHasFloor(false)
				.insertShedId(1)
				.build();

		Material firstMaterial = new Material("25x200mm. trykimp. Brædt", 80, 1);

		Order order = new Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(5)
				.insertRequiredWidth(5)
				.insertRequiredLength(5)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(firstMaterial)
				.insertRequiredCustomer(customer)
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(shed)
				.build();

		return order;
	}

}
