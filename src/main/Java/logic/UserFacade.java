package logic;

import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import entities.userEntities.unknownUser;
import data.exceptions.DataException;
import data.exceptions.UserException;
import data.dao.UserDAO;

import java.util.List;

public class UserFacade
{
	public static List<Employee> getAllEmployees() throws DataException, UserException
	{
		UserDAO userDao = new UserDAO();
		return userDao.allEmployees();
	}

	public static Customer createCustomer(String username, String password, int phone) throws
																					   DataException, UserException
	{
		UserDAO userDao = new UserDAO();
		return userDao.createAndReturnCustomer(username, password, phone);
	}

	public static User evaluateLogin(String username, String password) throws DataException,
																			  UserException
	{
		UserDAO userDao = new UserDAO();
		if (userDao.isCustomer(username) && userDao.customerHasValidLogin(username, password))
			return userDao.customerByUsername(username);

		if (!userDao.isCustomer(username) && userDao.employeeHasValidLogin(username, password))
			return userDao.employeeByUsername(username);
		return new unknownUser();
	}

	public static Customer findCustomerByUsername(String username) throws UserException, DataException
	{
		UserDAO userDAO = new UserDAO();
		return userDAO.customerByUsername(username);
	}
}
