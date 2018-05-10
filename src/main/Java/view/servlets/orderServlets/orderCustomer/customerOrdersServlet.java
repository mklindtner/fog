package view.servlets.orderServlets.orderCustomer;

import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customerOrders")
public class customerOrdersServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Customer customer = (Customer) request.getSession().getAttribute("customer");
		request.getRequestDispatcher("/WEB-INF/customer/customerOrders.jsp").forward(request, response);
		/*
		try {
			//List<Order> customerOrders = OrderFacade.ordersOfCustomer( customer.getId() );
			//request.setAttribute("customerOrders", customerOrders);
		} catch(OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		} */
	}
}
