package view.servlets.orderServlets.orderEmployee;

import entities.userEntities.Employee;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeChooseOrder")
public class employeeChooseOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		try {
			OrderFacade.employeeChooseOrder(employee.getId(), orderId);
		} catch (OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
