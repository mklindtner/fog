package data.exceptions;

public class UserException extends Exception
{
	public UserException(Throwable throwException) {
		super(throwException);
	}

	public UserException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public UserException(String message ) {
		super( message );
	}
}
