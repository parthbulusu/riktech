
package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class AnswerChoicesDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return AnswerChoicesDao
	 */
	public static AnswerChoicesDao create()
	{
		return new AnswerChoicesDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return AnswerChoicesDao
	 */
	public static AnswerChoicesDao create(Connection conn)
	{
		return new AnswerChoicesDaoImpl( conn );
	}

}
