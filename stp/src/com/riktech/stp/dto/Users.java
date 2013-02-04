
package com.riktech.stp.dto;

import com.riktech.stp.dao.*;
import com.riktech.stp.factory.*;
import com.riktech.stp.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class Users   extends BasicDTO
{
	/** 
	 * This attribute maps to the column USER_NAME in the USERS table.
	 */
	protected String userName;

	/** 
	 * This attribute maps to the column USER_PASS in the USERS table.
	 */
	protected String userPass;

	/** 
	 * This attribute maps to the column FIRST_NAME in the USERS table.
	 */
	protected String firstName;

	/** 
	 * This attribute maps to the column LAST_NAME in the USERS table.
	 */
	protected String lastName;

	/** 
	 * This attribute maps to the column EMAIL in the USERS table.
	 */
	protected String email;

	/**
	 * Method 'Users'
	 * 
	 */
	public Users()
	{
	}

	/**
	 * Method 'getUserName'
	 * 
	 * @return String
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Method 'setUserName'
	 * 
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Method 'getUserPass'
	 * 
	 * @return String
	 */
	public String getUserPass()
	{
		return userPass;
	}

	/**
	 * Method 'setUserPass'
	 * 
	 * @param userPass
	 */
	public void setUserPass(String userPass)
	{
		this.userPass = userPass;
	}

	/**
	 * Method 'getFirstName'
	 * 
	 * @return String
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Method 'setFirstName'
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Method 'getLastName'
	 * 
	 * @return String
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Method 'setLastName'
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
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
		
		if (!(_other instanceof Users)) {
			return false;
		}
		
		final Users _cast = (Users) _other;
		if (userName == null ? _cast.userName != userName : !userName.equals( _cast.userName )) {
			return false;
		}
		
		if (userPass == null ? _cast.userPass != userPass : !userPass.equals( _cast.userPass )) {
			return false;
		}
		
		if (firstName == null ? _cast.firstName != firstName : !firstName.equals( _cast.firstName )) {
			return false;
		}
		
		if (lastName == null ? _cast.lastName != lastName : !lastName.equals( _cast.lastName )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
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
		
		if (userPass != null) {
			_hashCode = 29 * _hashCode + userPass.hashCode();
		}
		
		if (firstName != null) {
			_hashCode = 29 * _hashCode + firstName.hashCode();
		}
		
		if (lastName != null) {
			_hashCode = 29 * _hashCode + lastName.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return UsersPk
	 */
	public UsersPk createPk()
	{
		return new UsersPk(userName);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.Users: " );
		ret.append( "userName=" + userName );
		ret.append( ", userPass=" + userPass );
		ret.append( ", firstName=" + firstName );
		ret.append( ", lastName=" + lastName );
		ret.append( ", email=" + email );
		return ret.toString();
	}

}
