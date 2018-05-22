package view.servlets.orderServlets.helpers;

import data.exceptions.*;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import logic.facades.MySqlOrderFacade;
import logic.facades.MySqlUserFacade;
import logic.facades.OrderFacade;
import logic.facades.UserFacade;
import logic.generators.BillOfMaterials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateOrderList
{
	public static void generateEmployeeOrders(HttpSession session, Employee employee) throws OrderException,
																							 DataException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		List<Order> employeeOrders = orderFacade.employeeChosenOrders(employee.getId());
		session.setAttribute("employeeOrders", employeeOrders);
	}

	public static void generateOrdersAvailable(HttpSession session) throws OrderException, DataException
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
		Shed  shed;
		Order order = createOrder(request, user);
		if (haveShed(request)) {
			shed = createShed(request, orderFacade);
			order.setshed(shed);
		}
		order = orderFacade.createAndReturnOrder(order);
		saveOrderLineDB(order);
		return order;
	}

	public static void setOrderForSession(HttpServletRequest request) throws OrderException, DataException
	{
		String id_string = request.getParameter("orderId");
		int    orderId;
		orderId = Integer.parseInt(id_string);
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		request.getSession().setAttribute("order", orderFacade.orderById(orderId));
	}

	public static void updateOrderLinesSession(HttpServletRequest request, Order order) throws DataException,
																							   MaterialException,
																							   OrderLineException
	{
		HttpSession session = request.getSession();
		OrderFacade facade = new MySqlOrderFacade();
		facade.getInstanceOrderLineDAO();

		List<OrderLine> orderLines = facade.orderLinesByOrderId(order.getId());
		order.setOrderLines(orderLines);
		session.setAttribute("billOfMaterial", orderLines);
		session.setAttribute("totalPrice", order.fullPriceOfOrder());
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
		int height = Integer.parseInt(request.getParameter("height"));
		int width  = Integer.parseInt(request.getParameter("width"));
		int length = Integer.parseInt(request.getParameter("length"));
		int slope  = Integer.parseInt(request.getParameter("slope"));

		return new Order
				.OrderBuilder()
				.createOrderWithoutShed(height, width, length, user, slope)
				.build();
	}


	private static boolean haveShed(HttpServletRequest request)
	{
		return (request.getParameter("shedWidth").equals("None")
				|| request.getParameter("shedLength").equals("None")) ? false : true;
	}

	private static void saveOrderLineDB(Order order) throws MaterialException, OrderLineException, DataException
	{
		BillOfMaterials billOfMaterials = new BillOfMaterials(order);
		if(order.getShed() != null)
			billOfMaterials.createCarportList();
		else
			billOfMaterials.createCarportListWithoutShed();
		billOfMaterials.saveOrderLinesToDB("APP");
	}

}


