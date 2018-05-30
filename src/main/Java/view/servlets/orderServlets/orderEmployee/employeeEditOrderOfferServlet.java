package view.servlets.orderServlets.orderEmployee;

import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderException;
import logic.generators.facades.OrderFacadeImpl;
import logic.generators.facades.OrderFacade;
import logic.generators.BillOfMaterials;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeEditOffer")
public class employeeEditOrderOfferServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		try {
			billOfMaterialWithoutShed(orderId,request.getSession());
			request.getRequestDispatcher("/WEB-INF/employee/employeeEditOffer.jsp").forward(request, response);
		} catch (DataException | OrderException | MaterialException finalDist) {
			throw new ServletException(finalDist);
		}
	}

	private void billOfMaterialWithoutShed(int orderId, HttpSession session) throws DataException, OrderException,
																	   MaterialException {

		OrderFacade orderFacade = new OrderFacadeImpl();
		orderFacade.getInstanceOrderDAO();
		Order           order           = orderFacade.orderById(orderId);
		BillOfMaterials billOfMaterials = new BillOfMaterials(order);
		if(order.getShed() != null)
			billOfMaterials.createCarportList();
		else
			billOfMaterials.createCarportListWithoutShed();
		order.setPrice(billOfMaterials.caportPrice());
		session.setAttribute("order", order);
	}
}
