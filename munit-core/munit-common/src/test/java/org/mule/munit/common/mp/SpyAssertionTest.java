/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import static junit.framework.Assert.assertEquals;
import org.mule.api.processor.MessageProcessor;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SpyAssertionTest
{

    @Test
    public void gettersMustReturnTheOriginalValues()
    {
        ArrayList<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();
        ArrayList<MessageProcessor> afterMessageProcessors = new ArrayList<MessageProcessor>();
        SpyAssertion spyAssertion = new SpyAssertion(beforeMessageProcessors, afterMessageProcessors);

        assertEquals(beforeMessageProcessors, spyAssertion.getBeforeMessageProcessors());
        assertEquals(afterMessageProcessors, spyAssertion.getAfterMessageProcessors());
    }

    @Test
    public void gettersMustReturnTheOriginalValuesAddedBySetters()
    {
        ArrayList<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();
        ArrayList<MessageProcessor> afterMessageProcessors = new ArrayList<MessageProcessor>();
        SpyAssertion spyAssertion = new SpyAssertion(null, null);
        spyAssertion.setAfterMessageProcessors(afterMessageProcessors);
        spyAssertion.setBeforeMessageProcessors(beforeMessageProcessors);

        assertEquals(beforeMessageProcessors, spyAssertion.getBeforeMessageProcessors());
        assertEquals(afterMessageProcessors, spyAssertion.getAfterMessageProcessors());
    }
}
