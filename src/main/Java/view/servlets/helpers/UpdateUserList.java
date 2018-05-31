package view.servlets.helpers;

import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.UserFacade;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserList
{
	public static void updateEmployeeListSession(HttpServletRequest request) throws DataException, UserException
	{
		UserFacade userFacade = new UserFacadeImpl();
		userFacade.getUserDAOInstance();
		request.getSession().setAttribute("employees", userFacade.getAllEmployees());
	}
}
