/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
