package logic.generators;

import data.dao.MaterialDAO;
import data.entities.OrderEntities.Material;
import data.entities.OrderEntities.MaterialDimensions;
import data.entities.OrderEntities.Order;
import data.entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.MaterialException;

import java.util.List;

public class BillOfMaterialsCalculator
{
	private int totalPrice = 0, area;
	private final double TREEWIDTH                 = 0.97; //mm
	private final int    MINIMUMSTAKEDISTANCE      = 360;
	private final int    RAFTERDISTANCE            = 55;
	private final int    REMS                      = 2;
	private final int    STAKESTARTPOSITION        = 110;
	private final int    STAKEDISTANCE             = 250;
	private final int    BRACKETS_EACH_STAKE       = 2;
	private final double REMHEIGHT                 = 1.95;
	private final double REMWIDTH                  = 0.45;
	private final int    hulbånd                   = 2;
	private final int    galjeScrews               = 8;
	private final int    screwsForOneBracket       = 9;
	private final int    EKSTRA_LENGTH_STAKE       = 40;
	//in centimeter
	private final double PolesEachCentimeter       = 0.000023504;
	private final double POLESLENGTH               = 0.000641026;
	private final double STAKES_LENGTH_UNDER_STERN = 0.000769231;
	private final double STAKES_LENGTH_SIDES       = 0.001153846;
	private MaterialDAO materialDAO;
	//private Map<String, Material> materials;
	private Order       order;


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
		Material m_nail = findMaterial("4,5 x 60 mm. skruer 200stk");
		order.addToOrderLines(nail(m_nail));

		Material m_pole = findMaterial("97x97mm. trykimp. Stolpe");
		order.addToOrderLines(pole(m_pole));

		Material m_rafter = findMaterial("25x200mm. trykimp. Brædt");
		order.addToOrderLines(rafterUnderStern(m_rafter));
		order.addToOrderLines(rafterSides(m_rafter));

		Material m_bracket = findMaterial("4,0 x 50 mm. beslagsskruer 250stk");
		order.addToOrderLines(bracket(m_bracket));
	}

	private Material findMaterial(String description) throws MaterialException
	{
		return materialDAO.materialByDescription(description);
	}


	private OrderLine pole(Material pole) //rename: pole
	{
		int length = (int) Math.ceil(POLESLENGTH * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(stakeAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(pole.getDescription())
				.insertSecondDescription("Stolper nedgraves 90 cm. i jord")
				.insertPriceForOrderLine(stakeAmount() * pole.getPricePrUnit())
				.insertIsTreeOrRoff(true)
				.build();
	}

	private int stakeAmount()
	{
		int stakeAmount = 0;
		if (order.getWidth() < 360)
			return 4;
		for (int i = STAKESTARTPOSITION; i < order.getWidth(); i += STAKEDISTANCE + TREEWIDTH)
			stakeAmount += 1;
		return stakeAmount;
	}

	private OrderLine rafterUnderStern(Material rafter)
	{
		int length = (int) Math.ceil(STAKES_LENGTH_UNDER_STERN * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(rafterAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder	til	for	& bag ende")
				.insertPriceForOrderLine(rafterAmount() * rafter.getPricePrUnit())
				.insertIsTreeOrRoff(true)
				.build();
	}

	private OrderLine rafterSides(Material rafter)
	{
		int length = (int) Math.ceil(STAKES_LENGTH_SIDES * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(rafterAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder	til	siderne")
				.insertPriceForOrderLine(rafterAmount() * rafter.getPricePrUnit() + EKSTRA_LENGTH_STAKE)
				.insertIsTreeOrRoff(true)
				.build();
	}

	private int rafterAmount()
	{
		int rafterAmountUnderStern = 0;
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
				.insertIsTreeOrRoff(false)
				.build();
	}

	private int bracketAmount() //190 mm
	{
		int bracketAmount = 4; //extra brackets
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
				.insertIsTreeOrRoff(false)
				.build();
	}

	private int nailsAmount()
	{
		//add more
		int galje        = 4 * galjeScrews;
		int bracketNails = 0;
		for (int x = 0; x < rafterAmount(); x++)
			bracketNails = screwsForOneBracket * bracketAmount(); //beslagsskruer
		return bracketNails + galje; //tbc need to calculate different kind of screws etc.*/
	}

	private int nailsForBracket()
	{
		return 0; //implement me
	}
}
