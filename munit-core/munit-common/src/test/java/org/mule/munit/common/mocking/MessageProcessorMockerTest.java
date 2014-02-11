/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mocking;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.registry.MuleRegistry;
import org.mule.modules.interceptor.processors.MessageProcessorBehavior;
import org.mule.munit.common.mp.MockedMessageProcessorManager;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MessageProcessorMockerTest
{

    private MuleContext muleContext;
    private MuleRegistry muleRegistry;
    private MockedMessageProcessorManager manager;
    private DefaultMuleMessage message;

    @Before
    public void setUp()
    {
        muleContext = mock(MuleContext.class);
        muleRegistry = mock(MuleRegistry.class);
        manager = mock(MockedMessageProcessorManager.class);
        message = mock(DefaultMuleMessage.class);

        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(MockedMessageProcessorManager.ID)).thenReturn(manager);

    }

    @Test
    public void addBehavior()
    {
        mocker().when("testMp")
                .ofNamespace("testNamespace")
                .withAttributes(new HashMap<String, Object>())
                .thenReturn(message);

        verify(manager).addBehavior(any(MessageProcessorBehavior.class));
    }

    @Test
    public void addBehaviorWithAttributes()
    {
        mocker().when("testMp")
                .ofNamespace("testNamespace")
                .withAttributes(Attribute.attribute("any"))
                .thenReturn(message);

        verify(manager).addBehavior(any(MessageProcessorBehavior.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfNoMessageProcessorNameNotSet()
    {
        mocker()
                .thenReturn(message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateThatThenThrowChecksMessageProcessorExistence()
    {
        mocker().thenThrow(new Exception());
    }

    @Test
    public void validateThatBehaviorIsAddedWhenThenThrow()
    {
        mocker().when("mp").thenThrow(new Exception());

        verify(manager).addBehavior(any(MessageProcessorBehavior.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIfNoMessageProcessorNameNotSetWheReturnSame()
    {
        mocker()
                .thenReturnSameEvent();
    }

    @Test
    public void validateThatBehaviorIsAddedWhenThenReturnSame()
    {
        mocker().when("mp").thenReturnSameEvent();

        verify(manager).addBehavior(any(MessageProcessorBehavior.class));
    }

    @Test
    public void validateThatBehaviorIsAddedWhenThenApply()
    {
        mocker().when("mp").thenApply(new CopyMessageTransformer(null));

        verify(manager).addBehavior(any(MessageProcessorBehavior.class));
    }

    private MessageProcessorMocker mocker()
    {
        return new MessageProcessorMocker(muleContext);
    }
}
