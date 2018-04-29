package daoTest;

import data.MySqlConnector;
import data.dao.UserDAO;
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

public class UserDAOTest
{
	Connection con;
	UserDAO    userDAO;
	private final int PHONE = 1234567, STANDARD_EMPLOYEE_ROLE = 3, GENERATION_AMOUNT = 6;

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
		userDAO.createAndReturnEmployee("testEmp1", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp2", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp3", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp4", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp5", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp6", "123", PHONE, STANDARD_EMPLOYEE_ROLE);

	}

	//this for fixing empl/customer time
	@Test
	public void allCustomersFromDAO() throws DataAccessException
	{
		List<Customer> actual   = userDAO.allCustomers();
		List<Customer> expected = expectedCustomersGenerator(GENERATION_AMOUNT);
		//assertEquals(actual, expected);
		assertEquals(expected.size(), actual.size());
		assertEquals(actual.get(0).toString(), expected.get(0).toString());
		assertEquals(actual.get(actual.size() - 1).toString(), expected.get(expected.size() - 1).toString());
	}


	private List<Customer> expectedCustomersGenerator(int size)
	{
		List<Customer> expected = new ArrayList<>();
		for (int i = 0; i < size; i++) {
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


	@Test
	public void allEmployeesFromDAO() throws UserAccessException
	{
		List<Employee> actual   = userDAO.allEmployees();
		List<Employee> expected = expectedEmployeeGenerator(GENERATION_AMOUNT);
		assertEquals(actual.get(0).toString(), expected.get(0).toString());
		assertEquals(actual.get(actual.size()-1).toString(), expected.get(expected.size() - 1).toString());
	}

	private List<Employee> expectedEmployeeGenerator(int size)
	{
		List<Employee> employees = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			Employee employeeTmp = new Employee
					.EmployeeBuilder(i, getCurrentTimeAsString())
					.createSimpleEmployee("testEmp" + (i + 1), "123", "SALGSMEDARBEJDER", PHONE).build();
			employees.add(employeeTmp);
		}
		return employees;
	}
	

}
