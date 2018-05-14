package view.servlets.orderServlets;

import data.exceptions.OrderException;
import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import logic.OrderFacade;
import logic.generators.BillOfMaterialsCalculator;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		try {
			checkForOrder(request);
			Order                     order                     = (Order) session.getAttribute("order");
			BillOfMaterialsCalculator billOfMaterialsCalculator = new BillOfMaterialsCalculator(order);
			session.setAttribute("billOfMaterial", billOfMaterialsCalculator.createCarportListWithoutShed());
			session.setAttribute("totalPrice", billOfMaterialsCalculator.caportPrice());
		} catch (DataException | MaterialException | OrderException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/billOfMaterial.jsp").forward(request, response);
	}

	private void checkForOrder(HttpServletRequest request) throws OrderException, DataException
	{
		String id_string = request.getParameter("orderId");
		int    orderId;
		orderId = Integer.parseInt(id_string);
		request.getSession().setAttribute("order", OrderFacade.orderById(orderId));
	}
}
