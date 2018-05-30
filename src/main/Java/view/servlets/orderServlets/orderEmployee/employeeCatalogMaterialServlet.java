package view.servlets.orderServlets.orderEmployee;

import data.exceptions.DataException;
import data.exceptions.MaterialException;
import entities.OrderEntities.Material;
import logic.generators.facades.OrderFacadeImpl;
import logic.generators.facades.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/employeeCatalogMaterial")
public class employeeCatalogMaterialServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OrderFacade facade = new OrderFacadeImpl();
		try {
			facade.getInstanceMaterialDAO();
			List<Material> allMaterials = facade.allMaterials();
			request.getSession().setAttribute("allMaterials", allMaterials);
		} catch(DataException | MaterialException finalDist) {
			throw new ServletException(finalDist);
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeMaterialCatalog.jsp").forward(request, response);
	}
}
