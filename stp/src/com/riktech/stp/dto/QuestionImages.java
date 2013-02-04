

package com.riktech.stp.dto;

import java.io.Serializable;

public class QuestionImages implements Serializable
{
	/** 
	 * This attribute maps to the column ID in the QUESTION_IMAGES table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column FILE_NAME in the QUESTION_IMAGES table.
	 */
	protected String fileName;

	/** 
	 * This attribute maps to the column FILE_TYPE in the QUESTION_IMAGES table.
	 */
	protected String fileType;

	/** 
	 * This attribute maps to the column IMAGE in the QUESTION_IMAGES table.
	 */
	protected byte[] image;

	/** 
	 * This attribute maps to the column QUEST_ID in the QUESTION_IMAGES table.
	 */
	protected long questId;

	/** 
	 * This attribute represents whether the primitive attribute questId is null.
	 */
	protected boolean questIdNull = true;

	/**
	 * Method 'QuestionImages'
	 * 
	 */
	public QuestionImages()
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
	 * Method 'getFileName'
	 * 
	 * @return String
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Method 'setFileName'
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Method 'getFileType'
	 * 
	 * @return String
	 */
	public String getFileType()
	{
		return fileType;
	}

	/**
	 * Method 'setFileType'
	 * 
	 * @param fileType
	 */
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	/**
	 * Method 'getImage'
	 * 
	 * @return byte[]
	 */
	public byte[] getImage()
	{
		return image;
	}

	/**
	 * Method 'setImage'
	 * 
	 * @param image
	 */
	public void setImage(byte[] image)
	{
		this.image = image;
	}

	/**
	 * Method 'getQuestId'
	 * 
	 * @return long
	 */
	public long getQuestId()
	{
		return questId;
	}

	/**
	 * Method 'setQuestId'
	 * 
	 * @param questId
	 */
	public void setQuestId(long questId)
	{
		this.questId = questId;
		this.questIdNull = false;
	}

	/**
	 * Method 'setQuestIdNull'
	 * 
	 * @param value
	 */
	public void setQuestIdNull(boolean value)
	{
		this.questIdNull = value;
	}

	/**
	 * Method 'isQuestIdNull'
	 * 
	 * @return boolean
	 */
	public boolean isQuestIdNull()
	{
		return questIdNull;
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
		
		if (!(_other instanceof QuestionImages)) {
			return false;
		}
		
		final QuestionImages _cast = (QuestionImages) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (fileName == null ? _cast.fileName != fileName : !fileName.equals( _cast.fileName )) {
			return false;
		}
		
		if (fileType == null ? _cast.fileType != fileType : !fileType.equals( _cast.fileType )) {
			return false;
		}
		
		if (image == null ? _cast.image != image : !image.equals( _cast.image )) {
			return false;
		}
		
		if (questId != _cast.questId) {
			return false;
		}
		
		if (questIdNull != _cast.questIdNull) {
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
		if (fileName != null) {
			_hashCode = 29 * _hashCode + fileName.hashCode();
		}
		
		if (fileType != null) {
			_hashCode = 29 * _hashCode + fileType.hashCode();
		}
		
		if (image != null) {
			_hashCode = 29 * _hashCode + image.hashCode();
		}
		
		_hashCode = 29 * _hashCode + (int) (questId ^ (questId >>> 32));
		_hashCode = 29 * _hashCode + (questIdNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return QuestionImagesPk
	 */
	public QuestionImagesPk createPk()
	{
		return new QuestionImagesPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.riktech.stp.dto.QuestionImages: " );
		ret.append( "id=" + id );
		ret.append( ", fileName=" + fileName );
		ret.append( ", fileType=" + fileType );
		ret.append( ", image=" + image );
		ret.append( ", questId=" + questId );
		return ret.toString();
	}

}
