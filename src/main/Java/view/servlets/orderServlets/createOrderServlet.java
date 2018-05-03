package view.servlets.orderServlets;

import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
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
import java.io.IOException;

@WebServlet(urlPatterns = "/createOrder")
public class createOrderServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		//add shed
		int      height   = Integer.parseInt(request.getParameter("height"));
		int      width    = Integer.parseInt(request.getParameter("width"));
		int      length   = Integer.parseInt(request.getParameter("length"));
		int      slope    = Integer.parseInt(request.getParameter("slope"));
		String user = request.getParameter("orderee"); //change this to user later
		String type = request.getParameter("type");
		try {
			Customer customer = UserFacade.findCustomerByUsername(user);
			Material material = OrderFacade.materialByType(type );
			Order    order    = new Order
					.OrderBuilder()
					.createOrderWithoutShed(height, width, length, customer, slope, material)
					.build();

			request.setAttribute("customer", customer);
			request.setAttribute("height", height);
			request.setAttribute("width", width);
			request.setAttribute("slope", slope);
			request.setAttribute("length", length);
			request.getSession().setAttribute("order", order);
			OrderFacade.createAndReturnOrder( order );

			request.getRequestDispatcher("/WEB-INF/customer/singleCustomer.jsp").forward(request, response);
		} catch(UserException | DataException  | MaterialException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}
}
