package view.servlets.userServlets;
import data.exceptions.DataException;
import data.exceptions.UserException;
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
		} catch (DataException | UserException e) {
			throw new ServletException(e);
		}
		request.getRequestDispatcher("/WEB-INF/employee/employees.jsp").forward(request, response);

	}
}