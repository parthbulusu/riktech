
package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class UserCommentsDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return UserCommentsDao
	 */
	public static UserCommentsDao create()
	{
		return new UserCommentsDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return UserCommentsDao
	 */
	public static UserCommentsDao create(Connection conn)
	{
		return new UserCommentsDaoImpl( conn );
	}

}
