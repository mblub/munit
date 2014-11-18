/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;


import org.junit.Test;

import org.mule.transport.NullPayload;

import static org.mockito.Mockito.*;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertNotNullMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String TEST_MESSAGE = "TEST MESSAGE";


    @Test
    public void testSendCorrectParams()
    {
        MunitMessageProcessor mp = buildMp(TEST_MESSAGE);

        when(muleMessage.getPayload()).thenReturn(NullPayload.getInstance());
        when(expressionManager.parse(TEST_MESSAGE, muleMessage)).thenReturn(TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module, times(1)).assertNotNull(TEST_MESSAGE, NullPayload.getInstance());
        verify(muleMessage, times(1)).getPayload();
    }

    @Test
    public void testDoNotSendMessage()
    {

        MunitMessageProcessor mp = buildMp(NULL_TEST_MESSAGE);

        when(muleMessage.getPayload()).thenReturn(NullPayload.getInstance());
        when(expressionManager.parse(NULL_TEST_MESSAGE, muleMessage)).thenReturn(NULL_TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module, times(1)).assertNotNull(NULL_TEST_MESSAGE, NullPayload.getInstance());
        verify(muleMessage, times(1)).getPayload();
    }


    @Override
    protected MunitMessageProcessor doBuildMp(String message)
    {
        AssertNotNullMessageProcessor mp = new AssertNotNullMessageProcessor();
        mp.setMessage(message);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "assertNotNull";
    }
}
