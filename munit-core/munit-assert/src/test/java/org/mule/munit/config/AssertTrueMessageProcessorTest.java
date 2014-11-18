/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;

import org.junit.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertTrueMessageProcessorTest extends AbstractMessageProcessorTest {

    public static final String TEST_MESSAGE = "testMessage";
    public static final String EXP = "#[exp]";


    @Test
    public void calledCorrectly() {
        AssertTrueMessageProcessor mp = (AssertTrueMessageProcessor) buildMp(TEST_MESSAGE);
        mp.setCondition(EXP);

        when(expressionManager.evaluate(EXP, muleMessage)).thenReturn(true);
        when(expressionManager.parse(TEST_MESSAGE, muleMessage)).thenReturn(TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).assertTrue(TEST_MESSAGE, true);
    }

    @Test
    public void testDoNotSendMessage() {

        AssertTrueMessageProcessor mp = (AssertTrueMessageProcessor) buildMp(NULL_TEST_MESSAGE);
        mp.setCondition(EXP);

        when(expressionManager.evaluate(EXP, muleMessage)).thenReturn(true);
        when(expressionManager.parse(NULL_TEST_MESSAGE, muleMessage)).thenReturn(NULL_TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).assertTrue(NULL_TEST_MESSAGE, true);
    }

    @Override
    protected MunitMessageProcessor doBuildMp(String message) {
        AssertTrueMessageProcessor mp = new AssertTrueMessageProcessor();
        mp.setMessage(message);
        return mp;
    }

    @Override
    protected String getExpectedName() {
        return "assertTrue";
    }
}
