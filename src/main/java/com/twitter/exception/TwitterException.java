package com.twitter.exception;

public class TwitterException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitterException() {
		super();
	}

	public TwitterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TwitterException(String message, Throwable cause) {
		super(message, cause);
	}

	public TwitterException(String message) {
		super(message);
	}

	public TwitterException(Throwable cause) {
		super(cause);
	}

}
