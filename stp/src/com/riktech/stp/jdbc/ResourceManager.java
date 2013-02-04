package com.riktech.stp.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ResourceManager
{
    //private static String JDBC_DRIVER   = "oracle.jdbc.driver.OracleDriver";
    //private static String JDBC_URL      = "jdbc:oracle:thin:@xpldd01.ieee.org:1525:DXPLR";

    //private static String JDBC_USER     = "xpl";
    //private static String JDBC_PASSWORD = "xpl_dxplr";


    public static synchronized Connection getConnection()	throws SQLException, NamingException
    {
            	Context initContext = new InitialContext();
            	Context envContext  = (Context)initContext.lookup("java:/comp/env");
            	DataSource ds = (DataSource)envContext.lookup("jdbc/stp");
            	return ds.getConnection();            	
    }


	public static void close(Connection conn)
	{
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt)
	{
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(ResultSet rs)
	{
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

}
