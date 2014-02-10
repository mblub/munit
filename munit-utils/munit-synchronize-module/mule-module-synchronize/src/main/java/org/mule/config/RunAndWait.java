package org.mule.config;

import org.mule.DefaultMuleEvent;
import org.mule.MessageExchangePattern;
import org.mule.Synchronizer;
import org.mule.api.DefaultMuleException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;

/**
 *
 */
public class RunAndWait implements MessageProcessor
{
    private MessageProcessor messageProcessor;
    private boolean runAsyc;
    private long timeout;

    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        MuleEvent processingEvent= event;
        if ( runAsyc )
        {
            processingEvent = new DefaultMuleEvent(event.getMessage(), MessageExchangePattern.ONE_WAY,
                                                   event.getFlowConstruct(), event.getSession());
        }

        Synchronizer synchronizer = new Synchronizer(event.getMuleContext(), timeout)
        {

            @Override
            protected MuleEvent process(MuleEvent event) throws Exception
            {
                return messageProcessor.process(event);
            }
        };

        try
        {
            return synchronizer.runAndWait(processingEvent);
        }
        catch (Exception e)
        {
            throw new DefaultMuleException(e);
        }
    }

    public void setMessageProcessor(MessageProcessor messageProcessor)
    {
        this.messageProcessor = messageProcessor;
    }

    public void setRunAsyc(boolean runAsyc)
    {
        this.runAsyc = runAsyc;
    }

    public void setTimeout(long timeout)
    {
        this.timeout = timeout;
    }
}