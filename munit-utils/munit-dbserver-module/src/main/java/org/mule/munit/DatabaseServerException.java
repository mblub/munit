/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

public class DatabaseServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseServerException() {
		super();
	}

	public DatabaseServerException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public DatabaseServerException(final String arg0) {
		super(arg0);
	}

	public DatabaseServerException(final Throwable arg0) {
		super(arg0);
	}

}
