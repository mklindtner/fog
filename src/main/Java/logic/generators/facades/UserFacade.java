package logic.generators.facades;

import data.MySQLDAO.UserDAO;
import data.exceptions.DataException;
import data.exceptions.UserException;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;

import java.util.List;

public interface UserFacade
{
	List<Employee> getAllEmployees() throws DataException, UserException;

	Customer createCustomer(String username, String password, int phone) throws DataException, UserException;

	Customer createCustomerWithoutPhone(String username, String password) throws DataException, UserException;

	User evaluateLogin(String username, String password) throws DataException, UserException, ClassCastException;

	Customer customerByUsername(String username) throws UserException, ClassCastException;

	UserDAO getUserDAOInstance() throws DataException;

	void deleteEmployeeById(int employeeId) throws UserException;

	void createEmployee(String username, String password, int phone, int role) throws UserException, DataException;

	void promoteEmployee(int employeeId) throws UserException;

	void demoteEmployee(int employeeId) throws UserException;

	Customer customerByOrderId(int customerId) throws UserException;
}
