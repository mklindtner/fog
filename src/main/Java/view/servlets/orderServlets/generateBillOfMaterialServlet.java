package view.servlets.orderServlets;

import data.exceptions.OrderException;
import entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import logic.facades.MySqlOrderFacade;
import logic.facades.OrderFacade;
import logic.generators.BillOfMaterials;

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
			checkForOrder(request);
			Order           order           = (Order) session.getAttribute("order");
			BillOfMaterials billOfMaterials = new BillOfMaterials(order);
			session.setAttribute("billOfMaterial", billOfMaterials.createCarportListWithoutShed());
			session.setAttribute("totalPrice", billOfMaterials.caportPrice());
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
		OrderFacade orderFacade = new MySqlOrderFacade();
		orderFacade.getInstanceOrderDAO();
		request.getSession().setAttribute("order", orderFacade.orderById(orderId));
		//request.getSession().setAttribute("order", MySqlOrderFacade.orderById(orderId));
	}
}
