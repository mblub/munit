/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.common.mp;

import org.mule.modules.interceptor.processors.AbstractMessageProcessorInterceptor;

/**
 * <p/>
 * Just an abstract class that forces all the interceptors of Munit to have a fileName and lineNumber of the
 * element that is being mocked.
 * <p/>
 *
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public abstract class AbstractMunitMessageProcessorInterceptor extends AbstractMessageProcessorInterceptor
{

    /**
     * <p>
     * The file name where the element that is being mocked is.
     * </p>
     */
    protected String fileName;

    /**
     * <p>
     * The start line number where the element that is being mocked is.
     * </p>
     */
    protected String lineNumber;

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setLineNumber(String lineNumber)
    {
        this.lineNumber = lineNumber;
    }
}
