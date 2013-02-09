

package com.riktech.stp.dto;


public class Technology extends BasicDTO
{
	/** 
	 * This attribute maps to the column ID in the TECHNOLOGY table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column NAME in the TECHNOLOGY table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column PARENT_ID in the TECHNOLOGY table.
	 */
	protected long parentId;

	/** 
	 * This attribute represents whether the primitive attribute parentId is null.
	 */
	protected boolean parentIdNull = true;

	/** 
	 * This attribute maps to the column QUESTION_ID in the TECHNOLOGY table.
	 */
	protected long questionId;

	/** 
	 * This attribute represents whether the primitive attribute questionId is null.
	 */
	protected boolean questionIdNull = true;
	protected String question;
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Method 'Technology'
	 * 
	 */
	public Technology()
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
	 * Method 'getName'
	 * 
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method 'setName'
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Method 'getParentId'
	 * 
	 * @return long
	 */
	public long getParentId()
	{
		return parentId;
	}

	/**
	 * Method 'setParentId'
	 * 
	 * @param parentId
	 */
	public void setParentId(long parentId)
	{
		this.parentId = parentId;
		this.parentIdNull = false;
	}

	/**
	 * Method 'setParentIdNull'
	 * 
	 * @param value
	 */
	public void setParentIdNull(boolean value)
	{
		this.parentIdNull = value;
	}

	/**
	 * Method 'isParentIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isParentIdNull()
	{
		return parentIdNull;
	}

	/**
	 * Method 'getQuestionId'
	 * 
	 * @return long
	 */
	public long getQuestionId()
	{
		return questionId;
	}

	/**
	 * Method 'setQuestionId'
	 * 
	 * @param questionId
	 */
	public void setQuestionId(long questionId)
	{
		this.questionId = questionId;
		this.questionIdNull = false;
	}

	/**
	 * Method 'setQuestionIdNull'
	 * 
	 * @param value
	 */
	public void setQuestionIdNull(boolean value)
	{
		this.questionIdNull = value;
	}

	/**
	 * Method 'isQuestionIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuestionIdNull()
	{
		return questionIdNull;
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
		
		if (!(_other instanceof Technology)) {
			return false;
		}
		
		final Technology _cast = (Technology) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (parentId != _cast.parentId) {
			return false;
		}
		
		if (parentIdNull != _cast.parentIdNull) {
			return false;
		}
		
		if (questionId != _cast.questionId) {
			return false;
		}
		
		if (questionIdNull != _cast.questionIdNull) {
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
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (parentId ^ (parentId >>> 32));
		_hashCode = 29 * _hashCode + (parentIdNull ? 1 : 0);
		_hashCode = 29 * _hashCode + (int) (questionId ^ (questionId >>> 32));
		_hashCode = 29 * _hashCode + (questionIdNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TechnologyPk
	 */
	public TechnologyPk createPk()
	{
		return new TechnologyPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.Technology: " );
		ret.append( "id=" + id );
		ret.append( ", name=" + name );
		ret.append( ", parentId=" + parentId );
		ret.append( ", questionId=" + questionId );
		return ret.toString();
	}

}
