package view.servlets.userServlets;

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

@WebServlet(urlPatterns = "/OrderCatalog")
public class OrderForEmployeeServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			List<Order> ordersWithoutShed = OrderFacade.allOthersWithoutShed();
			request.setAttribute("ordersWithoutShed", ordersWithoutShed);
			request.getRequestDispatcher("/WEB-INF/employeeOrdersPage.jsp").forward(request, response);

		} catch(DataException | OrderException finalDelegation) {
			throw new ServletException(finalDelegation);
		}
	}
}
