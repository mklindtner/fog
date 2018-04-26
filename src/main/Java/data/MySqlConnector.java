package data;

import data.exceptions.DataAccessException;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector
{
	private static MysqlDataSource source = null;
	private static Connection      con;

	private static void setInformationForMySql() {
		if(source == null) {
			source = new MysqlDataSource();
			source.setUser("root");
			source.setPassword("Hightech4u");
			source.setDatabaseName("fogdb");
		}
	}

	public static Connection getMySqlConnection() throws DataAccessException
	{
		setInformationForMySql();
		try {
			if(con == null) {
				con = source.getConnection();
				//con.setAutoCommit(false);
			}
		return con;
		} catch(SQLException throwSql) {
			throw new DataAccessException(throwSql);
		}
	}
}
