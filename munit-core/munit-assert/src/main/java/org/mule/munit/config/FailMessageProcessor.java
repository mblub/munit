/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.config;

import org.mule.api.MuleMessage;
import org.mule.munit.AssertModule;


/**
 * <p>
 * Assert Fail Message processor
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class FailMessageProcessor extends MunitMessageProcessor
{

    /**
     * @see AssertModule#fail(String)
     */
    private String message;

    /**
     * @see MunitMessageProcessor#doProcess(org.mule.api.MuleMessage, org.mule.munit.AssertModule)
     */
    @Override
    protected void doProcess(MuleMessage mulemessage, AssertModule module)
    {
        module.fail(message);
    }

    /**
     * @see org.mule.munit.config.MunitMessageProcessor#getProcessor()
     */
    @Override
    protected String getProcessor()
    {
        return "Fail";
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
