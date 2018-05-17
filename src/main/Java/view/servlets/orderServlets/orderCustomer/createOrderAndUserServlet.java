package view.servlets.orderServlets.orderCustomer;

import data.exceptions.*;
import entities.userEntities.Customer;
import logic.facades.MySqlUserFacade;
import logic.facades.UserFacade;
import view.servlets.orderServlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/indexCreateOrderUser")
public class createOrderAndUserServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserFacade facade = new MySqlUserFacade();
		String username = request.getParameter("contactInformation");

		try {
			facade.getUserDAOInstance();
			Customer   user   = facade.createCustomerWithoutPhone(username, "123");
			UpdateOrderList.createOrderAndOrderLine(request, user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(DataException | UserException | OrderLineException | MaterialException | OrderException | ShedException
				finalDist) {
			throw new ServletException(finalDist);
		}
	}

}
