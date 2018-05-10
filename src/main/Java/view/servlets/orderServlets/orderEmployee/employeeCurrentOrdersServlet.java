package view.servlets.orderServlets.orderEmployee;

import data.entities.OrderEntities.Order;
import data.entities.userEntities.Employee;
import data.entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/employeeCurrentOrders")
public class employeeCurrentOrdersServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		try {
			List<Order> employeeOrders = OrderFacade.employeeChosenOrders(employee.getId());
			request.setAttribute("employeeOrders", employeeOrders);
			request.getRequestDispatcher("/WEB-INF/employee/employeeCurrentOrders.jsp").forward(request, response);
		} catch (OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
	}
}
