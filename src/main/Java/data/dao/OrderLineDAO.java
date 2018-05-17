package data.dao;

import data.MySqlConnector;
import entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.OrderLineException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderLineDAO
{
	private Connection con;

	public OrderLineDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public OrderLineDAO(String connectionSelection) throws DataException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public void createOrderLine(OrderLine orderLine) throws OrderLineException
	{
		final String SQL = "Insert into orderLines(amount, unit, description, orderId, materialId) VALUES (?, ?, ?, " +
						   "?, ?);";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, orderLine.getAmount());
			statement.setString(2, orderLine.getUnit());
			statement.setString(3, orderLine.getSecondDescription());
			statement.setInt(4, orderLine.getOrderId());
			statement.setInt(5, orderLine.getMaterialId());
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
