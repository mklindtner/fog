package logic.generators.facades;

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

/**
 * The logic-facade for any order-DAO operation, the data Layer and Presentation layer both goes through this layer
 * when sending/recieving information, consider each layer closed
 * This interface uses multiple DAO's for mysql, but an implementation could decide to only use one if desired
 */
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

	Shed createShed(int shedLength, int shedWidth, boolean hasFloor) throws DataException, ShedException;

	void updateOrderLineAmount(int orderLineId, int amount) throws OrderLineException;

	List<OrderLine> orderLinesByOrderId(int orderId) throws OrderLineException, MaterialException;

	void deleteOrder(int orderId) throws OrderException;

	void deleteOrderLineByOrderId(int orderId) throws OrderLineException;

	List<Material> allMaterials() throws MaterialException;

	OrderDAO getInstanceOrderDAO() throws DataException;

	OrderDAO getInstanceOrderDAO(String connectionString) throws DataException;

	MaterialDAO getInstanceMaterialDAO() throws DataException;

	MaterialDAO getInstanceMaterialDAO(String connctionString) throws DataException;

	OrderLineDAO getInstanceOrderLineDAO() throws DataException;

	OrderLineDAO getInstanceOrderLineDAO(String connectionString) throws DataException;

	ShedDAO getInstanceShedDAO() throws DataException;

	ShedDAO getInstanceShedDAO(String connectionString) throws DataException;

	Order orderOrderLine(Order order, List<OrderLine> orderLine) throws OrderException, DataException,
																	   OrderLineException;

}
