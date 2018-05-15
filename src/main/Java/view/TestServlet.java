package view;

import configurations.Conf;
import data.exceptions.DataException;
import data.exceptions.OrderException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			throw new DataException("this is a test");
		} catch (DataException dae) {
			Conf.getLogger().log(Level.SEVERE, "This is a test Message");
		}

	}
}
