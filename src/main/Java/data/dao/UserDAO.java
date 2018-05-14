package data.dao;

import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import entities.userEntities.unknownUser;
import data.exceptions.DataException;
import data.MySqlConnector;
import data.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
	Connection con;

	public UserDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	//this is ugly
	public UserDAO(String connectionSelection) throws DataException
	{
		con = MySqlConnector.createConnection(connectionSelection);
	}

	public List<String> employeeRoles() throws DataException
	{
		String       SQL   = "Select * FROM roles";
		List<String> Roles = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String role = rs.getString("role");
				Roles.add(role);
			}
			return Roles;
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	public List<Employee> allEmployees() throws UserException
	{
		String         SQL       = "Select * FROM employees LEFT JOIN roles ON employees.role_id = roles.id";
		List<Employee> employees = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String username        = rs.getString("username");
				String password        = rs.getString("password");
				int    phone           = rs.getInt("phone");
				String role            = rs.getString("role");
				String registered_date = rs.getString("reg_date");
				int    id              = rs.getInt("id");
				Employee employee = new Employee
						.EmployeeBuilder(id, registered_date)
						.createSimpleEmployee(username, password, role, phone)
						.build();
				employees.add(employee);
			}
			return employees;
		} catch (SQLException throwSql) {
			throw new UserException(throwSql);
		}
	}

	public List<Customer> allCustomers() throws DataException
	{
		String         SQL       = "Select * FROM customers";
		List<Customer> customers = new ArrayList<>();
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String reg_date = rs.getString("reg_date");
				int    phone    = rs.getInt("phone");
				int    id       = rs.getInt("id");
				customers.add(new Customer.CustomerBuilder(id, reg_date)
									  .createSimpleCustomer(username, password, phone)
									  .build()
				);
			}
			return customers;
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	public Customer createAndReturnCustomer(String username, String password, int phone) throws DataException,
																								UserException
	{
		String SQL = "INSERT INTO customers(username, password, reg_date, phone) VALUES (?, ?, now(), ?)";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, phone);
			statement.executeUpdate();
			return customerByUsername(username);
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	public Customer createAndReturnCustomer(String username, String password) throws DataException, UserException {
		String SQL = "INSERT INTO customers(username, password, reg_date) VALUES (?, ?, now())";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.executeUpdate();
			return customerByUsername(username);
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}


	/**
	 * finds customer by username
	 */
	public Customer customerByUsername(String username) throws UserException, DataException
	{
		String SQL = "Select * FROM customers WHERE username=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			return (Customer) defineUser(username, statement, true);
		} catch (SQLException | UserException throwSql) {
			throw new UserException(throwSql);
		}
	}

	/**
	 * finds employee by username
	 */

	public Employee employeeByUsername(String username) throws UserException, DataException
	{
		String SQL = "Select * FROM employees WHERE username=?";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			return (Employee) defineUser(username, statement, false);
		} catch (SQLException | UserException throwSql) {
			throw new UserException(throwSql);
		}
	}


	/**
	 * helper method to find whether it's customer or employee
	 */
	private User defineUser(String username, PreparedStatement statement, boolean isCustomer) throws SQLException,
																									 UserException
	{
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			String password          = rs.getString("password");
			String registration_date = rs.getString("reg_date");
			int    id                = rs.getInt("id");
			int    phone             = rs.getInt("phone");
			if (!isCustomer) {
				String role = ServiceDAO.getRole(rs.getInt("role_id"), con);
				return new Employee
						.EmployeeBuilder(id, registration_date)
						.createSimpleEmployee(username, password, role, phone)
						.build();
			}
			if (isCustomer) {
				return new Customer
						.CustomerBuilder(id, registration_date)
						.createSimpleCustomer(username, password, phone)
						.build();
			}
		}
		return new unknownUser();
	}

	public Employee createAndReturnEmployee(String username, String password, int phone, int role) throws UserException, DataException
	{
		String SQL = "INSERT INTO employees(username, password, phone, role_id, reg_date) VALUES(?, ?, ?, ?, NOW())";
		try (PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, phone);
			statement.setInt(4, role);
			statement.executeUpdate();
			return employeeByUsername(username);
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}
	}

	public boolean isCustomer(String username) throws UserException, DataException
	{
		List<Customer> customers = allCustomers();
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = customers.get(i);
			if (customer.getUsername().equals(username))
				return true;
		}
		return false;
	}

	public boolean isEmployee(String username) throws UserException
	{
		List<Employee> employees = allEmployees();
		for (int i = 0; i < employees.size(); i++) {
			Employee emp = employees.get(i);
			if (emp.getUsername().equals(emp))
				return true;
		}
		return false;
	}

	public boolean customerHasValidLogin(String username, String password) throws UserException, DataException
	{
		return customerByUsername(username).getPassword().equals(password) ? true : false;
	}

	public boolean employeeHasValidLogin(String username, String password) throws UserException, DataException
	{
		return employeeByUsername(username).getPassword().equals(password) ? true : false;
	}

	public Customer getCustomerById(int id ) throws UserException, DataException
	{
		final String SQL = "Select * FROM customers WHERE id=?";
		try(PreparedStatement statement = con.prepareStatement(SQL)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next () ) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				int phone = rs.getInt("phone");
				String registration_date = rs.getString("reg_date");
				return new Customer
						.CustomerBuilder(id, registration_date)
						.createSimpleCustomer(username, password, phone)
						.build();
			}
			throw new SQLException();
		} catch (SQLException throwSql) {
			throw new UserException(throwSql);
		}
	}
}
