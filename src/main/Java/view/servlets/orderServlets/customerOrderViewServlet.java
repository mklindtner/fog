package view.servlets.orderServlets;

import data.entities.userEntities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/customerOrderView")
public class customerOrderViewServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int         height     = Integer.parseInt(request.getParameter("height"));
		int         width      = Integer.parseInt(request.getParameter("width"));
		int         length     = Integer.parseInt(request.getParameter("length"));
		int         slope      = Integer.parseInt(request.getParameter("slope"));
		int         shedWidth  = Integer.parseInt(request.getParameter("shedWidth"));
		int         shedLength = Integer.parseInt(request.getParameter("shedLength"));
		HttpSession session = request.getSession();
		session.setAttribute("height", height);
		session.setAttribute("width", width);
		session.setAttribute("length", length);
		session.setAttribute("slope", slope);
		session.setAttribute("shedWidth", shedWidth);
		session.setAttribute("shedLength", shedLength);
		request.getRequestDispatcher("/WEB-INF/customer/singleCustomer.jsp").forward(request, response);

	}
}
