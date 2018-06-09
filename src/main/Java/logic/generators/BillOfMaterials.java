package logic.generators;

import data.MySQLDAO.MaterialDAO;
import entities.OrderEntities.Material;
import entities.OrderEntities.Order;
import entities.OrderEntities.OrderLine;
import data.exceptions.DataException;
import data.exceptions.MaterialException;
import data.exceptions.OrderLineException;
import logic.generators.facades.OrderFacadeImpl;
import logic.generators.facades.OrderFacade;

import java.util.List;

public class BillOfMaterials
{
	private int totalPrice = 0, area;
	private final double TREEWIDTH           = 0.97;
	private final int    EKSTRA_LENGTH_STAKE = 40, NOLENGTH = 0;
	private MaterialDAO materialDAO;
	private Order       order;
	private final String PLANK_LARGE           = "25x200mm. trykimp. Brædt";
	private final String PLANK_MEDIUM          = "25x125mm. trykimp. Brædt";
	private final String LATH                  = "38x73mm. Lægte ubh.";
	private final String REGLAR                = "45x95mm. Reglar ubh.";
	private final String RAFTER                = "45x195mm. spærtræ ubh.";
	private final String POLE                  = "97x97mm. trykimp. Stolpe";
	private final String PLANKSMALL            = "19x100mm. trykimp. Brædt";
	private final String PLASTMO_ECOLITE_BLUE  = "Plastmo Ecolite blåtonet";
	private final String PLASTMO_BOTTOM_SCREWS = "Plastmo bundskruer 200stk";
	private final String PERFORATED_BAND       = "hulbånd 1x20mm. 10mtr.";
	private final String UNIVERSAL_RIGHT       = "universal 190mm højre";
	private final String UNIVERSAL_LEFT        = "universal 190mm venstre";
	private final String NAIL                  = "4,5 x 60 mm. skruer 200stk";
	private final String BRACKET               = "4,0 x 50 mm. beslagsskruer 250stk";
	private final String CARRIAGE_BOLT         = "bræddebolt 10 x 120 mm";
	private final String SQUARE_SLICES         = "firkantskiver 40x40x11mm";
	private final String SCREWS_FIFTY          = "4,5 x 50 mm. skruer 300 stk";
	private final String SCREWS_SEVENTY        = "4,5 x 70 mm. skruer 400 stk";


	//amount and length is shown pr.centimeter
	//General formula: amount / area
	//the amount is found in the examples of fog


	public BillOfMaterials(Order order) throws DataException
	{
		materialDAO = new MaterialDAO("APP");
		this.order = order;
		this.area = order.getWidth() * order.getLength();
	}

	public BillOfMaterials(Order order, String connectionString) throws DataException
	{
		materialDAO = new MaterialDAO(connectionString);
		this.order = order;
		this.area = order.getWidth() * order.getLength();
	}

	public int caportPrice()
	{
		for (OrderLine orderLine : order.getOrderLines()) {
			totalPrice += orderLine.getPriceForOrderLine();
		}
		return totalPrice;
	}

	public List<OrderLine> createCarportListWithoutShed() throws MaterialException
	{
		setValuesWithoutShed();
		return order.getOrderLines();
	}

	public List<OrderLine> createCarportList() throws MaterialException
	{
		setValuesWithShed();
		return order.getOrderLines();
	}

	public Order getOrder() {
		return order;
	}

	public void saveOrderLinesToDB(String connectionString) throws OrderLineException, DataException
	{
		OrderFacade orderFacade = new OrderFacadeImpl();
		orderFacade.getInstanceOrderLineDAO(connectionString);
		for (OrderLine orderLine : order.getOrderLines())
			orderFacade.createOrderLine(orderLine);
	}

	private void setValuesWithShed() throws MaterialException
	{
		setValuesWithoutShed();
		setValuesForShed();
	}

