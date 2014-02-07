/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.java;


import org.mule.munit.runner.functional.FunctionalMunitSuite;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class PollTest extends FunctionalMunitSuite
{

//    @Override
//    protected boolean haveToMockMuleConnectors() {
//        return false;
//    }
//
//    @Override
//    protected boolean haveToDisableInboundEndpoints() {
//        return false;
//    }

    @Test
    public void test() throws Exception
    {
        whenMessageProcessor("create-issue")
                .ofNamespace("jira")
                .thenReturn(muleMessageWithPayload("OK"));
//        whenMessageProcessor("logger").thenReturn(muleMessageWithPayload(Arrays.asList("Something")));

//        runSchedulersOnce("jira-mock-exampleFlow");
        runFlow("jira-mock-exampleFlow", testEvent(Arrays.asList("Something"))).getMessage().getPayload();

        verifyCallOfMessageProcessor("create-issue").ofNamespace("jira").times(1);
    }
}
