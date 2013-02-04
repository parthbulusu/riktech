

package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class QuestionBankDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return QuestionBankDao
	 */
	public static QuestionBankDao create()
	{
		return new QuestionBankDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return QuestionBankDao
	 */
	public static QuestionBankDao create(Connection conn)
	{
		return new QuestionBankDaoImpl( conn );
	}

}
