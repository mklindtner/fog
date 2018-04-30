package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO
{
	Connection con;

	public OrderDAO() throws DataAccessException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public List<Order> allOrdersWithoutShed() throws OrderAccessException
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
				Material material   = ServiceDAO.createMaterialById(rs.getInt("materials_as_roof"), con);
				Customer customer   = ServiceDAO.createCustomerById(rs.getInt("customerId"), con);
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

	public void createOrder( Order order ) throws OrderAccessException {
		final String SQL = "INSERT INTO orders" +
						   "(height, width, length, status, slope, customerId, materials_as_roof, shedId, created_at)" +
						   " VALUES(?, ?, ?, false, ?, ?, ?, ?, now()) ";
		try( PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt( 1, order.getHeight());
			statement.setInt( 2, order.getWidth());
			statement.setInt( 3, order.getLength());
			statement.setInt( 4, order.getSlope());
			statement.setInt( 5, order.getCustomer().getId());
			statement.setInt(6, order.getMaterial().getId());
			statement.setInt(7, order.getShed().getId() ); //will this work if shed is null?
			statement.executeUpdate();
		}catch(SQLException throwSql) {
			throw new OrderAccessException(throwSql);
		}


	}

}
