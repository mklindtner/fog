package ServiceClasses;

import data.dao.OrderDAO;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ServiceMethods
{
	private static String USER_PASSWORD = "123";
	private static int USER_PHONE = 1234567;

	public static String getCurrentTimeAsString()
	{
		Calendar  calender         = Calendar.getInstance();
		Date      now              = calender.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
		return currentTimeStamp.toString();
	}

	public static Order expectedFirstOrder() {
		Customer customer = new Customer
				.CustomerBuilder(ServiceSeed.getCustomers().get(0).getId(), ServiceMethods.getCurrentTimeAsString())
				.insertUsername("testUser1")
				.insertPassword(USER_PASSWORD)
				.insertPhone(USER_PHONE)
				.build();

		Shed shed = new Shed.ShedBuilder()
				.insertShedId(ServiceSeed.getShedsOn().get(0).getId())
				.insertWidth(5)
				.insertLength(5)
				.insertHasFloor(false)
				.insertShedId(1)
				.build();


		Order order = new Order
				.OrderBuilder(ServiceSeed.getOrders().get(0).getId(), ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(5)
				.insertRequiredWidth(5)
				.insertRequiredLength(5)
				.insertRequiredSlope(45)
				.insertRequiredCustomer(customer)
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(shed)
				.build();
		return order;
	}

	public static Order expectedSecondOrder()
	{
		Customer customer = new Customer
				.CustomerBuilder(ServiceSeed.getCustomers().get(1).getId(), ServiceMethods.getCurrentTimeAsString())
				.insertUsername("testUser2")
				.insertPassword(USER_PASSWORD)
				.insertPhone(USER_PHONE)
				.build();

		Order order = new Order
				.OrderBuilder(ServiceSeed.getOrders().get(1).getId(), ServiceMethods.getCurrentTimeAsString())
				.insertRequiredHeight(400)
				.insertRequiredWidth(400)
				.insertRequiredLength(350)
				.insertRequiredSlope(45)
				.insertRequiredCustomer(customer)
				.insertOptionalStatus(Order.Status.PENDING)
				.insertOptionalShed(null)
				.build();

		return order;

	}

	public static OrderLine expectedFirstOrderLine()
	{
		//doesn't check id's
		return new OrderLine
				.OrderLineBuilder()
				.insertLength(1)
				.insertAmount(4)
				.insertUnit("stk")
				.insertFirstDescription("25x200mm. trykimp. Brædt")
				.insertSecondDescription("understernbrædder til for & bag ende")
				.insertIsTreeOrRoof(true)
				.insertPriceForOrderLine(320)
				.build();
	}

	public static Material expectedFirstMaterial()
	{
		final String PLANK_LARGE           = "25x200mm. trykimp. Brædt";
		return new Material(PLANK_LARGE, 80, ServiceSeed.getMaterials().get(0).getId());
	}
}
