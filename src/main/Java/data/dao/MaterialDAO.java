package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.MaterialDimensions;
import data.entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;

import javax.xml.transform.Result;
import java.awt.*;
import java.sql.*;

public class MaterialDAO
{
	private Connection con;

	public MaterialDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public MaterialDAO(String connectionSelection) throws DataException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public Material materialById(int materialId) throws MaterialException
	{
		final String SQL = "Select * FROM materials WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, materialId);
			ResultSet rs = statement.executeQuery();
			return createMaterial(rs);
		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}

	public Material materialByDescription(String description) throws MaterialException
	{
		final String SQL = "Select * FROM materials WHERE materials.description=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, description);
			ResultSet rs = statement.executeQuery();
			return createMaterial(rs);

		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}

	private Material createMaterial(ResultSet resultSet) throws MaterialException
	{
		try {
			if (resultSet.next()) {
				String description = resultSet.getString("description");
				int    pricePrUnit = resultSet.getInt("pricePrUnit");
				return new Material(description, pricePrUnit);
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}

	/*
	public static void saveOrderLineDB(int orderId, int materialId) throws MaterialException {
		final String SQL = "";
	} */
}
