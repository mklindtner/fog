package view.servlets;

import data.exceptions.UserException;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signout")
public class signoutServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(isCustomer(request)) {
			request.getSession().setAttribute("customer", null);
		} else {
			request.getSession().setAttribute("employee", null);
		}
		request.getRequestDispatcher("/index.jsp").forward(null, null);
	}

	private boolean isCustomer(HttpServletRequest request)
	{
		Customer customer = (Customer) request.getAttribute("customer");
		if(!customer.equals("")) {
			return true;
		}
		return false;
	}

}
