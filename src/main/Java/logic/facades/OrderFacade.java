package logic.facades;

import data.dao.MaterialDAO;
import data.dao.OrderDAO;
import data.dao.OrderLineDAO;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.exceptions.OrderLineException;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;

import java.util.List;

public interface OrderFacade
{
	List<Order> allOrdersWithoutShed() throws OrderException, DataException;

	Order createAndReturnOrder(Order order) throws DataException, OrderException;

	Material materialById(int id) throws DataException, MaterialException;

	void addEmployeeToOrder(int employeeId, int orderId) throws DataException;

	Order orderById(int orderId) throws DataException, OrderException;

	List<Order> ordersOfCustomer(int id) throws OrderException, DataException;

	List<Order> ordersAvailable() throws OrderException, DataException;

	void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException;

	List<Order> employeeChosenOrders(int employeeId) throws OrderException, DataException;

	void updateOrderOffer(Order order) throws OrderException, DataException;

	void createOrderLine(OrderLine orderLine) throws OrderLineException, DataException;


	OrderDAO getInstanceOrderDAO() throws DataException;

	MaterialDAO getInstanceMaterialDAO() throws DataException;

	OrderLineDAO getInstanceOrderLineDAO() throws DataException;
}
