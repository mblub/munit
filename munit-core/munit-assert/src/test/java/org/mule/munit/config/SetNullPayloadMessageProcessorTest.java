/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;


import org.junit.Before;
import org.junit.Test;

import org.mule.api.MuleMessage;
import org.mule.transport.NullPayload;

import static org.mockito.Mockito.*;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SetNullPayloadMessageProcessorTest extends AbstractMessageProcessorTest
{

    MuleMessage muleMessage;

    @Before
    public void setUp()
    {
        muleMessage = mock(MuleMessage.class);
    }

    @Test
    public void alwaysSetNullPayload()
    {
        MunitMessageProcessor mp = buildMp(null);
        mp.doProcess(muleMessage, null);

        verify(muleMessage, times(1)).setPayload(NullPayload.getInstance());
    }

    @Override
    protected MunitMessageProcessor doBuildMp(String message)
    {
        return new SetNullPayloadMessageProcessor();
    }

    protected String getExpectedName()
    {
        return "SetNull";
    }
}
