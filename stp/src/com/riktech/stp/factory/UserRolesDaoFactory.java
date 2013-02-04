
package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class UserRolesDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return UserRolesDao
	 */
	public static UserRolesDao create()
	{
		return new UserRolesDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return UserRolesDao
	 */
	public static UserRolesDao create(Connection conn)
	{
		return new UserRolesDaoImpl( conn );
	}

}
