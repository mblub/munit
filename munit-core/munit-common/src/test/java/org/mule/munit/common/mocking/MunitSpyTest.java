package org.mule.munit.common.mocking;

import org.junit.Before;
import org.junit.Test;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.MuleRegistry;
import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.munit.common.mp.MockedMessageProcessorManager;
import org.mule.munit.common.mp.SpyAssertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Federico, Fernando
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
        verify(manager, times(0)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereIsASpyProcessBefore()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(1)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereIsASpyProcessAfter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .after(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(1)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }


    @Test
    public void testAddSpyIsCalledWhenThereAreSpyProcessesBeforeAndAfter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(Arrays.asList(mock(SpyProcess.class)))
                .after(Arrays.asList(mock(SpyProcess.class)));
        verify(manager, times(2)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }

    @Test
    public void testAddSpyIsCalledWhenThereAreSpyProcessesBeforeAndAfterWithArrayParameter()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before(mock(SpyProcess.class))
                .after(mock(SpyProcess.class));
        verify(manager, times(2)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }

    @Test
    public void testSpyWithNullBefore()
    {
        new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .before((List<SpyProcess>) null)
                .after((List<SpyProcess>) null);
        verify(manager, times(0)).addSpyAssertion(any(MessageProcessorId.class), any(SpyAssertion.class));
    }

    @Test
    public void testRunSpyProcess() throws MuleException
    {
        ArrayList<SpyProcess> calls = new ArrayList<SpyProcess>();
        Spy spy = new Spy();
        calls.add(spy);
        SpyAssertion spyAssertion = new MunitSpy(muleContext).spyMessageProcessor("test")
                .ofNamespace("testNamespace")
                .createSpyAssertion(calls, calls);

        for (MessageProcessor mp : spyAssertion.getAfterMessageProcessors())
        {
            mp.process(null);
        }

        for (MessageProcessor mp : spyAssertion.getBeforeMessageProcessors())
        {
            mp.process(null);
        }

        assertEquals(2, spy.timesCalled);
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
