package com.myproject.productrecommendor.exception;

/**
 * @author NKHarish
 * This class is custom Exception class which displays custom error messages.
 */
public class CustomException extends Exception
{
	private static final long serialVersionUID = -3531896933230753110L;
		/**
	 * Default Constructor.
	 */
	public CustomException()
	{
	}
	
	/**
	 * Constructor with custom message.
	 * @param message
	 */
	public CustomException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor with exception cause as parameter.
	 * @param cause
	 */
	public CustomException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * Constructor with custom message and exception cause as parameters.
	 * @param message
	 * @param cause
	 */
	public CustomException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
