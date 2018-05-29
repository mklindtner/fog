package view.servlets.orderServlets.orderCustomer;

import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
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

@WebServlet(urlPatterns = "/customerAcceptOrder")
public class customerAcceptOfferServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		HttpSession session = request.getSession();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		try {
			OrderFacade orderFacade = new MySqlOrderFacade();
			orderFacade.getInstanceOrderDAO();
			changeOrderStatus(orderId, orderFacade);
			UpdateOrderList.generateCustomerOrders(session, customer);
			request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		} catch( OrderException | DataException finalDist) {
			throw new ServletException( finalDist );
		}
	}

	private void changeOrderStatus(int orderId, OrderFacade orderFacade) throws DataException, OrderException {
		Order order = orderFacade.orderById(orderId);
		order.setStatus(Order.Status.ACCEPTED);
		orderFacade.updateOrderOffer(order );
	}
}

		/*
		order.setPrice(Integer.parseInt(request.getParameter("price")));
		order.setHeight(Integer.parseInt(request.getParameter("height")));
		order.setWidth(Integer.parseInt(request.getParameter("width")));
		order.setLength(Integer.parseInt(request.getParameter("length")));
		order.setSlope(Integer.parseInt(request.getParameter("slope"))); */
