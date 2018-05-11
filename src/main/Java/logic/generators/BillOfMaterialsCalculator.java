package logic.generators;

import data.dao.MaterialDAO;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderLineException;
import logic.OrderFacade;

import java.util.List;

public class BillOfMaterialsCalculator
{
	private int totalPrice = 0, area;
	private final double TREEWIDTH           = 0.97;
	private final int    EKSTRA_LENGTH_STAKE = 40;
	private MaterialDAO materialDAO;
	private Order       order;
	//amount and length is shown pr.centimeter
	//General formula: amount / area
	//the amount is found in the examples of fog


	public BillOfMaterialsCalculator(Order order) throws DataException
	{
		materialDAO = new MaterialDAO("APP");
		this.order = order;
		this.area = order.getHeight() / order.getLength();
	}

	public List<OrderLine> createCarportListWithoutShed() throws MaterialException
	{
		setValuesWithoutShed();
		return order.getOrderlines();
	}

	public int caportPrice()
	{
		for (OrderLine orderLine : order.getOrderlines()) {
			totalPrice += orderLine.getPriceForOrderLine();
		}
		return totalPrice;
	}

	private void setValuesWithoutShed() throws MaterialException
	{
		Material m_rafter = findMaterial("25x200mm. trykimp. Brædt");
		order.addToOrderLines(rafterUnderStern(m_rafter));
		order.addToOrderLines(rafterSides(m_rafter));

		Material m_lath = findMaterial("38x73mm. Lægte ubh.");
		order.addToOrderLines(lath(m_lath));

		Material m_pole = findMaterial("97x97mm. trykimp. Stolpe");
		order.addToOrderLines(pole(m_pole));

		Material m_nail = findMaterial("4,5 x 60 mm. skruer 200stk");
		order.addToOrderLines(nail(m_nail));

		Material m_bracket = findMaterial("4,0 x 50 mm. beslagsskruer 250stk");
		order.addToOrderLines(bracket(m_bracket));

		//shed material
		/*
		Material m_reglar = findMaterial("45x95mm. Reglar ubh.");
		order.addToOrderLines(reglarShedHeadBoards(m_reglar));
		order.addToOrderLines(reglarShedSides(m_reglar)); */


	}

	public void saveOrderLinesToDB() throws OrderLineException, DataException
	{
		for (OrderLine orderLine : order.getOrderlines())
			OrderFacade.createOrderLine(orderLine);
	}

