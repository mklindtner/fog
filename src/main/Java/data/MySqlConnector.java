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
		try {
			insertLocalSourceInformation("fog", "Coding4u@snail", "fogdb");
			if (con == null) {
				con = source.getConnection();
				//con.setAutoCommit(false);
			}
			return con;
		} catch (SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	private static void insertLocalSourceInformation(String username, String password, String databaseName) {
		if ( source == null ) {
			source = new MysqlDataSource();
			source.setUser(username);
			source.setPassword(password);
			source.setDatabaseName(databaseName);
		}
	}

	public static Connection connectLocalTestMysql() throws DataAccessException
	{
		try {
			insertLocalSourceInformation("root", "Hightech4u", "fogtest");
			if(con == null)
				con = source.getConnection();
			return con;
		}catch(SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}



	/**
	 * cloudTest server
	 *
	 * @return
	 * @throws DataAccessException
	 */

	public static Connection connectCloudMySql() throws DataAccessException
	{
		try {
			openTestCloudDB(); //this doesn't work due to ssh i think
			if(con == null)
				con = source.getConnection();
			return con;
		} catch (SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	//this should be refactored into sql connection when global environment works
	private static void openTestCloudDB()
	{
		final String DBNAME = "fogTestdb";
		final String HOST = "159.89.99.45";
		final String URL = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
		final String USERNAME = "fog";
		final String PASSWORD = "Coding4u@snail";
		if (source == null) {
			source = new MysqlDataSource();
			source.setUser("fog");
			source.setPassword("Coding4u@snail");
			source.setDatabaseName("fogTestdb");
			source.setServerName(HOST);
			source.setUrl(URL);
		}
		/*
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL, USERNAME, PASSWORD);*/
	}

		/*
		final String HOST   = "159.89.99.45";
		final String DBNAME = "fogTestdb";
		final String PASSWORD = "Coding4u@snail";
		final String USERNAME = "fog";
		final String URL    = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		/*
		if (source == null) {

			source = new MysqlDataSource();
			source.setUser("fog");
			source.setPassword("Coding4u@snail");
			source.setDatabaseName("fogTestdb");
			source.setServerName(HOST);
			source.setUrl(URL);
		} */

//different way of setting up a connection
		/*
		if (con == null) {
			final String USERNAME = "fog";
			final String PASSWORD = "Coding4u@snail";
			final String HOST     = "159.89.99.45";
			final String DBNAME   = "fogdb";
			final String URL      = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return con; */

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
