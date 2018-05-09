package view.servlets.orderServlets;

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
		//add shed
		HttpSession session    = request.getSession();
		try {
			Customer customer = UserFacade.findCustomerByUsername(((User) session.getAttribute("customer")).getUsername());
			Order order = new Order
					.OrderBuilder()
					.createOrderWithoutShed( (int) session.getAttribute("height"),
											 (int) session.getAttribute("width"),
											 (int) session.getAttribute("length"),
											 customer,
											 (int) session.getAttribute("slope"))
					.build();

			session.setAttribute("order", order);
			OrderFacade.createAndReturnOrder(order);

			request.getRequestDispatcher("/WEB-INF/customer/singleCustomer.jsp").forward(request, response);
		} catch (UserException | DataException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
