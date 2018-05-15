package view.servlets.orderServlets.helpers;

import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.exceptions.OrderLineException;
import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import logic.generators.BillOfMaterialsCalculator;

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
		//session.setAttribute("ordersAvailable", MySqlOrderFacade.ordersAvailable());
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
																								  DataException, OrderException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		Order order = createOrder(request, user);
		order = orderFacade.createAndReturnOrder(order);
		saveOrderLineDB(order);
		return order;
	}

	private static Order createOrder(HttpServletRequest request, Customer user)
	{
		// TODO: add shed
		int height     = Integer.parseInt(request.getParameter("height"));
		int width      = Integer.parseInt(request.getParameter("width"));
		int length     = Integer.parseInt(request.getParameter("length"));
		int slope      = Integer.parseInt(request.getParameter("slope"));
		int shedWidth  = Integer.parseInt(request.getParameter("shedWidth"));
		int shedLength = Integer.parseInt(request.getParameter("shedLength"));

		return new Order
				.OrderBuilder()
				.createOrderWithoutShed(height, width, length, user, slope)
				.build();
	}

	private static void saveOrderLineDB(Order order) throws MaterialException, OrderLineException, DataException
	{
		BillOfMaterialsCalculator billOfMaterialsCalculator = new BillOfMaterialsCalculator(order);
		billOfMaterialsCalculator.createCarportListWithoutShed();
		billOfMaterialsCalculator.saveOrderLinesToDB();
	}
}


