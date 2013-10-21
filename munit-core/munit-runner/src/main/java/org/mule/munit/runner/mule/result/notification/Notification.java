/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.result.notification;

/**
 * <p>The representation of the test notification</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class Notification
{

    private String fullMessage;
    private String shortMessage;

    public Notification(String shortMessage, String fullMessage)
    {
        this.shortMessage = shortMessage;
        this.fullMessage = fullMessage;
    }

    public String getFullMessage()
    {
        return fullMessage;
    }

    public String getShortMessage()
    {
        return shortMessage;
    }


}
