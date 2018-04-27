package logic;

import data.dao.OrderDAO;
import data.entities.OrderEntities.Order;
import data.exceptions.DataAccessException;
import data.exceptions.OrderAccessException;

import java.util.List;

public class OrderFacade
{
	public static List<Order> allOthersWithoutShed() throws OrderAccessException, DataAccessException
	{
		OrderDAO orderDAO = new OrderDAO();
		return orderDAO.allOrdersWithoutShed();
	}
}
