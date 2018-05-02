package daoTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.dao.UserDAO;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.ShedException;
import data.exceptions.UserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class UserDAOTest
{
	UserDAO    userDAO;
	private final int PHONE = 1234567, STANDARD_EMPLOYEE_ROLE = 3, GENERATION_AMOUNT = 6;

	@Before
	public void setUp() throws DataException
	{
		try {
			ServiceSeed.establishConnections();
			ServiceSeed.populateTables();
			userDAO = new UserDAO("TEST");
		} catch (DataException | UserException | ShedException | OrderException dae)
		{
			throw new DataException(dae);
		}
	}

	@After
	public void tearDown() throws DataException
	{
		try {
			ServiceSeed.eraseTables();
			ServiceSeed.resetLists();
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	//this for fixing empl/customer time
	@Test
	public void allCustomersFromDAO() throws DataException
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
					.CustomerBuilder(i, ServiceMethods.getCurrentTimeAsString())
					.createSimpleCustomer("testUser" + (i + 1), "123", PHONE)
					.build();
			expected.add(customerTmp);
		}
		return expected;
	}

	//this for fixing time
	@Test
	public void allEmployeesFromDAO() throws UserException, DataException
	{
		List<Employee> actual   = userDAO.allEmployees();
		List<Employee> expected = expectedEmployeeGenerator(GENERATION_AMOUNT);
		//assertEquals(actual, expected);
		assertEquals(actual.get(0).toString(), expected.get(0).toString());
		assertEquals(actual.get(actual.size() - 1).toString(), expected.get(expected.size() - 1).toString());
	}

	private List<Employee> expectedEmployeeGenerator(int size)
	{
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Employee employeeTmp = new Employee
					.EmployeeBuilder(i, ServiceMethods.getCurrentTimeAsString())
					.createSimpleEmployee("testEmp" + (i + 1), "123", "SALGSMEDARBEJDER", PHONE)
					.build();
			employees.add(employeeTmp);
		}
		return employees;
	}
}
