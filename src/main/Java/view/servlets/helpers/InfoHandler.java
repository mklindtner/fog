package view.servlets.helpers;

import configurations.Conf;

import java.util.logging.Level;

/**
 * utility class for presentation layer, the purpose of this class is to log relevant information
 */
public class InfoHandler
{
	public static void createOrder(String username)
	{
		Conf.getLogger().log(Level.INFO, "[UPDATE] {0} created an order", username);
	}
	public static void acceptOrder(String username, int orderId)
	{
		String info = String.format("[UPDATE] {0} accepted order id: {1}", username, orderId);
		Conf.getLogger().log(Level.INFO, info);
	}
}
