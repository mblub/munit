/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.java;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import org.mule.munit.MailServer;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

import static junit.framework.Assert.assertFalse;

@Ignore
public class MailServerTest extends FunctionalMunitSuite
{

    private static MailServer mailServer;

    @BeforeClass
    public static void startServer()
    {
        mailServer = new MailServer();
        mailServer.start();
    }


    @AfterClass
    public static void stopServer()
    {
        mailServer.stop();
    }

    @Override
    protected boolean haveToMockMuleConnectors()
    {
        return false;
    }

    @Override
    protected boolean haveToDisableInboundEndpoints()
    {
        return false;
    }

    @Override
    protected String getConfigResources()
    {
        return "mail-server-config.xml";
    }

    @Test
    public void testingServerCall() throws Exception
    {
        runFlow("NotificationOfError", testEvent("hello"));

        assertFalse(mailServer.getReceivedMessages().isEmpty());
    }
}
