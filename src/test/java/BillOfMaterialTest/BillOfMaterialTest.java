package BillOfMaterialTest;

import daoTest.ServiceClasses.ServiceMethods;
import daoTest.ServiceClasses.ServiceSeed;
import data.exceptions.*;
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
		List<OrderLine> actualOrderLineList = billOfMaterials.createCarportListWithoutShed();
		assertEquals(actualOrderLineList.size(), 23);
		assertEquals(billOfMaterials.caportPrice(), 5620);
		assertEquals(actualOrderLineList.get(0), ServiceMethods.expectedFirstOrderLine());
	}
}
