package data.dao;

import configurations.Conf;
import data.MySqlConnector;
import data.exceptions.ShedException;
import entities.OrderEntities.Order;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class OrderDAO
{
	Connection con;


	public OrderDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public OrderDAO(String connectionSelection) throws DataException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public List<Order> allOrders() throws OrderException
	{
		final String SQL               = "Select * FROM orders";
		List<Order>  ordersWithoutShed = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ordersWithoutShed.add(returnOrder(rs));
			}
			return ordersWithoutShed;
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "[Exception] {0}", throwSql.getStackTrace().toString());
			throw new OrderException(throwSql);
		}
	}


	public List<Order> ordersOfCustomer(int id) throws OrderException
	{
		final String SQL           = "Select * FROM orders WHERE orders.customerId=? order by STATUS";
		List<Order>  customerOrder = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				customerOrder.add(returnOrder(rs));
			}
			return customerOrder;
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"ordersOfCustomer",
											  "find orders for customer"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	public Order createAndReturnOrder(Order order) throws OrderException, DataException
	{
		final String SQL = "INSERT INTO orders" +
						   "(height, width, length, status, slope, customerId, shedId, created_at, " +
						   "employeeId)" +
						   " VALUES(?, ?, ?, ?, ?, ?, ?, now(), ?) ";
		try (PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			int  setHeight = order.getHeight();
			int  setWidth  = order.getWidth();
			int  setLength = order.getLength();
			int  setSlope  = order.getSlope();
			int  setCusId  = order.getCustomer().getId();
			Shed shed      = order.getShed();

			statement.setInt(1, setHeight);
			statement.setInt(2, setWidth);
			statement.setInt(3, setLength);
			statement.setString(4, order.getStatus().name());
			statement.setInt(5, setSlope);
			statement.setInt(6, setCusId);

			if (shed != null)
				statement.setInt(7, order.getShed().getId());
			else
				statement.setNull(7, Types.INTEGER);

			statement.setNull(8, Types.INTEGER);
			statement.executeUpdate();

			ResultSet resultId = statement.getGeneratedKeys();
			resultId.next();
			return orderById(resultId.getInt(1));
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"createAndReturnOrder",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	public Order orderById(int id) throws OrderException, DataException
	{
		final String SQL = "SELECT * FROM orders WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			return returnOrder(rs);
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"orderById",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	private Order returnOrder(ResultSet rs) throws OrderException
	{
		try {
			int          id         = rs.getInt("id");
			String       created_at = rs.getString("created_at");
			int          height     = rs.getInt("height");
			int          width      = rs.getInt("width");
			int          length     = rs.getInt("length");
			int          slope      = rs.getInt("slope");
			Customer     customer   = UtilityDAO.getCustomerById(rs.getInt("customerId"), con);
			Order.Status status     = Order.Status.valueOf(rs.getString("status"));
			int          shedId     = rs.getInt("shedId");
			int          price      = rs.getInt("price");
			Shed         shed;

			if (shedId != 0) {
				shed = UtilityDAO.shedById(shedId, con);
				return new Order
						.OrderBuilder(id, created_at)
						.insertRequiredHeight(height)
						.insertRequiredLength(length)
						.insertRequiredWidth(width)
						.insertRequiredSlope(slope)
						.insertRequiredCustomer(customer)
						.insertOptionalStatus(status)
						.insertOptionalShed(shed)
						.insertOptionalPrice(price)
						.build();
			}
			return new Order
					.OrderBuilder(id, created_at)
					.insertRequiredHeight(height)
					.insertRequiredLength(length)
					.insertRequiredWidth(width)
					.insertRequiredSlope(slope)
					.insertRequiredCustomer(customer)
					.insertOptionalStatus(status)
					.insertOptionalPrice(price)
					.build();

		} catch (SQLException | ShedException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "[{0}] were unable to {1}",
								 new Object[]{"returnOrder",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	public void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException
	{
		final String SQL = "update orders SET status=? where orders.id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, "OFFER");
			statement.setInt(2, orderId);
			statement.executeUpdate();
			addEmployeeToOrder(employeeId, orderId);
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"employeeChooseOrder",
											  "find employee or order"
								 }
			);
			throw new OrderException(throwSql);
		}
	}

	public List<Order> ordersAvailable() throws OrderException
	{
		List<Order>  ordersAvailable = new ArrayList<>();
		final String SQL             = "select * from orders where status=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, "PENDING");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ordersAvailable.add(returnOrder(rs));
			}
			return ordersAvailable;
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"ordersAvailable",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	public void addEmployeeToOrder(int employeeId, int ordersId) throws DataException
	{
		final String SQL = "update orders set employeeId=? where orders.id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, employeeId);
			statement.setInt(2, ordersId);
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"addEmployeeToOrder",
											  "execute"
								 }
			);
			throw new DataException(throwSql);
		}
	}


	public List<Order> employeesChosenOrders(int employeeId) throws OrderException
	{
		List<Order>  employeeOrders = new ArrayList<>();
		final String SQL            = "select * FROM orders WHERE orders.employeeId=? order by status";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, employeeId);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				employeeOrders.add(returnOrder(rs));
			return employeeOrders;
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"employeesChosenOrders",
											  "find employeeOrders"
								 }
			);

			throw new OrderException(throwSql);
		}
	}


	public void updateOrderOffer(Order order) throws OrderException
	{
		final String SQL = "UPDATE orders SET height=?, width=?, length=?, slope=?, price=?, status=? WHERE orders.id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, order.getHeight());
			statement.setInt(2, order.getWidth());
			statement.setInt(3, order.getLength());
			statement.setInt(4, order.getSlope());
			statement.setInt(5, order.getPrice());
			statement.setString(6, order.getStatus().name());
			statement.setInt(7, order.getId());
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"updateOrderOffer",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}


	public int findCustomerIdByOrder(Order order) throws OrderException
	{
		final String SQL = "Select customerId FROM orders WHERE orders.customerId = ?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, order.getCustomer().getId());
			ResultSet rs = statement.executeQuery();
			rs.next();
			int customerId = rs.getInt("customerId");
			return customerId;
		} catch (SQLException throwSql) {
			Conf.getLogger().log(Level.SEVERE, "{0} were unable to {1}",
								 new Object[]{"findCustomerIdByOrder",
											  "execute"
								 }
			);
			throw new OrderException(throwSql);
		}
	}

	public void deleteOrder(int orderId) throws OrderException
	{
		final String SQL = "delete from orders WHERE orders.id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, orderId);
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public Connection getCon()
	{
		return this.con;
	}

}
