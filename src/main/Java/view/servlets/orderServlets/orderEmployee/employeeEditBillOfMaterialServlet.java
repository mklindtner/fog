package view.servlets.orderServlets.orderEmployee;

import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderLineException;
import entities.OrderEntities.Order;
import logic.generators.facades.OrderFacadeImpl;
import logic.generators.facades.OrderFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/employeeEditBillOfMaterial")
public class employeeEditBillOfMaterialServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OrderFacade facade      = new OrderFacadeImpl();
		int         orderLineId = Integer.parseInt(request.getParameter("orderLineId"));
		int         amount      = Integer.parseInt(request.getParameter("orderLineAmount"));
		Order       order       = (Order) request.getSession().getAttribute("order");
		try {
			facade.getInstanceOrderLineDAO();
			facade.updateOrderLineAmount(orderLineId, amount);
			UpdateOrderList.updateOrderLinesSession(request, order);
			request.getRequestDispatcher("/WEB-INF/shared/billOfMaterial.jsp").forward(request, response);
		} catch (DataException | OrderLineException | MaterialException finalDist) {
			throw new ServletException(finalDist);
		}
	}
}
