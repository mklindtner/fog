package data.dao;

import data.MySqlConnector;
import entities.OrderEntities.Material;
import data.exceptions.DataException;
import data.exceptions.MaterialException;

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
			return returnMaterial(rs);
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
			return returnMaterial(rs);

		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}

	private Material returnMaterial(ResultSet resultSet) throws MaterialException
	{
		try {
			if (resultSet.next()) {
				String description = resultSet.getString("description");
				int    pricePrUnit = resultSet.getInt("pricePrUnit");
				int id = resultSet.getInt("id");
				return new Material(description, pricePrUnit, id);
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}

	public Material createMaterial(String description, int pricePrUnit) throws MaterialException {
		final String SQL = "Insert Into materials(description, pricePrUnit) VALUES (?, ?)";
		try(PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, description);
			statement.setInt(2, pricePrUnit);
			statement.executeUpdate();
			ResultSet id = statement.getGeneratedKeys();
			id.next();
			return new Material(description, pricePrUnit, id.getInt(1));
		} catch(SQLException throwSql) {

			throw new MaterialException(throwSql);
		}
	}

	public Connection getCon()
	{
		return this.con;
	}
}
