package view.servlets.orderServlets;

import data.exceptions.DataException;
import data.exceptions.MaterialException;
import logic.generators.BillOfMaterialsCalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/BillOfMaterial")
public class generateBillOfMaterialServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		try {
			BillOfMaterialsCalculator billOfMaterialsCalculator = new BillOfMaterialsCalculator( (int) session.getAttribute("height"), (int) session.getAttribute("width"), (int) session.getAttribute("length"));
			session.setAttribute("billOfMaterial", billOfMaterialsCalculator.createCarportListWithoutShed());
			session.setAttribute("totalPrice", billOfMaterialsCalculator.caportPrice());

		} catch(DataException | MaterialException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/shared/billOfMaterial.jsp").forward(request, response);

	}
}
