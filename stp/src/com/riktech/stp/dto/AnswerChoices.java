
package com.riktech.stp.dto;


public class AnswerChoices extends BasicDTO
{
	/** 
	 * This attribute maps to the column ID in the ANSWER_CHOICES table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column CURRENT_QUEST_ID in the ANSWER_CHOICES table.
	 */
	protected long currentQuestId;

	/** 
	 * This attribute represents whether the primitive attribute currentQuestId is null.
	 */
	protected boolean currentQuestIdNull = true;

	/** 
	 * This attribute maps to the column ANS_CHOICE in the ANSWER_CHOICES table.
	 */
	protected String ansChoice;

	/** 
	 * This attribute maps to the column NEXT_QUEST_ID in the ANSWER_CHOICES table.
	 */
	protected long nextQuestId=-1;

	/** 
	 * This attribute represents whether the primitive attribute nextQuestId is null.
	 */
	protected boolean nextQuestIdNull = true;

	protected String nextQuestion;
	public String getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(String nextQuestion) {
		this.nextQuestion = nextQuestion;
	}

	/**
	 * Method 'AnswerChoices'
	 * 
	 */
	public AnswerChoices()
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
	 * Method 'getCurrentQuestId'
	 * 
	 * @return long
	 */
	public long getCurrentQuestId()
	{
		return currentQuestId;
	}

	/**
	 * Method 'setCurrentQuestId'
	 * 
	 * @param currentQuestId
	 */
	public void setCurrentQuestId(long currentQuestId)
	{
		this.currentQuestId = currentQuestId;
		this.currentQuestIdNull = false;
	}

	/**
	 * Method 'setCurrentQuestIdNull'
	 * 
	 * @param value
	 */
	public void setCurrentQuestIdNull(boolean value)
	{
		this.currentQuestIdNull = value;
	}

	/**
	 * Method 'isCurrentQuestIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isCurrentQuestIdNull()
	{
		return currentQuestIdNull;
	}

	/**
	 * Method 'getAnsChoice'
	 * 
	 * @return String
	 */
	public String getAnsChoice()
	{
		return ansChoice;
	}

	/**
	 * Method 'setAnsChoice'
	 * 
	 * @param ansChoice
	 */
	public void setAnsChoice(String ansChoice)
	{
		this.ansChoice = ansChoice;
	}

	/**
	 * Method 'getNextQuestId'
	 * 
	 * @return long
	 */
	public long getNextQuestId()
	{
		return nextQuestId;
	}

	/**
	 * Method 'setNextQuestId'
	 * 
	 * @param nextQuestId
	 */
	public void setNextQuestId(long nextQuestId)
	{
		this.nextQuestId = nextQuestId;
		this.nextQuestIdNull = false;
	}

	/**
	 * Method 'setNextQuestIdNull'
	 * 
	 * @param value
	 */
	public void setNextQuestIdNull(boolean value)
	{
		this.nextQuestIdNull = value;
	}

	/**
	 * Method 'isNextQuestIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isNextQuestIdNull()
	{
		return nextQuestIdNull;
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
		
		if (!(_other instanceof AnswerChoices)) {
			return false;
		}
		
		final AnswerChoices _cast = (AnswerChoices) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (currentQuestId != _cast.currentQuestId) {
			return false;
		}
		
		if (currentQuestIdNull != _cast.currentQuestIdNull) {
			return false;
		}
		
		if (ansChoice == null ? _cast.ansChoice != ansChoice : !ansChoice.equals( _cast.ansChoice )) {
			return false;
		}
		
		if (nextQuestId != _cast.nextQuestId) {
			return false;
		}
		
		if (nextQuestIdNull != _cast.nextQuestIdNull) {
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
		_hashCode = 29 * _hashCode + (int) (currentQuestId ^ (currentQuestId >>> 32));
		_hashCode = 29 * _hashCode + (currentQuestIdNull ? 1 : 0);
		if (ansChoice != null) {
			_hashCode = 29 * _hashCode + ansChoice.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (nextQuestId ^ (nextQuestId >>> 32));
		_hashCode = 29 * _hashCode + (nextQuestIdNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return AnswerChoicesPk
	 */
	public AnswerChoicesPk createPk()
	{
		return new AnswerChoicesPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.AnswerChoices: " );
		ret.append( "id=" + id );
		ret.append( ", currentQuestId=" + currentQuestId );
		ret.append( ", ansChoice=" + ansChoice );
		ret.append( ", nextQuestId=" + nextQuestId );
		return ret.toString();
	}

}
