package view.servlets;

import data.exceptions.DataException;
import data.exceptions.OrderException;
import logic.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/redirect")
public class redirectToPageServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String goToPage = request.getParameter("goToPage");
		String role     = request.getParameter("role");
		request.getRequestDispatcher("/WEB-INF/" + role + "/" + goToPage + ".jsp").forward(request, response);
	}


}
