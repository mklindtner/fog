package data;

import com.mysql.cj.jdbc.MysqlDataSource;
import data.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection con;

	/**
	 * localServer
	 *
	 * @return
	 * @throws DataAccessException
	 */
	public static Connection connectLocalMySql() throws DataAccessException
	{
		insertLocalSourceInformation("fog", "Coding4u@snail", "fogdb");
		return connectionToSource();

	}

	private static void insertLocalSourceInformation(String username, String password, String databaseName)
	{
		if (source == null) {
			source = new MysqlDataSource();
			source.setUser(username);
			source.setPassword(password);
			source.setDatabaseName(databaseName);
		}
	}

	private static Connection connectionToSource() throws DataAccessException
	{
		try {
			if (con == null)
				con = source.getConnection();
			return con;
		} catch (SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}

	}

	public static Connection connectLocalTestMysql() throws DataAccessException
	{
		insertLocalSourceInformation("root", "Hightech4u", "fogtest");
		return connectionToSource();
	}

	private static void insertRemoteSourceInformation(String MysqlUsername, String MysqlPassword, String host, String dbName)
	{
		final String URL = String.format("jdbc:mysql://%s:3306/%s", host, dbName);
		if (source == null) {
			source = new MysqlDataSource();
			source.setUser(MysqlUsername);
			source.setPassword(MysqlPassword);
			source.setDatabaseName(dbName);
			source.setServerName(host);
			source.setUrl(URL);
		}
	}

	public static Connection connectTestCloudMySql() throws DataAccessException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogTestdb");
		return connectionToSource();
	}

	/**
	 * used for fog cloud server, fog wasn't included as we only operate on one server-side, should this change the
	 * rename: connectionFogCloudMySql()
	 *
	 * @return
	 */
	public static Connection connectCloudMySql() throws DataAccessException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogdb");
		return connectionToSource();
	}

	//String devState = System.getenv("developmentState");
		/* I should set env variables and use this instead
		switch (devState) {
			case "development": //local
				source.setUser("fog");
				source.setPassword("Coding4u@snail");
				source.setDatabaseName("fogdb");
				break;
			case "production": //cloud
				source.setUser("fog");
				source.setPassword("Coding4u@snail");
				source.setDatabaseName("fogdb");
				break;
		}*/
}
