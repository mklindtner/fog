package view.servlets.userServlets;

import configurations.Conf;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import view.servlets.orderServlets.helpers.ErrorHandler;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;

@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			String      username = request.getParameter("username");
			String      password = request.getParameter("password");
			HttpSession session  = request.getSession();
			UserFacade userFacade = new MySqlUserFacade();
			userFacade.getUserDAOInstance();
			User user = userFacade.evaluateLogin(username, password);
			if (user instanceof Customer) {
				session.setAttribute("customer", user);
				UpdateOrderList.generateCustomerOrders(session, (Customer) user);
				Conf.getLogger().log(Level.INFO, "[LOGGED] {0} signed in", user.getUsername());
				request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
			}
			if (user instanceof Employee) {
				session.setAttribute("employee", user);
				UpdateOrderList.generateEmployeeOrders(session, (Employee) user);
				UpdateOrderList.generateOrdersAvailable(session);
				Conf.getLogger().log(Level.INFO, "[LOGGED] {0} signed in", user.getUsername());
				request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
			}
			ErrorHandler.loginError(request);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (DataException | UserException | OrderException | ClassCastException finalDist) {
			ErrorHandler.loginError(request);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
}
