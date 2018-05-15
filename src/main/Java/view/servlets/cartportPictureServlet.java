package view.servlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.facades.MySqlOrderFacade;
import logic.facades.MySqlUserFacade;
import logic.facades.OrderFacade;
import logic.facades.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;

@WebServlet(urlPatterns = "/generateCarport")
public class cartportPictureServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			checkForOrder(request);
		} catch(OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/carportPicture.jsp").forward(request, response);
	}

	private void checkForOrder(HttpServletRequest request) throws OrderException, DataException
	{
		String id_string = request.getParameter("orderId");
		int    orderId;
		orderId = Integer.parseInt(id_string);
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		//request.getSession().setAttribute("order", MySqlOrderFacade.orderById(orderId));
		request.getSession().setAttribute("order", orderFacade.orderById(orderId));

	}
}
