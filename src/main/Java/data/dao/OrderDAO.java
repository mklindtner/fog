package data.dao;

import data.MySqlConnector;
import entities.OrderEntities.Order;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.ShedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

	public List<Order> allOrdersWithoutShed() throws OrderException, DataException
	{
		final String SQL               = "Select * FROM orders";
		List<Order>  ordersWithoutShed = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ordersWithoutShed.add(returnOrderWithoutShed(rs));
			}
			return ordersWithoutShed;
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	private Order returnOrderWithoutShed(ResultSet rs) throws OrderException
	{
		try {
			int          id         = rs.getInt("id");
			String       created_at = rs.getString("created_at");
			int          height     = rs.getInt("height");
			int          width      = rs.getInt("width");
			int          length     = rs.getInt("length");
			int          slope      = rs.getInt("slope");
			Customer     customer   = ServiceDAO.getCustomerById(rs.getInt("customerId"), con);
			Order.Status status     = Order.Status.valueOf(rs.getString("status"));
			return new Order
					.OrderBuilder(id, created_at)
					.createOrderWithoutShed(height, width, length, customer, slope)
					.insertOptionalStatus(status)
					.build();
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public List<Order> ordersOfCustomer( int id ) throws OrderException {
		final String SQL = "Select * FROM orders WHERE orders.customerId=? order by STATUS";
		List<Order> customerOrder = new ArrayList<>();
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				customerOrder.add(returnOrderWithoutShed(rs));
			}
			return customerOrder;
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}


	public Shed createAndReturnShed(int length, int width, int height, boolean hasFloor) throws ShedException
	{
		final String SQL = "Insert INTO sheds(length, width, height, hasFloor) VALUES(?, ?, ?, ?)";
		try (PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, length);
			statement.setInt(2, width);
			statement.setInt(3, height);
			statement.setBoolean(4, hasFloor);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			return new Shed
					.ShedBuilder(rs.getInt(1), hasFloor)
					.insertMinimumRequiredShed(length, width, height)
					.build();
		} catch (SQLException throwSql) {
			throw new ShedException(throwSql);
		}
	}

	public Order createAndReturnOrder(Order order) throws OrderException, DataException
	{
		final String SQL = "INSERT INTO orders" +
						   "(height, width, length, status, slope, customerId, shedId, created_at, " +
						   "employeeId)" +
						   " VALUES(?, ?, ?, ?, ?, ?, ?, now(), ?) ";
		try (PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			int setHeight = order.getHeight();
			int setWidth  = order.getWidth();
			int setLength = order.getLength();
			int setSlope  = order.getSlope();
			int setCusId  = order.getCustomer().getId();

			statement.setInt(1, setHeight);
			statement.setInt(2, setWidth);
			statement.setInt(3, setLength);
			statement.setString(4, order.getStatus().name());
			statement.setInt(5, setSlope);
			statement.setInt(6, setCusId);
			statement.setNull(7, Types.INTEGER);
			statement.setNull(8, Types.INTEGER);
			statement.executeUpdate();

			ResultSet resultId = statement.getGeneratedKeys();
			resultId.next();
			return orderByIdWithoutShed(resultId.getInt(1));
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	//should fix so it can return with shed too, create that in ServiceDAO
	public Order orderByIdWithoutShed(int id) throws OrderException, DataException
	{
		final String SQL = "SELECT * FROM orders WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			return returnOrderWithoutShed(rs);
			/*
			int          height   = rs.getInt("height");
			int          width    = rs.getInt("width");
			int          length   = rs.getInt("length");
			int          slope    = rs.getInt("slope");
			Customer     customer = ServiceDAO.getCustomerById(rs.getInt("customerId"), con);
			Order.Status status   = Order.Status.valueOf(rs.getString("status"));
			Order order = new Order
					.OrderBuilder(id, rs.getString("created_at"))
					.createOrderWithoutShed(height, width, length, customer, slope)
					.insertOptionalStatus(status)
					.build();
			return order; */
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public void employeeChooseOrder(int employeeId, int orderId) throws OrderException, DataException{
		final String SQL = "update orders SET status=? where orders.id=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, "OFFER");
			statement.setInt(2, orderId);
			statement.executeUpdate();
			addEmployeeToOrder(employeeId, orderId);
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public List<Order> ordersAvailable() throws OrderException {
		List<Order> ordersAvailable = new ArrayList<>();
		final String SQL = "select * from orders where status=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, "PENDING");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ordersAvailable.add(returnOrderWithoutShed(rs));
			}
			return ordersAvailable;
		} catch (SQLException throwSql) {
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
			throw new DataException(throwSql);
		}
	}

	public void createOrder(
			int height, int width, int length, boolean status, int slope, int customerId, int shedId) throws OrderException
	{
		final String SQL = "INSERT INTO orders" +
						   "(height, width, length, status, slope, customerId, materials_as_roof, shedId, created_at)" +
						   " VALUES(?, ?, ?, false, ?, ?, ?, ?, now()) ";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, height);
			statement.setInt(2, width);
			statement.setInt(3, length);
			statement.setInt(4, slope);
			statement.setInt(5, customerId);
			statement.setInt(7, shedId); //will this work if shed is null?
			statement.executeUpdate();
			//ResultSet rs = statement.getGeneratedKeys();
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public List<Order> employeesChosenOrders( int employeeId ) throws OrderException {
		List<Order> employeeOrders = new ArrayList<>();
		final String SQL = "select * FROM orders WHERE orders.employeeId=? order by status";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, employeeId);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
				employeeOrders.add(returnOrderWithoutShed(rs));
			return employeeOrders;
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}


	public void updateOrderOffer( Order order ) throws OrderException {
		final String SQL = "UPDATE orders SET height=?, width=?, length=?, slope=?, price=?, status=? WHERE orders.id=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, order.getHeight());
			statement.setInt(2, order.getWidth());
			statement.setInt(3, order.getLength());
			statement.setInt(4, order.getSlope());
			statement.setInt(5, order.getPrice());
			statement.setString(6, order.getStatus().name());
			statement.setInt(7, order.getId());
			statement.executeUpdate();
		} catch(SQLException throwSql) {
			throw new OrderException( throwSql );
		}
	}
}
