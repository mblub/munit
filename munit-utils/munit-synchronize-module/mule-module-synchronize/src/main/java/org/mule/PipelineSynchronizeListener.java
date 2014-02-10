package org.mule;

import org.mule.api.MuleEvent;
import org.mule.api.context.notification.PipelineMessageNotificationListener;
import org.mule.context.notification.PipelineMessageNotification;

import java.util.concurrent.atomic.AtomicInteger;

public class PipelineSynchronizeListener implements PipelineMessageNotificationListener<PipelineMessageNotification>, Synchronize
{

    AtomicInteger count = new AtomicInteger(0);
    private String messageRootId;

    public PipelineSynchronizeListener(String messageRootId)
    {
        this.messageRootId = messageRootId;
    }


    @Override
    public void onNotification(PipelineMessageNotification notification)
    {
        String notificationRootId = ((MuleEvent) notification.getSource()).getMessage().getMessageRootId();
        if (notificationRootId.equals(messageRootId))
        {
            if (notification.getAction() == PipelineMessageNotification.PROCESS_START)
            {
                count.incrementAndGet();
            }
            if (notification.getAction() == PipelineMessageNotification.PROCESS_END)
            {
                count.decrementAndGet();
            }
        }
    }

    @Override
    public synchronized boolean readyToContinue()
    {
        return count.get() <= 0;
    }
}
