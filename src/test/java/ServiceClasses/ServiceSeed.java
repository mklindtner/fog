package ServiceClasses;

import data.MySqlConnector;
import data.MySQLDAO.*;
import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.UserFacade;

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
	private static ShedDAO     shedDAO;
	private static MaterialDAO materialDAO;
	private final static int PHONE = 1234567, STANDARD_EMPLOYEE_ROLE = 3, GENERATION_AMOUNT = 6;
	private static Connection con;

	public static void establishConnections() throws DataException
	{
		con = MySqlConnector.createConnection("TEST");
		userDAO = new UserDAO("TEST");
		orderDAO = new OrderDAO("TEST");
		materialDAO = new MaterialDAO("TEST");
		shedDAO = new ShedDAO("TEST");
	}


	public static void eraseTablesAndCloseConnection() throws DataException
	{
		try {
			con.createStatement().execute("DELETE FROM orderLines");
			con.createStatement().execute("DELETE FROM orders");
			con.createStatement().execute("DELETE FROM customers");
			con.createStatement().execute("DELETE FROM employees");
			con.createStatement().execute("DELETE FROM materials");
			con.createStatement().execute("DELETE FROM sheds");
			MySqlConnector.closeConnections();
		} catch(SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	public static void resetLists()
	{
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
	}

	private static void populateCustomerTable() throws DataException, UserException
	{
		customers.add(userDAO.createAndReturnCustomer("testUser1", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser2", "123", PHONE));
		customers.add(userDAO.createAndReturnCustomer("testUser3", "123", PHONE));
	}

	private static void populateCustomerTableFacade() throws DataException, UserException
	{
		UserFacade userFacade = new UserFacadeImpl();
		userFacade.getUserDAOInstance();
		customers.add(userFacade.createCustomer("testUser4", "123", PHONE));
		customers.add(userFacade.createCustomer("testUser5", "123", PHONE));
		customers.add(userFacade.createCustomer("testUser6", "123", PHONE));
	}

	private static void populateEmployeeTable() throws DataException, UserException
	{
		employees.add(userDAO.createAndReturnEmployee("testEmp1", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
		employees.add(userDAO.createAndReturnEmployee("testEmp2", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
		employees.add(userDAO.createAndReturnEmployee("testEmp3", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
		employees.add(userDAO.createAndReturnEmployee("testEmp4", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
		employees.add(userDAO.createAndReturnEmployee("testEmp5", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
		employees.add(userDAO.createAndReturnEmployee("testEmp6", "123", PHONE, STANDARD_EMPLOYEE_ROLE));
	}

	private static void populateMaterialTable() throws OrderException, DataException, MaterialException
	{
		final String PLANK_LARGE           = "25x200mm. trykimp. Brædt";
		final String PLANK_MEDIUM          = "25x125mm. trykimp. Brædt";
		final String LATH                  = "38x73mm. Lægte ubh.";
		final String REGLAR                = "45x95mm. Reglar ubh.";
		final String RAFTER                = "45x195mm. spærtræ ubh.";
		final String POLE                  = "97x97mm. trykimp. Stolpe";
		final String PLANKSMALL            = "19x100mm. trykimp. Brædt";
		final String PLASTMO_ECOLITE_BLUE  = "Plastmo Ecolite blåtonet";
		final String PLASTMO_BOTTOM_SCREWS = "Plastmo bundskruer 200stk";
		final String PERFORATED_BAND       = "hulbånd 1x20mm. 10mtr.";
		final String UNIVERSAL_RIGHT       = "universal 190mm højre";
		final String UNIVERSAL_LEFT        = "universal 190mm venstre";
		final String NAIL                  = "4,5 x 60 mm. skruer 200stk";
		final String BRACKET               = "4,0 x 50 mm. beslagsskruer 250stk";
		final String CARRIAGE_BOLT         = "bræddebolt 10 x 120 mm";
		final String SQUARE_SLICES         = "firkantskiver 40x40x11mm";
		final String SCREWS_FIFTY          = "4,5 x 50 mm. skruer 300 stk";
		final String SCREWS_SEVENTY        = "4,5 x 70 mm. skruer 400 stk";

		//shed
		final String FARMGATE_GRIP = "stalddørsgreb 50x75";
		final String HINGE = "t hængsel 390 mm";
		final String angelBracket = "vinkelbeslag 35";
		final String reglar = "45x95mm. Reglar ubh.";

		materials.add(materialDAO.createMaterial(PLANK_LARGE, 80));
		materials.add(materialDAO.createMaterial(PLANK_MEDIUM, 70));
		materials.add(materialDAO.createMaterial(LATH, 100));
		materials.add(materialDAO.createMaterial(REGLAR, 85));
		materials.add(materialDAO.createMaterial(RAFTER, 300));
		materials.add(materialDAO.createMaterial(POLE, 65));
		materials.add(materialDAO.createMaterial(PLANKSMALL, 120));
		materials.add(materialDAO.createMaterial(PLASTMO_ECOLITE_BLUE, 70));
		materials.add(materialDAO.createMaterial(PLASTMO_BOTTOM_SCREWS, 45));
		materials.add(materialDAO.createMaterial(PERFORATED_BAND, 25));
		materials.add(materialDAO.createMaterial(UNIVERSAL_RIGHT, 15));
		materials.add(materialDAO.createMaterial(UNIVERSAL_LEFT, 15));
		materials.add(materialDAO.createMaterial(NAIL, 30));
		materials.add(materialDAO.createMaterial(BRACKET, 40));
		materials.add(materialDAO.createMaterial(CARRIAGE_BOLT, 78));
		materials.add(materialDAO.createMaterial(SQUARE_SLICES, 95));
		materials.add(materialDAO.createMaterial(SCREWS_FIFTY, 200));
		materials.add(materialDAO.createMaterial(SCREWS_SEVENTY, 280));

		//shed add to materials
		materials.add(materialDAO.createMaterial(FARMGATE_GRIP, 50));
		materials.add(materialDAO.createMaterial(HINGE, 25));
		materials.add(materialDAO.createMaterial(angelBracket, 35));
		materials.add(materialDAO.createMaterial(reglar, 15));
	}

	private static void populateShedTable() throws ShedException
	{
		sheds.add(shedDAO.createAndReturnShed(5, 5, false));
		sheds.add(shedDAO.createAndReturnShed(10, 10, false));
		sheds.add(shedDAO.createAndReturnShed(15, 15, false));

		sheds.add(shedDAO.createAndReturnShed(20, 20, true));
		sheds.add(shedDAO.createAndReturnShed(25, 25, true));
		sheds.add(shedDAO.createAndReturnShed(30, 30, true));
	}

	private static void populateOrderTable() throws OrderException, DataException
	{
		//only flatroof for now
		Order orderOne = new Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(5)
						   .insertRequiredWidth(5)
						   .insertRequiredLength(5)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(0))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(sheds.get(0))
						   .build();

		Order orderTwo = new Order
				.OrderBuilder(2, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(400)
						   .insertRequiredWidth(400)
						   .insertRequiredLength(350)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(1))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(null)
						   .build();

		Order orderThree = new Order
				.OrderBuilder(3, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(15)
						   .insertRequiredWidth(15)
						   .insertRequiredLength(15)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(2))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(sheds.get(2))
						   .build();

		Order orderFour = new Order
				.OrderBuilder(4, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(20)
						   .insertRequiredWidth(20)
						   .insertRequiredLength(20)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(3))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(sheds.get(3))
						   .build();

		Order orderFive = new Order
				.OrderBuilder(5, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(25)
						   .insertRequiredWidth(25)
						   .insertRequiredLength(25)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(4))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(sheds.get(3))
						   .build();



		Order orderSix = new Order
				.OrderBuilder(6, ServiceMethods.getCurrentTimeAsString())
						   .insertRequiredHeight(30)
						   .insertRequiredWidth(30)
						   .insertRequiredLength(30)
						   .insertRequiredSlope(45)
						   .insertRequiredCustomer(customers.get(5))
						   .insertOptionalStatus(Order.Status.PENDING)
						   .insertOptionalShed(sheds.get(5))
						   .build();

		orders.add(orderDAO.createAndReturnOrder(orderOne));
		orders.add(orderDAO.createAndReturnOrder(orderTwo));
		orders.add(orderDAO.createAndReturnOrder(orderThree));
		orders.add(orderDAO.createAndReturnOrder(orderFour));
		orders.add(orderDAO.createAndReturnOrder(orderFive));
		orders.add(orderDAO.createAndReturnOrder(orderSix));
	}

	public static List<Customer> getCustomers()
	{
		return customers;
	}

	public static List<Order> getOrders()
	{
		return orders;
	}

	public static List<Shed> getShedsOn()
	{
		return sheds;
	}

	public static List<Material> getMaterials()
	{
		return materials;
	}
}

/*
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
		materials.add(materialDAO.materialByDescription(SCREWS_SEVENTY)); */
