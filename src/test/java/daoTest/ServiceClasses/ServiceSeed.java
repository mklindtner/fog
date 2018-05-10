package daoTest.ServiceClasses;

import data.MySqlConnector;
import data.dao.*;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.MaterialDimensions;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.Shed;
import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.ShedException;
import data.exceptions.UserException;
import logic.UserFacade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

	public static void establishConnections() throws DataException
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

	public static void resetLists() {
		employees = new ArrayList<>();
		customers = new ArrayList<>();
		orders = new ArrayList<>();
		materials = new ArrayList<>();
		sheds = new ArrayList<>();
	}

	public static void populateTables() throws DataException, UserException, OrderException, ShedException
	{
		populateCustomerTable();
		populateCustomerTableFacade();
		populateEmployeeTable();
		populateMaterialTable();
		populateShedTable();
		//populateOrderTable();
		//populate here

	}

	private static void populateCustomerTable() throws DataException, UserException
	{
		customers.add(userDAO.createAndReturnCustomer("testUser1", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser2", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser3", "123", PHONE));
	}

	private static void populateCustomerTableFacade() throws DataException, UserException
	{
		customers.add(UserFacade.createCustomer("testUser4", "123", PHONE));
		customers.add(UserFacade.createCustomer("testUser5", "123", PHONE));
		customers.add(UserFacade.createCustomer("testUser6", "123", PHONE));
	}

	private static void populateEmployeeTable() throws DataException, UserException
	{
		userDAO.createAndReturnEmployee("testEmp1", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp2", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp3", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp4", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp5", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
		userDAO.createAndReturnEmployee("testEmp6", "123", PHONE, STANDARD_EMPLOYEE_ROLE);
	}

	private static void populateMaterialTable() throws OrderException, DataException
	{
		/*
		MaterialDimensions materialDimensions = new MaterialDimensions(10, 10, 10);
		materials.add(materialDAO.createAndReturnMaterial(50, "mediumWood", materialDimensions, "", 10));
		materials.add(materialDAO.createAndReturnMaterial(150, "wetWood", materialDimensions, "", 15));
		materials.add(materialDAO.createAndReturnMaterial(200, "hardWood", materialDimensions, "", 20));
		materials.add(materialDAO.createAndReturnMaterial(250, "mediumWood", materialDimensions, "", 25)); */
	}

	private static void populateShedTable() throws ShedException
	{
		sheds.add(orderDAO.createAndReturnShed(5, 5, 5, false));
		sheds.add(orderDAO.createAndReturnShed(10, 10, 10, true));
		sheds.add(orderDAO.createAndReturnShed(15, 15, 15, false));
		sheds.add(orderDAO.createAndReturnShed(20, 20, 20, true));

	}

	/*
	//no sheds for now
	private static void populateOrderTable() throws OrderException
	{
		orderDAO.createOrder
				(10, 10, 10, false, 45,
				 customers.get(0).getId(), materials.get(0).getId(), sheds.get(0).getId());
	} */
}
