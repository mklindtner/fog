package view.servlets.orderServlets.orderEmployee;

import data.entities.OrderEntities.Order;
import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/orderCatalog")
public class employeeOrderCatalogServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String employeeChoice = request.getParameter("employeeChoice");
		try {
			switch(employeeChoice) {
				case "ordersWithoutShed":
					ordersWithoutShed(request);
					break;
				case "ordersAvailable":
					ordersAvailable(request);
					break;
			}
			request.getRequestDispatcher("/WEB-INF/employee/employeeOrderCatalog.jsp").forward(request, response);
		} catch(DataException | OrderException finalDelegation) {
			throw new ServletException(finalDelegation);
		}

	}

	private void ordersWithoutShed(HttpServletRequest request) throws OrderException, DataException {
		List<Order> ordersWithoutShed = OrderFacade.allOrdersWithoutShed();
		request.setAttribute("ordersWithoutShed", ordersWithoutShed);
		request.setAttribute("choice", "ordersWithoutShed"); //for now as I'm unsure if any of them will be different
	}

	private void ordersAvailable(HttpServletRequest request) throws OrderException, DataException {
		List<Order> ordersAvailable = OrderFacade.ordersAvailable();
		request.setAttribute("ordersAvailable", ordersAvailable);
		request.setAttribute("choice", "ordersAvailable");
	}
}
