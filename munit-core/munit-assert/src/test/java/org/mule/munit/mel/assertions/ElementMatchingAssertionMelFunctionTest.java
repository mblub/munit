package org.mule.munit.mel.assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class ElementMatchingAssertionMelFunctionTest
{

    private MessageHasElementAssertionCommand command = mock(MessageHasElementAssertionCommand.class);
    private ExpressionLanguageContext context = mock(ExpressionLanguageContext.class);
    private MuleMessage message = mock(MuleMessage.class);

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void whenNullParamsThrowException()
    {
        assertFalse((Boolean) new ElementMatchingAssertionMelFunction().call(null, context));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void whenEmptyParamsThrowException()
    {
        assertFalse((Boolean) new ElementMatchingAssertionMelFunction().call(new Object[] {}, context));
    }

    @Test(expected = java.lang.RuntimeException.class)
    public void whenNotStringParamsThrowException()
    {
        assertFalse((Boolean) new ElementMatchingAssertionMelFunction().call(new Object[] {new Object()}, context));
    }

    @Test
    public void whenHasMessageThenExecuteCommand()
    {

        when(command.messageHas("anyString", message)).thenReturn(true);

        assertTrue((Boolean) new MockFunction(command, message).call(new Object[] {"anyString"}, context));

        verify(command).messageHas("anyString", message);
    }

    private class MockFunction extends MessageHasElementAssertionMelFunction
    {

        MuleMessage message;

        public MockFunction(MessageHasElementAssertionCommand command, MuleMessage message)
        {
            super(command);
            this.message = message;
        }

        @Override
        protected MuleMessage getMuleMessageFrom(ExpressionLanguageContext context)
        {
            return message;
        }
    }
}
