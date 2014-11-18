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
 * Assert true message processor
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertTrueMessageProcessor extends MunitMessageProcessor
{

    /**
     * @see AssertModule#assertTrue(String, Boolean)
     */
    private String message;

    /**
     * @see AssertModule#assertTrue(String, Boolean)
     */
    private Object condition;

    /**
     * @see MunitMessageProcessor#doProcess(org.mule.api.MuleMessage, org.mule.munit.AssertModule)
     */
    @Override
    protected void doProcess(MuleMessage mulemessage, AssertModule module)
    {
        String message = this.message == null ? null : evaluate(mulemessage, this.message).toString();
        module.assertTrue(message, (Boolean) evaluate(mulemessage, condition));
    }

    /**
     * @see org.mule.munit.config.MunitMessageProcessor#getProcessor()
     */
    @Override
    protected String getProcessor()
    {
        return "assertTrue";
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setCondition(Object value)
    {
        this.condition = value;
    }

}
