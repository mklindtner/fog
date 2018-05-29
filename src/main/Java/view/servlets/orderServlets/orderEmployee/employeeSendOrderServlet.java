package view.servlets.orderServlets.orderEmployee;

import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import entities.userEntities.Employee;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/sendOrder")
public class employeeSendOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OrderFacade orderFacade = new MySqlOrderFacade();
		HttpSession session = request.getSession();
		try {
			orderFacade.getInstanceOrderDAO();
			Order order = findOrder(request, orderFacade);
			order.setStatus(Order.Status.SEND);
			session.setAttribute("order", order);

			orderFacade.updateOrderOffer(order);
			UpdateOrderList.generateEmployeeOrders(session, (Employee) session.getAttribute("employee"));
			request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
		} catch (DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	private Order findOrder(HttpServletRequest request, OrderFacade orderFacade) throws DataException, OrderException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		return orderFacade.orderById(orderId);
	}
}
