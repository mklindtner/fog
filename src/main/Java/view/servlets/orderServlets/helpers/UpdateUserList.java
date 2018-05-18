package view.servlets.orderServlets.helpers;

import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserList
{
	public static void updateEmployeeListSession(HttpServletRequest request) throws DataException, UserException
	{
		UserFacade userFacade = new MySqlUserFacade();
		userFacade.getUserDAOInstance();
		request.getSession().setAttribute("employees", userFacade.getAllEmployees());
	}
}
