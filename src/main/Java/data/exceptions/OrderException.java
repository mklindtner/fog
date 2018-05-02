package data.exceptions;

public class OrderException extends Exception
{
	public OrderException(String message, Throwable throwException) {
		super(message, throwException);
	}
	public OrderException(Throwable throwException) {
		super(throwException);
	}
}
