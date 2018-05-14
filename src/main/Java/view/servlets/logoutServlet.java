package view.servlets;

import data.exceptions.UserException;
import entities.userEntities.Customer;
import entities.userEntities.Employee;
import entities.userEntities.User;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class logoutServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		session.setAttribute("customer", null);
		session.setAttribute("employee", null);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
