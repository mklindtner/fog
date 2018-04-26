package data.exceptions;

public class UserAccessException extends Exception
{
	public UserAccessException(Throwable throwException) {
		super(throwException);
	}

	public UserAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public UserAccessException( String message ) {
		super( message );
	}
}
