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

@WebServlet(urlPatterns = "/employeeOfferOrder")
public class employeeOfferOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session  = request.getSession();
		Order       order    = (Order) session.getAttribute("order");
		Employee    employee = (Employee) session.getAttribute("employee");
		order = editOrder(request, order);
		try {
			OrderFacade orderFacade = new MySqlOrderFacade();
			orderFacade.getInstanceOrderDAO();
			orderFacade.updateOrderOffer(order);
			UpdateOrderList.generateEmployeeOrders(session, employee);
			session.setAttribute("order", order);
		} catch (DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeHomepage.jsp").forward(request, response);
	}

	private Order editOrder(HttpServletRequest request, Order order)
	{
		int height = Integer.parseInt(request.getParameter("height"));
		int width  = Integer.parseInt(request.getParameter("width"));
		int length = Integer.parseInt(request.getParameter("length"));
		int slope  = Integer.parseInt(request.getParameter("slope"));
		int price  = Integer.parseInt(request.getParameter("price"));
		order.setHeight(height);
		order.setWidth(width);
		order.setLength(length);
		order.setSlope(slope);
		order.setStatus(Order.Status.OFFER);
		order.setPrice(price);
		return order;
	}
}
