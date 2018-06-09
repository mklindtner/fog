package view.servlets.helpers;

import configurations.Conf;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

/**
 * utility class for the presentationLayer whenever an error occurs
 * the purpose of this class is to log the exception with an appropriate level and message, saved in the request.
 * This is a static class
 */
public class ErrorHandler
{
	public static void loginError(HttpServletRequest request, Exception e)
	{
		request.setAttribute("error", "login or password is incorrect");
		Conf.getLogger().log(Level.INFO, "{0} attempted to login", request.getParameter("username"));
	}

	public static void findCustomerError(HttpServletRequest request)
	{
		request.setAttribute("error", "unable to find customer");
	}

	public static void customerAlreadyExists(HttpServletRequest request, Exception e)
	{
		request.setAttribute("error", "sorry that email is already in use");
		Conf.getLogger().log(Level.WARNING, "[Exception] " + e.getMessage());

	}

	public static void orderError(HttpServletRequest request, Exception e)
	{
		request.setAttribute("error", "unable to createOrder");
		Conf.getLogger().log(Level.SEVERE, "[ERROR] " + e.getMessage());
	}

	public static void acceptOrderError(HttpServletRequest request, Exception e)
	{
		request.setAttribute("error", "unable to accept order");
		Conf.getLogger().log(Level.SEVERE, "[ERROR] " + e.getMessage());
	}

}
