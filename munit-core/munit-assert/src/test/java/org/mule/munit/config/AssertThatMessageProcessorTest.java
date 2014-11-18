/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;

import org.junit.Test;

import org.mule.transport.NullPayload;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertThatMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String TEST_MESSAGE = "testMessage";
    public static final String EXP = "#[exp]";
    public static final String RETURN_VALUE = "r1";

    @Test
    public void calledCorrectly()
    {
        AssertThatMessageProcessor mp = (AssertThatMessageProcessor) buildMp(TEST_MESSAGE);
        mp.setPayloadIs(EXP);

        when(expressionManager.evaluate(EXP, muleMessage)).thenReturn(RETURN_VALUE);
        when(muleMessage.getPayload()).thenReturn(NullPayload.getInstance());
        when(expressionManager.parse(TEST_MESSAGE, muleMessage)).thenReturn(TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).assertThat(TEST_MESSAGE, RETURN_VALUE, NullPayload.getInstance());
    }

    @Test
    public void testDoNotSendMessage()
    {
        AssertThatMessageProcessor mp = (AssertThatMessageProcessor) buildMp(NULL_TEST_MESSAGE);
        mp.setPayloadIs(EXP);

        when(expressionManager.evaluate(EXP, muleMessage)).thenReturn(RETURN_VALUE);
        when(muleMessage.getPayload()).thenReturn(NullPayload.getInstance());
        when(expressionManager.parse(NULL_TEST_MESSAGE, muleMessage)).thenReturn(NULL_TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).assertThat(NULL_TEST_MESSAGE, RETURN_VALUE, NullPayload.getInstance());
    }

    @Override
    protected MunitMessageProcessor doBuildMp(String message)
    {
        AssertThatMessageProcessor mp = new AssertThatMessageProcessor();
        mp.setMessage(message);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "assertThat";
    }
}
