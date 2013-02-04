

package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface UsersDao
{
	/** 
	 * Inserts a new row in the USERS table.
	 */
	public UsersPk insert(Users dto) throws UsersDaoException;

	/** 
	 * Updates a single row in the USERS table.
	 */
	public void update(UsersPk pk, Users dto) throws UsersDaoException;

	/** 
	 * Deletes a single row in the USERS table.
	 */
	public void delete(UsersPk pk) throws UsersDaoException;

	/** 
	 * Returns the rows from the USERS table that matches the specified primary-key value.
	 */
	public Users findByPrimaryKey(UsersPk pk) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_NAME = :userName'.
	 */
	public Users findByPrimaryKey(String userName) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria ''.
	 */
	public ArrayList<Users> findAll() throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<Users> findWhereUserNameEquals(String userName) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'USER_PASS = :userPass'.
	 */
	public ArrayList<Users> findWhereUserPassEquals(String userPass) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'FIRST_NAME = :firstName'.
	 */
	public ArrayList<Users> findWhereFirstNameEquals(String firstName) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'LAST_NAME = :lastName'.
	 */
	public ArrayList<Users> findWhereLastNameEquals(String lastName) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the criteria 'EMAIL = :email'.
	 */
	public ArrayList<Users> findWhereEmailEquals(String email) throws UsersDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the USERS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Users> findByDynamicSelect(String sql, Object[] sqlParams) throws UsersDaoException;

	/** 
	 * Returns all rows from the USERS table that match the specified arbitrary SQL statement
	 */
	public ArrayList<Users> findByDynamicWhere(String sql, Object[] sqlParams) throws UsersDaoException;

}