	private void setValuesWithoutShed() throws MaterialException
	{
		Material m_plankLarge = findMaterial(PLANK_LARGE);
		order.addToOrderLines(plankUnderStern(m_plankLarge));
		order.addToOrderLines(plankSides(m_plankLarge));

		Material m_plankMedium = findMaterial(PLANK_MEDIUM);
		order.addToOrderLines(plankMediumOverStern(m_plankMedium));
		order.addToOrderLines(plankMediumSides(m_plankMedium));

		Material m_lath = findMaterial(LATH);
		order.addToOrderLines(lath(m_lath));

		Material m_rafter = findMaterial(RAFTER);
		order.addToOrderLines(rafterSides(m_rafter));
		order.addToOrderLines(RafterMountRem(m_rafter));

		Material m_pole = findMaterial(POLE);
		order.addToOrderLines(pole(m_pole));

		Material m_plankSmall = findMaterial(PLANKSMALL);
		order.addToOrderLines(plankShedClothing(m_plankSmall));
		order.addToOrderLines(plankSternSides(m_plankSmall));
		order.addToOrderLines(plankSternFront(m_plankSmall));

		Material m_plastmoEcoliteBlue = findMaterial(PLASTMO_ECOLITE_BLUE);
		order.addToOrderLines(plastmoBig(m_plastmoEcoliteBlue));
		order.addToOrderLines(plastmoSmall(m_plastmoEcoliteBlue));

		Material m_bottomScrews = findMaterial(PLASTMO_BOTTOM_SCREWS);
		order.addToOrderLines(bottomScrews(m_bottomScrews));

		Material m_perforatedBand = findMaterial(PERFORATED_BAND);
		order.addToOrderLines(performedBand(m_perforatedBand));

		Material m_universalRight = findMaterial(UNIVERSAL_RIGHT);
		Material m_universalLeft  = findMaterial(UNIVERSAL_LEFT);
		order.addToOrderLines(universalBoth(m_universalRight));
		order.addToOrderLines(universalBoth(m_universalLeft));

		Material m_nail = findMaterial(NAIL);
		order.addToOrderLines(nail(m_nail));

		Material m_bracket = findMaterial(BRACKET);
		order.addToOrderLines(bracket(m_bracket));

		Material m_carraigeBolt = findMaterial(CARRIAGE_BOLT);
		order.addToOrderLines(carriageBolt(m_carraigeBolt));

		Material m_squareSlices = findMaterial(SQUARE_SLICES);
		order.addToOrderLines(squareSlices(m_squareSlices));

		Material m_screwsFifty = findMaterial(SCREWS_FIFTY);
		order.addToOrderLines(screwsFifty(m_screwsFifty));

		Material m_screwsSeventy = findMaterial(SCREWS_SEVENTY);
		order.addToOrderLines(screwsSeventy(m_screwsSeventy));

	}

	private void setValuesForShed() throws MaterialException
	{
		Material m_farmgateGrip = findMaterial("stalddørsgreb 50x75");
		order.addToOrderLines(farmgateGrip(m_farmgateGrip));

		Material m_hinge = findMaterial("t hængsel 390 mm");
		order.addToOrderLines(tHing(m_hinge));

		Material m_angelBracket = findMaterial("vinkelbeslag 35");
		order.addToOrderLines(angelBracket(m_angelBracket));

		Material m_reglar = findMaterial("45x95mm. Reglar ubh.");
		order.addToOrderLines(reglarShedHeadBoards(m_reglar));
		order.addToOrderLines(reglarShedSides(m_reglar));

		Material m_rafter = findMaterial(RAFTER);
		order.addToOrderLines(rafterShedSides(m_rafter));
	}

