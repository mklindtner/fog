package view.servlets.orderServlets.orderCustomer;

import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;
import logic.UserFacade;

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
			changeOrderStatus(orderId);
			generateCustomerOrders(request.getSession(), (Customer)request.getSession().getAttribute("customer"));
			request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
		} catch( OrderException | DataException finalDist) {
			throw new ServletException( finalDist );
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	private void changeOrderStatus(int orderId) throws DataException, OrderException {
		Order order = OrderFacade.orderById(orderId);
		order.setStatus(Order.Status.ACCEPTED);
		OrderFacade.updateOrderOffer( order );
	}

	private void generateCustomerOrders(HttpSession session, Customer customer) throws OrderException,
																					   DataException
	{
		List<Order> customerOrders = OrderFacade.ordersOfCustomer(customer.getId() );
		session.setAttribute("customerOrders", customerOrders);
	}
}

		/*
		order.setPrice(Integer.parseInt(request.getParameter("price")));
		order.setHeight(Integer.parseInt(request.getParameter("height")));
		order.setWidth(Integer.parseInt(request.getParameter("width")));
		order.setLength(Integer.parseInt(request.getParameter("length")));
		order.setSlope(Integer.parseInt(request.getParameter("slope"))); */
