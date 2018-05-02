package data.exceptions;


public class DataException extends Exception
{
	public DataException(Throwable throwing) {
		super(throwing);
	}

	public DataException(String message) {
		super(message);
	}

	public DataException() {
		super();
	}
}
