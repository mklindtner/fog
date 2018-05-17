package daoTest.ServiceClasses;

import data.MySqlConnector;
import data.dao.*;
import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceSeed
{
	private static List<Employee>  employees  = new ArrayList<>();
	private static List<Customer>  customers  = new ArrayList<>();
	private static List<Order>     orders     = new ArrayList<>();
	private static List<Material>  materials  = new ArrayList<>();
	private static List<Shed>      sheds      = new ArrayList<>();
	private static List<OrderLine> orderLines = new ArrayList();
	private static UserDAO     userDAO;
	private static OrderDAO    orderDAO;
	private static ShedDAO shedsDAO;
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
		orderLines = new ArrayList<>();
	}

	public static void populateTables() throws DataException, UserException, OrderException, ShedException, MaterialException
	{
		populateCustomerTable();
		populateCustomerTableFacade();
		populateEmployeeTable();
		populateMaterialTable();
		populateShedTable();
		populateOrderTable();
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
		UserFacade userFacade = new MySqlUserFacade();
		userFacade.getUserDAOInstance();
		customers.add(userFacade.createCustomer("testUser4", "123", PHONE));
		customers.add(userFacade.createCustomer("testUser5", "123", PHONE));
		customers.add(userFacade.createCustomer("testUser6", "123", PHONE));
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

	private static void populateMaterialTable() throws OrderException, DataException, MaterialException
	{
		final String PLANK_LARGE          = "25x200mm. trykimp. Brædt";
		final String LATH                 = "38x73mm. Lægte ubh.";
		final String RAFTER               = "45x195mm. spærtræ ubh.";
		final String POLE                 = "97x97mm. trykimp. Stolpe";
		final String PLANKSMALL           = "19x100mm. trykimp. Brædt";
		final String PLASTMO_ECOLITE_BLUE = "Plastmo Ecolite blåtonet";
		final String PLASTMO_BOTTOM_SCREWS = "Plastmo bundskruer 200stk";
		final String PERFORATED_BAND =  "hulbånd 1x20mm. 10mtr.";
		final String UNIVERSAL_RIGHT = "universal 190mm højre";
		final String UNIVERSAL_LEFT = "universal 190mm venstre";
		final String NAIL = "4,5 x 60 mm. skruer 200stk";
		final String BRACKET = "4,0 x 50 mm. beslagsskruer 250stk";
		final String CARRIAGE_BOLT = "bræddebolt 10 x 120 mm";
		final String SQUARE_SLICES = "firkantskiver 40x40x11mm";
		final String SCREWS_FIFTY = "4,5 x 50 mm. skruer 300 stk";
		final String SCREWS_SEVENTY = "4,5 x 70 mm. skruer 400 stk";
		materials.add(materialDAO.materialByDescription(PLANK_LARGE));
		materials.add(materialDAO.materialByDescription(LATH));
		materials.add(materialDAO.materialByDescription(RAFTER));
		materials.add(materialDAO.materialByDescription(POLE));
		materials.add(materialDAO.materialByDescription(PLANKSMALL));
		materials.add(materialDAO.materialByDescription(PLASTMO_ECOLITE_BLUE));
		materials.add(materialDAO.materialByDescription(PLASTMO_BOTTOM_SCREWS));
		materials.add(materialDAO.materialByDescription(PERFORATED_BAND));
		materials.add(materialDAO.materialByDescription(UNIVERSAL_RIGHT));
		materials.add(materialDAO.materialByDescription(UNIVERSAL_LEFT));
		materials.add(materialDAO.materialByDescription(NAIL));
		materials.add(materialDAO.materialByDescription(BRACKET));
		materials.add(materialDAO.materialByDescription(CARRIAGE_BOLT));
		materials.add(materialDAO.materialByDescription(SQUARE_SLICES));
		materials.add(materialDAO.materialByDescription(SCREWS_FIFTY));
		materials.add(materialDAO.materialByDescription(SCREWS_SEVENTY));
	}

	private static void populateShedTable() throws ShedException
	{
		sheds.add(shedsDAO.createAndReturnShed(5, 5, false));
		sheds.add(shedsDAO.createAndReturnShed(10, 10, false));
		sheds.add(shedsDAO.createAndReturnShed(15, 15, false));

		sheds.add(shedsDAO.createAndReturnShed(20, 20, true));
		sheds.add(shedsDAO.createAndReturnShed(25, 25, true));
		sheds.add(shedsDAO.createAndReturnShed(30, 30, true));
	}

	private static void populateOrderTable() throws OrderException
	{
		//only flatroof for now
		orders.add(new Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(5)
				.insertRequiredWidth(5)
				.insertRequiredLength(5)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(0))
				.insertRequiredCustomer(customers.get(0))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(0))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(2, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(10)
				.insertRequiredWidth(10)
				.insertRequiredLength(10)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(1))
				.insertRequiredCustomer(customers.get(1))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(1))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(3, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(15)
				.insertRequiredWidth(15)
				.insertRequiredLength(15)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(2))
				.insertRequiredCustomer(customers.get(2))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(2))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(4, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(20)
				.insertRequiredWidth(20)
				.insertRequiredLength(20)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(3))
				.insertRequiredCustomer(customers.get(3))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(3))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(5, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(25)
				.insertRequiredWidth(25)
				.insertRequiredLength(25)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(4))
				.insertRequiredCustomer(customers.get(4))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(3))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(6, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(30)
				.insertRequiredWidth(30)
				.insertRequiredLength(30)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(5))
				.insertRequiredCustomer(customers.get(5))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(5))
				.build()
		);

		orders.add(new Order
				.OrderBuilder(7, ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(35)
				.insertRequiredWidth(35)
				.insertRequiredLength(35)
				.insertRequiredSlope(45)
				.insertRequiredMaterial(materials.get(6))
				.insertRequiredCustomer(customers.get(6))
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(sheds.get(6))
				.build()
		);

	}
}
