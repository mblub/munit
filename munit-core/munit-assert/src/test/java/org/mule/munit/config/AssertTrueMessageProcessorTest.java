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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertTrueMessageProcessorTest extends AbstractMessageProcessorTest
{

    public static final String TEST_MESSAGE = "testMessage";
    public static final String EXP = "#[exp]";


    @Test
    public void calledCorrectly()
    {
        AssertTrueMessageProcessor mp = (AssertTrueMessageProcessor) buildMp();
        mp.setCondition(EXP);

        when(expressionManager.evaluate(EXP, muleMessage)).thenReturn(true);

        mp.doProcess(muleMessage, module);

        verify(module).assertTrue(TEST_MESSAGE, true);
    }


    @Override
    protected MunitMessageProcessor doBuildMp()
    {
        AssertTrueMessageProcessor mp = new AssertTrueMessageProcessor();
        mp.setMessage(TEST_MESSAGE);
        return mp;
    }

    @Override
    protected String getExpectedName()
    {
        return "assertTrue";
    }
}
