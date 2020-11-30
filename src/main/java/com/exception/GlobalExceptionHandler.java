package com.exception;

import org.apache.log4j.Logger;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOGGER.error("Unhandled exception caught: " + e.getLocalizedMessage());
	}
}
