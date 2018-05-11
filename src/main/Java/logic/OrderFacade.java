package logic;

import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.OrderLineDAO;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.exceptions.OrderLineException;

import java.util.List;

public class OrderFacade
{
	public static List<Order> allOrdersWithoutShed() throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.allOrdersWithoutShed();
	}

	public static Order createAndReturnOrder(Order order) throws DataException, OrderException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.createAndReturnOrder(order);
	}

	public static Material materialById(int id) throws DataException, MaterialException
	{
		MaterialDAO materialDAO = new MaterialDAO();
		return materialDAO.materialById(id);
	}

	public static void addEmployeeToOrder(int employeeId, int orderId) throws DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.addEmployeeToOrder(employeeId, orderId);
	}

	public static Order orderById(int orderId) throws DataException, OrderException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.orderByIdWithoutShed(orderId);
	}

	public static List<Order> ordersOfCustomer(int id) throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.ordersOfCustomer(id);
	}

	public static List<Order> ordersAvailable() throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.ordersAvailable();
	}

	public static void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.employeeChooseOrder(employeeId, orderId);
	}

	public static List<Order> employeeChosenOrders(int employeeId) throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.employeesChosenOrders(employeeId);
	}

	public static void updateOrderOffer(Order order) throws OrderException, DataException
	{
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.updateOrderOffer(order);
	}

	public static void createOrderLine(OrderLine orderLine) throws OrderLineException, DataException
	{
		OrderLineDAO orderLineDAO = new OrderLineDAO();
		orderLineDAO.createOrderLine(orderLine);
	}

}
