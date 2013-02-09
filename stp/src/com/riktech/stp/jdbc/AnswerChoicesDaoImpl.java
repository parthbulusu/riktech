
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

public class AnswerChoicesDaoImpl extends AbstractDAO implements AnswerChoicesDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( AnswerChoicesDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ID, CURRENT_QUEST_ID, ANS_CHOICE, NEXT_QUEST_ID FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( CURRENT_QUEST_ID, ANS_CHOICE, NEXT_QUEST_ID ) VALUES ( ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, CURRENT_QUEST_ID = ?, ANS_CHOICE = ?, NEXT_QUEST_ID = ? WHERE ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/** 
	 * Index of column ID
	 */
	protected static final int COLUMN_ID = 1;

	/** 
	 * Index of column CURRENT_QUEST_ID
	 */
	protected static final int COLUMN_CURRENT_QUEST_ID = 2;

	/** 
	 * Index of column ANS_CHOICE
	 */
	protected static final int COLUMN_ANS_CHOICE = 3;

	/** 
	 * Index of column NEXT_QUEST_ID
	 */
	protected static final int COLUMN_NEXT_QUEST_ID = 4;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/** 
	 * Index of primary-key column ID
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the ANSWER_CHOICES table.
	 */
	public AnswerChoicesPk insert(AnswerChoices dto) throws AnswerChoicesDaoException
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
			if (dto.isCurrentQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getCurrentQuestId() );
			}
		
			stmt.setString( index++, dto.getAnsChoice() );
			if (dto.isNextQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getNextQuestId() );
			}
		
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
			throw new AnswerChoicesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the ANSWER_CHOICES table.
	 */
	public void update(AnswerChoicesPk pk, AnswerChoices dto) throws AnswerChoicesDaoException
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
			stmt.setLong( index++, dto.getId() );
			if (dto.isCurrentQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getCurrentQuestId() );
			}
		
			stmt.setString( index++, dto.getAnsChoice() );
			if (dto.isNextQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getNextQuestId() );
			}
		
			stmt.setLong( 5, pk.getId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new AnswerChoicesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the ANSWER_CHOICES table.
	 */
	public void delete(AnswerChoicesPk pk) throws AnswerChoicesDaoException
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
			throw new AnswerChoicesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the ANSWER_CHOICES table that matches the specified primary-key value.
	 */
	public AnswerChoices findByPrimaryKey(AnswerChoicesPk pk) throws AnswerChoicesDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ID = :id'.
	 */
	public AnswerChoices findByPrimaryKey(long id) throws AnswerChoicesDaoException
	{
		ArrayList<AnswerChoices> ret = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  new Long(id) } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria ''.
	 */
	public ArrayList<AnswerChoices> findAll() throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", null );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'NEXT_QUEST_ID = :nextQuestId'.
	 */
	public ArrayList<AnswerChoices> findByQuestionBank(long nextQuestId) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NEXT_QUEST_ID = ?", new Object[] {  new Long(nextQuestId) } );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'CURRENT_QUEST_ID = :currentQuestId'.
	 */
	public ArrayList<AnswerChoices> findByQuestionBank2(long currentQuestId) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE CURRENT_QUEST_ID = ?", new Object[] {  new Long(currentQuestId) } );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ID = :id'.
	 */
	public ArrayList<AnswerChoices> findWhereIdEquals(long id) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID = ? ORDER BY ID", new Object[] {  new Long(id) } );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'CURRENT_QUEST_ID = :currentQuestId'.
	 */
	public ArrayList<AnswerChoices> findWhereCurrentQuestIdEquals(long currentQuestId) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE CURRENT_QUEST_ID = ? ORDER BY CURRENT_QUEST_ID", new Object[] {  new Long(currentQuestId) } );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ANS_CHOICE = :ansChoice'.
	 */
	public ArrayList<AnswerChoices> findWhereAnsChoiceEquals(String ansChoice) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ANS_CHOICE = ? ORDER BY ANS_CHOICE", new Object[] { ansChoice } );
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'NEXT_QUEST_ID = :nextQuestId'.
	 */
	public ArrayList<AnswerChoices> findWhereNextQuestIdEquals(long nextQuestId) throws AnswerChoicesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NEXT_QUEST_ID = ? ORDER BY NEXT_QUEST_ID", new Object[] {  new Long(nextQuestId) } );
	}

	/**
	 * Method 'AnswerChoicesDaoImpl'
	 * 
	 */
	public AnswerChoicesDaoImpl()
	{
	}

	/**
	 * Method 'AnswerChoicesDaoImpl'
	 * 
	 * @param userConn
	 */
	public AnswerChoicesDaoImpl(final java.sql.Connection userConn)
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
		return "ANSWER_CHOICES";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected AnswerChoices fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			AnswerChoices dto = new AnswerChoices();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<AnswerChoices> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<AnswerChoices> resultList = new ArrayList<AnswerChoices>();
		while (rs.next()) {
			AnswerChoices dto = new AnswerChoices();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(AnswerChoices dto, ResultSet rs) throws SQLException
	{
		dto.setId( rs.getLong( COLUMN_ID ) );
		dto.setCurrentQuestId( rs.getLong( COLUMN_CURRENT_QUEST_ID ) );
		if (rs.wasNull()) {
			dto.setCurrentQuestIdNull( true );
		}
		
		dto.setAnsChoice( rs.getString( COLUMN_ANS_CHOICE ) );
		dto.setNextQuestId( rs.getLong( COLUMN_NEXT_QUEST_ID ) );
		if (rs.wasNull()) {
			dto.setNextQuestIdNull( true );
		}
		
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(AnswerChoices dto)
	{
	}

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<AnswerChoices> findByDynamicSelect(String sql, Object[] sqlParams) throws AnswerChoicesDaoException
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
			throw new AnswerChoicesDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the ANSWER_CHOICES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<AnswerChoices> findByDynamicWhere(String sql, Object[] sqlParams) throws AnswerChoicesDaoException
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
			throw new AnswerChoicesDaoException( "Exception: " + _e.getMessage(), _e );
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
