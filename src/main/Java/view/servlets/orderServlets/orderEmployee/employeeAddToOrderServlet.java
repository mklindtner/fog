package view.servlets.orderServlets.orderEmployee;

import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeToOrder")
public class employeeAddToOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		int orderId    = Integer.parseInt(request.getParameter("orderId"));
		try {
			OrderFacade.addEmployeeToOrder(employeeId, orderId);
			Order order = OrderFacade.orderById(orderId);
			request.getSession().setAttribute("order", order);
			request.getSession().setAttribute("ordersWithoutShed", OrderFacade.allOrdersWithoutShed());
			request.getRequestDispatcher("/WEB-INF/employee/orders.jsp").forward(request, response);
		} catch (DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
