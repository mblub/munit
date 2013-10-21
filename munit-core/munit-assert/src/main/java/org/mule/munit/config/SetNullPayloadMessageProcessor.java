/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.config;

import org.mule.api.MuleMessage;
import org.mule.munit.AssertModule;
import org.mule.transport.NullPayload;


/**
 * <p>
 * Sets the payload as null
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SetNullPayloadMessageProcessor extends MunitMessageProcessor
{

    @Override
    protected void doProcess(MuleMessage mulemessage, AssertModule module)
    {
        mulemessage.setPayload(NullPayload.getInstance());
    }

    @Override
    protected String getProcessor()
    {
        return "SetNull";
    }
}
