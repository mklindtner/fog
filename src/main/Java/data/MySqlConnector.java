package data;

import com.mysql.cj.jdbc.MysqlDataSource;
import data.exceptions.DataException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * MySQLConnector creates a connection to the cloud/local environment dependent on the variable @FILE
 * This class also creates a conection to the test or application database dependent on the
 *
 */
public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection con;
	private final static String FILE               = "LOCAL"; //CLOUD
	private final static String APPLICATION_SERVER = "APP";
	private final static String TEST_SERVER        = "TEST";

	public static Connection createConnection(String connectionSelection) throws DataException
	{
		String env = FILE;
		return (env.equals("CLOUD")) ? findHostCloud(connectionSelection) : findHostLocal(connectionSelection);
	}

	/**
	 * uses the cloud to decide
	 * @param connectionSelection whether the application or test server have been chosen
	 * if neither is true
	 * @throws DataException
	*/
	private static Connection findHostCloud(String connectionSelection) throws DataException
	{
		if (connectionSelection.equals(APPLICATION_SERVER))
			return connectCloudMySql();
		if (connectionSelection.equals(TEST_SERVER))
			return connectTestCloudMySql();
		throw new DataException();
	}

	/**
	 * uses the localhost to decide
	 * @param connectionSelection whether the application or test server have been chosen
	 * if netiher is true
	 * @throws DataException
	 */
	private static Connection findHostLocal(String connectionSelection) throws DataException
	{
		if (connectionSelection.equals(APPLICATION_SERVER))
			return connectLocalMySql();
		if (connectionSelection.equals(TEST_SERVER))
			return connectLocalTestMysql();
		throw new DataException();
	}


	/**
	 * creates the specific connection to an application localHost
	 * @return a the source connection
	 */
	private static Connection connectLocalMySql() throws DataException
	{
		insertLocalSourceInformation("fog", "Coding4u@snail", "fogdb");
		return connectionToSource();
	}

	/**
	 * sets the MySqlDataSource to the specified localhost with a
	 * @param username
	 * @param password
	 * @param databaseName
	 */
	private static void insertLocalSourceInformation(String username, String password, String databaseName)
	{
		if (source == null) {
			source = new MysqlDataSource();
			source.setUser(username);
			source.setPassword(password);
			source.setDatabaseName(databaseName);
		}
	}

	/**
	 * returns the current source, the source behaves as a simple singleton
	 */
	private static Connection connectionToSource() throws DataException
	{
		try {
			if (con == null)
				con = source.getConnection();
			return con;
		} catch (SQLException throwSql) {
			throw new DataException(throwSql);
		}

	}

	/**
	 *
	 */
	private static Connection connectLocalTestMysql() throws DataException
	{
		insertLocalSourceInformation("fog", "Coding4u@snail", "fogtest");
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

	private static Connection connectTestCloudMySql() throws DataException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogTestdb");
		return connectionToSource();
	}

	private static Connection connectCloudMySql() throws DataException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogdb");
		return connectionToSource();
	}

	public static void closeConnections() {
		source = null;
		con = null;
	}

}
