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
	private final int PHONE = 1234567;

	@Before
	public void setUp() throws DataAccessException, UserAccessException
	{
		//should i handle the exception or not?
		try {
			con = MySqlConnector.connectTestCloudMySql();
			//con = MySqlConnector.connectLocalTestMysql();
			con.createStatement().execute("DELETE FROM customers");
			con.createStatement().execute("DELETE FROM employees");
			userDAO = new UserDAO();
			createCustomerDAO();
			createCustomerLogic();
			createEmployeeDAO();
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

	//have to call thsi before startup
	public void createCustomerDAO() throws DataAccessException, UserAccessException
	{
		userDAO.createAndReturnCustomer("testUser1", "123", PHONE);
		userDAO.createAndReturnCustomer("testUser2", "123", PHONE);
		userDAO.createAndReturnCustomer("testUser3", "123", PHONE);
	}

	//samee
	public void createCustomerLogic() throws DataAccessException, UserAccessException
	{
		UserFacade.createCustomer("testUser4", "123", PHONE);
		UserFacade.createCustomer("testUser5", "123", PHONE);
		UserFacade.createCustomer("testUser6", "123", PHONE);
	}

	//same
	public void createEmployeeDAO() throws UserAccessException, DataAccessException
	{
		userDAO.createAndReturnEmployee("testEmp1", "123", PHONE, 1);
		userDAO.createAndReturnEmployee("testEmp2", "123", PHONE, 1);
		userDAO.createAndReturnEmployee("testEmp3", "123", PHONE, 1);
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
					.createSimpleCustomer("testUser" + (i + 1), "123", PHONE)
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
