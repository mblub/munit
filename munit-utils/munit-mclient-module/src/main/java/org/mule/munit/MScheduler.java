package org.mule.munit;

import org.mule.api.MuleException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.schedule.Scheduler;

/**
 * Created by  on 2/14/14.
 *
 * @author Mulesoft Inc.
 */
public class MScheduler implements Scheduler {

    private Scheduler wrappedScheduler;

    public MScheduler(Scheduler scheduler) {
        this.wrappedScheduler = scheduler;
    }

    @Override
    public void schedule() throws Exception {
        this.wrappedScheduler.schedule();
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
