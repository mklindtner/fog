package view.servlets.userServlets;

import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import logic.OrderFacade;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			String      username = request.getParameter("username");
			String      password = request.getParameter("password");
			HttpSession session  = request.getSession();
			User        user     = UserFacade.evaluateLogin(username, password);
			if (user instanceof Customer) {
				session.setAttribute("customer", user);
				generateCustomerOrders(session, (Customer) user);
				request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
			}
			if (user instanceof Employee) {
				session.setAttribute("employee", user);
				generateEmployeeOrders(session, (Employee) user);
				genreateOrdersAvailable(session);
				request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
			}
			response.sendRedirect("index.jsp");

		} catch (DataException | UserException | OrderException dau) {
			throw new ServletException(dau);
		}
	}

	private void generateCustomerOrders(HttpSession session, Customer customer) throws OrderException,
																					   DataException
	{
		List<Order> customerOrders = OrderFacade.ordersOfCustomer(customer.getId());
		session.setAttribute("customerOrders", customerOrders);
	}

	private void generateEmployeeOrders(HttpSession session, Employee employee) throws OrderException,
																					   DataException
	{
		List<Order> employeeOrders = OrderFacade.employeeChosenOrders(employee.getId());
		session.setAttribute("employeeOrders", employeeOrders);
	}

	private void genreateOrdersAvailable(HttpSession session) throws OrderException, DataException {
		session.setAttribute("ordersAvailable", OrderFacade.ordersAvailable());
	}
}
