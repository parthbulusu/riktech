
package com.riktech.stp.dto;

import java.util.ArrayList;


public class QuestionBank extends BasicDTO
{
	/** 
	 * This attribute maps to the column ID in the QUESTION_BANK table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column QUESTION in the QUESTION_BANK table.
	 */
	protected String question;

	protected ArrayList<AnswerChoices> answerChoices;
	public ArrayList<AnswerChoices> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(ArrayList<AnswerChoices> answerChoices) {
		this.answerChoices = answerChoices;
	}

	/**
	 * Method 'QuestionBank'
	 * 
	 */
	public QuestionBank()
	{
	}

	/**
	 * Method 'getId'
	 * 
	 * @return long
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Method 'getQuestion'
	 * 
	 * @return String
	 */
	public String getQuestion()
	{
		return question;
	}

	/**
	 * Method 'setQuestion'
	 * 
	 * @param question
	 */
	public void setQuestion(String question)
	{
		this.question = question;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof QuestionBank)) {
			return false;
		}
		
		final QuestionBank _cast = (QuestionBank) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (question == null ? _cast.question != question : !question.equals( _cast.question )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int) (id ^ (id >>> 32));
		if (question != null) {
			_hashCode = 29 * _hashCode + question.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return QuestionBankPk
	 */
	public QuestionBankPk createPk()
	{
		return new QuestionBankPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.QuestionBank: " );
		ret.append( "id=" + id );
		ret.append( ", question=" + question );
		return ret.toString();
	}

}
