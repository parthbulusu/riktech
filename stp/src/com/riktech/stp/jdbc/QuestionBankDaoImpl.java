
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

public class QuestionBankDaoImpl extends AbstractDAO implements QuestionBankDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( QuestionBankDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ID, QUESTION FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( QUESTION ) VALUES ( ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " QUESTION = ? WHERE ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/** 
	 * Index of column ID
	 */
	protected static final int COLUMN_ID = 1;

	/** 
	 * Index of column QUESTION
	 */
	protected static final int COLUMN_QUESTION = 2;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 2;

	/** 
	 * Index of primary-key column ID
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the QUESTION_BANK table.
	 */
	public QuestionBankPk insert(QuestionBank dto) throws QuestionBankDaoException
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
		
			stmt = conn.prepareStatement( SQL_INSERT ,Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			stmt.setString( index++, dto.getQuestion() );
			if (logger.isDebugEnabled()) {
				logger.debug( "Executing " + SQL_INSERT + " with DTO: " + dto);
			}
		
			int rows = stmt.executeUpdate();
		    long autoIncKeyFromApi = -1;

		    rs = stmt.getGeneratedKeys();

		    if (rs.next()) {
		        autoIncKeyFromApi = rs.getLong(1);
		    } else {

		        // throw an exception from here
		    }
			
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
			dto.setId(autoIncKeyFromApi);
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new QuestionBankDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the QUESTION_BANK table.
	 */
	public void update(QuestionBankPk pk, QuestionBank dto) throws QuestionBankDaoException
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
			stmt.setString( index++, dto.getQuestion() );
			stmt.setLong( 3, pk.getId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new QuestionBankDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the QUESTION_BANK table.
	 */
	public void delete(QuestionBankPk pk) throws QuestionBankDaoException
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
			throw new QuestionBankDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the QUESTION_BANK table that matches the specified primary-key value.
	 */
	public QuestionBank findByPrimaryKey(QuestionBankPk pk) throws QuestionBankDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'ID = :id'.
	 */
	public QuestionBank findByPrimaryKey(long id) throws QuestionBankDaoException
	{
		ArrayList<QuestionBank> ret = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  new Long(id) } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria ''.
	 */
	public ArrayList<QuestionBank> findAll() throws QuestionBankDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", null );
	}

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'ID = :id'.
	 */
	public ArrayList<QuestionBank> findWhereIdEquals(long id) throws QuestionBankDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID = ? ORDER BY ID", new Object[] {  new Long(id) } );
	}

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'QUESTION = :question'.
	 */
	public ArrayList<QuestionBank> findWhereQuestionEquals(String question) throws QuestionBankDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE QUESTION = ? ORDER BY QUESTION", new Object[] { question } );
	}
	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'QUESTION = :question'.
	 */
	public ArrayList<QuestionBank> findWhereQuestionLike(String term) throws QuestionBankDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE QUESTION like ? ORDER BY QUESTION", new Object[] { term } );
	}
	/**
	 * Method 'QuestionBankDaoImpl'
	 * 
	 */
	public QuestionBankDaoImpl()
	{
	}

	/**
	 * Method 'QuestionBankDaoImpl'
	 * 
	 * @param userConn
	 */
	public QuestionBankDaoImpl(final java.sql.Connection userConn)
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
		return "QUESTION_BANK";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected QuestionBank fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			QuestionBank dto = new QuestionBank();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<QuestionBank> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<QuestionBank> resultList = new ArrayList<QuestionBank>();
		while (rs.next()) {
			QuestionBank dto = new QuestionBank();
			populateDto( dto, rs);
			resultList.add( dto );
		}

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(QuestionBank dto, ResultSet rs) throws SQLException
	{
		dto.setId( rs.getLong( COLUMN_ID ) );
		dto.setQuestion( rs.getString( COLUMN_QUESTION ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(QuestionBank dto)
	{
	}

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionBank> findByDynamicSelect(String sql, Object[] sqlParams) throws QuestionBankDaoException
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
			throw new QuestionBankDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the QUESTION_BANK table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionBank> findByDynamicWhere(String sql, Object[] sqlParams) throws QuestionBankDaoException
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
			throw new QuestionBankDaoException( "Exception: " + _e.getMessage(), _e );
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
