package data.exceptions;


public class DataAccessException extends Exception
{
	public DataAccessException(Throwable throwing) {
		super(throwing);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException() {
		super();
	}
}
