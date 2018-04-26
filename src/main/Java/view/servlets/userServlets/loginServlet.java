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

@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User   user     = UserFacade.evaluateLogin(username, password );
			if( user instanceof Customer) { //check instanceof
				request.setAttribute("customer", user);
				request.getRequestDispatcher("/WEB-INF/employeeHomepage.jsp");
			}
			if( user instanceof Employee) {
				request.setAttribute("employee" , user);
				request.getRequestDispatcher("/WEB-INF/customerHomepage.jsp");
			}
			response.sendRedirect("index.jsp");

		} catch (DataAccessException | UserAccessException dau) {
			throw new ServletException(dau);
		}
	}
}
