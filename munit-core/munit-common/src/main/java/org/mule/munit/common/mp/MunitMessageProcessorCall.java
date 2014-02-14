/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import org.mule.modules.interceptor.processors.MessageProcessorCall;
import org.mule.modules.interceptor.processors.MessageProcessorId;

/**
 * <p>
 * Representation of the message processor call for Munit
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class MunitMessageProcessorCall extends MessageProcessorCall
{

    private String fileName;
    private String lineNumber;

    public MunitMessageProcessorCall(MessageProcessorId messageProcessorId)
    {
        super(messageProcessorId);
    }


    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setLineNumber(String lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public String getLineNumber()
    {
        return lineNumber;
    }
}
