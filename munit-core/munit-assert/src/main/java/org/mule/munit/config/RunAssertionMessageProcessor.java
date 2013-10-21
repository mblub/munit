/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.munit.MunitAssertion;


/**
 * <p>
 * Message processor that runs the custom assertion
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class RunAssertionMessageProcessor implements MessageProcessor
{

    /**
     * <p>
     * The assertion to be run.
     * </p>
     */
    private MunitAssertion assertion;


    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        return assertion.execute(event);
    }

    public void setAssertion(MunitAssertion assertion)
    {
        this.assertion = assertion;
    }
}
