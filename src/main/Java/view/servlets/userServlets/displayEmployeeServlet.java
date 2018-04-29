package view.servlets.userServlets;
import data.exceptions.DataAccessException;
import data.exceptions.UserAccessException;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employees")
public class displayEmployeeServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			request.setAttribute("employees", UserFacade.getAllEmployees());

			request.getRequestDispatcher("/WEB-INF/displayEmployees.jsp").forward(request, response);
		} catch (DataAccessException | UserAccessException e) {
			throw new ServletException(e);
		}
	}
}