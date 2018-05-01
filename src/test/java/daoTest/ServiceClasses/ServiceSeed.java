package daoTest.ServiceClasses;

import data.MySqlConnector;
import data.dao.*;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.Shed;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;
import data.exceptions.ShedCreationException;
import data.exceptions.UserAccessException;
import logic.UserFacade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;


public class ServiceSeed
{
	private static List<Employee> employees = new ArrayList<>();
	private static List<Customer> customers = new ArrayList<>();
	private static List<Order>    orders    = new ArrayList<>();
	private static List<Material> materials = new ArrayList<>();
	private static List<Shed>     sheds     = new ArrayList<>();
	private static UserDAO     userDAO;
	private static OrderDAO    orderDAO;
	private static MaterialDAO materialDAO;
	private final static int PHONE = 1234567, STANDARD_EMPLOYEE_ROLE = 3, GENERATION_AMOUNT = 6;
	private static Connection con;

	public static void establishConnections() throws DataAccessException
	{
		con = MySqlConnector.createConnection("TEST");
		userDAO = new UserDAO("TEST");
		orderDAO = new OrderDAO("TEST");
		materialDAO = new MaterialDAO("TEST");
	}


	public static void eraseTables() throws SQLException
	{
		con.createStatement().execute("DELETE FROM orders");
		con.createStatement().execute("DELETE FROM customers");
		con.createStatement().execute("DELETE FROM employees");
		con.createStatement().execute("DELETE FROM materials");
		con.createStatement().execute("DELETE FROM sheds");
	}

	public static void populateTables() throws DataAccessException, UserAccessException, OrderAccessException, ShedCreationException
	{
		populateCustomerTable();
		populateCustomerTableFacade();
		populateEmployeeTable();
		populateMaterialTable();
		populateShedTable();
		populateOrderTable();
		//populate here

	}

	private static void populateCustomerTable() throws DataAccessException, UserAccessException
	{
		customers.add(userDAO.createAndReturnCustomer("testUser1", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser2", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser3", "123", PHONE));
	}

	private static void populateCustomerTableFacade() throws DataAccessException, UserAccessException
	{
		customers.add(UserFacade.createCustomer("testUser4", "123", PHONE));
		customers.add(UserFacade.createCustomer("testUser5", "123", PHONE));
		customers.add(UserFacade.createCustomer("testUser6", "123", PHONE));
	}

	private static void populateEmployeeTable() throws DataAccessException, UserAccessException
	{
		userDAO.createAndReturnEmployee("testEmp1", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp2", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp3", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp4", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp5", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp6", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
	}

	private static void populateMaterialTable() throws OrderAccessException, DataAccessException
	{
		materials.add(materialDAO.createAndReturnMaterial(5, "20-25mm", "softwood"));
		materials.add(materialDAO.createAndReturnMaterial(10, "25-30mm", "mediumwood"));
		materials.add(materialDAO.createAndReturnMaterial(15, "30-35mm", "hardwood"));
		materials.add(materialDAO.createAndReturnMaterial(20, "35-40mm", "metalCoil"));
	}

	private static void populateShedTable() throws ShedCreationException
	{
		sheds.add(orderDAO.createAndReturnShed(5, 5, 5, false));
		sheds.add(orderDAO.createAndReturnShed(10, 10, 10, true));
		sheds.add(orderDAO.createAndReturnShed(15, 15, 15, false));
		sheds.add(orderDAO.createAndReturnShed(20, 20, 20, true));

	}

	//no sheds for now
	private static void populateOrderTable() throws OrderAccessException
	{
		orderDAO.createOrder
				(10, 10, 10, false, 45,
				 customers.get(0).getId(), materials.get(0).getId(), sheds.get(0).getId());
	}
}
