package view.servlets.orderServlets.orderCustomer;

import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customerAcceptOrder")
public class customerAcceptOfferServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		try {
			OrderFacade orderFacade = new MySqlOrderFacade();
			orderFacade.getInstanceOrderDAO();
			changeOrderStatus(orderId, orderFacade);
			generateCustomerOrders(request.getSession(), (Customer)request.getSession().getAttribute("customer"), orderFacade);
			request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
		} catch( OrderException | DataException finalDist) {
			throw new ServletException( finalDist );
		}
	}

	private void changeOrderStatus(int orderId, OrderFacade orderFacade) throws DataException, OrderException {
		Order order = orderFacade.orderById(orderId);
		//Order order = MySqlOrderFacade.orderById(orderId);
		order.setStatus(Order.Status.ACCEPTED);
		orderFacade.updateOrderOffer(order );
		//MySqlOrderFacade.updateOrderOffer(order );
	}

	private void generateCustomerOrders(HttpSession session, Customer customer, OrderFacade orderFacade) throws OrderException,
																					   DataException
	{
		List<Order> customerOrders = orderFacade.ordersOfCustomer(customer.getId() );
		//List<Order> customerOrders = MySqlOrderFacade.ordersOfCustomer(customer.getId() );
		session.setAttribute("customerOrders", customerOrders);
	}
}

		/*
		order.setPrice(Integer.parseInt(request.getParameter("price")));
		order.setHeight(Integer.parseInt(request.getParameter("height")));
		order.setWidth(Integer.parseInt(request.getParameter("width")));
		order.setLength(Integer.parseInt(request.getParameter("length")));
		order.setSlope(Integer.parseInt(request.getParameter("slope"))); */
