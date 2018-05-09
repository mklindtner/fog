package data;

import com.mysql.cj.jdbc.MysqlDataSource;
import data.exceptions.DataException;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection con;
	private final static String FILE = "environment.properties";
	//String connectionSelection

	//instead of a file I could use an environment variable in the shell
	public static Connection createConnection(String connectionSelection) throws DataException
	{
		//this.connectionSelection = connectionSelect
			//remove other params
		/*
		Properties  properties  = new Properties();
		try(InputStream inputStream = MySqlConnector.class.getResourceAsStream(FILE)) {
			if (inputStream == null)
				throw new FileNotFoundException();
			properties.load(inputStream);
			String env = properties.getProperty("HOST");
			return (env.equals("CLOUD")) ? findHostCloud(connectionSelection) : findHostLocal(connectionSelection);
		} catch(IOException throwIO) {
			throw new DataAccessException(throwIO);
		}*/
		String env = "LOCAL"; //CLOUD
		return (env.equals("CLOUD")) ? findHostCloud(connectionSelection) : findHostLocal(connectionSelection);
	}

	private static Connection findHostLocal(String connectionSelection) throws DataException
	{
		if (connectionSelection.equals("APP"))
			return connectLocalMySql();
		if (connectionSelection.equals("TEST"))
			return connectLocalTestMysql();
		throw new DataException();
	}

	private static Connection findHostCloud(String connectionSelection) throws DataException
	{
		if (connectionSelection.equals("APP"))
			return connectCloudMySql();
		if (connectionSelection.equals("TEST"))
			return connectTestCloudMySql();
		throw new DataException();
	}

	/**
	 * localServer
	 */
	private static Connection connectLocalMySql() throws DataException
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

	/**
	 * used for fog cloud server, fog wasn't included as we only operate on one server-side, should this change the
	 * rename: connectionFogCloudMySql()
	 *
	 * @return
	 */
	private static Connection connectCloudMySql() throws DataException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogdb");
		return connectionToSource();
	}

}
