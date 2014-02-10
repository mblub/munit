package org.mule;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;


public class EndedMessageProcessor implements MessageProcessor
{

    public static boolean ended = false;

    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        ended = true;
        return event;
    }
}
