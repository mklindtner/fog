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

@WebServlet(urlPatterns = "/sendOrder")
public class employeeSendOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			Order order = findOrder(request);
			order.setStatus(Order.Status.SEND);
			request.getSession().setAttribute("order", order);
			OrderFacade.updateOrderOffer( order );
			request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
		} catch(DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	private Order findOrder(HttpServletRequest request) throws DataException, OrderException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		return OrderFacade.orderById( orderId );
	}
}
