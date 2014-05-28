package com.ayning.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Security related util
 * 
 * @author Alex
 * 
 */
public class SecurityUtil {

	/**
	 * 
	 * @param pwd
	 * @return an encrypted String using MD5
	 * @throws NoSuchAlgorithmException
	 */
	public static final String encryptMD5(String pwd)
			throws NoSuchAlgorithmException {
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] btInput = pwd.getBytes();
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(btInput);
		byte[] md = mdInst.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = md5String[byte0 >>> 4 & 0xf];
			str[k++] = md5String[byte0 & 0xf];
		}

		return new String(str);

	}

}
