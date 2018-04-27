package data;

import data.exceptions.DataAccessException;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection con;

	private static void setInformationForLocalMySql()
	{
		if (source == null) {
			source = new MysqlDataSource();
			source.setUser("fog");
			source.setPassword("Coding4u@snail");
			source.setDatabaseName("fogdb");
		}
	}

	public static Connection getLocalMySqlConnection() throws DataAccessException
	{
		setInformationForLocalMySql();
		try {
			if (con == null) {
				con = source.getConnection();
				//con.setAutoCommit(false);
			}
			return con;
		} catch (SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}

	public static void setInformationForCloudMySql() {
		if(source == null) {

		}
	}
}
