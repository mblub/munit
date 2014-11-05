/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.java;


import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.munit.common.mocking.SpyProcess;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import java.util.HashMap;
import java.util.Map;

public class MpMockingSpyAndForEachTest extends FunctionalMunitSuite {
    @Override
    protected String getConfigResources() {
        return "spy-and-for-each.xml";
    }

    @Test
    public void test() throws Exception {

        Map<String, Object> a = new HashMap<String, Object>();
        a.put("project", "test-project");

        SpyProcess spyAfter = new SpyProcess() {
            @Override
            public void spy(MuleEvent event) throws MuleException {
                Assert.assertTrue("Mocked Jira".equals(event.getMessage().getPayload()));
                System.out.println(event.getMessage().getPayload());
            }
        };

        SpyProcess spyBefore = new SpyProcess() {
            @Override
            public void spy(MuleEvent event) throws MuleException {
                Assert.assertTrue(event.getMessage().getPayload() instanceof Map);
                System.out.println(event.getMessage().getPayload());
            }
        };
        spyMessageProcessor("create-issue").ofNamespace("jira").withAttributes(a).before(spyBefore).after(spyAfter);


        MuleMessage message = muleMessageWithPayload("Mocked Jira");
        whenMessageProcessor("create-issue").ofNamespace("jira").withAttributes(a).thenReturn(message);


        runFlow("jira-mock-exampleFlow", testEvent(""));
        System.out.println("Test done");
    }
}
