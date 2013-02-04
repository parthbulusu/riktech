
package com.riktech.stp.exceptions;

public class TechnologyDaoException extends DaoException
{
	/**
	 * Method 'TechnologyDaoException'
	 * 
	 * @param message
	 */
	public TechnologyDaoException(String message)
	{
		super(message);
	}

	/**
	 * Method 'TechnologyDaoException'
	 * 
	 * @param message
	 * @param cause
	 */
	public TechnologyDaoException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
