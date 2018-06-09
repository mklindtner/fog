package view.servlets;

import configurations.Conf;
import data.exceptions.*;
import entities.userEntities.Customer;
import logic.generators.facades.UserFacadeImpl;
import logic.generators.facades.UserFacade;
import view.servlets.helpers.ErrorHandler;
import view.servlets.helpers.UpdateOrderList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

/**
 * creates order and user, used when a user is not logged into the system and still want to place an order.
 * The default password is always "123"
 */
@WebServlet(urlPatterns = "/indexCreateOrderUser")
public class createOrderAndUserServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserFacade facade = new UserFacadeImpl();
		String username = request.getParameter("contactInformation");
		try {
			facade.getUserDAOInstance();
			Customer   user   = facade.createCustomerWithoutPhone(username, "123");
			UpdateOrderList.createOrderAndOrderLine(request, user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(DataException | UserException | OrderLineException | MaterialException | OrderException | ShedException
				finalDist)
		{
			ErrorHandler.customerAlreadyExists(request, finalDist);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
