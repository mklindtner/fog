package view.servlets.userServlets;

import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/getCustomer")
public class findCustomerServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			UserFacade userFacade = new UserFacadeImpl();
			userFacade.getUserDAOInstance();
			Customer customer = userFacade.customerByUsername(request.getParameter("username"));
			request.setAttribute("foundCustomer", customer);
		} catch(UserException | DataException finalDistException) {
			throw new ServletException(finalDistException);
		}
	}
}
