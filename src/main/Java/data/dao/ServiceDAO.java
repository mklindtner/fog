package data.dao;

import data.entities.OrderEntities.Material;
import data.entities.userEntities.Customer;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;
import data.exceptions.UserAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO
{
	public static Customer getCustomerById(int id, Connection con) throws OrderAccessException
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
				return new Customer.CustomerBuilder(id, reg_date).createSimpleCustomer(username, password, phone).build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new OrderAccessException("Error within createCustomerInsideOrderDAO", throwSql);
		}

	}

	public static Material getMaterialById(int id, Connection con) throws OrderAccessException, DataAccessException
	{
		MaterialDAO materialDAO = new MaterialDAO();
		return materialDAO.getMaterialById( id );
	}


	public static String getRole(int roleId, Connection con) throws UserAccessException
	{
		String SQL = "Select * FROM roles WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, roleId);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return rs.getString("role");
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new UserAccessException(throwSql);
		}
	}
}
