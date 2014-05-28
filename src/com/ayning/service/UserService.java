package com.ayning.service;

import com.ayning.exception.AyningException;
import com.ayning.exception.InvalidUserException;
import com.ayning.vo.User;

/**
 * User service interface
 * 
 * @author Alex
 * 
 */
public interface UserService {

	/**
	 * Check whether a user's email has already exist, user's email should be
	 * unique
	 * 
	 * @param user
	 * @return true if both nickname and email does not exist
	 */
	public boolean existEmail(User user);

	/**
	 * Check whether a user's nickname has already exist, user's nickname should
	 * be unique
	 * 
	 * @param user
	 * @return true if both nickname and email does not exist
	 */
	public boolean existNickName(User user);

	/**
	 * Register and initialize a new Ayning user
	 * 
	 * @param user
	 * @return an initialized User object, but the user has not activated yet
	 * @throws AyningException
	 */
	public User register(User user) throws AyningException;

	/**
	 * Active a user so that the user can use most function of Ayning
	 * 
	 * @param user
	 * @return activated user object
	 */
	public User activate(User user);

	/**
	 * Authenticate a User's email and password
	 * 
	 * @param user
	 * @throws InvalidUserException
	 *             if authenticate fails
	 */
	public void auth(User user) throws AyningException;

	/**
	 * Get a user's information
	 * 
	 * @param user
	 * @return
	 * @throws AyningException
	 */
	public User getUserInfo(User user) throws AyningException;

	/**
	 * User's sign in service
	 * 
	 * @param user
	 * @return
	 * @throws AyningException
	 */
	public User signIn(User user) throws AyningException;

	/**
	 * Update user's common information
	 * 
	 * @param user
	 */
	public void update(User user);

	/**
	 * Update a user's password
	 * 
	 * @param user
	 */
	public void updatePassword(User user);

	/**
	 * Update a user's reputation
	 * 
	 * @param reputation
	 * @param user
	 * @throws AyningException
	 */
	public void updateReputation(int reputation, User user)
			throws AyningException;

	/**
	 * Cancel a User record, but do not delete the user's information.
	 * 
	 * @param user
	 * @throws AyningException
	 */
	public void delete(User user) throws AyningException;

}
