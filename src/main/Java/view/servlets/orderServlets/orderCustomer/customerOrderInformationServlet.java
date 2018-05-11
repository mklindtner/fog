package view.servlets.orderServlets.orderCustomer;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/orderInformation")
public class customerOrderInformationServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			checkForOrder(request);
		} catch ( OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/customer/customerOrderInformation.jsp").forward(request, response);
	}

	private void checkForOrder(HttpServletRequest request) throws OrderException, DataException
	{
		String id_string = request.getParameter("orderId");
		if (id_string != null) {
			int orderId;
			orderId = Integer.parseInt(id_string);
			request.getSession().setAttribute("order", OrderFacade.orderById(orderId));
		}
	}
}
