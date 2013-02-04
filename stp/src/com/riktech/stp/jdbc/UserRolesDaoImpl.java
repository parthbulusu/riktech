

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

public class UserRolesDaoImpl extends AbstractDAO implements UserRolesDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( UserRolesDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT USER_NAME, ROLE_NAME FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( USER_NAME, ROLE_NAME ) VALUES ( ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET USER_NAME = ?, ROLE_NAME = ? WHERE ROLE_NAME = ? AND USER_NAME = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ROLE_NAME = ? AND USER_NAME = ?";

	/** 
	 * Index of column USER_NAME
	 */
	protected static final int COLUMN_USER_NAME = 1;

	/** 
	 * Index of column ROLE_NAME
	 */
	protected static final int COLUMN_ROLE_NAME = 2;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 2;

	/** 
	 * Index of primary-key column ROLE_NAME
	 */
	protected static final int PK_COLUMN_ROLE_NAME = 1;

	/** 
	 * Index of primary-key column USER_NAME
	 */
	protected static final int PK_COLUMN_USER_NAME = 2;

	/** 
	 * Inserts a new row in the USER_ROLES table.
	 */
	public UserRolesPk insert(UserRoles dto) throws UserRolesDaoException
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
			stmt.setString( index++, dto.getRoleName() );
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
			throw new UserRolesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the USER_ROLES table.
	 */
	public void update(UserRolesPk pk, UserRoles dto) throws UserRolesDaoException
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
			stmt.setString( index++, dto.getRoleName() );
			stmt.setString( 3, pk.getRoleName() );
			stmt.setString( 4, pk.getUserName() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UserRolesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the USER_ROLES table.
	 */
	public void delete(UserRolesPk pk) throws UserRolesDaoException
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
			stmt.setString( 1, pk.getRoleName() );
			stmt.setString( 2, pk.getUserName() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UserRolesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the USER_ROLES table that matches the specified primary-key value.
	 */
	public UserRoles findByPrimaryKey(UserRolesPk pk) throws UserRolesDaoException
	{
		return findByPrimaryKey( pk.getRoleName(), pk.getUserName() );
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'ROLE_NAME = :roleName AND USER_NAME = :userName'.
	 */
	public UserRoles findByPrimaryKey(String roleName, String userName) throws UserRolesDaoException
	{
		ArrayList<UserRoles> ret = findByDynamicSelect( SQL_SELECT + " WHERE ROLE_NAME = ? AND USER_NAME = ?", new Object[] { roleName, userName } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria ''.
	 */
	public ArrayList<UserRoles> findAll() throws UserRolesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ROLE_NAME, USER_NAME", null );
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserRoles> findByUsers(String userName) throws UserRolesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ?", new Object[] { userName } );
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserRoles> findWhereUserNameEquals(String userName) throws UserRolesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ? ORDER BY USER_NAME", new Object[] { userName } );
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'ROLE_NAME = :roleName'.
	 */
	public ArrayList<UserRoles> findWhereRoleNameEquals(String roleName) throws UserRolesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ROLE_NAME = ? ORDER BY ROLE_NAME", new Object[] { roleName } );
	}

	/**
	 * Method 'UserRolesDaoImpl'
	 * 
	 */
	public UserRolesDaoImpl()
	{
	}

	/**
	 * Method 'UserRolesDaoImpl'
	 * 
	 * @param userConn
	 */
	public UserRolesDaoImpl(final java.sql.Connection userConn)
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
		return "USER_ROLES";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected UserRoles fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			UserRoles dto = new UserRoles();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<UserRoles> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<UserRoles> resultList = new ArrayList<UserRoles>();
		while (rs.next()) {
			UserRoles dto = new UserRoles();
			populateDto( dto, rs);
			resultList.add( dto );
		}

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(UserRoles dto, ResultSet rs) throws SQLException
	{
		dto.setUserName( rs.getString( COLUMN_USER_NAME ) );
		dto.setRoleName( rs.getString( COLUMN_ROLE_NAME ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserRoles dto)
	{
	}

	/** 
	 * Returns all rows from the USER_ROLES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserRoles> findByDynamicSelect(String sql, Object[] sqlParams) throws UserRolesDaoException
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
			throw new UserRolesDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the USER_ROLES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserRoles> findByDynamicWhere(String sql, Object[] sqlParams) throws UserRolesDaoException
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
			throw new UserRolesDaoException( "Exception: " + _e.getMessage(), _e );
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
