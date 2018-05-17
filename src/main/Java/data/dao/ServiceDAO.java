package data.dao;

import data.exceptions.*;
import entities.OrderEntities.Material;
import entities.OrderEntities.Shed;
import entities.userEntities.Customer;

import java.sql.*;

public class ServiceDAO
{
	public static Customer getCustomerById(int id, Connection con) throws OrderException
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
			throw new OrderException("Error within createCustomerInsideOrderDAO", throwSql);
		}

	}

	public static String getRole(int roleId, Connection con) throws UserException
	{
		String SQL = "Select * FROM roles WHERE id=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, roleId);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				return rs.getString("role");
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new UserException(throwSql);
		}
	}

	public static Shed getShedById(int id, Connection con) throws ShedException
	{
		final String SQL = "Select * FROM sheds WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				int length = rs.getInt("length");
				int width = rs.getInt("width");
				boolean hasFloor = rs.getBoolean("hasFloor");
				int idShed = rs.getInt("id");
				return new Shed
						.ShedBuilder()
						.insertLength(length)
						.insertWidth(width)
						.insertHasFloor(hasFloor)
						.insertShedId(idShed)
						.build();
			}
			throw new SQLException();
		} catch(SQLException throwSql) {
			throw new ShedException(throwSql);
		}
	}


}
