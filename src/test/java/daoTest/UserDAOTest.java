package daoTest;

import com.mysql.cj.xdevapi.SqlDataResult;
import data.MySqlConnector;
import data.dao.UserDAO;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.*;

public class UserDAOTest
{
	Connection con;
	UserDAO    userDAO;

	@Before
	public void setUp() throws DataAccessException
	{
		//should i handle the exception or not?
		try {
			con = MySqlConnector.connectTestCloudMySql();
			con.createStatement().execute("DELETE FROM customers");
			userDAO = new UserDAO();
		} catch (SQLException | DataAccessException dae) {
			throw new DataAccessException(dae);
		}
		//con = MySqlConnector.connectLocalMySql();

	}


	@Test
	public void checkConnection()
	{
		assertNotNull(con);
	}


	@Test
	public void createCustomerDAO() throws DataAccessException, UserAccessException
	{
		userDAO.createAndReturnCustomer("testUser1", "123", 1234567);
		userDAO.createAndReturnCustomer("testUser2", "123", 2134567);
		userDAO.createAndReturnCustomer("testUser3", "123", 3124567);
	}

	@Test
	public void createCustomerLogic() throws DataAccessException, UserAccessException
	{
		UserFacade.createCustomer("testUser4", "123", 4123567);
		UserFacade.createCustomer("testUser5", "123", 5123467);
		UserFacade.createCustomer("testUser6", "123", 6123457);
	}




	//this for fixing empl/cus time & id
	@Test
	public void allCustomersFromDAO() throws DataAccessException
	{
		List<Customer> actual   = userDAO.allCustomers();
		List<Customer> expected = expectedCustomersGenerator(6);
		//assertEquals(actual, expected);
		assertEquals(expected.size(), actual.size());
		assertEquals(actual.get(0).toString(), expected.get(0).toString());
		assertEquals(actual.get(actual.size() - 1).toString(), expected.get(expected.size() - 1).toString());
	}


	private List<Customer> expectedCustomersGenerator(int amount)
	{
		List<Customer> expected = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			Customer customerTmp = new Customer
					.CustomerBuilder(i, getCurrentTimeAsString())
					.createSimpleCustomer("testUser" + (i + 1), "123", 1234567)
					.build();
			expected.add(customerTmp);
		}
		return expected;
	}

	private String getCurrentTimeAsString()
	{
		Calendar  calender         = Calendar.getInstance();
		Date      now              = calender.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
		return currentTimeStamp.toString();
	}

	/*
	@Test
	public void allEmployeesFromDAO() throws UserAccessException
	{
		List<Employee> actual = userDAO.allEmployees();
	//	List<Employee> expected =
	} */

}
