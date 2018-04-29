package data;

import com.mysql.cj.jdbc.MysqlDataSource;
import data.exceptions.DataAccessException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection con;
	private final static String FILE      = "environment.txt";

	//instead of a file I could use an environment variable in the shell
	public static Connection createConnection(String connectionSelection) throws DataAccessException
	{
		try {
			BufferedReader br  = new BufferedReader(new FileReader(FILE));
			String         env = br.readLine();
			return (env.equals("CLOUD")) ? findHostCloud(connectionSelection) : findHostLocal(connectionSelection);
		}catch(IOException fio) {
			throw new DataAccessException(fio);
		}
	}

	private static Connection findHostLocal(String connectionSelection) throws DataAccessException {
		if(connectionSelection.equals("APP"))
			return connectLocalMySql();
		if( connectionSelection.equals("TEST"))
			return connectLocalTestMysql();
		throw new DataAccessException();
	}

	private static Connection findHostCloud(String connectionSelection) throws DataAccessException
	{
		if (connectionSelection.equals("APP"))
			return connectCloudMySql();
		if (connectionSelection.equals("TEST"))
			return connectLocalTestMysql();
		throw new DataAccessException();
	}

	/**
	 * localServer
	 */
	private static Connection connectLocalMySql() throws DataAccessException
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

	private static Connection connectLocalTestMysql() throws DataAccessException
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

	private static Connection connectTestCloudMySql() throws DataAccessException
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
	private static Connection connectCloudMySql() throws DataAccessException
	{
		insertRemoteSourceInformation("fogRemote", "", "159.89.99.45", "fogdb");
		return connectionToSource();
	}

}
