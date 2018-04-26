package data.exceptions;

public class LoginException extends Exception
{
	public LoginException(Throwable throwException) {
		super(throwException);
	}

	public LoginException(String message) {
		super(message);
	}
}
