package view.servlets.orderServlets.orderEmployee;

import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;

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
			OrderFacade orderFacade = new MySqlOrderFacade();
			orderFacade.getInstanceOrderDAO().updateOrderOffer(order);
			//MySqlOrderFacade.updateOrderOffer(order );
			request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
		} catch (DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	private Order findOrder(HttpServletRequest request) throws DataException, OrderException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		return orderFacade.orderById(orderId);
		//return MySqlOrderFacade.orderById(orderId);
	}
}
