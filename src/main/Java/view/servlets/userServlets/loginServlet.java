package view.servlets.userServlets;

import data.entities.userEntities.Customer;
import data.entities.userEntities.Employee;
import data.entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.UserException;
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
			if( user instanceof Customer ) {
				request.getSession().setAttribute("customer", user);
				request.getRequestDispatcher("/WEB-INF/customerHomepage.jsp").forward(request, response);
			}
			if( user instanceof Employee) {
				request.setAttribute("employee" , user);
				request.getRequestDispatcher("/WEB-INF/employeeHomepage.jsp").forward(request, response);
			}
			response.sendRedirect("index.jsp");

		} catch (DataException | UserException dau) {
			throw new ServletException(dau);
		}
	}
}
