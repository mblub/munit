/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.assertions;

import org.mule.api.MuleEvent;
import org.mule.munit.MunitAssertion;


public class CustomAssertion implements MunitAssertion
{

    @Override
    public MuleEvent execute(MuleEvent muleEvent) throws AssertionError
    {
        if (!muleEvent.getMessage().getPayload().equals("Hello World"))
        {
            throw new AssertionError("Error the payload is incorrect");
        }

        return muleEvent;
    }
}
