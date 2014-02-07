/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
