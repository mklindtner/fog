package view.servlets.userServlets;

import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createCustomer")
public class createCustomerServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Customer customer;
		try {
			UserFacade userFacade = new MySqlUserFacade();
			userFacade.getUserDAOInstance();
			if(phoneIsValid(request)) {
				int    phone    = Integer.parseInt(request.getParameter("phoneNumber"));
				customer = userFacade.createCustomer(username, password, phone);
				//customer = MySqlUserFacade.createCustomer(username, password, phone);
			} else
				customer = userFacade.createCustomerWithoutPhone(username, password);
				//customer = MySqlUserFacade.createCustomerWithoutPhone(username, password);
			request.getSession().setAttribute("customer", customer);
			request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
		} catch (DataException | UserException finalExceptionLayer) {
			throw new ServletException(finalExceptionLayer); //this should be output for exception
		}
	}

	private boolean phoneIsValid(HttpServletRequest request) throws ServletException, IOException
	{
		String test = request.getParameter("phoneNumber");
		return test.equals("") ? false : true;
	}
}
