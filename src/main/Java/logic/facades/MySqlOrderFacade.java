package logic.facades;

import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.OrderLineDAO;
import data.dao.ShedDAO;
import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;

import java.util.List;

public class MySqlOrderFacade implements OrderFacade
{
	private OrderDAO     orderDAO;
	private MaterialDAO  materialDAO;
	private OrderLineDAO orderLineDAO;
	private ShedDAO      shedDAO;

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

	public ShedDAO getInstanceShedDAO() throws DataException {
		if(shedDAO == null)
			shedDAO = new ShedDAO();
		return shedDAO;
	}

	public List<Order> allOrdersWithoutShed() throws OrderException, DataException
	{
		return orderDAO.allOrders();
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
		return orderDAO.orderById(orderId);
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

	public Shed createShed(int shedLength, int shedWidth, boolean hasFloor) throws DataException, ShedException
	{
		return shedDAO.createAndReturnShed(shedLength, shedWidth, hasFloor);
	}

	public void updateOrderLineAmount(int orderLineId, int amount) throws OrderLineException
	{
		orderLineDAO.updateOrderLineAmount(orderLineId, amount);
	}

	public List<OrderLine> orderLinesByOrderId(int orderId) throws OrderLineException, MaterialException
	{
		return orderLineDAO.orderLineByOrderId(orderId);
	}

	public void deleteOrder(int orderId) throws OrderException {
		orderDAO.deleteOrder(orderId);
	}

	public void deleteOrderLineByOrderId(int orderId) throws OrderLineException
	{
		orderLineDAO.deleteOrderLineByOrderId(orderId);
	}

	public List<Material> allMaterials() throws MaterialException {
		return materialDAO.allMaterials();
	}

}
