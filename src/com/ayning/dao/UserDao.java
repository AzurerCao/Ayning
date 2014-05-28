package com.ayning.dao;

import com.ayning.vo.User;

/**
 * User Object Dao
 * 
 * @author Alex
 * 
 */
public interface UserDao {

	/**
	 * Add a new user.
	 * 
	 * Attention: the following properties of user object have to be set:
	 * password, email, nickname;
	 * 
	 * @param user
	 * @return primary key of the new added object
	 */
	public String insert(User user);

	/**
	 * Common update a user basic information
	 * 
	 * @param user
	 * @return true if updated successfully, otherwise false will be returned
	 */
	public boolean update(User user);

	public User findByEmail(String email);

	public User findByNickName(String nickName);
	
	/**
	 * Deactivate a user, the id property of this user object has to be set
	 * 
	 * @param user
	 */
	public void delete(User user);

	/**
	 * Update a user's reputation
	 * 
	 * @param reputation
	 * @param user
	 */
	public void updateReputation(int reputation, User user);

	/**
	 * Update the user's password, password and id property of user object can
	 * not be null
	 * 
	 * @param password
	 * @param user
	 */
	public void updatePassword(String password, User user);

	/**
	 * Id property of user object has to be set
	 * 
	 * @param user
	 */
	public void updateSigninTime(User user);
}
