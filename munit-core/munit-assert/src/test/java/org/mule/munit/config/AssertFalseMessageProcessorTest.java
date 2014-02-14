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
public class AssertFalseMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String FALSE_CONDITION = "#[1==2]";
    public static final String TEST_MESSAGE = "testMessage";

    @Test
    public void validateFalseCondition()
    {
        AssertFalseMessageProcessor mp = (AssertFalseMessageProcessor) buildMp();
        mp.setCondition(FALSE_CONDITION);


        when(expressionManager.evaluate(FALSE_CONDITION, muleMessage)).thenReturn(false);

        mp.doProcess(muleMessage, module);

        verify(module, times(1)).assertFalse(TEST_MESSAGE, false);
    }

    @Override
    protected MunitMessageProcessor doBuildMp()
    {
        AssertFalseMessageProcessor mp = new AssertFalseMessageProcessor();
        mp.setMessage(TEST_MESSAGE);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "assertFalse";
    }
}
