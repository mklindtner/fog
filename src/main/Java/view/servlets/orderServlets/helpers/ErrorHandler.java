package view.servlets.orderServlets.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler
{
	public static void loginError(HttpServletRequest request)
	{
		request.setAttribute("error", "login or password is incorrect");
	}

	public static void findCustomerError(HttpServletRequest request)
	{
		request.setAttribute("error", "unable to find customer");
	}

}
