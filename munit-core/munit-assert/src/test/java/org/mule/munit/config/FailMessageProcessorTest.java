/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;

import org.junit.Test;

import static org.mockito.Mockito.verify;

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
        MunitMessageProcessor mp = buildMp();


        mp.doProcess(muleMessage, module);

        verify(module).fail(TEST_MESSAGE);
    }

    @Override
    protected MunitMessageProcessor doBuildMp()
    {
        FailMessageProcessor mp = new FailMessageProcessor();
        mp.setMessage(TEST_MESSAGE);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "Fail";
    }
}
