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
 * Assert on Equals message processor
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class AssertOnEqualsMessageProcessor extends MunitMessageProcessor
{

    /**
     * @see AssertModule#assertOnEquals(String, Object, Object)
     */
    private String message;

    /**
     * @see AssertModule#assertOnEquals(String, Object, Object)
     */
    private Object expected;

    /**
     * @see AssertModule#assertOnEquals(String, Object, Object)
     */
    private Object value;

    /**
     * @see MunitMessageProcessor#doProcess(org.mule.api.MuleMessage, org.mule.munit.AssertModule)
     */
    @Override
    protected void doProcess(MuleMessage mulemessage, AssertModule module)
    {
        String message = this.message == null ? null : evaluate(mulemessage, this.message).toString();
        module.assertOnEquals(message, evaluate(mulemessage, expected), evaluate(mulemessage, value));
    }

    /**
     * @see org.mule.munit.config.MunitMessageProcessor#getProcessor()
     */
    @Override
    protected String getProcessor()
    {
        return "assertOnEquals";
    }

    public void setMessage(String value)
    {
        this.message = value;
    }

    public void setExpected(Object value)
    {
        this.expected = value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

}
