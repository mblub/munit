/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule;

import org.junit.Test;

import org.mule.api.MuleEvent;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import static junit.framework.Assert.assertEquals;


/**
 * Munit test created with JAVA
 */
public class FirstTest extends FunctionalMunitSuite
{

    /**
     * @return The location of your MULE config file
     */
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }


    @Test
    public void validateEchoFlow() throws Exception
    {
        MuleEvent resultEvent = runFlow("echoFlow", testEvent("Hello world!"));

        assertEquals("Hello world!", resultEvent.getMessage().getPayloadAsString());
    }
}
