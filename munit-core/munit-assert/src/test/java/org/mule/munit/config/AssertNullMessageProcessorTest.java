/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
