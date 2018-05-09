package data.dao;

import data.MySqlConnector;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.MaterialDimensions;
import data.entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;

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

	public Material getMaterialById(int id) throws OrderException
	{
		final String SQL = "Select * FROM materials WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int                price          = rs.getInt("price");
				String             type           = rs.getString("type");
				int                materialAmount = rs.getInt("materialAmount");
				String             packageType    = rs.getString("packageType");
				int                height         = rs.getInt("height");
				int                width          = rs.getInt("width");
				int                length         = rs.getInt("length");
				String             description    = rs.getString("description");
				String             treatment      = rs.getString("treatment");
				MaterialDimensions dimensions     = new MaterialDimensions(height, width, length);
				return new Material
						.MaterialBuilder(id)
						.insertType(type)
						.insertPrice(price)
						.insertMaterialAmount(materialAmount)
						.insertOptionalTreatment(treatment)
						.insertOptionalDescription(description)
						.insertOptionalDimensions(dimensions)
						.insertOptionalPackageType(packageType)
						.build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new OrderException(throwSql);
		}
	}

	public Material getMaterialByName(String type) throws MaterialException
	{
		final String SQL = "Select * FROM materials WHERE type=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, type);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int                price          = rs.getInt("price");
				int                id             = rs.getInt("id");
				int                height         = rs.getInt("height");
				int                width          = rs.getInt("width");
				int                length         = rs.getInt("length");
				String             packageType    = rs.getString("packageType");
				int                materialAmount = rs.getInt("materialAmount");
				String             treatment       = rs.getString("treatment");
				String             description    = rs.getString("description");
				MaterialDimensions dimensions     = new MaterialDimensions(height, width, length);
				return new Material
						.MaterialBuilder(id)
						.createCompleteMaterial(price, type, materialAmount, dimensions, packageType, treatment, description)
						.build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}
/*
	public Material createAndReturnMaterial(
			int price, String type, MaterialDimensions dimension, String packageType, int
			materialAmount) throws DataException
	{
		final String SQL = "INSERT INTO materials(height, width, length, packageType, materialAmount, price, type) "
						   + "VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			int dimensionHeight = dimension.getHeight();
			int dimensionWidth  = dimension.getWidth();
			int dimensionLength = dimension.getLength();

			statement.setInt(1, dimensionHeight);
			statement.setInt(2, dimensionWidth);
			statement.setInt(3, dimensionLength);
			statement.setString(4, packageType);
			statement.setInt(5, materialAmount);
			statement.setInt(6, price);
			statement.setString(7, type);
			statement.executeUpdate();
			ResultSet ids = statement.getGeneratedKeys();
			int       id  = ids.getInt(1);
			return new Material
					.MaterialBuilder(id)
					.createCompleteMaterial(price, type, materialAmount, dimension, packageType)
					.build();
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}*/

	//this for now
	public Material materialByTypeWidthLength(String type, int width, int length) throws MaterialException
	{
		String SQL = "Select * FROM materials WHERE TYPE=? AND width=? AND length=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, type);
			statement.setInt(2, width);
			statement.setInt(3, length);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int                price          = rs.getInt("price");
				int                materialAmount = rs.getInt("materialAmount");
				String             packageType    = rs.getString("packageType");
				int                height         = rs.getInt("height");
				int                id             = rs.getInt("id");
				String             treatment      = rs.getString("treatment");
				String             description    = rs.getString("description");
				MaterialDimensions dimensions     = new MaterialDimensions(height, width, length);
				return new Material
						.MaterialBuilder(id)
						.insertType(type)
						.insertPrice(price)
						.insertMaterialAmount(materialAmount)
						.insertOptionalDimensions(dimensions)
						.insertOptionalPackageType(packageType)
						.insertOptionalTreatment(treatment)
						.insertOptionalDescription(description)
						.build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new MaterialException(throwSql);
		}
	}
}
