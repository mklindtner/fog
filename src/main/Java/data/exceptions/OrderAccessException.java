package data.exceptions;

public class OrderAccessException extends Exception
{
	public OrderAccessException(String message, Throwable throwException) {
		super(message, throwException);
	}
	public OrderAccessException(Throwable throwException) {
		super(throwException);
	}
}
