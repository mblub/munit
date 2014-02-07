/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
