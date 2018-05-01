package daoTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.dao.UserDAO;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;
import data.exceptions.ShedCreationException;
import data.exceptions.UserAccessException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
	public void setUp() throws DataAccessException
	{
		try {
			ServiceSeed.establishConnections();
			ServiceSeed.populateTables();
			userDAO = new UserDAO("TEST");
		} catch (DataAccessException | UserAccessException | ShedCreationException | OrderAccessException dae)
		{
			throw new DataAccessException(dae);
		}
	}

	@After
	public void tearDown() throws DataAccessException
	{
		try {
			ServiceSeed.eraseTables();
		} catch (SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
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
					.CustomerBuilder(i, ServiceMethods.getCurrentTimeAsString())
					.createSimpleCustomer("testUser" + (i + 1), "123", PHONE)
					.build();
			expected.add(customerTmp);
		}
		return expected;
	}
/*
	//this for fixing time
	@Test
	public void allEmployeesFromDAO() throws UserAccessException, DataAccessException
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
	}*/
}
