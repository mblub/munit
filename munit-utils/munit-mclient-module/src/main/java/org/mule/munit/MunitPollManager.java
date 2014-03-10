/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit;

import org.mule.api.MuleContext;
import org.mule.api.registry.RegistrationException;
import org.mule.api.schedule.Scheduler;
import org.mule.api.schedule.SchedulerFactoryPostProcessor;
import org.mule.api.schedule.Schedulers;
import org.mule.transport.PollingReceiverWorker;
import org.mule.transport.polling.MessageProcessorPollingMessageReceiver;

import java.util.Collection;

/**
 * <p>
 * Utility class to manage polls in a Munit Test
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.5.0
 */
public class MunitPollManager
{

    /**
     * <p>
     * The test {@link MuleContext}
     * </p>
     */
    private MuleContext muleContext;

    /**
     * <p>
     * Create Instance
     * </p>
     */
    public static MunitPollManager instance(MuleContext context){
        return new MunitPollManager(context);
    }

    /**
     * <p>
     * Action to set the poll as stopped
     * </p>
     *
     * @param job       <p>
     *                  The job the schedule is going to perform
     *                  </p>
     * @param scheduler <p>
     *                  The scheduler to be wrapped
     *                  </p>
     * @return <p>
     *         The wrapped scheduler
     *         </p>
     */
    static Scheduler postProcessSchedulerFactory(Object job, Scheduler scheduler)
    {
        if (job instanceof PollingReceiverWorker)
        {
            return new MunitScheduler(scheduler, (MessageProcessorPollingMessageReceiver) ((PollingReceiverWorker) job).getReceiver());
        }
        else
        {
            return scheduler;
        }
    }

    public MunitPollManager(MuleContext muleContext)
    {
        this.muleContext = muleContext;
    }

    /**
     * <p>
     * Used at {@link MuleContext} initialisation, it avoids polls to start the scheduling.
     * </p>
     */
    public void avoidPollLaunch()
    {
        try
        {
            muleContext.getRegistry().registerObject("__Munit_Poll_Stopper", new SchedulerFactoryPostProcessor()
            {
                @Override
                public Scheduler process(Object o, Scheduler scheduler)
                {
                    return postProcessSchedulerFactory(o, scheduler);
                }
            });
        }
        catch (RegistrationException e)
        {
            throw new RuntimeException("Could not stop polls");
        }
    }

    /**
     * <p>
     * Schedule a poll to run, this is an async call
     * </p>
     *
     * @param flowName <p>
     *                 The name of the flow that contains the poll
     *                 </p>
     * @throws Exception <p>
     *                   Failure Exception
     *                   </p>
     */
    public void schedulePoll(String flowName) throws Exception
    {
        Scheduler scheduler = getScheduler(flowName);
        scheduler.schedule();
    }

    /**
     * <p>
     * Stops a poll that is running
     * </p>
     *
     * @param flowName <p>
     *                 The name of the flow that contains the poll
     *                 </p>
     * @throws Exception <p>
     *                   Failure Exception
     *                   </p>
     */
    public void stopPoll(String flowName) throws Exception
    {
        Scheduler scheduler = getScheduler(flowName);
        scheduler.stop();
    }

    /**
     * <p>
     * Starts a poll that is stopped
     * </p>
     *
     * @param flowName <p>
     *                 The name of the flow that contains the poll
     *                 </p>
     * @throws Exception <p>
     *                   Failure Exception
     *                   </p>
     */
    public void startPoll(String flowName) throws Exception
    {
        Scheduler scheduler = getScheduler(flowName);
        scheduler.start();
    }

    private Scheduler getScheduler(String flowName) throws Exception
    {
        Collection<Scheduler> schedulers = muleContext.getRegistry().lookupScheduler(Schedulers.flowConstructPollingSchedulers(flowName));
        if (schedulers.isEmpty())
        {
            throw new Exception("Flow " + flowName + " does not exist");
        }

        return schedulers.iterator().next();
    }
}
