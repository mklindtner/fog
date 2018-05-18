package view.servlets.orderServlets.orderCustomer;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.OrderLineException;
import entities.OrderEntities.Order;
import entities.userEntities.Customer;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/customerDeclineOrder")
public class customerDeclineOfferServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			int      orderId  = Integer.parseInt(request.getParameter("orderId"));
			Customer customer = (Customer) request.getSession().getAttribute("customer");
			deleteOrder(orderId);
			UpdateOrderList.generateCustomerOrders(request.getSession(), customer);
			request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		} catch(OrderException | DataException | OrderLineException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	private void deleteOrder(int orderId) throws OrderException, DataException, OrderLineException
	{
		OrderFacade facade = new MySqlOrderFacade();
		facade.getInstanceOrderDAO();
		facade.getInstanceOrderLineDAO();
		facade.deleteOrderLineByOrderId(orderId);
		facade.deleteOrder(orderId);
	}
}
