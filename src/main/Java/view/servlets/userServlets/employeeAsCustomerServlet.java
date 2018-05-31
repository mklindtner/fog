package view.servlets.userServlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import entities.userEntities.Customer;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.UserFacade;
import view.servlets.helpers.ErrorHandler;
import view.servlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeAsCustomer")
public class employeeAsCustomerServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		UserFacade userFacade = new UserFacadeImpl();
		String customerUsername = request.getParameter("customerByUsername");
		try {
			userFacade.getUserDAOInstance();
			Customer    customer       = userFacade.customerByUsername(customerUsername);
			UpdateOrderList.generateCustomerOrders(session, customer);
			session.setAttribute("customer", customer);
		} catch (DataException | UserException | OrderException | ClassCastException finalDist) {
			ErrorHandler.findCustomerError(request);
			request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
	}
}
