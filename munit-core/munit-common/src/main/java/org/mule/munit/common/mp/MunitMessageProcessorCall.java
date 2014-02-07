/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