	private OrderLine plankMediumSides(Material m_plankMedium)
	{
		final double PLANK_MEDIUM_SIDES_AMOUNT = 0.000008547;
		final double PLANK_MEDIUM_SIDES_LENGTH = 0.00115384615;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLANK_MEDIUM_SIDES_AMOUNT))
				.insertLength(orderLine_length(PLANK_MEDIUM_SIDES_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plankMedium.getDescription())
				.insertSecondDescription("oversternbrædder til siderne")
				.insertMaterialId(m_plankMedium.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine plankMediumOverStern(Material m_plankMedium)
	{
		final double PLANK_MEDIUM_OVERSTERN_AMOUNT = 0.0000042735;
		final double PLANK_MEDIUM_OVERSTERN_LENGTH = 0.00076923076;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLANK_MEDIUM_OVERSTERN_AMOUNT))
				.insertLength(orderLine_amount(PLANK_MEDIUM_OVERSTERN_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plankMedium.getDescription())
				.insertSecondDescription("oversternbrædder til forenden")
				.insertMaterialId(m_plankMedium.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine angelBracket(Material m_angelBracket)
	{
		final double ANGELBRACKET_AMOUNT = 0.00006837606;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_length(ANGELBRACKET_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("stk")
				.insertFirstDescription(m_angelBracket.getDescription())
				.insertSecondDescription("Til montering af løsholter i skur")
				.insertMaterialId(m_angelBracket.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine tHing(Material m_tHinge)
	{
		final double HINGAMOUNT = 2;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_length(HINGAMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("stk")
				.insertFirstDescription(m_tHinge.getDescription())
				.insertSecondDescription("Til skurdør")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_tHinge.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine farmgateGrip(Material m_farmgateGrip)
	{
		final int FARMGATEAMOUNT = 1;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(FARMGATEAMOUNT)
				.insertLength(NOLENGTH)
				.insertUnit("sæt")
				.insertFirstDescription(m_farmgateGrip.getDescription())
				.insertSecondDescription("Til lås på dør i skur")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_farmgateGrip.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine screwsFifty(Material m_screwsFifty)
	{
		final double SCREWS_FIFTY_AMOUNT = 0.0000042735;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(SCREWS_FIFTY_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("pakke")
				.insertFirstDescription(m_screwsFifty.getDescription())
				.insertSecondDescription("til montering af inderste beklædning")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_screwsFifty.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine screwsSeventy(Material m_screwsSeventy)
	{
		final double SCREWS_SEVENTY_AMOUNT = 0.0000042735;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(SCREWS_SEVENTY_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("pakke")
				.insertFirstDescription(m_screwsSeventy.getDescription())
				.insertSecondDescription("til montering af yderste beklædning")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_screwsSeventy.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine squareSlices(Material m_squareSlices)
	{
		final double SQUARESLICES_AMOUNT = 0.00002564102;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(SQUARESLICES_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("stk")
				.insertFirstDescription(m_squareSlices.getDescription())
				.insertSecondDescription("Til montering af rem på stolper")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_squareSlices.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine carriageBolt(Material m_carraigeBolt)
	{
		final double CARRIAGEBOLT_AMOUNT = 0.00003846153;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(CARRIAGEBOLT_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("stk")
				.insertFirstDescription(m_carraigeBolt.getDescription())
				.insertSecondDescription("Til montering af rem på stolper")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_carraigeBolt.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine universalBoth(Material m_universalRight)
	{
		final double UNIVERSAL_AMOUNT = 0.00003205128;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(UNIVERSAL_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("stk")
				.insertFirstDescription(m_universalRight.getDescription())
				.insertSecondDescription("Til montering af spær på rem")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_universalRight.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine performedBand(Material m_perforated_band)
	{
		final double PERFORMEDBAND_AMOUNT = 0.00001282051;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PERFORMEDBAND_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("rulle")
				.insertFirstDescription(m_perforated_band.getDescription())
				.insertSecondDescription("Til vindkryds på spær")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_perforated_band.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine bottomScrews(Material m_bottom_screws)
	{
		final double BOTTOM_SCREW_AMOUNT = 0.00000641025;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(BOTTOM_SCREW_AMOUNT))
				.insertLength(NOLENGTH)
				.insertUnit("pakke")
				.insertFirstDescription(m_bottom_screws.getDescription())
				.insertSecondDescription("Skruer til tagplader")
				.insertIsTreeOrRoof(false)
				.insertMaterialId(m_bottom_screws.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine plastmoSmall(Material m_plastmo_ecolite_blue)
	{
		final double PLASTMO_SMALL_LENGTH = 0.00076923076;
		final double PLASTMO_SMALL_AMOUNT = 0.00001282051;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLASTMO_SMALL_AMOUNT))
				.insertLength(orderLine_length(PLASTMO_SMALL_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plastmo_ecolite_blue.getDescription())
				.insertSecondDescription("tagplader monteres på spær")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_plastmo_ecolite_blue.getId())
				.insertOrderId(order.getId())
				.build();

	}

	private OrderLine plastmoBig(Material m_plastmo_ecolite_blue)
	{
		final double PLASTMO_BIG_LENGTH = 0.00128205128;
		final double PLASTMO_BIG_AMOUNT = 0.00001282051;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLASTMO_BIG_AMOUNT))
				.insertLength(orderLine_length(PLASTMO_BIG_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plastmo_ecolite_blue.getDescription())
				.insertSecondDescription("tagplader monteres på spær")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_plastmo_ecolite_blue.getId())
				.insertOrderId(order.getId())
				.build();

	}

	private OrderLine plankSternFront(Material m_plank_small)
	{
		final double PLANK_FRONT_LENGTH = 0.00076923076;
		final double PLANK_FRONT_AMOUNT = 0.0000042735;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLANK_FRONT_AMOUNT))
				.insertLength(orderLine_length(PLANK_FRONT_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plank_small.getDescription())
				.insertSecondDescription("vandbrædt på stern i forende")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_plank_small.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int orderLine_length(double constLength)
	{
		return (int) Math.ceil(constLength * area);
	}

	private int orderLine_amount(double constAmount)
	{
		return (int) Math.ceil(constAmount * area);
	}

	private OrderLine plankSternSides(Material m_plank_small)
	{
		final double PLANK_LENGTH = 0.00115384615;
		final double PLANK_AMOUNT = 0.000008547;
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(orderLine_amount(PLANK_AMOUNT))
				.insertLength(orderLine_length(PLANK_LENGTH))
				.insertUnit("stk")
				.insertFirstDescription(m_plank_small.getDescription())
				.insertSecondDescription("vandbrædt på stern i sider")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_plank_small.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine plankShedClothing(Material m_plank_small)
	{
		final double PLANKSHED_Length = 0.00044871794;
		final double PLANSHED_AMOUNT  = 0.00042735042;
		int          length           = (int) Math.ceil(PLANKSHED_Length * area);
		int          amount           = (int) Math.ceil(PLANSHED_AMOUNT * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(amount)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(m_plank_small.getDescription())
				.insertSecondDescription("til beklædning af skur 1 på 2")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_plank_small.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine RafterMountRem(Material m_rafter)
	{
		final double RAFTER_MOUNT_LENGTH           = 0.00128205128;
		final double RAFTER_AMOUNT_EACH_CENTIMETER = 0.00003205128;
		int          length                        = (int) Math.ceil(RAFTER_MOUNT_LENGTH * area);
		int          amount                        = (int) Math.ceil(RAFTER_AMOUNT_EACH_CENTIMETER * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(amount)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(m_rafter.getDescription())
				.insertSecondDescription("Spær, monteres på rem")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine rafterShedSides(Material m_rafter)
	{
		final double RAFTER_SHED_LENGTH = 0.00102564102;
		final int    RAFTER_AMOUNT_SHED = 1;
		int          length             = (int) Math.ceil(RAFTER_SHED_LENGTH * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(RAFTER_AMOUNT_SHED)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(m_rafter.getDescription())
				.insertSecondDescription("Remme i sider, sadles ned i stolper ( skur del, deles)")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine rafterSides(Material m_rafter)
	{
		final double RAFTER_LENGTH            = 0.00128205128;
		final double RAFTER_AMOUNT_CENTIMETER = 0.0000042735;
		int          length                   = (int) Math.ceil(RAFTER_LENGTH * area);
		int          amount                   = (int) Math.ceil(RAFTER_AMOUNT_CENTIMETER * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(amount)
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(m_rafter.getDescription())
				.insertSecondDescription("Remme i sider, sadles ned i stolper")
				.insertIsTreeOrRoof(true)
				.insertMaterialId(m_rafter.getId())
				.insertOrderId(order.getId())
				.build();
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

	private OrderLine plankUnderStern(Material rafter)
	{
		final double STAKES_LENGTH_UNDER_STERN = 0.000769231;
		int          length                    = (int) Math.ceil(STAKES_LENGTH_UNDER_STERN * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(plankAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder til for & bag ende")
				.insertPriceForOrderLine(plankAmount() * rafter.getPricePrUnit())
				.insertIsTreeOrRoof(true)
				.insertMaterialId(rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private OrderLine plankSides(Material rafter)
	{
		final double STAKES_LENGTH_SIDES = 0.001153846;
		int          length              = (int) Math.ceil(STAKES_LENGTH_SIDES * area);
		return new OrderLine
				.OrderLineBuilder()
				.insertAmount(plankAmount())
				.insertLength(length)
				.insertUnit("stk")
				.insertFirstDescription(rafter.getDescription())
				.insertSecondDescription("understernbrædder til siderne")
				.insertPriceForOrderLine(plankAmount() * rafter.getPricePrUnit() + EKSTRA_LENGTH_STAKE)
				.insertIsTreeOrRoof(true)
				.insertMaterialId(rafter.getId())
				.insertOrderId(order.getId())
				.build();
	}

	private int plankAmount()
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
				.insertLength(NOLENGTH)
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
				.insertLength(NOLENGTH)
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
		for (int x = 0; x < plankAmount(); x++)
			bracketNails = screwsForOneBracket * bracketAmount(); //beslagsskruer
		return bracketNails + galje;
	}
}
