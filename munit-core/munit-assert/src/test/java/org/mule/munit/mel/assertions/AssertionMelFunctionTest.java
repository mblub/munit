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
