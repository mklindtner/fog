package view.servlets.userServlets;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.entities.userEntities.User;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;

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
			Customer customer = UserFacade.findCustomerByUsername( request.getParameter("username") );
			request.setAttribute("foundCustomer", customer);
			request.getRequestDispatcher("/WEB-INF/singleCustomer.jsp").forward(request, response);
		} catch(UserAccessException | DataAccessException finalDistException) {
			throw new ServletException(finalDistException);
		}
	}
}
