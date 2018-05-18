package view.servlets.orderServlets.helpers;

import data.exceptions.*;
import entities.OrderEntities.Order;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import logic.generators.BillOfMaterials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateOrderList
{
	/*
	private OrderFacade orderFacade;


	private static void getOrderFacade() throws DataException {
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
	}*/

	public static void generateEmployeeOrders(HttpSession session, Employee employee) throws OrderException,
																							 DataException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		//List<Order> employeeOrders = MySqlOrderFacade.employeeChosenOrders(employee.getId());
		List<Order> employeeOrders = orderFacade.employeeChosenOrders(employee.getId());
		session.setAttribute("employeeOrders", employeeOrders);
	}

	public static void genreateOrdersAvailable(HttpSession session) throws OrderException, DataException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		session.setAttribute("ordersAvailable", orderFacade.ordersAvailable());

	}

	public static void generateCustomerOrders(HttpSession session, Customer customer) throws OrderException,
																							 DataException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		List<Order> customerOrders = orderFacade.ordersOfCustomer(customer.getId());
		session.setAttribute("customerOrders", customerOrders);
	}

	public static Order createOrderAndOrderLine(HttpServletRequest request, Customer user) throws MaterialException,
																								  OrderLineException,
																								  DataException,
																								  OrderException, ShedException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		Shed shed;
		Order order = createOrder(request, user);
		if (haveShed(request)) {
			shed = createShed(request, orderFacade);
			order.setshed(shed);
		}
		order = orderFacade.createAndReturnOrder(order);
		saveOrderLineDB(order);
		return order;
	}

	private static Shed createShed(HttpServletRequest request, OrderFacade orderFacade) throws DataException,
																							 ShedException
	{
		int    shedWidth  = Integer.parseInt(request.getParameter("shedWidth"));
		int    shedLength = Integer.parseInt(request.getParameter("shedLength"));
		String hasFloor   = request.getParameter("hasFloor");
		orderFacade.getInstanceShedDAO();
		return (hasFloor != null) ? orderFacade.createShed(shedWidth, shedLength, true) : orderFacade.createShed(shedWidth, shedLength, false);

	}

	private static Order createOrder(HttpServletRequest request, Customer user)
	{
		int    height     = Integer.parseInt(request.getParameter("height"));
		int    width      = Integer.parseInt(request.getParameter("width"));
		int    length     = Integer.parseInt(request.getParameter("length"));
		int    slope      = Integer.parseInt(request.getParameter("slope"));

		return new Order
				.OrderBuilder()
				.createOrderWithoutShed(height, width, length, user, slope)
				.build();
	}


	private static boolean haveShed(HttpServletRequest request)
	{
		return  (request.getParameter("shedWidth").equals("choose a value")
				 || request.getParameter("shedLength").equals("choose a value")) ? false : true;
	}

	/*
	 (request.getParameter("shedWidth").equals("")
				|| request.getParameter("shedLength").equals("")) ? false : true;
	 */

	private static void saveOrderLineDB(Order order) throws MaterialException, OrderLineException, DataException
	{
		BillOfMaterials billOfMaterials = new BillOfMaterials(order);
		billOfMaterials.createCarportListWithoutShed();
		billOfMaterials.saveOrderLinesToDB();
	}
}


