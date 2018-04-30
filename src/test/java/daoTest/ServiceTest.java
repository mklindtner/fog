package daoTest;

import data.MySqlConnector;
import data.dao.OrderDAO;
import data.dao.UserDAO;
import data.entities.OrderEntities.Order;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class ServiceTest
{
	private static UserDAO  userDAO;
	private static OrderDAO orderDAO;
	private final static int PHONE = 1234567, STANDARD_EMPLOYEE_ROLE = 3, GENERATION_AMOUNT = 6;
	private static Connection con;

	public static void establishConnections() throws DataAccessException
	{
		con = MySqlConnector.createConnection("TEST");
		userDAO = new UserDAO();
		orderDAO = new OrderDAO();
	}


	@Test
	public static void checkConnections() {
		assertNotNull(userDAO);
		assertNotNull(orderDAO);
		assertNotNull(con);
	}

	public static String getCurrentTimeAsString()
	{
		Calendar  calender         = Calendar.getInstance();
		Date      now              = calender.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
		return currentTimeStamp.toString();
	}

	public static void eraseTables() throws SQLException
	{
		con.createStatement().execute("DELETE FROM orders");
		con.createStatement().execute("DELETE FROM customers");
		con.createStatement().execute("DELETE FROM employees");
	}

	public static void populateTables() throws DataAccessException, UserAccessException
	{
		populateCustomerTable();
		populateCustomerTableFacade();
		populateEmployeeTable();
		//populate here

	}

	private static void populateCustomerTable() throws DataAccessException, UserAccessException
	{
		userDAO.createAndReturnCustomer("testUser1", "123", PHONE);
		userDAO.createAndReturnCustomer("testUser2", "123", PHONE);
		userDAO.createAndReturnCustomer("testUser3", "123", PHONE);
	}

	private static void populateCustomerTableFacade() throws DataAccessException, UserAccessException
	{
		UserFacade.createCustomer("testUser4", "123", PHONE);
		UserFacade.createCustomer("testUser5", "123", PHONE);
		UserFacade.createCustomer("testUser6", "123", PHONE);
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



}
