package org.mule.java;


import org.mule.munit.runner.functional.FunctionalMunitSuite;

import java.util.Arrays;

import org.junit.Test;

public class PollTest extends FunctionalMunitSuite
{
    @Test
    public void test() throws Exception {
        whenMessageProcessor("create-issue")
                .ofNamespace("jira")
                .thenReturn(muleMessageWithPayload("OK"));

        runFlow("jira-mock-exampleFlow", testEvent(Arrays.asList("Something"))).getMessage().getPayload();

        verifyCallOfMessageProcessor("create-issue").ofNamespace("jira").times(1);
    }
}
