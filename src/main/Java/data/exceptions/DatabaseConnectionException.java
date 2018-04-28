package data.exceptions;

public class DatabaseConnectionException extends Exception
{
	public DatabaseConnectionException(Throwable throwException) {
		super(throwException);
	}
}
