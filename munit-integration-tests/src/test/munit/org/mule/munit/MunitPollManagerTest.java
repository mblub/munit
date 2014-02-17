/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mule.api.MuleContext;
import org.mule.api.registry.MuleRegistry;
import org.mule.api.registry.RegistrationException;
import org.mule.api.schedule.Scheduler;
import org.mule.api.schedule.SchedulerFactoryPostProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.transport.PollingReceiverWorker;
import org.mule.util.Predicate;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.5.
 */
public class MunitPollManagerTest
{

    private MuleContext muleContext = mock(MuleContext.class);
    private MuleRegistry muleRegistry = mock(MuleRegistry.class);
    private Scheduler scheduler = mock(Scheduler.class);
    private PollingReceiverWorker pollingReceiverWorker = mock(PollingReceiverWorker.class);

    @Before
    public void setContextExpectedBehavior()
    {
        when(muleContext.getRegistry()).thenReturn(muleRegistry);
    }

    @Test
    public void registerSuccessfully() throws RegistrationException
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        manager.avoidPollLaunch();

        verify(muleRegistry).registerObject(eq("__Munit_Poll_Stopper"), any(SchedulerFactoryPostProcessor.class));
    }

    @Test(expected = RuntimeException.class)
    public void failOnRegister() throws RegistrationException
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        doThrow(new RegistrationException(CoreMessages.noMuleTransactionAvailable())).when(muleRegistry)
                .registerObject(eq("__Munit_Poll_Stopper"), any(SchedulerFactoryPostProcessor.class));

        manager.avoidPollLaunch();
    }

    @Test(expected = Exception.class)
    public void stopWrongFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

       when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(new ArrayList<Scheduler>());

        manager.stopPoll("flowName");
    }

    @Test(expected = Exception.class)
    public void startWrongFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(new ArrayList<Scheduler>());

        manager.startPoll("flowName");
    }

    @Test(expected = Exception.class)
    public void scheduleWrongFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(new ArrayList<Scheduler>());

        manager.schedulePoll("flowName");
    }


    @Test
    public void scheduleCorrectFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(Arrays.asList(scheduler));

        manager.schedulePoll("flowName");

        verify(scheduler).schedule();
    }


    @Test
    public void stopCorrectFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(Arrays.asList(scheduler));

        manager.stopPoll("flowName");

        verify(scheduler).stop();
    }

    @Test
    public void startCorrectFlow() throws Exception
    {
        MunitPollManager manager = new MunitPollManager(muleContext);

        when(muleContext.getRegistry().lookupScheduler(any(Predicate.class))).thenReturn(Arrays.asList(scheduler));

        manager.startPoll("flowName");

        verify(scheduler).start();
    }


    @Test
    public void testFactoryPostProcessorNotPoll(){
        assertEquals(scheduler, MunitPollManager.postProcessSchedulerFactory(new Object(), scheduler));
    }


    @Test
    public void testFactoryPostProcessorPoll(){
        assertNotSame(scheduler, MunitPollManager.postProcessSchedulerFactory(pollingReceiverWorker, scheduler));
    }

}
