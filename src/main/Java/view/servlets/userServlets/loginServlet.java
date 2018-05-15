package view.servlets.userServlets;

import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
			//User        user     = MySqlUserFacade.evaluateLogin(username, password);
			if (user instanceof Customer) {
				session.setAttribute("customer", user);
				UpdateOrderList.generateCustomerOrders(session, (Customer) user);
				request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
			}
			if (user instanceof Employee) {
				session.setAttribute("employee", user);
				UpdateOrderList.generateEmployeeOrders(session, (Employee) user);
				UpdateOrderList.genreateOrdersAvailable(session);
				request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
			}
			response.sendRedirect("index.jsp");

		} catch (DataException | UserException | OrderException dau) {
			throw new ServletException(dau);
		}
	}
}
