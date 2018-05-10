package view.servlets.orderServlets.orderCustomer;

import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.entities.userEntities.User;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import logic.OrderFacade;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/createOrder")
public class createOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Order order = createOrder(request);
		try {
			order = OrderFacade.createAndReturnOrder(order);
			request.getSession().setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		} catch (DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	private Order createOrder(HttpServletRequest request) {
		// TODO: add shed
		int      height     = Integer.parseInt(request.getParameter("height"));
		int      width      = Integer.parseInt(request.getParameter("width"));
		int      length     = Integer.parseInt(request.getParameter("length"));
		int      slope      = Integer.parseInt(request.getParameter("slope"));
		int      shedWidth  = Integer.parseInt(request.getParameter("shedWidth"));
		int      shedLength = Integer.parseInt(request.getParameter("shedLength"));
		Customer user       = (Customer) request.getSession().getAttribute("customer");

		return new Order
				.OrderBuilder()
				.createOrderWithoutShed(height, width, length, user, slope)
				.build();
	}
}
