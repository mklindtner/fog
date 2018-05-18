package logic.facades;

import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import entities.userEntities.unknownUser;
import data.exceptions.DataException;
import data.exceptions.UserException;
import data.dao.UserDAO;

import java.util.List;

public class MySqlUserFacade implements UserFacade
{
	private UserDAO userDao;

	public UserDAO getUserDAOInstance() throws DataException
	{
		if(userDao == null)
			userDao = new UserDAO();
		return userDao;
	}

	public List<Employee> getAllEmployees() throws DataException, UserException
	{
		return userDao.allEmployees();
	}

	public Customer createCustomer(String username, String password, int phone) throws
																				DataException, UserException
	{
		return userDao.createAndReturnCustomer(username, password, phone);
	}

	public Customer createCustomerWithoutPhone(String username, String password) throws DataException, UserException
	{
		return userDao.createAndReturnCustomer(username, password);
	}

	public User evaluateLogin(String username, String password) throws DataException, UserException
	{
		if (userDao.isCustomer(username) && userDao.customerHasValidLogin(username, password))
			return userDao.customerByUsername(username);

		if (!userDao.isCustomer(username) && userDao.employeeHasValidLogin(username, password))
			return userDao.employeeByUsername(username);
		return new unknownUser();
	}

	public Customer findCustomerByUsername(String username) throws UserException, DataException
	{
		return userDao.customerByUsername(username);
	}

	public void deleteEmployeeById(int employeeId) throws UserException
	{
		userDao.deleteEmployeeById(employeeId);
	}

}
