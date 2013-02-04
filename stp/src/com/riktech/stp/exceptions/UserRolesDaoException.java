

package com.riktech.stp.exceptions;

public class UserRolesDaoException extends DaoException
{
	/**
	 * Method 'UserRolesDaoException'
	 * 
	 * @param message
	 */
	public UserRolesDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'UserRolesDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public UserRolesDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
