
package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface TechnologyDao
{
	/** 
	 * Inserts a new row in the TECHNOLOGY table.
	 */
	public TechnologyPk insert(Technology dto) throws TechnologyDaoException;

	/** 
	 * Updates a single row in the TECHNOLOGY table.
	 */
	public void update(TechnologyPk pk, Technology dto) throws TechnologyDaoException;

	/** 
	 * Deletes a single row in the TECHNOLOGY table.
	 */
	public void delete(TechnologyPk pk) throws TechnologyDaoException;

	/** 
	 * Returns the rows from the TECHNOLOGY table that matches the specified primary-key value.
	 */
	public Technology findByPrimaryKey(TechnologyPk pk) throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'ID = :id'.
	 */
	public Technology findByPrimaryKey(long id) throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria ''.
	 */
	public ArrayList<Technology> findAll() throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'ID = :id'.
	 */
	public ArrayList<Technology> findWhereIdEquals(long id) throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'NAME = :name'.
	 */
	public ArrayList<Technology> findWhereNameEquals(String name) throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'PARENT_ID = :parentId'.
	 */
	public ArrayList<Technology> findWhereParentIdEquals(long parentId) throws TechnologyDaoException;
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'PARENT_ID = :parentId'.
	 */
	public ArrayList<Technology> findAllParents() throws TechnologyDaoException;
	/** 
	 * Returns all rows from the TECHNOLOGY table that match the criteria 'QUESTION_ID = :questionId'.
	 */
	public ArrayList<Technology> findWhereQuestionIdEquals(long questionId) throws TechnologyDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicSelect(String sql, Object[] sqlParams) throws TechnologyDaoException;

	/** 
	 * Returns all rows from the TECHNOLOGY table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Technology> findByDynamicWhere(String sql, Object[] sqlParams) throws TechnologyDaoException;

}
