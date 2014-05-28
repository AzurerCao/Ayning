package com.ayning.dao;

import java.sql.Timestamp;
import java.util.List;

import com.ayning.vo.OperationLog;
import com.ayning.vo.User;

/**
 * OperationLog Dao
 * 
 * @author Alex
 * 
 */
public interface LogDao {

	/**
	 * Insert a new OperationLog record into database
	 * 
	 * @param log
	 * @return the primary key of the new inserted OperationLog object
	 */
	public int insert(OperationLog log);

	/**
	 * Query OperationLog by User object, with user object's id
	 * 
	 * @param user
	 * @return a List of OperationLog
	 */
	public List<OperationLog> queryByUser(User user);

	/**
	 * Query OperationLog by a time block, including start time but does not
	 * exclude end. At least one of start and end should not be empty or null
	 * 
	 * @param start
	 * @param end
	 * @return a List of Operation log
	 */
	public List<OperationLog> queryByTime(Timestamp start, Timestamp end);
}
