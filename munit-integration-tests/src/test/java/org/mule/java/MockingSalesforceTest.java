package org.mule.java;

import static junit.framework.Assert.assertEquals;

import org.mule.munit.runner.functional.FunctionalMunitSuite;

import org.junit.Test;


public class MockingSalesforceTest extends FunctionalMunitSuite
{
    @Override
    protected String getConfigResources()
    {
        return "sfdc-config.xml";
    }

    @Test
    public void test() throws Exception
    {
        whenMessageProcessor("get-deleted")
                .ofNamespace("sfdc")
                .thenReturn(muleMessageWithPayload("expected"));

        Object payload = runFlow("sync-contacts", testEvent("something")).getMessage().getPayload();

        assertEquals("expected", payload);
    }
}
