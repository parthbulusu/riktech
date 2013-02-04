
package com.riktech.stp.dao;

import java.util.ArrayList;

import com.riktech.stp.dto.*;
import com.riktech.stp.exceptions.*;

public interface UserRolesDao
{
	/** 
	 * Inserts a new row in the USER_ROLES table.
	 */
	public UserRolesPk insert(UserRoles dto) throws UserRolesDaoException;

	/** 
	 * Updates a single row in the USER_ROLES table.
	 */
	public void update(UserRolesPk pk, UserRoles dto) throws UserRolesDaoException;

	/** 
	 * Deletes a single row in the USER_ROLES table.
	 */
	public void delete(UserRolesPk pk) throws UserRolesDaoException;

	/** 
	 * Returns the rows from the USER_ROLES table that matches the specified primary-key value.
	 */
	public UserRoles findByPrimaryKey(UserRolesPk pk) throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'ROLE_NAME = :roleName AND USER_NAME = :userName'.
	 */
	public UserRoles findByPrimaryKey(String roleName, String userName) throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria ''.
	 */
	public ArrayList<UserRoles> findAll() throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserRoles> findByUsers(String userName) throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'USER_NAME = :userName'.
	 */
	public ArrayList<UserRoles> findWhereUserNameEquals(String userName) throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the criteria 'ROLE_NAME = :roleName'.
	 */
	public ArrayList<UserRoles> findWhereRoleNameEquals(String roleName) throws UserRolesDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the USER_ROLES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserRoles> findByDynamicSelect(String sql, Object[] sqlParams) throws UserRolesDaoException;

	/** 
	 * Returns all rows from the USER_ROLES table that match the specified arbitrary SQL statement
	 */
	public ArrayList<UserRoles> findByDynamicWhere(String sql, Object[] sqlParams) throws UserRolesDaoException;

}
