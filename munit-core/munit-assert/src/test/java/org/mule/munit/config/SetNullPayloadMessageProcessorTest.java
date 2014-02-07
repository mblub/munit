/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
        MunitMessageProcessor mp = buildMp();
        mp.doProcess(muleMessage, null);

        verify(muleMessage, times(1)).setPayload(NullPayload.getInstance());
    }

    @Override
    protected MunitMessageProcessor doBuildMp()
    {
        return new SetNullPayloadMessageProcessor();
    }

    protected String getExpectedName()
    {
        return "SetNull";
    }
}
