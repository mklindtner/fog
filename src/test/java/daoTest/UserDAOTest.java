package daoTest;

import data.MySqlConnector;
import data.dao.UserDAO;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
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
			con = MySqlConnector.createConnection("TEST");
			//con = MySqlConnector.connectLocalTestMysql();
			ServiceTest.establishConnections();
			ServiceTest.eraseTables();
			ServiceTest.populateTables();
			ServiceTest.checkConnections(); //how do i run this?
			userDAO = new UserDAO();
		} catch (SQLException | DataAccessException dae) {
			throw new DataAccessException(dae);
		}
		//con = MySqlConnector.connectLocalMySql();

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
					.CustomerBuilder(i, ServiceTest.getCurrentTimeAsString())
					.createSimpleCustomer("testUser" + (i + 1), "123", PHONE)
					.build();
			expected.add(customerTmp);
		}
		return expected;
	}

	//this for fixing time
	@Test
	public void allEmployeesFromDAO() throws UserAccessException
	{
		List<Employee> actual   = userDAO.allEmployees();
		List<Employee> expected = expectedEmployeeGenerator(GENERATION_AMOUNT);
		//assertEquals(actual, expected);
		assertEquals(actual.get(0).toString(), expected.get(0).toString());
		assertEquals(actual.get(actual.size()-1).toString(), expected.get(expected.size() - 1).toString());
	}

	private List<Employee> expectedEmployeeGenerator(int size)
	{
		List<Employee> employees = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			Employee employeeTmp = new Employee
					.EmployeeBuilder(i, ServiceTest.getCurrentTimeAsString())
					.createSimpleEmployee("testEmp" + (i + 1), "123", "SALGSMEDARBEJDER", PHONE)
					.build();
			employees.add(employeeTmp);
		}
		return employees;
	}


}
