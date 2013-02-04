
package com.riktech.stp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the USERS table.
 */
public class UsersPk implements Serializable
{
	protected String userName;

	/** 
	 * Sets the value of userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/** 
	 * Gets the value of userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Method 'UsersPk'
	 * 
	 */
	public UsersPk()
	{
	}

	/**
	 * Method 'UsersPk'
	 * 
	 * @param userName
	 */
	public UsersPk(final String userName)
	{
		this.userName = userName;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof UsersPk)) {
			return false;
		}
		
		final UsersPk _cast = (UsersPk) _other;
		if (userName == null ? _cast.userName != userName : !userName.equals( _cast.userName )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (userName != null) {
			_hashCode = 29 * _hashCode + userName.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.UsersPk: " );
		ret.append( "userName=" + userName );
		return ret.toString();
	}

}
