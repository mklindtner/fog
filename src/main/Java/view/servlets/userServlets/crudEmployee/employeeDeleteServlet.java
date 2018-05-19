package view.servlets.userServlets.crudEmployee;

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

@WebServlet(urlPatterns = "/deleteEmployee")
public class employeeDeleteServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserFacade facade = new MySqlUserFacade();
		try {
			facade.getUserDAOInstance();
			deleteEmployee(request, facade);
			request.getRequestDispatcher("/WEB-INF/employee/employees.jsp").forward(request, response);
		} catch(DataException | UserException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	private void deleteEmployee(HttpServletRequest request, UserFacade facade) throws DataException, UserException
	{
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		facade.deleteEmployeeById(employeeId);
		UpdateUserList.updateEmployeeListSession(request);
	}
}
