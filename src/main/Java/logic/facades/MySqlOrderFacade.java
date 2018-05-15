package logic.facades;

import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.OrderLineDAO;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.exceptions.OrderLineException;

import java.util.List;

public class MySqlOrderFacade implements OrderFacade
{
	private OrderDAO orderDAO;
	private MaterialDAO materialDAO;
	private OrderLineDAO orderLineDAO;

	public OrderDAO getInstanceOrderDAO() throws DataException {
		if(orderDAO == null)
			orderDAO = new OrderDAO();
		return orderDAO;
	}

	public MaterialDAO getInstanceMaterialDAO() throws DataException{
		if(materialDAO == null)
			materialDAO = new MaterialDAO();
		return materialDAO;
	}

	public OrderLineDAO getInstanceOrderLineDAO() throws DataException {
		if(orderLineDAO == null)
			orderLineDAO = new OrderLineDAO();
		return orderLineDAO;
	}

	public List<Order> allOrdersWithoutShed() throws OrderException, DataException
	{
		return orderDAO.allOrdersWithoutShed();
	}

	public Order createAndReturnOrder(Order order) throws DataException, OrderException
	{
		return orderDAO.createAndReturnOrder(order);
	}

	public Material materialById(int id) throws MaterialException
	{
		return materialDAO.materialById(id);
	}

	public void addEmployeeToOrder(int employeeId, int orderId) throws DataException
	{
		orderDAO.addEmployeeToOrder(employeeId, orderId);
	}

	public Order orderById(int orderId) throws DataException, OrderException
	{
		return orderDAO.orderByIdWithoutShed(orderId);
	}

	public List<Order> ordersOfCustomer(int id) throws OrderException
	{
		return orderDAO.ordersOfCustomer(id);
	}

	public List<Order> ordersAvailable() throws OrderException
	{
		return orderDAO.ordersAvailable();
	}

	public void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException
	{
		orderDAO.employeeChooseOrder(employeeId, orderId);
	}

	public List<Order> employeeChosenOrders(int employeeId) throws OrderException, DataException
	{
		return orderDAO.employeesChosenOrders(employeeId);
	}

	public void updateOrderOffer(Order order) throws OrderException, DataException
	{
		orderDAO.updateOrderOffer(order);
	}

	public void createOrderLine(OrderLine orderLine) throws OrderLineException, DataException
	{
		orderLineDAO.createOrderLine(orderLine);
	}

}
