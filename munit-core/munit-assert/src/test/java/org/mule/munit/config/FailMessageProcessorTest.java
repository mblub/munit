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
public class FailMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String TEST_MESSAGE = "testMessage";


    @Test
    public void calledCorrectly()
    {
        MunitMessageProcessor mp = buildMp(TEST_MESSAGE);

        when(expressionManager.parse(TEST_MESSAGE,muleMessage)).thenReturn(TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).fail(TEST_MESSAGE);
    }

    @Test
    public void testDoNotSendMessage() {
        MunitMessageProcessor mp = buildMp(NULL_TEST_MESSAGE);

        when(expressionManager.parse(TEST_MESSAGE,muleMessage)).thenReturn(NULL_TEST_MESSAGE);

        mp.doProcess(muleMessage, module);

        verify(module).fail(NULL_TEST_MESSAGE);
    }

    @Override
    protected MunitMessageProcessor doBuildMp(String message)
    {
        FailMessageProcessor mp = new FailMessageProcessor();
        mp.setMessage(message);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "Fail";
    }
}
