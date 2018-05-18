package view.servlets.orderServlets.orderCustomer;

import entities.OrderEntities.Order;
import data.exceptions.*;
import entities.userEntities.Customer;
import view.servlets.orderServlets.helpers.UpdateOrderList;

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
		Customer user  = (Customer) request.getSession().getAttribute("customer");
		try {
			Order order = UpdateOrderList.createOrderAndOrderLine(request, user);
			request.getSession().setAttribute("order", order);
			UpdateOrderList.generateCustomerOrders(request.getSession(), user);
			request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		} catch (DataException | MaterialException | OrderException | OrderLineException | ShedException finalDist) {
			throw new ServletException(finalDist);
		}

	}




}
