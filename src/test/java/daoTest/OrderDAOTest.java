package daoTest;

import data.MySqlConnector;
import data.dao.OrderDAO;
import data.dao.UserDAO;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class OrderDAOTest
{
	Connection con;
	OrderDAO   orderDAO;

	@Before
	public void setUp() throws DataAccessException
	{
		try {
			con = MySqlConnector.createConnection("TEST");
			con.createStatement().execute("DELETE FROM orders");
			orderDAO = new OrderDAO();
		}catch( SQLException throwSql ) {
			throw new DataAccessException(throwSql);
		}
	}


	@Test
	public void checkConnection() {
		assertNotNull(con);
	}

}
