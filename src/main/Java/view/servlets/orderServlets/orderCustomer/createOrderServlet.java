package view.servlets.orderServlets.orderCustomer;

import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import data.exceptions.*;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import logic.generators.BillOfMaterialsCalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createOrder")
public class createOrderServlet extends HttpServlet
{
	//TODO: create a way to differentiate between carport or no carport
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Order order = createOrder(request);
		try {
			OrderFacade orderFacade = new MySqlOrderFacade();

			//order = MySqlOrderFacade.createAndReturnOrder(order);
			orderFacade.getInstanceOrderDAO();
			order = orderFacade.createAndReturnOrder(order);
			saveOrderLineDB(order);
			request.getSession().setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		} catch (DataException | OrderException | MaterialException | OrderLineException finalDist) {
			throw new ServletException(finalDist);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	private void saveOrderLineDB(Order order) throws MaterialException, OrderLineException, DataException
	{
		BillOfMaterialsCalculator billOfMaterialsCalculator = new BillOfMaterialsCalculator(order);
		billOfMaterialsCalculator.createCarportListWithoutShed();
		billOfMaterialsCalculator.saveOrderLinesToDB();
	}

	private Order createOrder(HttpServletRequest request)
	{
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
