/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.endpoint;

import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;

import java.util.List;

/**
 * <p>
 * This class defines how the outbound endpoint must behave. It has the list of message processors that
 * asserts the incoming payload and the return message of the outbound.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class OutboundBehavior
{

    /**
     * <p>
     * The expected mule message to be returned
     * </p>
     */
    private MuleMessage muleMessage;

    /**
     * <p>
     * The list of message processors for message assertion. These assertions will be called before
     * calling the outbund endpoints.
     * </p>
     */
    private List<MessageProcessor> assertions;

    public OutboundBehavior(MuleMessage muleMessage, List<MessageProcessor> assertions)
    {
        this.muleMessage = muleMessage;
        this.assertions = assertions;
    }


    public List<MessageProcessor> getAssertions()
    {
        return assertions;
    }

    public MuleMessage getMessage()
    {
        return muleMessage;
    }
}
