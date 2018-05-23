package BillOfMaterialTest;

import ServiceClasses.ServiceMethods;
import ServiceClasses.ServiceSeed;
import data.dao.OrderDAO;
import data.exceptions.*;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import logic.generators.BillOfMaterials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class BillOfMaterialTest
{
	@Before
	public void setUp() throws DataException, ShedException, MaterialException, OrderException, UserException
	{
		ServiceSeed.establishConnections();
		ServiceSeed.populateTables();
	}

	@After
	public void tearDown() throws DataException
	{
		ServiceSeed.eraseTablesAndCloseConnection();
		ServiceSeed.resetLists();
	}

	@Test
	public void BillOfMaterial() throws DataException, MaterialException
	{
		BillOfMaterials billOfMaterials = new BillOfMaterials(ServiceMethods.expectedFirstOrder(), "TEST");
		List<OrderLine> actualOrderLineList = billOfMaterials.createCarportList();
		assertEquals(actualOrderLineList.size(), 29);
		assertEquals(billOfMaterials.caportPrice(), 5620);
		assertEquals(actualOrderLineList.get(0), ServiceMethods.expectedFirstOrderLine());
	}


	@Test
	public void secondOrderWithoutShed() throws OrderException, DataException, MaterialException
	{
		OrderDAO orderDAO = new OrderDAO("TEST");
		List<Order> ordersActual = orderDAO.allOrders();
		assertEquals(ordersActual.get(1), ServiceMethods.expectedSecondOrder());
		BillOfMaterials billOfMaterial = new BillOfMaterials(ServiceMethods.expectedSecondOrder(), "TEST");
		billOfMaterial.createCarportListWithoutShed();
	}

	@Test
	public void saveOrderLinesToDB() throws DataException, MaterialException, OrderLineException
	{
		Order           expected            = ServiceMethods.expectedFirstOrder();
		expected.setId(ServiceSeed.getOrders().get(0).getId());

		BillOfMaterials billOfMaterials     = new BillOfMaterials(expected, "TEST");
		billOfMaterials.createCarportList();
		billOfMaterials.saveOrderLinesToDB("TEST");
	}
}

