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
import org.mule.api.expression.ExpressionManager;
import org.mule.munit.AssertModule;
import org.mule.util.TemplateParser;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public abstract class AbstractMessageProcessorTest
{

    public static final String NULL_TEST_MESSAGE = null;

    protected MuleMessage muleMessage;
    protected AssertModule module;
    protected ExpressionManager expressionManager;

    @Before
    public void setUp()
    {
        muleMessage = mock(MuleMessage.class);
        module = mock(AssertModule.class);
        expressionManager = mock((ExpressionManager.class));
    }

    @Test
    public void checkProcessorName()
    {
        MunitMessageProcessor mp = buildMp("");
        assertEquals(getExpectedName(), mp.getProcessor());
    }

    protected MunitMessageProcessor buildMp(String message)
    {
        MunitMessageProcessor mp = doBuildMp( message);
        mp.expressionManager = this.expressionManager;
        mp.patternInfo = TemplateParser.createMuleStyleParser().getStyle();

        return mp;
    }

    protected abstract MunitMessageProcessor doBuildMp(String message);

    protected abstract String getExpectedName();
}
