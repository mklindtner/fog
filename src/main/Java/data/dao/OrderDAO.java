package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.Shed;
import data.entities.userEntities.Customer;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;
import data.exceptions.ShedCreationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO
{
	Connection con;


	public OrderDAO() throws DataAccessException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public OrderDAO(String connectionSelection) throws DataAccessException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public List<Order> allOrdersWithoutShed() throws OrderAccessException, DataAccessException
	{
		final String SQL               = "Select * FROM orders";
		List<Order>  ordersWithoutShed = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int      id         = rs.getInt("id");
				String   created_at = rs.getString("created_at");
				int      height     = rs.getInt("height");
				int      width      = rs.getInt("width");
				int      length     = rs.getInt("length");
				int      slope      = rs.getInt("slope");
				Material material   = ServiceDAO.getMaterialById(rs.getInt("materials_as_roof"), con);
				Customer customer   = ServiceDAO.getCustomerById(rs.getInt("customerId"), con);
				Order order = new Order
						.OrderBuilder(id, created_at)
						.createOrderWithoutShed(height, width, length, customer, slope, material)
						.build();
				ordersWithoutShed.add(order);
			}
			return ordersWithoutShed;
		} catch (SQLException throwSql) {
			throw new OrderAccessException(throwSql);
		}
	}

	public Shed createAndReturnShed(int length, int width, int height, boolean hasFloor) throws ShedCreationException
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
			throw new ShedCreationException(throwSql);
		}
	}

	public void createOrder(Order order) throws OrderAccessException
	{
		final String SQL = "INSERT INTO orders" +
						   "(height, width, length, status, slope, customerId, materials_as_roof, shedId, created_at)" +
						   " VALUES(?, ?, ?, false, ?, ?, ?, ?, now()) ";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, order.getHeight());
			statement.setInt(2, order.getWidth());
			statement.setInt(3, order.getLength());
			statement.setInt(4, order.getSlope());
			statement.setInt(5, order.getCustomer().getId());
			statement.setInt(6, order.getMaterial().getId());
			statement.setInt(7, order.getShed().getId()); //will this work if shed is null?
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			throw new OrderAccessException(throwSql);
		}
	}


	public void createOrder(
			int height, int width, int length, boolean status, int slope, int customerId, int materials_as_roof,
			int shedId)
			throws OrderAccessException
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
			statement.setInt(6, materials_as_roof);
			statement.setInt(7, shedId); //will this work if shed is null?
			statement.executeUpdate();
			//ResultSet rs = statement.getGeneratedKeys();
		} catch (SQLException throwSql) {
			throw new OrderAccessException(throwSql);
		}
	}

}
