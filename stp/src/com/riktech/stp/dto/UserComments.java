

package com.riktech.stp.dto;

import java.sql.Timestamp;

public class UserComments  extends BasicDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int VISIBILITY_PUBLIC=1;
	public static final int VISIBILITY_PRIVATE=0;
	/** 
	 * This attribute maps to the column USER_NAME in the USER_COMMENTS table.
	 */
	protected String userName;

	/** 
	 * This attribute maps to the column ID in the USER_COMMENTS table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column USER_COMMENT in the USER_COMMENTS table.
	 */
	protected String userComment;
	protected Timestamp modifiedDate;
	protected long questionId;
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/** 
	 * This attribute maps to the column ROLE_NAME in the USER_COMMENTS table.
	 */
	protected int visibility=VISIBILITY_PRIVATE;

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	/**
	 * Method 'UserComments'
	 * 
	 */
	public UserComments()
	{
	}

	/**
	 * Method 'getUserName'
	 * 
	 * @return String
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Method 'setUserName'
	 * 
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
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
	 * Method 'getUserComment'
	 * 
	 * @return String
	 */
	public String getUserComment()
	{
		return userComment;
	}

	/**
	 * Method 'setUserComment'
	 * 
	 * @param userComment
	 */
	public void setUserComment(String userComment)
	{
		this.userComment = userComment;
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
		
		if (!(_other instanceof UserComments)) {
			return false;
		}
		
		final UserComments _cast = (UserComments) _other;
		if (userName == null ? _cast.userName != userName : !userName.equals( _cast.userName )) {
			return false;
		}
		
		if (id != _cast.id) {
			return false;
		}
		
		if (userComment == null ? _cast.userComment != userComment : !userComment.equals( _cast.userComment )) {
			return false;
		}
		if (visibility != _cast.visibility) {
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
		if (userName != null) {
			_hashCode = 29 * _hashCode + userName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (id ^ (id >>> 32));
		if (userComment != null) {
			_hashCode = 29 * _hashCode + userComment.hashCode();
		}
		_hashCode = 29 * _hashCode + visibility;
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return UserCommentsPk
	 */
	public UserCommentsPk createPk()
	{
		return new UserCommentsPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.UserComments: " );
		ret.append( "userName=" + userName );
		ret.append( ", id=" + id );
		ret.append( ", userComment=" + userComment );
		ret.append( ", visibility=" + visibility );
		return ret.toString();
	}

}
