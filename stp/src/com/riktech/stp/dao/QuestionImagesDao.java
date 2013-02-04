

package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface QuestionImagesDao
{
	/** 
	 * Inserts a new row in the QUESTION_IMAGES table.
	 */
	public QuestionImagesPk insert(QuestionImages dto) throws QuestionImagesDaoException;

	/** 
	 * Updates a single row in the QUESTION_IMAGES table.
	 */
	public void update(QuestionImagesPk pk, QuestionImages dto) throws QuestionImagesDaoException;

	/** 
	 * Deletes a single row in the QUESTION_IMAGES table.
	 */
	public void delete(QuestionImagesPk pk) throws QuestionImagesDaoException;

	/** 
	 * Returns the rows from the QUESTION_IMAGES table that matches the specified primary-key value.
	 */
	public QuestionImages findByPrimaryKey(QuestionImagesPk pk) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'ID = :id'.
	 */
	public QuestionImages findByPrimaryKey(long id) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria ''.
	 */
	public ArrayList<QuestionImages> findAll() throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'ID = :id'.
	 */
	public ArrayList<QuestionImages> findWhereIdEquals(long id) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'FILE_NAME = :fileName'.
	 */
	public ArrayList<QuestionImages> findWhereFileNameEquals(String fileName) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'FILE_TYPE = :fileType'.
	 */
	public ArrayList<QuestionImages> findWhereFileTypeEquals(String fileType) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'IMAGE = :image'.
	 */
	public ArrayList<QuestionImages> findWhereImageEquals(byte[] image) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the criteria 'QUEST_ID = :questId'.
	 */
	public ArrayList<QuestionImages> findWhereQuestIdEquals(long questId) throws QuestionImagesDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionImages> findByDynamicSelect(String sql, Object[] sqlParams) throws QuestionImagesDaoException;

	/** 
	 * Returns all rows from the QUESTION_IMAGES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionImages> findByDynamicWhere(String sql, Object[] sqlParams) throws QuestionImagesDaoException;

}
