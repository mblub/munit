/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
