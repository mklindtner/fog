package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;

import javax.xml.transform.Result;
import java.sql.*;

public class MaterialDAO
{
	private Connection con;

	public MaterialDAO() throws DataAccessException {
		con = MySqlConnector.createConnection("APP");
	}

	public MaterialDAO(String connectionSelection) throws  DataAccessException {
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public Material getMaterialById(int id) throws OrderAccessException
	{
		final String SQL = "Select * FROM materials WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int    price             = rs.getInt("price");
				String typeSpecification = rs.getString("typeSpecification");
				String type              = rs.getString("type");
				return new Material.MaterialBuilder(id).createRequiredMaterial(price, typeSpecification, type).build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new OrderAccessException(throwSql);
		}
	}

	public Material createAndReturnMaterial(int price, String typeSpecification, String type) throws DataAccessException
	{
		final String SQL = "INSERT INTO materials(price, typeSpecification, type) VALUES(?, ?, ?)";
		try(PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, price);
			statement.setString(2, typeSpecification);
			statement.setString(3, type);
			statement.executeUpdate();
			ResultSet ids = statement.getGeneratedKeys();
			ids.next();
			int id = ids.getInt(1);
			return new Material
					.MaterialBuilder(ids.getInt(1))
					.createRequiredMaterial(price, typeSpecification, type)
					.build();
		} catch( SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
 	}
}
