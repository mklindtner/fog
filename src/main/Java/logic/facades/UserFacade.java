package logic.facades;

import data.dao.UserDAO;
import data.exceptions.DataException;
import data.exceptions.UserException;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import entities.userEntities.unknownUser;

import java.util.List;

public interface UserFacade
{
	List<Employee> getAllEmployees() throws DataException, UserException;

	Customer createCustomer(String username, String password, int phone) throws DataException, UserException;

	Customer createCustomerWithoutPhone(String username, String password) throws DataException, UserException;

	User evaluateLogin(String username, String password) throws DataException, UserException;

	Customer findCustomerByUsername(String username) throws UserException, DataException;

	UserDAO getUserDAOInstance() throws DataException;

	void deleteEmployeeById(int employeeId) throws UserException;

	void createEmployee(String username, String password, int phone, int role) throws UserException, DataException;

	void promoteEmployee(int employeeId) throws UserException;

	void demoteEmployee(int employeeId) throws UserException;
}
