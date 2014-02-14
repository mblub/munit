/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
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
public class MessageHasElementAssertionMelFunctionTest
{

    private MessageHasElementAssertionCommand command = mock(MessageHasElementAssertionCommand.class);
    private ExpressionLanguageContext context = mock(ExpressionLanguageContext.class);
    private MuleMessage message = mock(MuleMessage.class);

    @Test
    public void whenNullParamsReturnFalse()
    {
        assertFalse((Boolean) new MessageHasElementAssertionMelFunction(command).call(null, context));
    }

    @Test
    public void whenEmptyParamsReturnFalse()
    {
        assertFalse((Boolean) new MessageHasElementAssertionMelFunction(command).call(new Object[] {}, context));
    }

    @Test
    public void whenNotStringParamsReturnFalse()
    {
        assertFalse((Boolean) new MessageHasElementAssertionMelFunction(command).call(new Object[] {new Object()}, context));
    }

    @Test
    public void whenMessageIsNullReturnFalse()
    {
        assertFalse((Boolean) new MockFunction(command, null).call(new Object[] {"anyString"}, context));
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
