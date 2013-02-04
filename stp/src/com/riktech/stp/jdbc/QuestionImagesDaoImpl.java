

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

public class QuestionImagesDaoImpl extends AbstractDAO implements QuestionImagesDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( QuestionImagesDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ID, FILE_NAME, FILE_TYPE, IMAGE, QUEST_ID FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( ID, FILE_NAME, FILE_TYPE, IMAGE, QUEST_ID ) VALUES ( ?, ?, ?, EMPTY_BLOB(), ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET ID = ?, FILE_NAME = ?, FILE_TYPE = ?, IMAGE = EMPTY_BLOB(), QUEST_ID = ? WHERE ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/** 
	 * Index of column ID
	 */
	protected static final int COLUMN_ID = 1;

	/** 
	 * Index of column FILE_NAME
	 */
	protected static final int COLUMN_FILE_NAME = 2;

	/** 
	 * Index of column FILE_TYPE
	 */
	protected static final int COLUMN_FILE_TYPE = 3;

	/** 
	 * Index of column IMAGE
	 */
	protected static final int COLUMN_IMAGE = 4;

	/** 
	 * Index of column QUEST_ID
	 */
	protected static final int COLUMN_QUEST_ID = 5;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 5;

	/** 
	 * Index of primary-key column ID
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the QUESTION_IMAGES table.
	 */
	public QuestionImagesPk insert(QuestionImages dto) throws QuestionImagesDaoException
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
			stmt.setLong( index++, dto.getId() );
			stmt.setString( index++, dto.getFileName() );
			stmt.setString( index++, dto.getFileType() );
			// no need to bind value for LOB column
			if (dto.isQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getQuestId() );
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
			throw new QuestionImagesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the QUESTION_IMAGES table.
	 */
	public void update(QuestionImagesPk pk, QuestionImages dto) throws QuestionImagesDaoException
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
			stmt.setString( index++, dto.getFileName() );
			stmt.setString( index++, dto.getFileType() );
			// no need to bind value for LOB column
			if (dto.isQuestIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getQuestId() );
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
			throw new QuestionImagesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the QUESTION_IMAGES table.
	 */
	public void delete(QuestionImagesPk pk) throws QuestionImagesDaoException
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
			throw new QuestionImagesDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns the rows from the QUESTION_IMAGES table that matches the specified primary-key value.
	 */
	public QuestionImages findByPrimaryKey(QuestionImagesPk pk) throws QuestionImagesDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'ID = :id'.
	 */
	public QuestionImages findByPrimaryKey(long id) throws QuestionImagesDaoException
	{
		ArrayList<QuestionImages> ret = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  new Long(id) } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria ''.
	 */
	public ArrayList<QuestionImages> findAll() throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", null );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'ID = :id'.
	 */
	public ArrayList<QuestionImages> findWhereIdEquals(long id) throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID = ? ORDER BY ID", new Object[] {  new Long(id) } );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'FILE_NAME = :fileName'.
	 */
	public ArrayList<QuestionImages> findWhereFileNameEquals(String fileName) throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE FILE_NAME = ? ORDER BY FILE_NAME", new Object[] { fileName } );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'FILE_TYPE = :fileType'.
	 */
	public ArrayList<QuestionImages> findWhereFileTypeEquals(String fileType) throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE FILE_TYPE = ? ORDER BY FILE_TYPE", new Object[] { fileType } );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'IMAGE = :image'.
	 */
	public ArrayList<QuestionImages> findWhereImageEquals(byte[] image) throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IMAGE = ? ORDER BY IMAGE", new Object[] { image } );
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'QUEST_ID = :questId'.
	 */
	public ArrayList<QuestionImages> findWhereQuestIdEquals(long questId) throws QuestionImagesDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE QUEST_ID = ? ORDER BY QUEST_ID", new Object[] {  new Long(questId) } );
	}

	/**
	 * Method 'QuestionImagesDaoImpl'
	 * 
	 */
	public QuestionImagesDaoImpl()
	{
	}

	/**
	 * Method 'QuestionImagesDaoImpl'
	 * 
	 * @param userConn
	 */
	public QuestionImagesDaoImpl(final java.sql.Connection userConn)
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
		return "QUESTION_IMAGES";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected QuestionImages fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			QuestionImages dto = new QuestionImages();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected ArrayList<QuestionImages> fetchMultiResults(ResultSet rs) throws SQLException
	{
		ArrayList<QuestionImages> resultList = new ArrayList<QuestionImages>();
		while (rs.next()) {
			QuestionImages dto = new QuestionImages();
			populateDto( dto, rs);
			resultList.add( dto );
		}

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(QuestionImages dto, ResultSet rs) throws SQLException
	{
		dto.setId( rs.getLong( COLUMN_ID ) );
		dto.setFileName( rs.getString( COLUMN_FILE_NAME ) );
		dto.setFileType( rs.getString( COLUMN_FILE_TYPE ) );
		dto.setImage( super.getBlobColumn(rs, COLUMN_IMAGE ) );
		dto.setQuestId( rs.getLong( COLUMN_QUEST_ID ) );
		if (rs.wasNull()) {
			dto.setQuestIdNull( true );
		}
		
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(QuestionImages dto)
	{
	}

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionImages> findByDynamicSelect(String sql, Object[] sqlParams) throws QuestionImagesDaoException
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
			throw new QuestionImagesDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the QUESTION_IMAGES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionImages> findByDynamicWhere(String sql, Object[] sqlParams) throws QuestionImagesDaoException
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
			throw new QuestionImagesDaoException( "Exception: " + _e.getMessage(), _e );
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
