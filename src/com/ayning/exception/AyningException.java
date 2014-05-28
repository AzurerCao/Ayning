package com.ayning.exception;

/**
 * Ayning top level Exception
 * 
 * @author Alex
 * 
 */
public class AyningException extends Exception {

	private static final long serialVersionUID = 8494565101683201401L;

	public AyningException(String msg) {
		super(msg);
	}

	public AyningException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
