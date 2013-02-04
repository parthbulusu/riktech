

package com.riktech.stp.exceptions;

public class UserCommentsDaoException extends DaoException
{
	/**
	 * Method 'UserCommentsDaoException'
	 * 
	 * @param message
	 */
	public UserCommentsDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserCommentsDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserCommentsDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
