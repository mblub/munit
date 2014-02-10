package org.mule;

import org.mule.tck.probe.Probe;
import org.mule.tck.probe.Prober;
import org.mule.tck.probe.Timeout;

/**
 *
 */
public class SynchronizeProber  implements Prober
{
    public static final long DEFAULT_TIMEOUT = 1000;
    public static final long DEFAULT_POLLING_INTERVAL = 100;

    private final long timeoutMillis;
    private final long pollDelayMillis;

    public SynchronizeProber()
    {
        this(DEFAULT_TIMEOUT, DEFAULT_POLLING_INTERVAL);
    }

    public SynchronizeProber(long timeoutMillis, long pollDelayMillis)
    {
        this.timeoutMillis = timeoutMillis;
        this.pollDelayMillis = pollDelayMillis;
    }

    @Override
    public void check(Probe probe)
    {
        if (!poll(probe))
        {
            throw new AssertionError(probe.describeFailure());
        }
    }

    private boolean poll(Probe probe)
    {
        Timeout timeout = new Timeout(timeoutMillis);

        while (true)
        {
            if (probe.isSatisfied())
            {
                return true;
            }
            else if (timeout.hasTimedOut())
            {
                return false;
            }
            else
            {
                waitFor(pollDelayMillis);
            }
        }
    }

    private void waitFor(long duration)
    {
        try
        {
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
