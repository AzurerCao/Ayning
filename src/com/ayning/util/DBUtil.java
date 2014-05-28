package com.ayning.util;

import java.util.UUID;

/**
 * Util class for database operation
 * 
 * @author Alex
 * 
 */
public class DBUtil {

	/**
	 * Generate a UUID, mostly used for generating a row primary key
	 * 
	 * @return a UUID String
	 */
	public static final String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
