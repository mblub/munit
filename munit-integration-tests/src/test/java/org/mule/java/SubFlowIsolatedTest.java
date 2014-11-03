/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.java;


import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class SubFlowIsolatedTest extends FunctionalMunitSuite {
    @Override
    protected String getConfigResources() {
        return "sub-flow-isolated.xml";
    }

    @Test
    public void test() throws Exception {
        MuleEvent event = testEvent("");
        event.setFlowVariable("test", 0);

        runFlow("subFlow", event);

        verifyCallOfMessageProcessor("logger").times(1);
    }
}
