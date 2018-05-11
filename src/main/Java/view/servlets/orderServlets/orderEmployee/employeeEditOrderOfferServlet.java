package view.servlets.orderServlets.orderEmployee;

import data.entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import logic.OrderFacade;
import logic.generators.BillOfMaterialsCalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeEditOffer")
public class employeeEditOrderOfferServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		try {
			Order                     order                     = OrderFacade.orderById(orderId);
			BillOfMaterialsCalculator billOfMaterialsCalculator = new BillOfMaterialsCalculator(order);
			billOfMaterialsCalculator.createCarportListWithoutShed();
			order.setPrice(billOfMaterialsCalculator.caportPrice());
			request.getSession().setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/employee/employeeEditOffer.jsp").forward(request, response);
		} catch (DataException | OrderException | MaterialException finalDist) {
			throw new ServletException(finalDist);
		}
	}
}
