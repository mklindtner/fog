package view.servlets.orderServlets.helpers;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;

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

	public static void genreateOrdersAvailable(HttpSession session) throws OrderException, DataException {
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
}
