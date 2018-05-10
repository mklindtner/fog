package view.servlets.orderServlets.orderCustomer;

import data.entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/customerAcceptOrder")
public class customerAcceptOfferServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		try {
			Order order = OrderFacade.orderById(orderId);
			order.setStatus(Order.Status.ACCEPTED);
			OrderFacade.updateOrderOffer( order );
			request.getRequestDispatcher("/WEB-INF/customer/customerHomepage.jsp").forward(request, response);
		} catch( OrderException | DataException finalDist) {
			throw new ServletException( finalDist );
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}

		/*
		order.setPrice(Integer.parseInt(request.getParameter("price")));
		order.setHeight(Integer.parseInt(request.getParameter("height")));
		order.setWidth(Integer.parseInt(request.getParameter("width")));
		order.setLength(Integer.parseInt(request.getParameter("length")));
		order.setSlope(Integer.parseInt(request.getParameter("slope"))); */
