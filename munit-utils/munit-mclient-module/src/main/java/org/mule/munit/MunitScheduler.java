/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.schedule.Scheduler;
import org.mule.transport.polling.MessageProcessorPollingMessageReceiver;

/**
 * Created by  on 2/14/14.
 *
 * @author Mulesoft Inc.
 */
public class MunitScheduler implements Scheduler {
    private MessageProcessorPollingMessageReceiver job;
    private Scheduler wrappedScheduler;

    public MunitScheduler(Scheduler scheduler, MessageProcessorPollingMessageReceiver job) {
        this.wrappedScheduler = scheduler;
        this.job = job;
    }

    @Override
    public void schedule() throws Exception {
        job.poll();
    }

    @Override
    public void dispose() {
        this.wrappedScheduler.dispose();
    }

    @Override
    public void initialise() throws InitialisationException {
        this.wrappedScheduler.initialise();
    }

    @Override
    public void start() throws MuleException {
        //DO nothing
    }

    @Override
    public void stop() throws MuleException {
        this.wrappedScheduler.stop();
    }

    @Override
    public void setName(String s) {
       this.wrappedScheduler.setName(s);
    }

    @Override
    public String getName() {
        return this.wrappedScheduler.getName();
    }
}
