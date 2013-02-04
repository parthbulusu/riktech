

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
import java.sql.Timestamp;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class UserCommentsDaoImpl extends AbstractDAO implements UserCommentsDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( UserCommentsDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT USER_NAME, ID, USER_COMMENT, VISIBILITY, MODIFIED_DATE FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( USER_NAME, USER_COMMENT, VISIBILITY, MODIFIED_DATE ) VALUES ( ?, ?, ?,? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET USER_NAME = ?, USER_COMMENT = ?, VISIBILITY = ?, MODIFIED_DATE=? WHERE ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/** 
	 * Index of column USER_NAME
	 */
	protected static final int COLUMN_USER_NAME = 1;

	/** 
	 * Index of column ID
	 */
	protected static final int COLUMN_ID = 2;

	/** 
	 * Index of column USER_COMMENT
	 */
	protected static final int COLUMN_USER_COMMENT = 3;

	/** 
	 * Index of column VISIBILITY
	 */
	protected static final int COLUMN_VISIBILITY = 4;
	/** 
	 * Index of column VISIBILITY
	 */
	protected static final int COLUMN_MODIFIED_DATE = 5;
	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/** 
	 * Index of primary-key column ID
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the USER_COMMENTS table.
	 */
	public UserCommentsPk insert(UserComments dto) throws UserCommentsDaoException
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
			stmt.setString( index++, dto.getUserComment() );
			stmt.setInt( index++, dto.getVisibility() );
			stmt.setTimestamp(index++, new Timestamp(System.currentTimeMillis()) );
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
			throw new UserCommentsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the USER_COMMENTS table.
	 */
	public void update(UserCommentsPk pk, UserComments dto) throws UserCommentsDaoException
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
			stmt.setString( index++, dto.getUserComment() );
			stmt.setInt( index++, dto.getVisibility() );
			stmt.setTimestamp( index++, new Timestamp(System.currentTimeMillis()) );
			stmt.setLong( index++, pk.getId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UserCommentsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the USER_COMMENTS table.
	 */
	public void delete(UserCommentsPk pk) throws UserCommentsDaoException
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
			stmt.setLong( 1, pk.getId() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new UserCommentsDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the USER_COMMENTS table that matches the specified primary-key value.
	 */
	public UserComments findByPrimaryKey(UserCommentsPk pk) throws UserCommentsDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'ID = :id'.
	 */
	public UserComments findByPrimaryKey(long id) throws UserCommentsDaoException
	{
		ArrayList<UserComments> ret = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  new Long(id) } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria ''.
	 */
	public ArrayList<UserComments> findAll() throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", null );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserComments> findByUsers(String userName) throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ?", new Object[] { userName } );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserComments> findWhereUserNameEquals(String userName) throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_NAME = ? ORDER BY USER_NAME", new Object[] { userName } );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'ID = :id'.
	 */
	public ArrayList<UserComments> findWhereIdEquals(long id) throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID = ? ORDER BY ID", new Object[] {  new Long(id) } );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_COMMENT = :userComment'.
	 */
	public ArrayList<UserComments> findWhereUserCommentEquals(String userComment) throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE USER_COMMENT = ? ORDER BY USER_COMMENT", new Object[] { userComment } );
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'VISIBILITY = :Visibility'.
	 */
	public ArrayList<UserComments> findWhereVisibilityEquals(int Visibility) throws UserCommentsDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE VISIBILITY = ? ORDER BY VISIBILITY", new Object[] { Visibility } );
	}

	/**
	 * Method 'UserCommentsDaoImpl'
	 * 
	 */
	public UserCommentsDaoImpl()
	{
	}

	/**
	 * Method 'UserCommentsDaoImpl'
	 * 
	 * @param userConn
	 */
	public UserCommentsDaoImpl(final java.sql.Connection userConn)
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
		return "USER_COMMENTS";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected UserComments fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			UserComments dto = new UserComments();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<UserComments> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<UserComments> resultList = new ArrayList<UserComments>();
		while (rs.next()) {
			UserComments dto = new UserComments();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(UserComments dto, ResultSet rs) throws SQLException
	{
		dto.setUserName( rs.getString( COLUMN_USER_NAME ) );
		dto.setId( rs.getLong( COLUMN_ID ) );
		dto.setUserComment( rs.getString( COLUMN_USER_COMMENT ) );
		dto.setVisibility( rs.getInt( COLUMN_VISIBILITY ) );
		dto.setModifiedDate( rs.getTimestamp( COLUMN_MODIFIED_DATE ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(UserComments dto)
	{
	}

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserComments> findByDynamicSelect(String sql, Object[] sqlParams) throws UserCommentsDaoException
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
			throw new UserCommentsDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the USER_COMMENTS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserComments> findByDynamicWhere(String sql, Object[] sqlParams) throws UserCommentsDaoException
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
			System.out.println("***sql****"+SQL);
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
			throw new UserCommentsDaoException( "Exception: " + _e.getMessage(), _e );
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
