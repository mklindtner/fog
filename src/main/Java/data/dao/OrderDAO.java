package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.Shed;
import data.entities.userEntities.Customer;
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
						   "(height, width, length, status, slope, customerId, materials_as_roof, shedId, created_at)" +
						   " VALUES(?, ?, ?, false, ?, ?, ?, ?, now()) ";
		try (PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			int setHeight = order.getHeight();
			int setWidth = order.getWidth();
			int setLength = order.getLength();
			int setSlope = order.getSlope();
			int setCusId = order.getCustomer().getId();
			int setMaterialId = order.getMaterial().getId();

			statement.setInt(1, setHeight);
			statement.setInt(2, setWidth);
			statement.setInt(3, setLength);
			statement.setInt(4, setSlope);
			statement.setInt(5, setCusId);
			statement.setInt(6, setMaterialId);
			statement.setNull(7, Types.INTEGER); //will this work if shed is null?
			statement.executeUpdate();
			ResultSet resultId = statement.getGeneratedKeys();
			resultId.next();
			return generateOrder(resultId.getInt(1));
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public void createOrder(
			int height, int width, int length, boolean status, int slope, int customerId, int materials_as_roof,
			int shedId)
			throws OrderException
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
			throw new OrderException(throwSql);
		}
	}

	//should fix so it can return with shed too, create that in ServiceDAO
	private Order generateOrder(int id) throws OrderException, DataException
	{
		final String SQL = "SELECT * FROM orders WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs       = statement.executeQuery();
			rs.next();
			int       height   = rs.getInt("height");
			int       width    = rs.getInt("width");
			int       length   = rs.getInt("length");
			int       slope    = rs.getInt("slope");
			Customer  customer = ServiceDAO.getCustomerById(rs.getInt("customerId"), con);
			Material  material = ServiceDAO.getMaterialById(rs.getInt("materials_as_roof"), con);
			Order order = new Order
					.OrderBuilder(id, rs.getString("created_at"))
					.createOrderWithoutShed(height, width, length, customer, slope, material)
					.build();
			return order;
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

}
