

package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface QuestionBankDao
{
	/** 
	 * Inserts a new row in the QUESTION_BANK table.
	 */
	public QuestionBankPk insert(QuestionBank dto) throws QuestionBankDaoException;

	/** 
	 * Updates a single row in the QUESTION_BANK table.
	 */
	public void update(QuestionBankPk pk, QuestionBank dto) throws QuestionBankDaoException;

	/** 
	 * Deletes a single row in the QUESTION_BANK table.
	 */
	public void delete(QuestionBankPk pk) throws QuestionBankDaoException;

	/** 
	 * Returns the rows from the QUESTION_BANK table that matches the specified primary-key value.
	 */
	public QuestionBank findByPrimaryKey(QuestionBankPk pk) throws QuestionBankDaoException;

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'ID = :id'.
	 */
	public QuestionBank findByPrimaryKey(long id) throws QuestionBankDaoException;

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria ''.
	 */
	public ArrayList<QuestionBank> findAll() throws QuestionBankDaoException;

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'ID = :id'.
	 */
	public ArrayList<QuestionBank> findWhereIdEquals(long id) throws QuestionBankDaoException;

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the criteria 'QUESTION = :question'.
	 */
	public ArrayList<QuestionBank> findWhereQuestionEquals(String question) throws QuestionBankDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionBank> findByDynamicSelect(String sql, Object[] sqlParams) throws QuestionBankDaoException;

	/** 
	 * Returns all rows from the QUESTION_BANK table that match the specified arbitrary SQL statement
	 */
	public ArrayList<QuestionBank> findByDynamicWhere(String sql, Object[] sqlParams) throws QuestionBankDaoException;

}
