
package com.riktech.stp.dto;


public class UserRoles  extends BasicDTO
{
	/** 
	 * This attribute maps to the column USER_NAME in the USER_ROLES table.
	 */
	protected String userName;

	/** 
	 * This attribute maps to the column ROLE_NAME in the USER_ROLES table.
	 */
	protected String roleName;

	/**
	 * Method 'UserRoles'
	 * 
	 */
	public UserRoles()
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
	 * Method 'getRoleName'
	 * 
	 * @return String
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * Method 'setRoleName'
	 * 
	 * @param roleName
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
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
		
		if (!(_other instanceof UserRoles)) {
			return false;
		}
		
		final UserRoles _cast = (UserRoles) _other;
		if (userName == null ? _cast.userName != userName : !userName.equals( _cast.userName )) {
			return false;
		}
		
		if (roleName == null ? _cast.roleName != roleName : !roleName.equals( _cast.roleName )) {
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
		
		if (roleName != null) {
			_hashCode = 29 * _hashCode + roleName.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return UserRolesPk
	 */
	public UserRolesPk createPk()
	{
		return new UserRolesPk(roleName, userName);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.UserRoles: " );
		ret.append( "userName=" + userName );
		ret.append( ", roleName=" + roleName );
		return ret.toString();
	}

}
