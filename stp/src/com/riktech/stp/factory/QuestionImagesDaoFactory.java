
package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class QuestionImagesDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return QuestionImagesDao
	 */
	public static QuestionImagesDao create()
	{
		return new QuestionImagesDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return QuestionImagesDao
	 */
	public static QuestionImagesDao create(Connection conn)
	{
		return new QuestionImagesDaoImpl( conn );
	}

}
