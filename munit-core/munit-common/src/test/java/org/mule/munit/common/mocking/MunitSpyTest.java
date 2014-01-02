/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mocking;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.MuleRegistry;
import org.mule.modules.interceptor.processors.MessageProcessorCall;
import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.munit.common.mp.MockedMessageProcessorManager;
import org.mule.munit.common.mp.SpyAssertion;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitSpyTest
{

    private MuleContext muleContext;
    private MuleRegistry muleRegistry;
    private MockedMessageProcessorManager manager;

    @Before
    public void setUp()
    {
        muleContext = mock(MuleContext.class);
        muleRegistry = mock(MuleRegistry.class);
        manager = mock(MockedMessageProcessorManager.class);

        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(MockedMessageProcessorManager.ID)).thenReturn(manager);

    }

    @Test
    public void testAddSpyIsNotCalledWhenThereIsNothingToSpy()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(Collections.<SpyProcess>emptyList());
        verify(manager, times(0)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereIsASpyProcessBefore()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(1)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereIsASpyProcessAfter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .after(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(1)).addAfterCallSpyAssertion(any(SpyAssertion.class));
    }


    @Test
    public void testAddSpyIsCalledWhenThereAreSpyProcessesBeforeAndAfter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(Arrays.asList(mock(SpyProcess.class)))
                .after(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(1)).addAfterCallSpyAssertion(any(SpyAssertion.class));
        verify(manager, times(1)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereAreSpyProcessesBeforeAndAfterWithArrayParameter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(mock(SpyProcess.class))
                .after(mock(SpyProcess.class));
        verify(manager, times(1)).addAfterCallSpyAssertion(any(SpyAssertion.class));
        verify(manager, times(1)).addBeforeCallSpyAssertion(any(SpyAssertion.class));

    }

    @Test
    public void testSpyWithNullBefore()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before((List<SpyProcess>) null)
                .after((List<SpyProcess>) null);
        verify(manager, times(0)).addAfterCallSpyAssertion(any(SpyAssertion.class));
        verify(manager, times(0)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }

    @Test
    public void testRunSpyProcess() throws MuleException
    {
        ArrayList<SpyProcess> calls = new ArrayList<SpyProcess>();
        Spy spy = new Spy();
        calls.add(spy);
        SpyAssertion spyAssertion = new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .createSpyAssertion(new MessageProcessorCall(new MessageProcessorId("test", "testNamespace")), calls);

        for (MessageProcessor mp : spyAssertion.getMessageProcessors())
        {
            mp.process(null);
        }

        assertEquals(1, spy.timesCalled);
    }

    @Test
    public void testSpyWithAttributesMapParameter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .withAttributes(new HashMap<String, Object>())
                .before((List<SpyProcess>) null)
                .after((List<SpyProcess>) null);
        verify(manager, times(0)).addAfterCallSpyAssertion(any(SpyAssertion.class));
        verify(manager, times(0)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }
    
    @Test
    public void testSpyWithAttributesObjectParameter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .withAttributes(Attribute.attribute("any"))
                .before((List<SpyProcess>) null)
                .after((List<SpyProcess>) null);
        verify(manager, times(0)).addAfterCallSpyAssertion(any(SpyAssertion.class));
        verify(manager, times(0)).addBeforeCallSpyAssertion(any(SpyAssertion.class));
    }

    private class Spy implements SpyProcess
    {

        int timesCalled = 0;

        @Override
        public void spy(MuleEvent event)
        {
            timesCalled++;
        }
    }
}
