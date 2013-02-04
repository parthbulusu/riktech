

package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface UserCommentsDao
{
	/** 
	 * Inserts a new row in the USER_COMMENTS table.
	 */
	public UserCommentsPk insert(UserComments dto) throws UserCommentsDaoException;

	/** 
	 * Updates a single row in the USER_COMMENTS table.
	 */
	public void update(UserCommentsPk pk, UserComments dto) throws UserCommentsDaoException;

	/** 
	 * Deletes a single row in the USER_COMMENTS table.
	 */
	public void delete(UserCommentsPk pk) throws UserCommentsDaoException;

	/** 
	 * Returns the rows from the USER_COMMENTS table that matches the specified primary-key value.
	 */
	public UserComments findByPrimaryKey(UserCommentsPk pk) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'ID = :id'.
	 */
	public UserComments findByPrimaryKey(long id) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria ''.
	 */
	public ArrayList<UserComments> findAll() throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserComments> findByUsers(String userName) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserComments> findWhereUserNameEquals(String userName) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'ID = :id'.
	 */
	public ArrayList<UserComments> findWhereIdEquals(long id) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'USER_COMMENT = :userComment'.
	 */
	public ArrayList<UserComments> findWhereUserCommentEquals(String userComment) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the criteria 'Visibility = :Visibility'.
	 */
	public ArrayList<UserComments> findWhereVisibilityEquals(int Visibility) throws UserCommentsDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserComments> findByDynamicSelect(String sql, Object[] sqlParams) throws UserCommentsDaoException;

	/** 
	 * Returns all rows from the USER_COMMENTS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserComments> findByDynamicWhere(String sql, Object[] sqlParams) throws UserCommentsDaoException;

}
