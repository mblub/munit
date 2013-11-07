/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import org.mule.api.processor.MessageProcessor;
import org.mule.modules.interceptor.processors.MessageProcessorCall;
import org.mule.modules.interceptor.processors.MessageProcessorCallAction;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * The Assertions the must be executed after and before a message processor call
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SpyAssertion extends MessageProcessorCallAction
{

    /**
     * <p>
     * The Message processors to be executed for the call
     * </p>
     */
    private List<MessageProcessor> messageProcessors = new ArrayList<MessageProcessor>();


    public SpyAssertion(MessageProcessorCall messageProcessorCall, List<MessageProcessor> messageProcessors)
    {
        super(messageProcessorCall);
        this.messageProcessors = messageProcessors;
    }

    public List<MessageProcessor> getMessageProcessors()
    {
        return messageProcessors;
    }

    public void setMessageProcessors(List<MessageProcessor> messageProcessors)
    {
        this.messageProcessors = messageProcessors;
    }

}
