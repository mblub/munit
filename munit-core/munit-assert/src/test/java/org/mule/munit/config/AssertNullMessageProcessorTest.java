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
public class AssertNullMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String TEST_MESSAGE = "testMessage";


    @Test
    public void testCallCorrectly()
    {
        MunitMessageProcessor mp = buildMp();

        when(muleMessage.getPayload()).thenReturn(NullPayload.getInstance());

        mp.doProcess(muleMessage, module);

        verify(module).assertNull(TEST_MESSAGE, NullPayload.getInstance());
    }


    @Override
    protected MunitMessageProcessor doBuildMp()
    {
        AssertNullMessageProcessor mp = new AssertNullMessageProcessor();
        mp.setMessage(TEST_MESSAGE);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "assertNull";
    }
}
