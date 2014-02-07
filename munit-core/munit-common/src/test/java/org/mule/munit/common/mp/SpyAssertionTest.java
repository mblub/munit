/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import static junit.framework.Assert.assertEquals;
import org.mule.api.processor.MessageProcessor;
import org.mule.modules.interceptor.processors.MessageProcessorCall;
import org.mule.modules.interceptor.processors.MessageProcessorId;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SpyAssertionTest
{

    public static final MessageProcessorCall MESSAGE_PROCESSOR_CALL = new MessageProcessorCall(new MessageProcessorId("test", "test"));

    @Test
    public void gettersMustReturnTheOriginalValues()
    {
        ArrayList<MessageProcessor> mps = new ArrayList<MessageProcessor>();
        SpyAssertion spyAssertion = new SpyAssertion(MESSAGE_PROCESSOR_CALL, mps);

        assertEquals(mps, spyAssertion.getMessageProcessors());
        assertEquals(MESSAGE_PROCESSOR_CALL, spyAssertion.getMessageProcessorCall());
    }
}
