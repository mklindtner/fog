package logic;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.entities.userEntities.User;
import data.entities.userEntities.unknownUser;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import data.users.UserDAO;

import java.util.List;

public class UserFacade
{
	public static List<Employee> getAllEmployees() throws DataAccessException, UserAccessException
	{
		UserDAO userDao = new UserDAO();
		return userDao.allEmployees();
	}

	public static Customer createCustomer(String username, String password, int phone) throws DataAccessException, UserAccessException
	{
		UserDAO userDao = new UserDAO();
		return userDao.createAndReturnCustomer(username, password, phone);
	}

	public static User evaluateLogin( String username, String password) throws DataAccessException,
																			   UserAccessException {
		UserDAO userDao = new UserDAO();
		if(userDao.isCustomer( username ) && userDao.customerHasValidLogin( username, password ))
			return userDao.customerByUsername( username );

		if(!userDao.isCustomer( username ) && userDao.employeeHasValidLogin( username, password ))
			return userDao.employeeByUsername( username );
		return new unknownUser();
	}


}
