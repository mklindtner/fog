package data.exceptions;

public class ApplicationException extends RuntimeException
{
	public ApplicationException(Throwable throwException)
	{
		super(throwException);
	}
}
