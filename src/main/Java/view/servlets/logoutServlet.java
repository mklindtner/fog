package view.servlets;

import configurations.Conf;
import entities.userEntities.Customer;
import entities.userEntities.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;

@WebServlet(urlPatterns = "/logout")
public class logoutServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("customer") != null) {
			Customer customer = (Customer) session.getAttribute("customer");
			Conf.getLogger().log(Level.INFO, "[LOGGED] {0} signed out", customer.getUsername());
			session.setAttribute("customer", null);
		}
		if(session.getAttribute("employee") != null) {
			Employee employee = (Employee) session.getAttribute("employee");
			Conf.getLogger().log(Level.INFO, "[LOGGED] {0} signed out", employee.getUsername());
			session.setAttribute("employee", null);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