	private OrderLine reglarShedSides(Material m_reglar)
	{
		final double reglarShedSideLength = 0.00057692307;
		final double reglarShedSideAmount = 0.00002564102;
		int          length               = (int) Math.ceil(reglarShedSideLength * area);
		int          amount               = (int) Math.ceil(reglarShedSideAmount * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(amount)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(m_reglar.getDescription())
				.insertSecondDescription("løsholter til skur sider")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_reglar.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine reglarShedHeadBoards(Material reglar)
	{

		final double REGLAR_LENGTH = 0.00057692307;
		final double REGLAR_AMOUNT = 0.00002564102;
		int          length        = (int) Math.ceil(REGLAR_LENGTH * area);
		int          amount        = (int) Math.ceil(REGLAR_AMOUNT * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(amount)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(reglar.getDescription())
				.insertSecondDescription("løsholter til skur gavle")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(reglar.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private Material findMaterial(String description) throws MaterialException
	{
		return materialDAO.materialByDescription(description);
	}

	public OrderLine lath(Material lath) throws MaterialException
	{
		final double LATH_LENGTH = 0.00089743589;
		int          LATH_AMOUNT = 1;
		int          length      = (int) Math.ceil(LATH_LENGTH * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(LATH_AMOUNT)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(lath.getDescription())
				.insertSecondDescription("til z på bagside af dør")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(lath.getId())
				.insertOrderId(order.getId())
				.build();
	}


	private OrderLine pole(Material pole) //rename: pole
	{
		final double POLESLENGTH = 0.000641026;
		int          length      = (int) Math.ceil(POLESLENGTH * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(stakeAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(pole.getDescription())
				.insertSecondDescription("Stolper nedgraves 90 cm. i jord")
				.insertPriceForOrderLine(stakeAmount() * pole.getPricePrUnit())
				.insertIsTreeOrRoof(true)
				.insertMaterialId(pole.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int stakeAmount()
	{
		final int STAKESTARTPOSITION = 110;
		final int STAKEDISTANCE      = 250;
		int       stakeAmount        = 0;
		if (order.getWidth() < 360)
			return 4;
		for (int i = STAKESTARTPOSITION; i < order.getWidth(); i += STAKEDISTANCE + TREEWIDTH)
			stakeAmount += 1;
		return stakeAmount;
	}

	private OrderLine rafterUnderStern(Material rafter)
	{
		final double STAKES_LENGTH_UNDER_STERN = 0.000769231;
		int          length                    = (int) Math.ceil(STAKES_LENGTH_UNDER_STERN * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(rafterAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder til for & bag ende")
				.insertPriceForOrderLine(rafterAmount() * rafter.getPricePrUnit())
				.insertIsTreeOrRoof(true)
				.insertMaterialId(rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine rafterSides(Material rafter)
	{
		final double STAKES_LENGTH_SIDES = 0.001153846;
		int          length              = (int) Math.ceil(STAKES_LENGTH_SIDES * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(rafterAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder til siderne")
				.insertPriceForOrderLine(rafterAmount() * rafter.getPricePrUnit() + EKSTRA_LENGTH_STAKE)
				.insertIsTreeOrRoof(true)
				.insertMaterialId(rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int rafterAmount()
	{
		final int RAFTERDISTANCE         = 55;
		final int MINIMUMSTAKEDISTANCE   = 360;
		int       rafterAmountUnderStern = 0;
		if (order.getWidth() < MINIMUMSTAKEDISTANCE)
			return 4; //the amount of rafters for 4 stakes
		for (int x = rafterAmountUnderStern; x < order.getWidth(); x += RAFTERDISTANCE + TREEWIDTH)
			rafterAmountUnderStern += 1;
		return rafterAmountUnderStern;
	}

	private OrderLine bracket(Material bracket)
	{
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(bracketAmount())
				.insertLength(0)
				.insertUnit("pakke")
				.insertFirstDescription(bracket.getDescription())
				.insertSecondDescription("Til montering af universalbeslag + hulbånd")
				.insertPriceForOrderLine(bracketAmount() * bracket.getPricePrUnit())
				.insertIsTreeOrRoof(false)
				.insertMaterialId(bracket.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int bracketAmount() //190 mm
	{
		final int BRACKETS_EACH_STAKE = 2;
		int       bracketAmount       = 4; //extra brackets
		for (int x = 0; x < stakeAmount(); x++)
			bracketAmount += BRACKETS_EACH_STAKE;
		return bracketAmount; //left and right */
	}

	private OrderLine nail(Material nail)
	{
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(nailsAmount())
				.insertLength(0)
				.insertUnit("pakke")
				.insertFirstDescription(nail.getDescription())
				.insertSecondDescription("Til montering af stern&vandbrædt")
				.insertPriceForOrderLine(nailsAmount() * nail.getPricePrUnit())
				.insertIsTreeOrRoof(false)
				.insertMaterialId(nail.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int nailsAmount()
	{
		final int galjeScrews         = 8;
		final int screwsForOneBracket = 9;
		int       galje               = 4 * galjeScrews; //always 4 galjer
		int       bracketNails        = 0;
		for (int x = 0; x < rafterAmount(); x++)
			bracketNails = screwsForOneBracket * bracketAmount(); //beslagsskruer
		return bracketNails + galje; //tbc need to calculate different kind of screws etc.*/
	}

}
