package view.servlets.orderServlets.shared;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import entities.OrderEntities.Order;
import logic.generators.facades.OrderFacadeImpl;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.OrderFacade;
import logic.generators.facades.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/specificOrder")
public class specificOrderServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		OrderFacade orderFacade = new OrderFacadeImpl();
		UserFacade userFacade = new UserFacadeImpl();
		HttpSession session = request.getSession();
		try {
			orderFacade.getInstanceOrderDAO();
			userFacade.getUserDAOInstance();
			Order order =  orderFacade.orderById(orderId);
			session.setAttribute("order", order);
			session.setAttribute("customer", userFacade.customerByOrderId(order.getCustomer().getId()));
		}catch(DataException | OrderException | UserException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/specificOrder.jsp").forward(request, response);
	}
}
