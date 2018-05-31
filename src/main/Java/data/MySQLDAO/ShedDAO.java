package data.MySQLDAO;

import data.MySqlConnector;
import data.exceptions.DataException;
import data.exceptions.ShedException;
import entities.OrderEntities.Shed;

import java.sql.*;

public class ShedDAO implements DAO
{
	Connection con;
	public ShedDAO() throws DataException
	{
		con = MySqlConnector.createConnection("APP");
	}

	public ShedDAO(String connectionString) throws DataException
	{
		con = MySqlConnector.createConnection(connectionString);
	}

	public Shed createAndReturnShed(int shedWidth, int shedLength, boolean hasFloor) throws ShedException
	{
		final String SQL = "Insert INTO sheds(length, width, hasFloor) VALUES (?, ?, ?)";

		try(PreparedStatement statement = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1,shedLength);
			statement.setInt(2, shedWidth);
			statement.setBoolean(3, hasFloor);
			statement.executeUpdate();

			ResultSet ids = statement.getGeneratedKeys();
			ids.next();

			return new Shed
					.ShedBuilder()
					.insertLength(shedLength)
					.insertWidth(shedWidth)
					.insertHasFloor(hasFloor)
					.insertShedId(ids.getInt(1))
					.build();
		} catch(SQLException throwSql) {
			throw new ShedException(throwSql);
		}
	}

	public Connection getCon()
	{
		return this.con;
	}
}
