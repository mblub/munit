/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

/**
 * Exception for failing database server startup and queries.
 * 
 * @since 3.4-M4
 *
 */
public class DatabaseServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseServerException() {
		super();
	}

	public DatabaseServerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DatabaseServerException(final String message) {
		super(message);
	}

	public DatabaseServerException(final Throwable cause) {
		super(cause);
	}

}
