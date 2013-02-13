
package com.riktech.stp.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.riktech.stp.dao.AnswerChoicesDao;
import com.riktech.stp.dao.QuestionBankDao;
import com.riktech.stp.dao.TechnologyDao;
import com.riktech.stp.dto.AnswerChoices;
import com.riktech.stp.dto.QuestionBankPk;
import com.riktech.stp.dto.Technology;
import com.riktech.stp.dto.TechnologyPk;
import com.riktech.stp.exceptions.AnswerChoicesDaoException;
import com.riktech.stp.exceptions.QuestionBankDaoException;
import com.riktech.stp.exceptions.TechnologyDaoException;
import com.riktech.stp.factory.AnswerChoicesDaoFactory;
import com.riktech.stp.factory.QuestionBankDaoFactory;
import com.riktech.stp.factory.TechnologyDaoFactory;

public class TechnologyDaoImpl extends AbstractDAO implements TechnologyDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	protected static final Logger logger = Logger.getLogger( TechnologyDaoImpl.class );

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT ID, NAME, PARENT_ID, QUESTION_ID FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( NAME, PARENT_ID, QUESTION_ID ) VALUES ( ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET  NAME = ?, PARENT_ID = ?, QUESTION_ID = ? WHERE ID = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE ID = ?";

	/** 
	 * Index of column ID
	 */
	protected static final int COLUMN_ID = 1;

	/** 
	 * Index of column NAME
	 */
	protected static final int COLUMN_NAME = 2;

	/** 
	 * Index of column PARENT_ID
	 */
	protected static final int COLUMN_PARENT_ID = 3;

	/** 
	 * Index of column QUESTION_ID
	 */
	protected static final int COLUMN_QUESTION_ID = 4;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 4;

	/** 
	 * Index of primary-key column ID
	 */
	protected static final int PK_COLUMN_ID = 1;

	/** 
	 * Inserts a new row in the TECHNOLOGY table.
	 */
	public TechnologyPk insert(Technology dto) throws TechnologyDaoException
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
			stmt.setString( index++, dto.getName() );
			if (dto.isParentIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getParentId() );
			}
		
			if (dto.isQuestionIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getQuestionId() );
			}
		
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
		    dto.setId(autoIncKeyFromApi);			
			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( rows + " rows affected (" + (t2-t1) + " ms)");
			}
		
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new TechnologyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Updates a single row in the TECHNOLOGY table.
	 */
	public void update(TechnologyPk pk, Technology dto) throws TechnologyDaoException
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
			stmt.setString( index++, dto.getName() );
			if (dto.isParentIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getParentId() );
			}
		
			if (dto.isQuestionIdNull()) {
				stmt.setNull( index++, java.sql.Types.INTEGER );
			} else {
				stmt.setLong( index++, dto.getQuestionId() );
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
			throw new TechnologyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Deletes a single row in the TECHNOLOGY table.
	 * @throws QuestionBankDaoException 
	 * @throws AnswerChoicesDaoException 
	 */
	public void delete(TechnologyPk pk) throws TechnologyDaoException, QuestionBankDaoException, AnswerChoicesDaoException
	{
		Technology tech=this.findByPrimaryKey(pk);
		if(isTLeafNode(tech))
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
				throw new TechnologyDaoException( "Exception: " + _e.getMessage(), _e );
			}
			finally {
				ResourceManager.close(stmt);
				if (!isConnSupplied) {
					ResourceManager.close(conn);
				}
			
			}
		
			if(isQuestionLeafNode(tech)){
				QuestionBankDao dao=QuestionBankDaoFactory.create();
				dao.delete(new QuestionBankPk(tech.getQuestionId()));
			}
		}
	}

	/** 
	 * Returns the rows from the TECHNOLOGY table that matches the specified primary-key value.
	 */
	public Technology findByPrimaryKey(TechnologyPk pk) throws TechnologyDaoException
	{
		return findByPrimaryKey( pk.getId() );
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'ID = :id'.
	 */
	public Technology findByPrimaryKey(long id) throws TechnologyDaoException
	{
		ArrayList<Technology> ret = findByDynamicSelect( SQL_SELECT + " WHERE ID = ?", new Object[] {  new Long(id) } );
		return ret.size()==0 ? null : ret.get(0);
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria ''.
	 */
	public ArrayList<Technology> findAll() throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY ID", null );
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'ID = :id'.
	 */
	public ArrayList<Technology> findWhereIdEquals(long id) throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ID = ? ORDER BY ID", new Object[] {  new Long(id) } );
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'NAME = :name'.
	 */
	public ArrayList<Technology> findWhereNameEquals(String name) throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NAME = ? ORDER BY NAME", new Object[] { name } );
	}
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'NAME = :name'.
	 */
	public ArrayList<Technology> findWhereNameLike(String name) throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE NAME LIKE ? ORDER BY NAME", new Object[] { name } );
	}
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'PARENT_ID = :parentId'.
	 */
	public ArrayList<Technology> findWhereParentIdEquals(long parentId) throws TechnologyDaoException
	{
		return findWhereParentIdEquals(parentId,false);
	}
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'PARENT_ID = :parentId'.
	 */
	public ArrayList<Technology> findWhereParentIdEquals(long parentId,boolean isDeletePermission) throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE PARENT_ID = ? ORDER BY PARENT_ID", new Object[] {  new Long(parentId) } ,isDeletePermission);
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'QUESTION_ID = :questionId'.
	 */
	public ArrayList<Technology> findWhereQuestionIdEquals(long questionId) throws TechnologyDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE QUESTION_ID = ? ORDER BY QUESTION_ID", new Object[] {  new Long(questionId) } );
	}

	/**
	 * Method 'TechnologyDaoImpl'
	 * 
	 */
	public TechnologyDaoImpl()
	{
	}

	/**
	 * Method 'TechnologyDaoImpl'
	 * 
	 * @param userConn
	 */
	public TechnologyDaoImpl(final java.sql.Connection userConn)
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
	protected String getTableName()
	{
		return "TECHNOLOGY";
	}

	/** 
	 * Fetches a single row from the result set
	 * @throws AnswerChoicesDaoException 
	 * @throws TechnologyDaoException 
	 */
	protected Technology fetchSingleResult(ResultSet rs,boolean isDeletePermission) throws SQLException, AnswerChoicesDaoException, TechnologyDaoException
	{
		if (rs.next()) {
			Technology dto = new Technology();
			populateDto( dto, rs, isDeletePermission);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 * @throws AnswerChoicesDaoException 
	 * @throws TechnologyDaoException 
	 */
	protected ArrayList<Technology> fetchMultiResults(ResultSet rs,boolean isDeletePermission) throws SQLException, AnswerChoicesDaoException, TechnologyDaoException
	{
		ArrayList<Technology> resultList = new ArrayList<Technology>();
		while (rs.next()) {
			Technology dto = new Technology();
			populateDto( dto, rs, isDeletePermission);
			resultList.add( dto );
		}

		return resultList;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 * @throws AnswerChoicesDaoException 
	 * @throws TechnologyDaoException 
	 */
	protected void populateDto(Technology dto, ResultSet rs,boolean isDeletePermission) throws SQLException, AnswerChoicesDaoException, TechnologyDaoException
	{
		dto.setId( rs.getLong( COLUMN_ID ) );
		dto.setName( rs.getString( COLUMN_NAME ) );
		dto.setParentId( rs.getLong( COLUMN_PARENT_ID ) );
		if (rs.wasNull()) {
			dto.setParentIdNull( true );
		}
		
		dto.setQuestionId( rs.getLong( COLUMN_QUESTION_ID ) );
		if (rs.wasNull()) {
			dto.setQuestionIdNull( true );
		}
		if(isDeletePermission)
		{
			dto.setDeletable(this.isTLeafNode(dto));
		}	
	}
	public boolean isQuestionLeafNode(Technology t) throws AnswerChoicesDaoException
	{
		AnswerChoicesDao dao=AnswerChoicesDaoFactory.create();
		ArrayList<AnswerChoices> list=dao.findWhereNextQuestIdEquals(t.getQuestionId());		
		if(list!=null & list.size()>0)return false;
		return true;
	}	
	public boolean isTLeafNode(Technology t) throws AnswerChoicesDaoException, TechnologyDaoException
	{
		ArrayList<Technology> tList=this.findWhereParentIdEquals(t.getId());
		if(tList!=null & tList.size()>0)return false;
		AnswerChoicesDao dao=AnswerChoicesDaoFactory.create();
		ArrayList<AnswerChoices> alist=dao.findWhereCurrentQuestIdEquals(t.getQuestionId());
		if(alist!=null & alist.size()>0)return false;
		return true;
	}
	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(Technology dto)
	{
	}
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicSelect(String sql, Object[] sqlParams) throws TechnologyDaoException
	{
		return findByDynamicSelect(sql,sqlParams,false);
	}

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicSelect(String sql, Object[] sqlParams,boolean isDeletePermission) throws TechnologyDaoException
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
			return fetchMultiResults(rs, isDeletePermission);
		}
		catch (Exception _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new TechnologyDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicWhere(String sql, Object[] sqlParams) throws TechnologyDaoException
	{
		return findByDynamicWhere(sql,sqlParams,false);
	}
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicWhere(String sql, Object[] sqlParams,boolean isDeletePermission) throws TechnologyDaoException
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
		
			System.out.println( "Executing " + SQL);
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
			return fetchMultiResults(rs,isDeletePermission);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new TechnologyDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	@Override
	public ArrayList<Technology> findAllParents() throws TechnologyDaoException {
		
		return findByDynamicWhere("PARENT_ID is NULL", null);
	}

}
