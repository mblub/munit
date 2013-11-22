/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.examples.mongo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.munit.common.mocking.SpyProcess;
import org.mule.munit.runner.functional.FunctionalMunitSuite;
import org.mule.transport.NullPayload;


public class MongoTest extends FunctionalMunitSuite
{

    @Override
    protected String getConfigResources()
    {
        return "mongo-spy-example.xml";
    }

    @Test
    public void testMongoCall() throws MuleException, Exception
    {
        whenMessageProcessor("add-user").ofNamespace("mongo").thenReturnSameEvent();
        spyMessageProcessor("add-user").ofNamespace("mongo").before(assertNotNull()).after(assertNotNull());

        runFlow("mongo-storage", testEvent(getClass().getResourceAsStream("/users.xml")));

        verifyCallOfMessageProcessor("add-user").ofNamespace("mongo").times(2);
    }

    private List<SpyProcess> assertNotNull()
    {
        ArrayList<SpyProcess> process = new ArrayList<SpyProcess>();

        process.add(new SpyProcess()
        {

            @Override
            public void spy(MuleEvent arg0) throws MuleException
            {
                assertFalse(arg0.getMessage().getPayload() instanceof NullPayload);

            }
        });
        return process;
    }

}
