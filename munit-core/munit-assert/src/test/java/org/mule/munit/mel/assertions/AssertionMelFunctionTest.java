/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mule.api.el.ExpressionLanguageContext;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class AssertionMelFunctionTest
{

    private ExpressionLanguageContext context = mock(ExpressionLanguageContext.class);

    @Test
    public void test()
    {
        new MockFunction().getMuleMessageFrom(context);

        verify(context).getVariable("_muleMessage");
    }

    private class MockFunction extends AssertionMelFunction
    {

        @Override
        public Object call(Object[] params, ExpressionLanguageContext context)
        {
            return null;
        }
    }
}
