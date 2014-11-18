/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;


import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertFalseMessageProcessorTest extends AbstractMessageProcessorTest {

    public static final String FALSE_CONDITION = "#[1==2]";
    public static final String TEST_MESSAGE = "testMessage";

    @Test
    public void validateFalseCondition() {
        AssertFalseMessageProcessor mp = (AssertFalseMessageProcessor) buildMp(TEST_MESSAGE);
        mp.setCondition(FALSE_CONDITION);


        when(expressionManager.evaluate(FALSE_CONDITION, muleMessage)).thenReturn(false);
        when(expressionManager.parse(TEST_MESSAGE, muleMessage)).thenReturn(TEST_MESSAGE);


        mp.doProcess(muleMessage, module);

        verify(module, times(1)).assertFalse(TEST_MESSAGE, false);
    }

    @Test
    public void testDoNotSendMessage()
    {
        AssertFalseMessageProcessor mp = (AssertFalseMessageProcessor) buildMp(NULL_TEST_MESSAGE);
        mp.setCondition(FALSE_CONDITION);


        when(expressionManager.evaluate(FALSE_CONDITION, muleMessage)).thenReturn(false);
        when(expressionManager.parse(NULL_TEST_MESSAGE, muleMessage)).thenReturn(NULL_TEST_MESSAGE);


        mp.doProcess(muleMessage, module);

        verify(module, times(1)).assertFalse(NULL_TEST_MESSAGE, false);

    }

    @Override
    protected MunitMessageProcessor doBuildMp(String message) {
        AssertFalseMessageProcessor mp = new AssertFalseMessageProcessor();
        mp.setMessage(message);
        return mp;
    }

    @Override
    protected String getExpectedName() {
        return "assertFalse";
    }
}
