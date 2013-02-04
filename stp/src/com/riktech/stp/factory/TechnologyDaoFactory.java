
package com.riktech.stp.factory;

import java.sql.Connection;
import com.riktech.stp.dao.*;
import com.riktech.stp.jdbc.*;

public class TechnologyDaoFactory
{
	public static final int TECHNOLOGY_BASED_TS=1;
	public static final int SCENARIO_BASED_TS=2;
	/**
	 * Method 'create'
	 * 
	 * @return TechnologyDao
	 */
	public static TechnologyDao create()
	{
		return create(TECHNOLOGY_BASED_TS);
	}
	/**
	 * Method 'create'
	 * 
	 * @return TechnologyDao
	 */
	public static TechnologyDao create(int troubleShootingType)
	{
		System.out.println("***troubleShootingType***"+troubleShootingType);
		if(troubleShootingType==SCENARIO_BASED_TS)
			return new ScenarioDaoImpl();
			
		else
			return new TechnologyDaoImpl();		
		
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return TechnologyDao
	 */
	public static TechnologyDao create(Connection conn)
	{
		return new TechnologyDaoImpl( conn );
	}

}
