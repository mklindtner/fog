package data.users;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.entities.userEntities.User;
import data.exceptions.DataAccessException;
import data.MySqlConnector;
import data.exceptions.LoginException;
import data.exceptions.UserAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
	Connection con;
	public UserDAO() throws DataAccessException {
		con = MySqlConnector.getMySqlConnection();
	}

	public List<String> employeeRoles() throws DataAccessException
	{
		String SQL = "Select * FROM roles";
		List<String> Roles = new ArrayList<>();
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String role = rs.getString("role");
				Roles.add(role);
			}
			return Roles;
		}catch(SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	public List<Employee> allEmployees() throws UserAccessException {
		String SQL = "Select * FROM employees LEFT JOIN roles ON employees.role_id = roles.id";
		List<Employee> employees = new ArrayList<>();
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				int phone = rs.getInt("phone");
				String role = rs.getString("role");
				String registered_date = rs.getString("reg_date");
				int id = rs.getInt("id");
				Employee employee = new Employee(username, password, phone, role, registered_date, id);
				employees.add(employee);
			}
			return employees;
		}catch(SQLException throwSql) {
			throw new UserAccessException(throwSql);
		}
	}

	public List<Customer> allCustomers() throws DataAccessException {
		String SQL = "Select * FROM customers";
		List<Customer> customers = new ArrayList<>();
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String reg_date = rs.getString("reg_date");
				int phone = rs.getInt("phone");
				int id = rs.getInt("id");
				customers.add(new Customer(username, password, reg_date, phone, id));
			}
			return customers;
		}catch(SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	/**
	 * this was chosen over a builder or overloading a constructor as both would be more error-prone later on
	 * the drawback of choosing this is that the UserAccessException is redundant and misplaced in this method
	 * @param username
	 * @param password
	 * @param phone
	 * @return
	 * @throws DataAccessException
	 * @throws UserAccessException
	 */

	public Customer createAndReturnCustomer( String username, String password, int phone ) throws DataAccessException,
																								  UserAccessException
	{
		String SQL = "INSERT INTO customers(username, password, reg_date, phone) VALUES (?, ?, now(), ?)";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, phone);
			statement.executeUpdate();
			return customerByUsername(username );
		} catch(SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	/**
	 * finds customer by username
	 * @param username
	 * @return
	 * @throws UserAccessException
	 */

	public Customer customerByUsername( String username ) throws UserAccessException
	{
		String SQL = "Select * FROM customers WHERE username=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			return (Customer) findUser(username, statement, true);
		}catch(SQLException | UserAccessException throwSql) {
			throw new UserAccessException(throwSql);
		}
	}

	/**
	 * finds employee by username
	 * @param username
	 * @return
	 * @throws UserAccessException
	 */

	public Employee employeeByUsername( String username ) throws UserAccessException {
		String SQL = "Select * FROM employees WHERE username=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			return (Employee) findUser(username, statement, false);
		}catch(SQLException | UserAccessException throwSql) {
			throw new UserAccessException(throwSql);
		}
	}

	/**
	 * heler method to find whether it's customer or employee
	 * @param username
	 * @param statement
	 * @param isCustomer
	 * @return
	 * @throws SQLException
	 */

	private User findUser(String username, PreparedStatement statement, boolean isCustomer) throws SQLException, UserAccessException
	{
		statement.setString(1, username );
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			String password = rs.getString("password");
			String registration_date = rs.getString("reg_date");
			int id = rs.getInt("id");
			int phone = rs.getInt("phone");
			if(!isCustomer) {
				String role = rs.getString("role");
				return new Employee(username, password, phone, registration_date, role, id);
			}
			return new Customer(username, password, registration_date, phone, id);
		}
		throw new UserAccessException("unable to find user");
	}

	public boolean isCustomer( String username ) throws UserAccessException, DataAccessException
	{
		List<Customer> customers = allCustomers();
		for(int i = 0; i < customers.size(); i++) {
			Customer customer = customers.get(i);
			if(customers.contains(customer))
				return true;
		}
		return false;
	}

	public boolean customerHasValidLogin(String username, String password ) throws UserAccessException
	{
		return customerByUsername( username ).getPassword().equals(password) ? true : false;
	}

	public boolean employeeHasValidLogin(String username, String password ) throws UserAccessException {
		return employeeByUsername( username ).getPassword().equals(password) ? true : false;
	}
}
