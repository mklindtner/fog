package data.dao;

import data.entities.OrderEntities.Material;
import data.entities.userEntities.Customer;
import data.exceptions.OrderAccessException;
import data.exceptions.UserAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO
{
	public static Customer createCustomerById(int id, Connection con) throws OrderAccessException
	{
		String SQL = "Select * FROM customers WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String reg_date = rs.getString("reg_date");
				int    phone    = rs.getInt("phone");
				return new Customer(username, password, reg_date, phone, id);
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new OrderAccessException("Error within createCustomerInsideOrderDAO", throwSql);
		}

	}

	public static Material createMaterialById(int id, Connection con) throws OrderAccessException
	{
		String SQL = "Select * FROM materials WHERE id=?";
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

	public static String createRole(int roleId, Connection con) throws UserAccessException
	{
		String SQL = "Select * FROM roles WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, roleId);
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				return rs.getString("role");
			throw new SQLException();
		}catch(SQLException throwSql) {
			throw new UserAccessException(throwSql);
		}
	}
}
