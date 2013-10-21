/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

import javax.mail.internet.MimeMessage;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Representation of a mail server for Munit tests</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MailServer
{

    /**
     * <p>This class is just a wrapper of Green Mail, so here is the instance of GreenMail server</p>
     */
    private GreenMail mailServer;

    public MailServer()
    {
        mailServer = new GreenMail(ServerSetupTest.ALL);
    }

    public void start()
    {
        mailServer.start();
    }

    public void stop()
    {
        try
        {
            mailServer.stop();
        }
        catch (Throwable e)
        {
            // Do nothing
        }
    }

    public List<MimeMessage> getReceivedMessages()
    {
        return Arrays.asList(mailServer.getReceivedMessages());
    }

}
