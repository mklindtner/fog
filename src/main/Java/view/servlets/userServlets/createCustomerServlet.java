package view.servlets.userServlets;

import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

@WebServlet(urlPatterns = "/createCustomer")
public class createCustomerServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Customer customer;
		try {
			if(phoneIsValid(request)) {
				int    phone    = Integer.parseInt(request.getParameter("phoneNumber"));
				customer = UserFacade.createCustomer(username, password, phone);
			} else
				customer = UserFacade.createCustomerWithoutPhone(username, password);
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
