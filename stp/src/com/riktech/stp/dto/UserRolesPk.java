

package com.riktech.stp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the USER_ROLES table.
 */
public class UserRolesPk implements Serializable
{
	protected String roleName;

	protected String userName;

	/** 
	 * Sets the value of roleName
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/** 
	 * Gets the value of roleName
	 */
	public String getRoleName()
	{
		return roleName;
	}

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
	 * Method 'UserRolesPk'
	 * 
	 */
	public UserRolesPk()
	{
	}

	/**
	 * Method 'UserRolesPk'
	 * 
	 * @param roleName
	 * @param userName
	 */
	public UserRolesPk(final String roleName, final String userName)
	{
		this.roleName = roleName;
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
		
		if (!(_other instanceof UserRolesPk)) {
			return false;
		}
		
		final UserRolesPk _cast = (UserRolesPk) _other;
		if (roleName == null ? _cast.roleName != roleName : !roleName.equals( _cast.roleName )) {
			return false;
		}
		
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
		if (roleName != null) {
			_hashCode = 29 * _hashCode + roleName.hashCode();
		}
		
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
		ret.append( "com.riktech.stp.dto.UserRolesPk: " );
		ret.append( "roleName=" + roleName );
		ret.append( ", userName=" + userName );
		return ret.toString();
	}

}
