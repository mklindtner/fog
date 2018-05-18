package daoTest.ServiceClasses;

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
				.CustomerBuilder(1, ServiceMethods.getCurrentTimeAsString())
				.insertUsername("testUser1")
				.insertPassword(USER_PASSWORD)
				.insertPhone(USER_PHONE)
				.build();

		Shed shed = new Shed.ShedBuilder()
				.insertWidth(5)
				.insertLength(5)
				.insertHasFloor(false)
				.insertShedId(1)
				.build();


		Order order = new Order
				.OrderBuilder(1, ServiceMethods.getCurrentTimeAsString())
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
}
