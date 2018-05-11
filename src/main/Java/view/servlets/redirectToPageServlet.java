package view.servlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/redirect")
public class redirectToPageServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String goToPage = request.getParameter("goToPage");
		String role     = request.getParameter("role");
		try {
			checkForOrder(request);
		} catch( OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/" + role + "/" + goToPage + ".jsp").forward(request, response);
	}

	private void checkForOrder(HttpServletRequest request) throws OrderException, DataException
	{
		String id_string = request.getParameter("orderId");
		if (!id_string.equalsIgnoreCase("")) {
			int orderId;
			orderId = Integer.parseInt(id_string);
			request.getSession().setAttribute("order", OrderFacade.orderById(orderId));
		}
	}
}
