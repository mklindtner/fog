package view.servlets.userServlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;
import view.servlets.orderServlets.helpers.UpdateUserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/employeeAsCustomer")
public class employeeAsCustomerServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		UserFacade userFacade = new MySqlUserFacade();
		String customerUsername = request.getParameter("customerByUsername");
		try {
			userFacade.getUserDAOInstance();
			Customer    customer       = userFacade.customerByUsername(customerUsername);
			UpdateOrderList.generateCustomerOrders(session, customer);
			session.setAttribute("customer", customer);
		} catch (DataException | UserException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
	}
}
