package view.servlets.userServlets;

import data.entities.userEntities.Customer;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/createCustomer")
public class createCustomerServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int    phone    = Integer.parseInt(request.getParameter("phone"));
		try {
			Customer customer = UserFacade.createCustomer(username, password, phone);
			request.getSession().setAttribute("customer", customer);
			request.getRequestDispatcher("/WEB-INF/customerHomepage.jsp").forward(request, response);
		} catch (DataAccessException | UserAccessException finalExceptionLayer) {
			throw new ServletException(finalExceptionLayer); //this should be output for exception
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
