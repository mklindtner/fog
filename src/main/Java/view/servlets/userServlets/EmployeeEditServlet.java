package view.servlets.userServlets;

import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import view.servlets.orderServlets.helpers.UpdateUserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeEdit")
public class EmployeeEditServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		UserFacade facade = new MySqlUserFacade();
		try {
			facade.getUserDAOInstance();
			facade.deleteEmployeeById(employeeId);
			UpdateUserList.updateEmployeeListSession(request);
			request.getRequestDispatcher("/WEB-INF/employee/employees.jsp").forward(request, response);
		} catch(DataException | UserException finalDist) {
			throw new ServletException(finalDist);
		}
	}
}
