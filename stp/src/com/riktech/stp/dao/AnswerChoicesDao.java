
package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface AnswerChoicesDao
{
	/** 
	 * Inserts a new row in the ANSWER_CHOICES table.
	 */
	public AnswerChoicesPk insert(AnswerChoices dto) throws AnswerChoicesDaoException;

	/** 
	 * Updates a single row in the ANSWER_CHOICES table.
	 */
	public void update(AnswerChoicesPk pk, AnswerChoices dto) throws AnswerChoicesDaoException;

	/** 
	 * Deletes a single row in the ANSWER_CHOICES table.
	 */
	public void delete(AnswerChoicesPk pk) throws AnswerChoicesDaoException;

	/** 
	 * Returns the rows from the ANSWER_CHOICES table that matches the specified primary-key value.
	 */
	public AnswerChoices findByPrimaryKey(AnswerChoicesPk pk) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ID = :id'.
	 */
	public AnswerChoices findByPrimaryKey(long id) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria ''.
	 */
	public ArrayList<AnswerChoices> findAll() throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'NEXT_QUEST_ID = :nextQuestId'.
	 */
	public ArrayList<AnswerChoices> findByQuestionBank(long nextQuestId) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'CURRENT_QUEST_ID = :currentQuestId'.
	 */
	public ArrayList<AnswerChoices> findByQuestionBank2(long currentQuestId) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ID = :id'.
	 */
	public ArrayList<AnswerChoices> findWhereIdEquals(long id) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'CURRENT_QUEST_ID = :currentQuestId'.
	 */
	public ArrayList<AnswerChoices> findWhereCurrentQuestIdEquals(long currentQuestId) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'ANS_CHOICE = :ansChoice'.
	 */
	public ArrayList<AnswerChoices> findWhereAnsChoiceEquals(String ansChoice) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the criteria 'NEXT_QUEST_ID = :nextQuestId'.
	 */
	public ArrayList<AnswerChoices> findWhereNextQuestIdEquals(long nextQuestId) throws AnswerChoicesDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<AnswerChoices> findByDynamicSelect(String sql, Object[] sqlParams) throws AnswerChoicesDaoException;

	/** 
	 * Returns all rows from the ANSWER_CHOICES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<AnswerChoices> findByDynamicWhere(String sql, Object[] sqlParams) throws AnswerChoicesDaoException;

}
