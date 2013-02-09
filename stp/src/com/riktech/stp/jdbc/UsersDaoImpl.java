

package com.riktech.stp.jdbc;

import com.riktech.stp.dao.*;
import com.riktech.stp.factory.*;
import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class UsersDaoImpl extends AbstractDAO implements UsersDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( UsersDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT USER_NAME, USER_PASS, FIRST_NAME, LAST_NAME, EMAIL,ROLE_NAME FROM V_USERS";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( USER_NAME, USER_PASS, FIRST_NAME, LAST_NAME, EMAIL ) VALUES ( ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET USER_NAME = ?, USER_PASS = ?, FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ? WHERE USER_NAME = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE USER_NAME = ?";

	/** 
	 * Index of column USER_NAME
	 */
	protected static final int COLUMN_USER_NAME = 1;

	/** 
	 * Index of column USER_PASS
	 */
	protected static final int COLUMN_USER_PASS = 2;

	/** 
	 * Index of column FIRST_NAME
	 */
	protected static final int COLUMN_FIRST_NAME = 3;

	/** 
	 * Index of column LAST_NAME
	 */
	protected static final int COLUMN_LAST_NAME = 4;

	/** 
	 * Index of column EMAIL
	 */
	protected static final int COLUMN_EMAIL = 5;

	
	protected static final int COLUMN_ROLE_NAME=6;
	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 6;

	/** 
	 * Index of primary-key column USER_NAME
	 */
	protected static final int PK_COLUMN_USER_NAME = 1;

	/** 
	 * Inserts a new row in the USERS table.
	 */
	public UsersPk insert(Users dto) throws UsersDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setString( index++, dto.getUserName() );
			stmt.setString( index++, dto.getUserPass() );
			stmt.setString( index++, dto.getFirstName() );
			stmt.setString( index++, dto.getLastName() );
			stmt.setString( index++, dto.getEmail() );
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL_INSERT + " with DTO: " + dto);
			}
		
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UsersDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the USERS table.
	 */
	public void update(UsersPk pk, Users dto) throws UsersDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL_UPDATE + " with DTO: " + dto);
			}
		
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setString( index++, dto.getUserName() );
			stmt.setString( index++, dto.getUserPass() );
			stmt.setString( index++, dto.getFirstName() );
			stmt.setString( index++, dto.getLastName() );
			stmt.setString( index++, dto.getEmail() );
			stmt.setString( 6, pk.getUserName() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UsersDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the USERS table.
	 */
	public void delete(UsersPk pk) throws UsersDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL_DELETE + " with PK: " + pk);
			}
		
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setString( 1, pk.getUserName() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UsersDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the USERS table that matches the specified primary-key value.
	 */
	public Users findByPrimaryKey(UsersPk pk) throws UsersDaoException
	{
		return findByPrimaryKey( pk.getUserName() );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_NAME = :userName'.
	 */
	public Users findByPrimaryKey(String userName) throws UsersDaoException
	{
		ArrayList<Users> ret = findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ?", new Object[] { userName } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria ''.
	 */
	public ArrayList<Users> findAll() throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY USER_NAME", null );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<Users> findWhereUserNameEquals(String userName) throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ? ORDER BY USER_NAME", new Object[] { userName } );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_PASS = :userPass'.
	 */
	public ArrayList<Users> findWhereUserPassEquals(String userPass) throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_PASS = ? ORDER BY USER_PASS", new Object[] { userPass } );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'FIRST_NAME = :firstName'.
	 */
	public ArrayList<Users> findWhereFirstNameEquals(String firstName) throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE FIRST_NAME = ? ORDER BY FIRST_NAME", new Object[] { firstName } );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'LAST_NAME = :lastName'.
	 */
	public ArrayList<Users> findWhereLastNameEquals(String lastName) throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE LAST_NAME = ? ORDER BY LAST_NAME", new Object[] { lastName } );
	}

	/** 
	 * Returns all rows from the USERS table that match the criteria 'EMAIL = :email'.
	 */
	public ArrayList<Users> findWhereEmailEquals(String email) throws UsersDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE EMAIL = ? ORDER BY EMAIL", new Object[] { email } );
	}

	/**
	 * Method 'UsersDaoImpl'
	 * 
	 */
	public UsersDaoImpl()
	{
	}

	/**
	 * Method 'UsersDaoImpl'
	 * 
	 * @param userConn
	 */
	public UsersDaoImpl(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "USERS";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected Users fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			Users dto = new Users();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<Users> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<Users> resultList = new ArrayList<Users>();
		while (rs.next()) {
			Users dto = new Users();
			populateDto( dto, rs);
			resultList.add( dto );
		}

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(Users dto, ResultSet rs) throws SQLException
	{
		dto.setUserName( rs.getString( COLUMN_USER_NAME ) );
		dto.setUserPass( rs.getString( COLUMN_USER_PASS ) );
		dto.setFirstName( rs.getString( COLUMN_FIRST_NAME ) );
		dto.setLastName( rs.getString( COLUMN_LAST_NAME ) );
		dto.setEmail( rs.getString( COLUMN_EMAIL ) );
		dto.setRole( rs.getString( COLUMN_ROLE_NAME ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Users dto)
	{
	}

	/** 
	 * Returns all rows from the USERS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Users> findByDynamicSelect(String sql, Object[] sqlParams) throws UsersDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL);
			}
		
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UsersDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the USERS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Users> findByDynamicWhere(String sql, Object[] sqlParams) throws UsersDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL);
			}
		
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UsersDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}
