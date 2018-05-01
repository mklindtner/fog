package daoTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.UserDAO;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;
import data.exceptions.ShedCreationException;
import data.exceptions.UserAccessException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

public class OrderDAOTest
{/*
	OrderDAO   orderDAO;

	@Before
	public void setUp() throws DataAccessException, UserAccessException, OrderAccessException, ShedCreationException
	{
		try {
		//	con = MySqlConnector.createConnection("TEST");
		//	con.createStatement().execute("DELETE FROM orders");

			ServiceSeed.establishConnections();
			ServiceSeed.eraseTables();
			ServiceSeed.populateTables();
			orderDAO = new OrderDAO("TEST");
		}catch( SQLException throwSql ) {
			throw new DataAccessException(throwSql);
		}
	}



	/*
	@Test
	public void firstOrder() throws OrderAccessException, DataAccessException, UserAccessException {
		List<Order> orders = orderDAO.allOrdersWithoutShed();
		assertEquals(orders.get(0), expectedOrder());
	}

	private Order expectedOrder() throws DataAccessException, UserAccessException, OrderAccessException
	{
		//this is garbage
		UserDAO userDAO = new UserDAO();
		MaterialDAO materialDAO = new MaterialDAO();
		Customer customer = userDAO.customerByUsername("testUser1");
		//List<Order> expected = new ArrayList<>();
		Order order = new  Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
				.createOrderWithoutShed(10, 10, 10, customer, 35, materialDAO.getMaterialById(1) )
				.build();
		return order;
	} */

}
