/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;


import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.transport.PropertyScope;
import org.mule.modules.interceptor.matchers.mel.AnyClassMatcherFunction;
import org.mule.modules.interceptor.matchers.mel.AnyMatcherFunction;
import org.mule.modules.interceptor.matchers.mel.EqMatcherFunction;
import org.mule.modules.interceptor.matchers.mel.NotNullMatcherFunction;
import org.mule.modules.interceptor.matchers.mel.NullMatcherFunction;
import org.mule.munit.mel.assertions.AssertionMelFunction;
import org.mule.munit.mel.assertions.ElementMatcher;
import org.mule.munit.mel.assertions.ElementMatcherFactory;
import org.mule.munit.mel.assertions.MessageHasElementAssertionCommand;
import org.mule.munit.mel.assertions.MessageHasElementAssertionMelFunction;
import org.mule.munit.mel.assertions.MessageMatchingAssertionMelFunction;
import org.mule.munit.mel.utils.FlowResultFunction;
import org.mule.munit.mel.utils.GetResourceFunction;
import org.mule.transport.NullPayload;

import javax.activation.DataHandler;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertModuleTest
{

    private ExpressionLanguageContext context = mock(ExpressionLanguageContext.class);

    /**
     * Two equal payloads should not fail
     */
    @Test
    public void assertPayloadOkWithEmptyMessage()
    {
        module().assertThat(null, "a", "a");
    }

    /**
     * Two equal payloads should not fail
     */
    @Test
    public void assertPayloadOkWithMessage()
    {
        module().assertThat("My Message", "a", "a");
    }

    /**
     * Two different payloads must fail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertPayloadFailWithEmptyMessage()
    {
        module().assertThat(null, "a", "b");
    }

    /**
     * Assert True accepts true values
     */
    @Test
    public void assertTrueOkWithTrueValues()
    {
        module().assertTrue(null, true);
    }

    /**
     * Assert True rejects false values
     */
    @Test(expected = AssertionFailedError.class)
    public void assertTrueRejectsFalseValues()
    {
        module().assertTrue(null, false);
    }

    /**
     * if two expressions are equal then return ok
     */
    @Test
    public void assertOnEqualsOk()
    {
        module().assertOnEquals(null, "a", "a");
    }

    /**
     * if two expressions are not equal then fail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertOnEqualsFail()
    {
        module().assertOnEquals(null, "a", "b");
    }

    /**
     * if are equal Fail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertNotSameFail()
    {
        module().assertNotSame(null, "a", "a");
    }

    /**
     * if are different ok
     */
    @Test
    public void assertNotSameOk()
    {
        module().assertNotSame(null, "a", "b");
    }


    /**
     * if true then fail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertTrueFail()
    {
        module().assertFalse(null, true);
    }

    /**
     * if is false ok
     */
    @Test
    public void assertFalseOk()
    {
        module().assertFalse(null, false);
    }

    /**
     * if Null Payload ThenFail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertNotNullFail()
    {
        module().assertNotNull(null, NullPayload.getInstance());
    }

    /**
     * if not Null then ok
     */
    @Test
    public void assertNotNullOk()
    {
        module().assertNotNull(null, new Object());
    }

    /**
     * if not Null Payload ThenFail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertNullFail()
    {
        module().assertNull(null, new Object());
    }

    /**
     * if  Null then ok
     */
    @Test
    public void assertNullOk()
    {
        module().assertNull(null, NullPayload.getInstance());
    }

    /**
     * No matter what just fail
     */
    @Test(expected = AssertionFailedError.class)
    public void assertFail()
    {
        module().fail(null);
    }


    /**
     * Make sure all the mel expressions are set in the context
     */
    @Test
    public void configureContext()
    {
        AssertModule module = module();
        module.configureContext(context);

        verify(context).declareFunction(eq("messageHasPropertyInAnyScopeCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getProperty("param", PropertyScope.INBOUND)).thenReturn(new Object());

                return command.messageHas("param", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasInboundPropertyCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getInboundProperty("param")).thenReturn(new Object());

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasOutboundPropertyCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getOutboundProperty("param")).thenReturn(new Object());

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasSessionPropertyCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getProperty("param", PropertyScope.SESSION)).thenReturn(new Object());

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasInvocationPropertyCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getInvocationProperty("param")).thenReturn(new Object());

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasInboundAttachmentCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getInboundAttachment("param")).thenReturn(new DataHandler(new Object(), "url"));

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageHasOutboundAttachmentCalled"), argThat(functionMatcher(MessageHasElementAssertionMelFunction.class, new CommandValidator<MessageHasElementAssertionCommand>()
        {

            @Override
            public boolean validate(MessageHasElementAssertionCommand command)
            {
                MuleMessage message = mock(MuleMessage.class);
                when(message.getOutboundAttachment("param")).thenReturn(new DataHandler(new Object(), "url"));

                return command.messageHas("param", message) && !command.messageHas("param2", message);
            }
        })));
        verify(context).declareFunction(eq("messageInboundProperty"), argThat(assertMatcher(MessageMatchingAssertionMelFunction.class, new CommandValidator<ElementMatcherFactory>()
        {

            @Override
            public boolean validate(ElementMatcherFactory command)
            {
                MuleMessage message = mock(MuleMessage.class);
                command.build("param", message);

                verify(message).getInboundProperty("param");

                return true;
            }
        })));
        verify(context).declareFunction(eq("messageOutboundProperty"), argThat(assertMatcher(MessageMatchingAssertionMelFunction.class, new CommandValidator<ElementMatcherFactory>()
        {

            @Override
            public boolean validate(ElementMatcherFactory command)
            {
                MuleMessage message = mock(MuleMessage.class);
                command.build("param", message);

                verify(message).getOutboundProperty("param");

                return true;
            }
        })));
        verify(context).declareFunction(eq("messageInvocationProperty"), argThat(assertMatcher(MessageMatchingAssertionMelFunction.class, new CommandValidator<ElementMatcherFactory>()
        {

            @Override
            public boolean validate(ElementMatcherFactory command)
            {
                MuleMessage message = mock(MuleMessage.class);
                command.build("param", message);

                verify(message).getInvocationProperty("param");

                return true;
            }
        })));
        verify(context).declareFunction(eq("messageInboundAttachment"), argThat(assertMatcher(MessageMatchingAssertionMelFunction.class, new CommandValidator<ElementMatcherFactory>()
        {

            @Override
            public boolean validate(ElementMatcherFactory command)
            {
                MuleMessage message = mock(MuleMessage.class);
                command.build("param", message);

                verify(message).getInboundAttachment("param");

                return true;
            }
        })));
        verify(context).declareFunction(eq("messageOutboundAttachment"), argThat(assertMatcher(MessageMatchingAssertionMelFunction.class, new CommandValidator<ElementMatcherFactory>()
        {

            @Override
            public boolean validate(ElementMatcherFactory command)
            {
                MuleMessage message = mock(MuleMessage.class);
                command.build("param", message);

                verify(message).getOutboundAttachment("param");

                return true;
            }
        })));
        verify(context).declareFunction(eq("eq"), isA(EqMatcherFunction.class));
        verify(context).declareFunction(eq("anyByte"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyInt"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyDouble"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyFloat"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyShort"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyObject"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyString"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyList"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anySet"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyMap"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("anyCollection"), isA(AnyMatcherFunction.class));
        verify(context).declareFunction(eq("isNull"), isA(NullMatcherFunction.class));
        verify(context).declareFunction(eq("isNotNull"), isA(NotNullMatcherFunction.class));
        verify(context).declareFunction(eq("any"), isA(AnyClassMatcherFunction.class));
        verify(context).declareFunction(eq("resultOfScript"), isA(FlowResultFunction.class));
        verify(context).declareFunction(eq("getResource"), isA(GetResourceFunction.class));

    }

    private static AssertModule module()
    {
        return new AssertModule();
    }

    private static FunctionMatcher functionMatcher(Class functionClass, CommandValidator commandValidator)
    {
        return new FunctionMatcher(functionClass, commandValidator);
    }

    private static AssertMatcher assertMatcher(Class functionClass, CommandValidator commandValidator)
    {
        return new AssertMatcher(functionClass, commandValidator);
    }

    private static class FunctionMatcher extends ArgumentMatcher<AssertionMelFunction>
    {

        Class functionClass;
        CommandValidator commandValidator;

        public FunctionMatcher(Class functionClass, CommandValidator commandValidator)
        {
            this.functionClass = functionClass;
            this.commandValidator = commandValidator;
        }

        @Override
        public boolean matches(Object o)
        {
            if (o.getClass().isAssignableFrom(functionClass))
            {
                MessageHasElementAssertionMelFunction function = (MessageHasElementAssertionMelFunction) o;
                return commandValidator.validate(function.getCommand());
            }
            else
            {
                return false;
            }
        }

    }

    private static class AssertMatcher extends ArgumentMatcher<AssertionMelFunction>
    {

        Class functionClass;
        CommandValidator commandValidator;

        public AssertMatcher(Class functionClass, CommandValidator commandValidator)
        {
            this.functionClass = functionClass;
            this.commandValidator = commandValidator;
        }

        @Override
        public boolean matches(Object o)
        {
            if (o.getClass().isAssignableFrom(functionClass))
            {
                MessageMatchingAssertionMelFunction function = (MessageMatchingAssertionMelFunction) o;
                return commandValidator.validate(function.getCommand());
            }
            else
            {
                return false;
            }
        }

    }

    private static interface CommandValidator<T>
    {

        boolean validate(T command);
    }


}
