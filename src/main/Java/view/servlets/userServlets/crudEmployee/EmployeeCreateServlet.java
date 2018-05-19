package view.servlets.userServlets.crudEmployee;

import data.exceptions.DataException;
import data.exceptions.UserException;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import org.omg.CORBA.UnknownUserException;
import view.servlets.orderServlets.helpers.UpdateUserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createEmployee")
public class EmployeeCreateServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//create employee from parameters of employeeCreatUser
		//create facade/userdao that creates employee
		//do this here lol
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int phone = Integer.parseInt(request.getParameter("phoneNumber"));
		String role = request.getParameter("employeeRole");
		UserFacade facade = new MySqlUserFacade();
		try {
			facade.getUserDAOInstance();
			facade.createEmployee(username, password, phone, evaluateRoleToInt(role));
			UpdateUserList.updateEmployeeListSession(request);
		} catch( UserException | DataException | UnknownUserException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("WEB-INF/employee/employees.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("/WEB-INF/employee/employeeCreateUser.jsp").forward(request, response);
	}

	private int evaluateRoleToInt(String role) throws UnknownUserException
	{
		if(role.toUpperCase().equals("SALGSMEDARBEJDER"))
			return 3;
		if(role.toUpperCase().equals("MATERIALEANSVARLIG"))
			return 2;
		throw new UnknownUserException();
	}
}
