package logic.generators.facades;

import configurations.Conf;
import data.MySQLDAO.MaterialDAO;
import data.MySQLDAO.OrderDAO;
import data.MySQLDAO.OrderLineDAO;
import data.MySQLDAO.ShedDAO;
import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import entities.OrderEntities.Shed;

import java.util.List;
import java.util.logging.Level;

public class OrderFacadeImpl implements OrderFacade
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

	public OrderDAO getInstanceOrderDAO(String connectionString) throws DataException {
		if(orderDAO == null)
			orderDAO = new OrderDAO(connectionString);
		return orderDAO;
	}

	public MaterialDAO getInstanceMaterialDAO() throws DataException{
		if(materialDAO == null)
			materialDAO = new MaterialDAO();
		return materialDAO;
	}

	public MaterialDAO getInstanceMaterialDAO(String connectionString) throws DataException{
		if(materialDAO == null)
			materialDAO = new MaterialDAO(connectionString);
		return materialDAO;
	}

	public OrderLineDAO getInstanceOrderLineDAO() throws DataException {
		if(orderLineDAO == null)
			orderLineDAO = new OrderLineDAO();
		return orderLineDAO;
	}

	public OrderLineDAO getInstanceOrderLineDAO(String connectionString) throws DataException {
		if(orderLineDAO == null)
			orderLineDAO = new OrderLineDAO(connectionString);
		return orderLineDAO;
	}

	public ShedDAO getInstanceShedDAO() throws DataException {
		if(shedDAO == null)
			shedDAO = new ShedDAO();
		return shedDAO;
	}

	public ShedDAO getInstanceShedDAO(String connectionString) throws DataException {
		if(shedDAO == null)
			shedDAO = new ShedDAO();
		return shedDAO;
	}

	public List<Order> allOrdersWithoutShed() throws OrderException
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

	public List<Order> ordersAvailable()
	{
		try {
			return orderDAO.ordersAvailable();
		} catch(OrderException finalDist)
		{
			Conf.getLogger().log(Level.SEVERE, "[RUNTIME ERROR] " + finalDist.getMessage());
			throw new data.exceptions.ApplicationException(finalDist);
		}
	}

	public void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException
	{
		orderDAO.employeeChooseOrder(employeeId, orderId);
	}

	public List<Order> employeeChosenOrders(int employeeId) throws OrderException
	{
		return orderDAO.employeesChosenOrders(employeeId);
	}

	public void updateOrderOffer(Order order) throws OrderException
	{
		orderDAO.updateOrderOffer(order);
	}

	public void createOrderLine(OrderLine orderLine) throws OrderLineException
	{
		orderLineDAO.createOrderLine(orderLine);
	}

	public Shed createShed(int shedLength, int shedWidth, boolean hasFloor) throws ShedException
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

	public Order orderOrderLine(Order order, List<OrderLine> orderLine) throws OrderException, DataException,
																		 OrderLineException
	{
		return orderDAO.createOrderTest(order, orderLine);
	}

}
