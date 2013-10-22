/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.common.mp;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MyTestMessageProcessor implements MessageProcessor
{

    private Object o;

    public MyTestMessageProcessor(Object o)
    {
        this.o = o;
    }

    @Override
    public MuleEvent process(MuleEvent muleEvent) throws MuleException
    {
        return doProcess(muleEvent);
    }

    private MuleEvent doProcess(MuleEvent muleEvent)
    {
        return muleEvent;
    }
}
