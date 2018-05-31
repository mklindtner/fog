package view.servlets.orderServlets.shared;

import data.exceptions.OrderException;
import data.exceptions.OrderLineException;
import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import view.servlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/billOfMaterial")
public class generateBillOfMaterialServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		try {
			UpdateOrderList.setOrderForSession(request);
			Order order = (Order) session.getAttribute("order");
			UpdateOrderList.updateOrderLinesSession(request, order);
		} catch (DataException | MaterialException | OrderException | OrderLineException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/billOfMaterial.jsp").forward(request, response);
	}
}
