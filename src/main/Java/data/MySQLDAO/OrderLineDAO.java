package data.MySQLDAO;

import data.MySqlConnector;
import data.exceptions.MaterialException;
import entities.OrderEntities.Material;
import entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.OrderLineException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDAO implements DAO
{
	private Connection con;


	public OrderLineDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public OrderLineDAO(Connection currentCon)
	{
		this.con = currentCon;
	}

	public OrderLineDAO(String connectionSelection) throws DataException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public void createOrderLine(OrderLine orderLine) throws OrderLineException
	{
		final String SQL = "Insert into orderLines(amount, unit, description, orderId, materialId, length, " +
						   "isTreeOrRoof) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			int amount = orderLine.getAmount();
			String unit = orderLine.getUnit();
			String secondDescription = orderLine.getSecondDescription();
			int orderId = orderLine.getOrderId();
			int materialId = orderLine.getMaterialId();
			int length = orderLine.getLength();
			boolean isTreeOrRoof = orderLine.isTreeOrRoof();

			statement.setInt(1, amount);
			statement.setString(2, unit);
			statement.setString(3, secondDescription);
			statement.setInt(4, orderId);
			statement.setInt(5, materialId);
			statement.setInt(6, length);
			statement.setBoolean(7, isTreeOrRoof);
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			throw new OrderLineException(throwSql);
		}
	}

	public void updateOrderLineAmount(int orderLineId, int amount) throws OrderLineException
	{
		final String SQL = "UPDATE orderLines SET amount=? WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, amount);
			statement.setInt(2, orderLineId);
			statement.executeUpdate();
		} catch (SQLException throwSql) {
			throw new OrderLineException(throwSql);
		}
	}

	public List<OrderLine> orderLineByOrderId(int orderId) throws OrderLineException, MaterialException
	{
		List<OrderLine> orderLines = new ArrayList<>();
		final String SQL = "Select * FROM orderLines WHERE orderLines.orderId=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, orderId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				orderLines.add(returnOrderLine(rs));
			}
			return orderLines;
		} catch (SQLException throwSql) {
			throw new OrderLineException(throwSql);
		}
	}

	private OrderLine returnOrderLine(ResultSet resultSet) throws OrderLineException, MaterialException
	{
		OrderLine orderLine;
		try {
			int id = resultSet.getInt("id");
			int amount = resultSet.getInt("amount");
			String unit = resultSet.getString("unit");
			String description = resultSet.getString("description");
			int orderId = resultSet.getInt("orderId");
			int materialId = resultSet.getInt("materialId");
			int length = resultSet.getInt("length");
			boolean isTreeOrRoof = resultSet.getBoolean("isTreeOrRoof");
			Material material = UtilityDAO.materialById(materialId, con);

			return new OrderLine
					.OrderLineBuilder()
					.insertAmount(amount)
					.insertLength(length)
					.insertUnit(unit)
					.insertFirstDescription(material.getDescription())
					.insertSecondDescription(description)
					.insertPriceForOrderLine(amount * material.getPricePrUnit())
					.insertIsTreeOrRoof(isTreeOrRoof)
					.insertMaterialId(materialId)
					.insertOrderId(orderId)
					.insertOrderLineId(id)
					.build();
		} catch(SQLException throwSql) {
			throw new OrderLineException(throwSql);
		}
	}

	public void deleteOrderLineByOrderId(int orderId) throws OrderLineException
	{
		final String SQL = "delete from orderLines WHERE orderLines.orderId=?";
		try(PreparedStatement statement = con.prepareStatement(SQL))
		{
			statement.setInt(1, orderId);
			statement.executeUpdate();
		} catch(SQLException throwSql) {
			throw new OrderLineException(throwSql);
		}
	}

	public Connection getCon()
	{
		return this.con;
	}
}
