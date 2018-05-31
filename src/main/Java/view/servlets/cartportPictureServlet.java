package view.servlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import view.servlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/generateCarport")
public class cartportPictureServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			UpdateOrderList.setOrderForSession(request);
		} catch(OrderException | DataException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/carportPicture.jsp").forward(request, response);
	}
}
