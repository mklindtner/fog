package view.servlets.orderServlets.orderCustomer;

import data.entities.OrderEntities.Order;
import data.entities.userEntities.Customer;
import data.entities.userEntities.User;
import logic.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/customerOrderView")
public class customerOrderViewServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int      height     = Integer.parseInt(request.getParameter("height"));
		int      width      = Integer.parseInt(request.getParameter("width"));
		int      length     = Integer.parseInt(request.getParameter("length"));
		int      slope      = Integer.parseInt(request.getParameter("slope"));
		int      shedWidth  = Integer.parseInt(request.getParameter("shedWidth"));
		int      shedLength = Integer.parseInt(request.getParameter("shedLength"));
		Customer user       = (Customer) request.getSession().getAttribute("customer");
		Order order = new Order
				.OrderBuilder()
				.createOrderWithoutShed(height, width, length, user, slope)
				.build();
		request.getSession().setAttribute("order", order);
		request.getRequestDispatcher("/WEB-INF/customer/customerOrderInformation.jsp").forward(request, response);

	}
}
